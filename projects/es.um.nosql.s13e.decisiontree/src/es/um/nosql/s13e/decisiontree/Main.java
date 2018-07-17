package es.um.nosql.s13e.decisiontree;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;

import es.um.nosql.s13e.DecisionTree.DecisionTrees;
import es.um.nosql.s13e.DecisionTree.DecisionTreePackage;
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.decisiontree.m2m.EntityDiffToDecisionTree;
import es.um.nosql.s13e.decisiontree.m2t.DecisionTreeToJS;
import es.um.nosql.s13e.util.emf.ResourceManager;

public class Main 
{
  public static final String INPUT_FOLDER = "../es.um.nosql.examples/";
  public static final String OUTPUT_FOLDER = "../es.um.nosql.examples/";

  public static void main(String[] args)
  {
    String[] input_models = new String[] {/*"everypolitician_sweden", "facebook", "harvard", "links", "mongomovies", "opensanctions",
        "proteins", "publications", "stackoverflow", "urban", "webclicks", */"mongosongs"};

    for (String input_model : input_models)
    {
      String inputFile = INPUT_FOLDER + input_model + "/" + input_model + "_Diff.xmi";
      String outputFile = OUTPUT_FOLDER + input_model + "/" + input_model + "_Tree.xmi";
      String outputFolder = OUTPUT_FOLDER + input_model + "/";
      prepareM2MExample(new File(inputFile), new File(outputFile));
      prepareM2TExample(new File(outputFile), new File(outputFolder));
    }
  }

  public static void prepareM2MExample(File inputFile, File outputFile)
  {
    System.out.println("Generating DecisionTree model for " + inputFile.getName() + " in " + outputFile.getPath());

    EntityDiffToDecisionTree transformer = new EntityDiffToDecisionTree();
    DecisionTrees dTrees = transformer.m2m(inputFile);

    EntityDifferentiationPackage entitydiffPackage = EntityDifferentiationPackage.eINSTANCE;
    DecisionTreePackage decisiontreePackage = DecisionTreePackage.eINSTANCE;
    ResourceManager resManager = new ResourceManager(entitydiffPackage, decisiontreePackage);

    entitydiffPackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e.entitydifferentiation/model/entitydifferentiation.ecore", true));
    decisiontreePackage.eResource().setURI(URI.createPlatformResourceURI("es.um.nosql.s13e.entitydifferentiation/model/decisiontree.ecore", true));

    Resource outputRes = resManager.getResourceSet().createResource(URI.createFileURI(outputFile.getAbsolutePath()));
    outputRes.getContents().add(dTrees);

    // Configure output
    Map<Object,Object> options = new HashMap<Object,Object>();
    options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
    options.put(XMIResource.OPTION_ENCODING, "UTF-8");

    try
    {
      outputRes.save(new FileOutputStream(outputFile), options);
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    System.out.println("Transformation model finished");
  }

  public static void prepareM2TExample(File inputFile, File outputFolder)
  {
    System.out.println("Generating Javascript code for " + inputFile + " in " + outputFolder);

    if (!outputFolder.exists())
      outputFolder.mkdirs();

    DecisionTreeToJS tree2js = new DecisionTreeToJS();
    tree2js.m2t(inputFile, outputFolder);

    System.out.println("Code generation finished");
  }
}