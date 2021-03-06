'use strict'

var mongoose = require('mongoose');

function makeUnionType(name, ...types)
{
  function isArray(type) { return type.match(new RegExp("^\\[")) && type.match(new RegExp("\\]$"));}

  var capitalizedName = name.charAt(0).toUpperCase() + name.slice(1);
  var typeFunction = function(key, options) {mongoose.SchemaType.call(this, key, options, capitalizedName);}
  typeFunction.prototype = Object.create(mongoose.SchemaType.prototype);
  typeFunction.prototype.cast = function(val)
  {
    var funcCheckMongooseType = function(type)
    {
      return function (value)
      {
        // If the type is kind of [mongooseType]...
        if (isArray(type))
        {
          // Remember to remove the [type] brackets...we should cast each value in the array to the given type (no heterogeneous types)
          var arrResult = [];

          if (value.constructor !== Array)
            arrResult.push(mongoose.Schema.Types[type.slice(1, -1)].prototype.cast(value));
          else
            for (var i of value)
              arrResult.push(mongoose.Schema.Types[type.slice(1, -1)].prototype.cast(i));

          return arrResult;
        }
        else
          // Else the type was just mongooseType
          return mongoose.Schema.Types[type].prototype.cast(value);
      }
    };

    var funcCheckMongooseSchema = function (type)
    {
      return function (value)
      {
        if (isArray(type))
        {
          // Remember to remove the [type] brackets...we should check each object constructor...
          if (value.constructor !== Array)
          {
            if (value.constructor.modelName === type.slice(1, -1))
              return [value];
            else
            {
              var returnValue = mongoose.models[type.slice(1, -1)].hydrate(value);
              if (returnValue.validateSync() !== undefined)
                throw new Error();
              else
                return [returnValue];
            }
          }
          else
          {
            var returnValue = [];
            for (var singleElement of value)
            {
              if (singleElement.constructor.modelName !== type.slice(1, -1))
                returnValue.push(singleElement);
              else
              {
                var elemValue = mongoose.models[type.slice(1, -1)].hydrate(singleElement);
                if (elemValue.validateSync() !== undefined)
                  throw new Error();
                else
                  returnValue.push(elemValue);
              }
            }
            return returnValue;
          }
        }
        else
        {
          // When a model object is created...
          if (value.constructor.modelName === type)
            return value;
          else
          {
            // When a model object is loaded...cast and validate
            var returnValue = mongoose.models[type].hydrate(value);
            if (returnValue.validateSync() !== undefined)
              throw new Error();
            else
              return returnValue;
          }
        }
      }
    };

    var castFunctions = [];
    var returnVal = val;

    // For each Union type we try to cast the value to that type.
    // If for some type the cast is successful, we can return the casted value
    // If the casting fails for each type, then the value was not compatible with the Union type.
    for (var i = 0; i < types.length; i++)
    {
      castFunctions[i] = (types[i] in mongoose.Schema.Types || (isArray(types[i]) && types[i].slice(1, -1) in mongoose.Schema.Types))?
        funcCheckMongooseType(types[i]) : funcCheckMongooseSchema(types[i]);

      try
      {
        returnVal = castFunctions[i](val);
      } catch (typeError)
      {
        if (i == types.length - 1)
          throw new Error(capitalizedName + ': ' + val + ' couldn\'t be cast to any type of ' + types);
        else
          continue;
      }

      break;
    }

    return returnVal;
  }

  Object.defineProperty(typeFunction, "name", { value: capitalizedName });
  mongoose.Schema.Types[capitalizedName] = typeFunction;

  return typeFunction;
}

module.exports = makeUnionType;
