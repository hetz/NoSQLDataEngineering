/**
 *
 */
package es.um.nosql.streaminginference.json2dbschema.util.abstractjson.impl.gson;

import com.google.gson.JsonElement;

import es.um.nosql.streaminginference.json2dbschema.util.abstractjson.IAJObject;

/**
 * @author dsevilla
 *
 */
public class GsonObject extends GsonElement implements IAJObject
{
	public GsonObject(JsonElement val) {
		super(val);
	}
}
