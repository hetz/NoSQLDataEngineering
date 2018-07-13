package es.um.nosql.s13e.m2m.util;

import java.io.File;

import es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation;
import es.um.nosql.s13e.EntityDifferentiation.EntityVariationProp;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.EntityDifferentiation.PropertySpec;
import es.um.nosql.s13e.util.emf.ModelLoader;

public class PrettyPrinter
{
  private static final String TAB = "  ";
  private static final String ENDL = System.lineSeparator();

  public static void main(String[] args)
  {
    ModelLoader loader = new ModelLoader(EntityDifferentiationPackage.eINSTANCE);
    EntityDifferentiation eDiff = loader.load(new File("../es.um.nosql.examples/mongomovies/mongomovies_Diff.xmi"), EntityDifferentiation.class);
    System.out.println(printPretty(eDiff));
  }

  public static String printPretty(EntityDifferentiation entityDifferentiation)
  {
    if (entityDifferentiation == null)
      return null;

    StringBuilder result = new StringBuilder();

    result.append("EntityDifferentiation name:" + entityDifferentiation.getName() + ENDL);

    for (EntityDiffSpec eDiffSpec : entityDifferentiation.getEntityDiffSpecs())
      result.append(printPretty(eDiffSpec, TAB) + ENDL);

    return result.toString();
  }

  public static String printPretty(EntityDiffSpec eDiffSpec, String defTabs)
  {
    if (eDiffSpec == null)
      return null;

    String tabs = defTabs + TAB;
    StringBuilder result = new StringBuilder();

    result.append(tabs + "Entity: " + eDiffSpec.getEntity().getName() + ENDL);

    if (!eDiffSpec.getCommonProps().isEmpty())
    {
      result.append(tabs + "Common props:" + ENDL);
      for (PropertySpec pSpec : eDiffSpec.getCommonProps())
        result.append(prettyPrint(pSpec, tabs) + ENDL);
    }

    if (!eDiffSpec.getEntityVariationProps().isEmpty())
    {
      for (EntityVariationProp evProp : eDiffSpec.getEntityVariationProps())
      {
        result.append(tabs + "EV " + evProp.getEntityVariation().getVariationId() + " ---------" + ENDL);

        if (!evProp.getPropertySpecs().isEmpty())
        {
          result.append(tabs + "Own properties:" + ENDL);
          for (PropertySpec pSpec : evProp.getPropertySpecs())
            result.append(prettyPrint(pSpec, tabs) + ENDL);          
        }

        if (!evProp.getNotProps().isEmpty())
        {
          result.append(tabs + "Properties NOT to have:" + ENDL);
          for (PropertySpec pSpec : evProp.getNotProps())
            result.append(prettyPrint(pSpec, tabs) + ENDL);          
        }
      }
    }

    return result.toString();
  }

  public static String prettyPrint(PropertySpec pSpec, String defTabs)
  {
    if (pSpec == null)
      return null;

    String tabs = defTabs + TAB;
    StringBuilder result = new StringBuilder();
    result.append(tabs + " * " + pSpec.getProperty().getName() + (pSpec.isNeedsTypeCheck() ? "*" : ""));

    return result.toString();
  }
}