/**
 */
package es.um.nosql.s13e.entitydifferentiation.DecisionTree.util;

import es.um.nosql.s13e.entitydifferentiation.DecisionTree.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see es.um.nosql.s13e.entitydifferentiation.DecisionTree.DecisionTreePackage
 * @generated
 */
public class DecisionTreeSwitch<T> extends Switch<T> {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static DecisionTreePackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DecisionTreeSwitch() {
    if (modelPackage == null) {
      modelPackage = DecisionTreePackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case DecisionTreePackage.DECISION_TREE_NODE: {
        DecisionTreeNode decisionTreeNode = (DecisionTreeNode)theEObject;
        T result = caseDecisionTreeNode(decisionTreeNode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DecisionTreePackage.LEAF_NODE: {
        LeafNode leafNode = (LeafNode)theEObject;
        T result = caseLeafNode(leafNode);
        if (result == null) result = caseDecisionTreeNode(leafNode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DecisionTreePackage.INTERMEDIATE_NODE: {
        IntermediateNode intermediateNode = (IntermediateNode)theEObject;
        T result = caseIntermediateNode(intermediateNode);
        if (result == null) result = caseDecisionTreeNode(intermediateNode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DecisionTreePackage.DECISION_TREE_FOR_ENTITY: {
        DecisionTreeForEntity decisionTreeForEntity = (DecisionTreeForEntity)theEObject;
        T result = caseDecisionTreeForEntity(decisionTreeForEntity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DecisionTreePackage.DECISION_TREES: {
        DecisionTrees decisionTrees = (DecisionTrees)theEObject;
        T result = caseDecisionTrees(decisionTrees);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case DecisionTreePackage.PROPERTY_SPEC2: {
        PropertySpec2 propertySpec2 = (PropertySpec2)theEObject;
        T result = casePropertySpec2(propertySpec2);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Node</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDecisionTreeNode(DecisionTreeNode object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Leaf Node</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Leaf Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLeafNode(LeafNode object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Intermediate Node</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Intermediate Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntermediateNode(IntermediateNode object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>For Entity</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>For Entity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDecisionTreeForEntity(DecisionTreeForEntity object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Decision Trees</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Decision Trees</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDecisionTrees(DecisionTrees object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Property Spec2</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Spec2</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePropertySpec2(PropertySpec2 object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object) {
    return null;
  }

} //DecisionTreeSwitch
