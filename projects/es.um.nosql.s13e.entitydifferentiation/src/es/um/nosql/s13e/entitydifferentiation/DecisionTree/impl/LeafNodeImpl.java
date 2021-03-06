/**
 */
package es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl;

import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

import es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreePackage;
import es.um.nosql.s13e.entitydifferentiation.DecisionTree.LeafNode;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Leaf Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.DecisionTree.impl.LeafNodeImpl#getIdentifiedVariation <em>Identified Variation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LeafNodeImpl extends DecisionTreeNodeImpl implements LeafNode {
  /**
   * The cached value of the '{@link #getIdentifiedVariation() <em>Identified Variation</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentifiedVariation()
   * @generated
   * @ordered
   */
  protected StructuralVariation identifiedVariation;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LeafNodeImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return DecisionTreePackage.Literals.LEAF_NODE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public StructuralVariation getIdentifiedVariation() {
    if (identifiedVariation != null && identifiedVariation.eIsProxy()) {
      InternalEObject oldIdentifiedVariation = (InternalEObject)identifiedVariation;
      identifiedVariation = (StructuralVariation)eResolveProxy(oldIdentifiedVariation);
      if (identifiedVariation != oldIdentifiedVariation) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, DecisionTreePackage.LEAF_NODE__IDENTIFIED_VARIATION, oldIdentifiedVariation, identifiedVariation));
      }
    }
    return identifiedVariation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StructuralVariation basicGetIdentifiedVariation() {
    return identifiedVariation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setIdentifiedVariation(StructuralVariation newIdentifiedVariation) {
    StructuralVariation oldIdentifiedVariation = identifiedVariation;
    identifiedVariation = newIdentifiedVariation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DecisionTreePackage.LEAF_NODE__IDENTIFIED_VARIATION, oldIdentifiedVariation, identifiedVariation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case DecisionTreePackage.LEAF_NODE__IDENTIFIED_VARIATION:
        if (resolve) return getIdentifiedVariation();
        return basicGetIdentifiedVariation();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case DecisionTreePackage.LEAF_NODE__IDENTIFIED_VARIATION:
        setIdentifiedVariation((StructuralVariation)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case DecisionTreePackage.LEAF_NODE__IDENTIFIED_VARIATION:
        setIdentifiedVariation((StructuralVariation)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case DecisionTreePackage.LEAF_NODE__IDENTIFIED_VARIATION:
        return identifiedVariation != null;
    }
    return super.eIsSet(featureID);
  }

} //LeafNodeImpl
