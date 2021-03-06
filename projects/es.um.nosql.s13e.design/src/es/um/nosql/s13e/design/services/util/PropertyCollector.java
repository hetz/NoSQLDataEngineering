package es.um.nosql.s13e.design.services.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.um.nosql.s13e.NoSQLSchema.SchemaType;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.util.compare.CompareProperty;

public class PropertyCollector
{
  private CompareProperty propComparer;

  public PropertyCollector()
  {
    propComparer = new CompareProperty();
  }

  public <T extends Property> List<T> getUnionProperties(SchemaType schemaT, Class<T> theClass)
  {
    List<T> result = new ArrayList<T>();

    schemaT.getVariations().forEach(var ->
    {
      var.getProperties().stream().filter(prop -> theClass.isInstance(prop)).forEach(prop ->
      {
        if (result.stream().noneMatch(prop2 -> propComparer.compare(prop, prop2)))
          result.add(theClass.cast(prop));
      });
    });

    result.sort((prop1, prop2) -> prop1.getName().compareTo(prop2.getName()));

    return result;
  }

  public <T extends Property> List<T> getCommonProperties(SchemaType schemaT, Class<T> theClass)
  {
    List<T> result = new ArrayList<T>();

    if (!schemaT.getVariations().isEmpty())
    {
      result.addAll(schemaT.getVariations().get(0).getProperties().stream()
          .filter(prop -> theClass.isInstance(prop))
          .map(prop -> theClass.cast(prop))
          .collect(Collectors.toList()));

      if (schemaT.getVariations().size() > 1)
      {
        result = result.stream().filter(prop ->
        {
          return schemaT.getVariations().stream().skip(1).allMatch(var ->
          {
            return var.getProperties().stream().anyMatch(prop2 -> propComparer.compare(prop, prop2));
          });
        }).collect(Collectors.toList());
      }
    }

    result.sort((prop1, prop2) -> prop1.getName().compareTo(prop2.getName()));

    return result;
  }

  public <T extends Property> List<T> getOptionalProperties(SchemaType schemaT, Class<T> theClass)
  {
    List<T> result = new ArrayList<T>();

    if (!schemaT.getVariations().isEmpty())
    {
      schemaT.getVariations().forEach(var ->
      {
        // Watch out we are modifying the list as we iterate, so do not try to put that condition on a filter...
        var.getProperties().stream().filter(prop -> theClass.isInstance(prop) && prop.isOptional()).forEach(prop ->
        {
          if (result.stream().noneMatch(prop2 -> propComparer.compare(prop, prop2)))
            result.add(theClass.cast(prop));
        });
      });
    }

    result.sort((prop1, prop2) -> prop1.getName().compareTo(prop2.getName()));

    return result;
  }

  public <T extends Property> List<T> getParticularProperties(StructuralVariation var, Class<T> theClass)
  {
    List<T> result = new ArrayList<T>();
    List<T> commonProps = getCommonProperties(var.getContainer(), theClass);

    for (Property prop : var.getProperties())
      if (theClass.isInstance(prop))
        result.add(theClass.cast(prop));

    result = result.stream().filter(prop1 ->
    {
      return commonProps.stream().noneMatch(prop2 ->
      {
        return propComparer.compare(prop1, prop2);
      });
    }).collect(Collectors.toList());

    result.sort((prop1, prop2) -> prop1.getName().compareTo(prop2.getName()));

    return result;
  }
}
