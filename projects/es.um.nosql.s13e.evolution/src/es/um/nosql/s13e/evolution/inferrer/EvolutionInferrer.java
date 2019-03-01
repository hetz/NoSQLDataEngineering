package es.um.nosql.s13e.evolution.inferrer;

import java.io.File;

import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.evolution.output.OutputGen;
import es.um.nosql.s13e.evolution.templates.TimestampInferrer;
import es.um.nosql.s13e.evolution.templates.gen.TimestampAnalyzer;
import es.um.nosql.s13e.evolution.templates.gen.BasicTimestampAnalyzer;
import es.um.nosql.s13e.evolution.templates.gen.DateTimestampAnalyzer;
import es.um.nosql.s13e.evolution.templates.gen.DefaultTimestampAnalyzer;
import es.um.nosql.s13e.evolution.util.InferenceMode;
import es.um.nosql.s13e.evolution.util.constants.ConfigConstants;
import es.um.nosql.s13e.util.ModelLoader;

//TODO: Filters? GreaterThan class, LessThan, GreaterOrEqual, LessOrEqual, Equal, Zero, Nonzero...how about a list of conditions?
public class EvolutionInferrer
{
  private TimestampInferrer inferrer;
  private OutputGen output;

  public EvolutionInferrer()
  {
    inferrer = new TimestampInferrer();
    output = new OutputGen();
  }

  public void runLinksExample(InferenceMode option, String dbName)
  {
    runExample(option, dbName, new BasicTimestampAnalyzer("timestamp"));
  }

  public void runWebclickExample(InferenceMode option, String dbName)
  {
    runExample(option, dbName, new BasicTimestampAnalyzer("timestamp"));
  }

  public void runPublicationsExample(InferenceMode option, String dbName)
  {
    runExample(option, dbName, new DefaultTimestampAnalyzer());
  }

  public void runFacebookExample(InferenceMode option, String dbName)
  {
    runExample(option, dbName, new DateTimestampAnalyzer("created_time"));
  }

  public void runSanctionsExample(InferenceMode option, String dbName)
  {
    runExample(option, dbName, new DateTimestampAnalyzer("timestamp"));
  }

  public void runProteinsExample(InferenceMode option, String dbName)
  {
    runExample(option, dbName, new DefaultTimestampAnalyzer());
  }

  public void runStackOverflowExample(InferenceMode option, String dbName)
  {
    runExample(option, dbName, new DateTimestampAnalyzer("CreationDate"));
  }

  public void runHarvardExample(InferenceMode option, String dbName)
  {
    runExample(option, dbName, new DateTimestampAnalyzer("start_time_DI"));
  }

  public void runRedditExample(InferenceMode option, String dbName)
  {
    runExample(option, dbName, new BasicTimestampAnalyzer("created_utc"));
  }

  private void runExample(InferenceMode option, String dbName, TimestampAnalyzer analyzer)
  {
    NoSQLSchema schema = null;

    if (option != InferenceMode.ANALYZE_ONLY)
    {
      schema = inferrer.infer(dbName, analyzer);
      output.genModelFile(schema);
    }

    if (option == InferenceMode.ANALYZE_ONLY)
    {
      ModelLoader loader = new ModelLoader(NoSQLSchemaPackage.eINSTANCE);
      schema = loader.load(new File(ConfigConstants.MODELS_FOLDER + dbName + "/" + dbName + ".xmi"), NoSQLSchema.class);
    }

    if (option != InferenceMode.INFER_ONLY)
      output.genOutput(schema);
  }
}