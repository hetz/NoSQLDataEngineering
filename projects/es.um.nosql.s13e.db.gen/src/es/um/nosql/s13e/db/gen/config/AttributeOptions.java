package es.um.nosql.s13e.db.gen.config;

import es.um.nosql.s13e.db.gen.util.constants.ConfigConstants;

public class AttributeOptions
{
  private PrimitiveTypeOptions primitiveTypes;
  public void setPrimitiveTypes(PrimitiveTypeOptions primitiveTypes) {this.primitiveTypes = primitiveTypes;}
  public PrimitiveTypeOptions getPrimitiveTypes() {return this.primitiveTypes;}

  private TupleOptions tuples;
  public void setTuples(TupleOptions tuples) {this.tuples = tuples;}
  public TupleOptions getTuples() {return this.tuples;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getPrimitiveTypes() != null) result.append(ConfigConstants.GET_TABS(this.getClass()) + "-PrimitiveTypes:\n" + this.getPrimitiveTypes().toString());
    if (this.getTuples() != null)         result.append(ConfigConstants.GET_TABS(this.getClass()) + "-Tuples:\n" + this.getTuples().toString());

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getPrimitiveTypes() != null)
      this.getPrimitiveTypes().doCheck();

    if (this.getTuples() != null)
      this.getTuples().doCheck();

    return true;
  }
}
