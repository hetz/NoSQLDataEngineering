/**
 */
package es.um.nosql.s13e.NoSQLSchema.provider;


import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaFactory;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link es.um.nosql.s13e.NoSQLSchema.StructuralVariation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StructuralVariationItemProvider 
  extends ItemProviderAdapter
  implements
    IEditingDomainItemProvider,
    IStructuredItemContentProvider,
    ITreeItemContentProvider,
    IItemLabelProvider,
    IItemPropertySource {
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StructuralVariationItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
    if (itemPropertyDescriptors == null) {
      super.getPropertyDescriptors(object);

      addVariationIdPropertyDescriptor(object);
      addCountPropertyDescriptor(object);
      addFirstTimestampPropertyDescriptor(object);
      addLastTimestampPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Variation Id feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addVariationIdPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StructuralVariation_variationId_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StructuralVariation_variationId_feature", "_UI_StructuralVariation_type"),
         NoSQLSchemaPackage.Literals.STRUCTURAL_VARIATION__VARIATION_ID,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Count feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCountPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StructuralVariation_count_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StructuralVariation_count_feature", "_UI_StructuralVariation_type"),
         NoSQLSchemaPackage.Literals.STRUCTURAL_VARIATION__COUNT,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the First Timestamp feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addFirstTimestampPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StructuralVariation_firstTimestamp_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StructuralVariation_firstTimestamp_feature", "_UI_StructuralVariation_type"),
         NoSQLSchemaPackage.Literals.STRUCTURAL_VARIATION__FIRST_TIMESTAMP,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Last Timestamp feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addLastTimestampPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_StructuralVariation_lastTimestamp_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_StructuralVariation_lastTimestamp_feature", "_UI_StructuralVariation_type"),
         NoSQLSchemaPackage.Literals.STRUCTURAL_VARIATION__LAST_TIMESTAMP,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
    if (childrenFeatures == null) {
      super.getChildrenFeatures(object);
      childrenFeatures.add(NoSQLSchemaPackage.Literals.STRUCTURAL_VARIATION__PROPERTIES);
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child) {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

  /**
   * This returns StructuralVariation.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/StructuralVariation"));
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getText(Object object) {
    StructuralVariation structuralVariation = (StructuralVariation)object;
    return getString("_UI_StructuralVariation_type") + " " + structuralVariation.getVariationId();
  }


  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(StructuralVariation.class)) {
      case NoSQLSchemaPackage.STRUCTURAL_VARIATION__VARIATION_ID:
      case NoSQLSchemaPackage.STRUCTURAL_VARIATION__COUNT:
      case NoSQLSchemaPackage.STRUCTURAL_VARIATION__FIRST_TIMESTAMP:
      case NoSQLSchemaPackage.STRUCTURAL_VARIATION__LAST_TIMESTAMP:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case NoSQLSchemaPackage.STRUCTURAL_VARIATION__PROPERTIES:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    newChildDescriptors.add
      (createChildParameter
        (NoSQLSchemaPackage.Literals.STRUCTURAL_VARIATION__PROPERTIES,
         NoSQLSchemaFactory.eINSTANCE.createAttribute()));

    newChildDescriptors.add
      (createChildParameter
        (NoSQLSchemaPackage.Literals.STRUCTURAL_VARIATION__PROPERTIES,
         NoSQLSchemaFactory.eINSTANCE.createReference()));

    newChildDescriptors.add
      (createChildParameter
        (NoSQLSchemaPackage.Literals.STRUCTURAL_VARIATION__PROPERTIES,
         NoSQLSchemaFactory.eINSTANCE.createAggregate()));

    newChildDescriptors.add
      (createChildParameter
        (NoSQLSchemaPackage.Literals.STRUCTURAL_VARIATION__PROPERTIES,
         NoSQLSchemaFactory.eINSTANCE.createNull()));
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ResourceLocator getResourceLocator() {
    return NosqlschemaEditPlugin.INSTANCE;
  }

}
