package es.um.nosql.s13e.decisiontree.util;

import es.um.nosql.s13e.NoSQLSchema.EntityVariation;
import es.um.nosql.s13e.EntityDifferentiation.PropertySpec;

public class ModelNode
{
  private PropertySpec property;
  private EntityVariation ev;
  private ModelNode nodePresent;
  private ModelNode nodeAbsent;
  private boolean checkNot;

  public ModelNode(EntityVariation tag)
  {
    setEv(tag);
    setCheckNot(false);
  }

  public ModelNode(PropertySpec property, boolean checkNot)
  {
    setProperty(property);
    setCheckNot(checkNot);
  }

  public PropertySpec getProperty()
  {
    return property;
  }

  public void setProperty(PropertySpec property)
  {
    this.property = property;
  }

  public EntityVariation getEv()
  {
    return ev;
  }

  public void setEv(EntityVariation tag)
  {
    this.ev = tag;
  }

  public ModelNode getNodePresent()
  {
    return nodePresent;
  }

  public void setNodePresent(ModelNode nodePresent)
  {
    this.nodePresent = nodePresent;
  }

  public ModelNode getNodeAbsent()
  {
    return nodeAbsent;
  }

  public void setNodeAbsent(ModelNode nodeAbsent)
  {
    this.nodeAbsent = nodeAbsent;
  }

  public boolean is_leaf()
  {
    return nodePresent == null && nodeAbsent == null;
  }

  public boolean isCheckNot()
  {
    return checkNot;
  }

  public void setCheckNot(boolean checkNot)
  {
    this.checkNot = checkNot;
  }
}