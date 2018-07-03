/**
 *
 * $Id$
 */
package Variation_Diff.validation;


/**
 * A sample validator interface for {@link Variation_Diff.AssociationType}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface AssociationTypeValidator
{
  boolean validate();

  boolean validateLowerBound(int value);
  boolean validateUpperBound(int value);
}