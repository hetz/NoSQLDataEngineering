package es.um.nosql.s13e.db.gen.config.pojo;

public class TuplesOptions
{
  private Integer minTupleElements;
  public void setMinTupleElements(Integer minTupleElements) {this.minTupleElements = minTupleElements;}
  public Integer getMinTupleElements() {return this.minTupleElements;}

  private Integer maxTupleElements;
  public void setMaxTupleElements(Integer maxTupleElements) {this.maxTupleElements = maxTupleElements;}
  public Integer getMaxTupleElements() {return this.maxTupleElements;}

  private Double strangeTypesProbability;
  public void setStrangeTypesProbability(Double strangeTypesProbability) {this.strangeTypesProbability = strangeTypesProbability;}
  public Double getStrangeTypesProbability() {return this.strangeTypesProbability;}

  private Double nullProbability;
  public void setNullProbability(Double nullProbability) {this.nullProbability = nullProbability;}
  public Double getNullProbability() {return this.nullProbability;}

  private Double tuplesInTuplesProbability;
  public void setTuplesInTuplesProbability(Double tuplesInTuplesProbability) {this.tuplesInTuplesProbability = tuplesInTuplesProbability;}
  public Double getTuplesInTuplesProbability() {return this.tuplesInTuplesProbability;}

  public String toString()
  {
    StringBuilder result = new StringBuilder();

    if (this.getMinTupleElements() != null)           result.append("  -Min tuple elements: " + this.getMinTupleElements() + "\n");
    if (this.getMaxTupleElements() != null)           result.append("  -Max tuple elements: " + this.getMaxTupleElements() + "\n");
    if (this.getStrangeTypesProbability() != null)    result.append("  -Strange types probability: " + this.getStrangeTypesProbability() + "\n");
    if (this.getNullProbability() != null)            result.append("  -Null probability: " + this.getNullProbability() + "\n");
    if (this.getTuplesInTuplesProbability() != null)  result.append("  -Tuples in tuples probability: " + this.getTuplesInTuplesProbability() + "\n");

    return result.toString();
  }

  public boolean doCheck()
  {
    if (this.getMinTupleElements() != null && this.getMinTupleElements() < 0)
      throw new IllegalArgumentException("Minimum tuple elements must be equal or greater than 0.");

    if (this.getMaxTupleElements() != null && this.getMaxTupleElements() < 1)
      throw new IllegalArgumentException("Maximum tuple elements must be equal or greater than 1.");

    if (this.getMinTupleElements() != null && this.getMaxTupleElements() != null && this.getMinTupleElements() > this.getMaxTupleElements())
      throw new IllegalArgumentException("Maximum tuple elements must be equal or greater than Minimum tuple elements.");

    if (this.getStrangeTypesProbability() != null && (this.getStrangeTypesProbability() < 0.0 || this.getStrangeTypesProbability() >= 1.0))
      throw new IllegalArgumentException("The \"StrangeTypesProbability\" double must be equal or greater than 0.0 and less than 1.0.");

    if (this.getTuplesInTuplesProbability() != null && (this.getTuplesInTuplesProbability() < 0.0 || this.getTuplesInTuplesProbability() >= 1.0))
      throw new IllegalArgumentException("The \"TuplesInTuplesProbability\" double must be equal or greater than 0.0 and less than 1.0.");

    if (this.getNullProbability() != null && (this.getNullProbability() < 0.0 || this.getNullProbability() >= 1.0))
      throw new IllegalArgumentException("The \"NullProbability\" double must be equal or greater than 0.0 and less than 1.0.");

    return true;
  }
}