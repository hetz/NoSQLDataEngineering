/**
 */
package es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl;

import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.EntityDifferentiationPackage;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.PropertySpec;
import es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.StructuralVariationDiff;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structural Variation Diff</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.StructuralVariationDiffImpl#getPropertySpecs <em>Property Specs</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.StructuralVariationDiffImpl#getVariation <em>Variation</em>}</li>
 *   <li>{@link es.um.nosql.s13e.entitydifferentiation.EntityDifferentiation.impl.StructuralVariationDiffImpl#getNotProps <em>Not Props</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StructuralVariationDiffImpl extends MinimalEObjectImpl.Container implements StructuralVariationDiff {
  /**
   * The cached value of the '{@link #getPropertySpecs() <em>Property Specs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertySpecs()
   * @generated
   * @ordered
   */
  protected EList<PropertySpec> propertySpecs;

  /**
   * The cached value of the '{@link #getVariation() <em>Variation</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariation()
   * @generated
   * @ordered
   */
  protected StructuralVariation variation;

  /**
   * The cached value of the '{@link #getNotProps() <em>Not Props</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNotProps()
   * @generated
   * @ordered
   */
  protected EList<PropertySpec> notProps;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected StructuralVariationDiffImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return EntityDifferentiationPackage.Literals.STRUCTURAL_VARIATION_DIFF;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<PropertySpec> getPropertySpecs() {
    if (propertySpecs == null) {
      propertySpecs = new EObjectContainmentEList<PropertySpec>(PropertySpec.class, this, EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__PROPERTY_SPECS);
    }
    return propertySpecs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public StructuralVariation getVariation() {
    if (variation != null && variation.eIsProxy()) {
      InternalEObject oldVariation = (InternalEObject)variation;
      variation = (StructuralVariation)eResolveProxy(oldVariation);
      if (variation != oldVariation) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__VARIATION, oldVariation, variation));
      }
    }
    return variation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StructuralVariation basicGetVariation() {
    return variation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setVariation(StructuralVariation newVariation) {
    StructuralVariation oldVariation = variation;
    variation = newVariation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__VARIATION, oldVariation, variation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<PropertySpec> getNotProps() {
    if (notProps == null) {
      notProps = new EObjectContainmentEList<PropertySpec>(PropertySpec.class, this, EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__NOT_PROPS);
    }
    return notProps;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__PROPERTY_SPECS:
        return ((InternalEList<?>)getPropertySpecs()).basicRemove(otherEnd, msgs);
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__NOT_PROPS:
        return ((InternalEList<?>)getNotProps()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__PROPERTY_SPECS:
        return getPropertySpecs();
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__VARIATION:
        if (resolve) return getVariation();
        return basicGetVariation();
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__NOT_PROPS:
        return getNotProps();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__PROPERTY_SPECS:
        getPropertySpecs().clear();
        getPropertySpecs().addAll((Collection<? extends PropertySpec>)newValue);
        return;
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__VARIATION:
        setVariation((StructuralVariation)newValue);
        return;
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__NOT_PROPS:
        getNotProps().clear();
        getNotProps().addAll((Collection<? extends PropertySpec>)newValue);
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
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__PROPERTY_SPECS:
        getPropertySpecs().clear();
        return;
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__VARIATION:
        setVariation((StructuralVariation)null);
        return;
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__NOT_PROPS:
        getNotProps().clear();
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
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__PROPERTY_SPECS:
        return propertySpecs != null && !propertySpecs.isEmpty();
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__VARIATION:
        return variation != null;
      case EntityDifferentiationPackage.STRUCTURAL_VARIATION_DIFF__NOT_PROPS:
        return notProps != null && !notProps.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //StructuralVariationDiffImpl
