package es.um.nosql.s13e.evolution.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.ECollections;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import es.um.nosql.s13e.NoSQLSchema.EntityType;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.evolution.output.chart.TimestampLineChart;
import es.um.nosql.s13e.evolution.util.constants.ConfigConstants;
import es.um.nosql.s13e.util.NoSQLSchemaWriter;

public class OutputGen
{
  private NoSQLSchemaWriter writer;
  private CsvMapper mapper;

  public OutputGen()
  {
    writer = new NoSQLSchemaWriter();
    mapper = new CsvMapper();
  }

  public void genOutput(NoSQLSchema schema)
  {
    if (ConfigConstants.OUTPUT_CSV)
      genCSVFile(schema);

    if (ConfigConstants.OUTPUT_CONSOLE)
      genConsole(schema);

    if (ConfigConstants.OUTPUT_CHART)
      genChart(schema);

    if (ConfigConstants.OUTPUT_CHART_FILE)
      genChartFile(schema);
  }

  public void genCSVFile(NoSQLSchema schema)
  {
    new File(ConfigConstants.OUTPUT_FOLDER).mkdirs();
    String outputRoute = ConfigConstants.OUTPUT_FOLDER + schema.getName() + ".csv";
    Map<EntityType, List<StructuralVariation>> orderedMap = genOrderedMap(schema);

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Generating CSV file...");

    CsvSchema csvSchema = CsvSchema.builder()
      .addColumn("entityName", CsvSchema.ColumnType.STRING)
      .addColumn("variationId", CsvSchema.ColumnType.NUMBER)
      .addColumn("count", CsvSchema.ColumnType.NUMBER)
      .addColumn("firstTimestamp", CsvSchema.ColumnType.NUMBER)
      .addColumn("lastTimestamp", CsvSchema.ColumnType.NUMBER)
      .build()
      .withHeader();

    List<List<Object>> objects = new ArrayList<List<Object>>();

    for (EntityType entity : orderedMap.keySet())
      for (StructuralVariation variation : orderedMap.get(entity))
        objects.add(Arrays.asList(entity.getName(), variation.getVariationId(), variation.getCount(), variation.getFirstTimestamp(), variation.getLastTimestamp()));

    try
    {
      OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputRoute));
      mapper.writer(csvSchema).writeValue(writer, objects);
    } catch (IOException e)
    {
      e.printStackTrace();
    }

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > CSV file created: " + outputRoute);
  }

  public void genModelFile(NoSQLSchema schema)
  {
    new File(ConfigConstants.OUTPUT_FOLDER).mkdirs();
    String outputRoute = ConfigConstants.OUTPUT_FOLDER + schema.getName() + ".xmi";
    writer.write(schema, outputRoute);
  }

  public void genConsole(NoSQLSchema schema)
  {
    StringBuilder result = new StringBuilder();
    Map<EntityType, List<StructuralVariation>> orderedMap = genOrderedMap(schema);

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Generating console output...");

    for (EntityType entity : orderedMap.keySet())
    {
      result.append("Entity: " + entity.getName() + "\n");
      for (StructuralVariation var : orderedMap.get(entity))
      {
        result.append("  variationid: " + var.getVariationId());
        result.append(", count: " + var.getCount());
        result.append(", firstTimestamp: " + var.getFirstTimestamp());
        result.append(", lastTimestamp: " + var.getLastTimestamp());
        result.append("\n");
      }
    }

    System.out.println(result.toString());

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Console output finished.");
  }

  public void genChart(NoSQLSchema schema)
  {
    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Drawing timestamp charts...");

    Map<EntityType, List<StructuralVariation>> orderedMap = genOrderedMap(schema);

    for (EntityType entity : orderedMap.keySet())
    {
      if (ConfigConstants.DEBUG)
        System.out.println(schema.getName() + " > " + entity.getName() + " chart drawn.");

      new TimestampLineChart(schema.getName(), entity.getName(), orderedMap.get(entity)).showChart();
    }

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Finished drawing charts.");
  }

  public void genChartFile(NoSQLSchema schema)
  {
    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Creating timestamp chart images...");

    Map<EntityType, List<StructuralVariation>> orderedMap = genOrderedMap(schema);

    for (EntityType entity : orderedMap.keySet())
    {
      if (ConfigConstants.DEBUG)
        System.out.println(schema.getName() + " > " + entity.getName() + " chart created.");

      new TimestampLineChart(schema.getName(), entity.getName(), orderedMap.get(entity)).saveChart(ConfigConstants.OUTPUT_FOLDER);
    }

    if (ConfigConstants.DEBUG)
      System.out.println(schema.getName() + " > Finished creating charts.");
  }

  private Map<EntityType, List<StructuralVariation>> genOrderedMap(NoSQLSchema schema)
  {
    Map<EntityType, List<StructuralVariation>> result = new HashMap<EntityType, List<StructuralVariation>>();

    for (EntityType entity : schema.getEntities())
    {
      ECollections.sort(entity.getVariations(), (var1, var2) -> Integer.compare(var1.getVariationId(), var2.getVariationId()));
      result.put(entity, entity.getVariations());
    }

    return result;
  }
}
