package data.transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import Variation_Diff.Variation_DiffFactory;
import es.um.nosql.s13e.NoSQLSchema.Aggregate;
import es.um.nosql.s13e.NoSQLSchema.Attribute;
import es.um.nosql.s13e.NoSQLSchema.StructuralVariation;
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchema;
import es.um.nosql.s13e.NoSQLSchema.Property;
import es.um.nosql.s13e.NoSQLSchema.Reference;
import es.um.nosql.s13e.NoSQLSchema.PTuple;
import es.um.nosql.s13e.NoSQLSchema.DataType;
import data.utils.serializer.NoSQLSchemaSerializer;
import Variation_Diff.HasField;
import Variation_Diff.HasNotField;
import Variation_Diff.NoSQLDifferences;
import Variation_Diff.PrimitiveType;
import Variation_Diff.AggregateType;
import Variation_Diff.TypeDifference;
import Variation_Diff.EntityType;
import Variation_Diff.HeterogeneousTupleType;
import Variation_Diff.TypeHint;
import Variation_Diff.HomogeneousTupleType;
import Variation_Diff.ReferenceType;
import Variation_Diff.FieldType;

/**
 * Class used to transform DBSCHEMA models into DBDIFFERENCES models.
 * @author Alberto Hernández Chillón
 */
public class M2M
{
	/**
	 * Singleton reference.
	 */
	private static M2M instance = null;

	/**
	 * Method used to get the M2M instance.
	 * @return M2M instance.
	 */
	public static M2M getInstance()
	{
		if (instance == null)
			instance = new M2M();

		return instance;
	}

	/**
	 * Default private constructor.
	 */
	private M2M()
	{
	}

	/**
	 * Transform method used to transform NoSQLSchema into a NoSQLDifferences.
	 * @param baseModel The NoSQLSchema object to be transformed.
	 * @return A NoSQLDifferences object.
	 */
	public NoSQLDifferences transform(NoSQLSchema baseModel)
	{
		NoSQLDifferences differenceModel = Variation_DiffFactory.eINSTANCE.createNoSQLDifferences();
		differenceModel.setName(baseModel.getName());
		Map<StructuralVariation, List<Pair<String, FieldType>>> evMap;

		for (es.um.nosql.s13e.NoSQLSchema.EntityType entity : baseModel.getEntities())
		{
			evMap = getEVPropertiesMap(entity);

			for (StructuralVariation entityVariation : entity.getVariations())
			{
				TypeDifference tDiff = Variation_DiffFactory.eINSTANCE.createTypeDifference();
				tDiff.setName(entity.getName() + "_" + entityVariation.getVariationId());
				differenceModel.getHasTypeDifferences().add(tDiff);

				addHasFields(evMap.get(entityVariation), tDiff);
				addHasNotFields(entityVariation, tDiff, evMap);
			}
		}
		return differenceModel;
	}

	/**
	 * Method used to get a map in which properties (name, value) are associated to each StructuralVariation of an EntityType.
	 * @param baseEntityType The EntityType containing the StructuralVariations to be mapped.
	 * @return A Map<StructuralVariation, List<Pair<String, String>>> in which the mapping is stored.
	 */
	private Map<StructuralVariation, List<Pair<String, FieldType>>> getEVPropertiesMap(es.um.nosql.s13e.NoSQLSchema.EntityType baseEntityType)
	{
		Map<StructuralVariation, List<Pair<String, FieldType>>> evMap = new HashMap<StructuralVariation, List<Pair<String, FieldType>>>();

		for (StructuralVariation ev : baseEntityType.getVariations())
		{
			List<Pair<String, FieldType>> pairList = new ArrayList<Pair<String, FieldType>>();
			evMap.put(ev, pairList);

			for (Property property : ev.getProperties())
			{
				String name = property.getName();
				FieldType type = null;

				if (property instanceof Attribute)
				{
				  DataType theType = ((Attribute)property).getType();

					if (theType instanceof es.um.nosql.s13e.NoSQLSchema.PrimitiveType)
					{
						if (name.equals("type") || name.equals("Type"))
						{
							type = Variation_DiffFactory.eINSTANCE.createEntityType();
							((EntityType)type).setType(((es.um.nosql.s13e.NoSQLSchema.PrimitiveType)theType).getName());
						}
						else
						{
							type = Variation_DiffFactory.eINSTANCE.createPrimitiveType();
							((PrimitiveType)type).setType(((es.um.nosql.s13e.NoSQLSchema.PrimitiveType)theType).getName());
						}
					}
					else if (theType instanceof PTuple)
					{
						type = Variation_DiffFactory.eINSTANCE.createHeterogeneousTupleType();

						for (DataType tupleType : ((PTuple)theType).getElements())
						{
							if (tupleType instanceof es.um.nosql.s13e.NoSQLSchema.PrimitiveType)
								((HeterogeneousTupleType)type).getType().add(((es.um.nosql.s13e.NoSQLSchema.PrimitiveType)tupleType).getName());
							else if (tupleType instanceof PTuple)
								((HeterogeneousTupleType)type).getType().add("PTuple[" + NoSQLSchemaSerializer.getInstance().stringify(((PTuple)tupleType).getElements()) + "]");
						}

						String aType = getAType(((HeterogeneousTupleType)type).getType().get(0));
						if (checkSameTypes(((HeterogeneousTupleType)type).getType(), aType))
						{
							type = Variation_DiffFactory.eINSTANCE.createHomogeneousTupleType();
							((HomogeneousTupleType)type).setType(aType);
						}
								
					}
				}
				else if (property instanceof Reference)
				{
					type = Variation_DiffFactory.eINSTANCE.createReferenceType();

					((ReferenceType)type).setType(((Reference)property).getRefsTo().getName());
					((ReferenceType)type).setLowerBound(((Reference)property).getLowerBound());
					((ReferenceType)type).setUpperBound(((Reference)property).getUpperBound());
				}
				else if (property instanceof Aggregate)
				{
					type = Variation_DiffFactory.eINSTANCE.createAggregateType();

					((AggregateType)type).setLowerBound(((Aggregate)property).getLowerBound());
					((AggregateType)type).setUpperBound(((Aggregate)property).getUpperBound());
					for (StructuralVariation aggregatedEV : ((Aggregate)property).getAggregates())
						((AggregateType)type).getType().add(((es.um.nosql.s13e.NoSQLSchema.EntityType)aggregatedEV.getContainer()).getName() + "_" + String.valueOf(aggregatedEV.getVariationId()));
				}

				pairList.add(new MutablePair<String, FieldType>(name, type));
			}

			boolean typeIsDefined = false;
			for (Pair<String, FieldType> pair : pairList)
				if (pair.getKey().equals("type") && pair.getValue() instanceof EntityType)
				{
					typeIsDefined = true;
					break;
				}
			if (!typeIsDefined)
			{
				FieldType type = Variation_DiffFactory.eINSTANCE.createEntityType();
				((EntityType)type).setType(baseEntityType.getName());
				pairList.add(new MutablePair<String, FieldType>("type", type));
			}
				
		}
		return evMap;
	}

	/**
	 * Method used to add HasField objects to a TypeDifference object from a propertiesList.
	 * @param propertiesList The propertiesList associated to the StructuralVariation whose TypeDifferences are being generated.
	 * @param tDiff A TypeDifference object in which store the HasField objects.
	 */
	private void addHasFields(List<Pair<String, FieldType>> propertiesList, TypeDifference tDiff)
	{
		// Para cada StructuralVariation, agregamos sus propiedades como HasValue.
		for (Pair<String, FieldType> propertyPair : propertiesList)
		{
			HasField hField = Variation_DiffFactory.eINSTANCE.createHasField();
			hField.setWithName(propertyPair.getLeft());
			hField.setWithType(createNewFieldType(propertyPair.getRight()));

			tDiff.getHints().add(hField);
		}
	}

	/**
	 * Method used to add HasNotField objects from a Map to a TypeDifference object associated with an StructuralVariation.
	 * @param theStructuralVariation The StructuralVariation whose TypeDifferences are being generated.
	 * @param tDiff TypeDifferences whose fields HasNotFields are being generated.
	 * @param evMap The map in which StructuralVariations are associated with their Properties (name, value).
	 */
	private void addHasNotFields(StructuralVariation theStructuralVariation, TypeDifference tDiff, Map<StructuralVariation, List<Pair<String, FieldType>>> evMap)
	{
		boolean propertyFound = false;

		for (StructuralVariation evInList : evMap.keySet())
		{
			// And we add other StructuralVariation properties as HasNotValue.
			if (evInList != theStructuralVariation)
			{
				for (Pair<String, FieldType> pairProperty : evMap.get(evInList))
				{
					for (TypeHint hint : tDiff.getHints())
					{
						// Except if an StructuralVariation has a {name, value} property equals to another's.
						// Or the property was already added as HasNotField.
						if (hint.getWithName().equals(pairProperty.getLeft()) && sameTypeHints(hint.getWithType(), pairProperty.getRight()))
						{
							propertyFound = true;
							break;
						}
					}

					// We can't do this step inside the loop because we are modifying the collection we are iterating.
					if (!propertyFound)
					{
						HasNotField hNotField = Variation_DiffFactory.eINSTANCE.createHasNotField();
						hNotField.setWithName(pairProperty.getLeft());
						hNotField.setWithType(createNewFieldType(pairProperty.getRight()));

						tDiff.getHints().add(hNotField);
					}

					propertyFound = false;
				}
			}
		}
	}

	/**
	 * Method used to check if two WithTypes are equal. The result will depend on the WithType subtypes.
	 * @param type1 The type1 to be checked.
	 * @param type2 The type2 to be checked.
	 * @return True if the two types are equal, false otherwise.
	 */
	private boolean sameTypeHints(FieldType type1, FieldType type2)
	{
		if (!type1.getClass().equals(type2.getClass()))
			return false;

		// Two primitive types are equal if their types are exactly equal.
		if (type1 instanceof PrimitiveType)
			return ((PrimitiveType)type1).getType().equals(((PrimitiveType)type2).getType());

		// Two entities are equal if their names are equal.
		if (type1 instanceof EntityType)
			return ((EntityType)type1).getType().equals(((EntityType)type2).getType());

		// Two homogeneous tuples are equal if their type is equal.
		if (type1 instanceof HomogeneousTupleType)
			return ((HomogeneousTupleType)type1).getType().equals(((HomogeneousTupleType)type2).getType()); 

		// Two heterogeneous tuples are equal if their types are equal one by one.
		if (type1 instanceof HeterogeneousTupleType)
		{
			HeterogeneousTupleType type11 = (HeterogeneousTupleType)type1;
			HeterogeneousTupleType type22 = (HeterogeneousTupleType)type2;

			return type11.getType().equals(type22.getType());
		}

		// Two aggregates are equal if they are of the same size and they are equal aggregate by aggregate. Cardinalities are not taken into account.
		if (type1 instanceof AggregateType)
		{
			AggregateType type11 = (AggregateType)type1;
			AggregateType type22 = (AggregateType)type2;

			return type11.getType().equals(type22.getType());
		}

		// Two references are equal if their types are equal. Cardinalities are not taken into account.
		if (type1 instanceof ReferenceType)
		{
			ReferenceType type11 = (ReferenceType)type1;
			ReferenceType type22 = (ReferenceType)type2;

			return type11.getType().equals(type22.getType());
		}

		return false;
	}

	/**
	 * Method used to clone a WithType object.
	 * @param type The type to be cloned.
	 * @return A new WithType object.
	 */
	private FieldType createNewFieldType(FieldType type)
	{

		if (type instanceof PrimitiveType)
		{
			PrimitiveType newType = Variation_DiffFactory.eINSTANCE.createPrimitiveType();
			newType.setType(((PrimitiveType)type).getType());
			return newType;
		}
		else if (type instanceof EntityType)
		{
			EntityType newType = Variation_DiffFactory.eINSTANCE.createEntityType();
			newType.setType(((EntityType)type).getType());
			return newType;
		}
		else if (type instanceof HomogeneousTupleType)
		{
			HomogeneousTupleType newType = Variation_DiffFactory.eINSTANCE.createHomogeneousTupleType();
			newType.setType(((HomogeneousTupleType)type).getType());
			return newType;
		}
		else if (type instanceof HeterogeneousTupleType)
		{
			HeterogeneousTupleType newType = Variation_DiffFactory.eINSTANCE.createHeterogeneousTupleType();
			newType.getType().addAll(((HeterogeneousTupleType)type).getType());
			return newType;
		}
		else if (type instanceof AggregateType)
		{
			AggregateType newType = Variation_DiffFactory.eINSTANCE.createAggregateType();
			newType.setLowerBound(((AggregateType)type).getLowerBound());
			newType.setUpperBound(((AggregateType)type).getUpperBound());
			newType.getType().addAll(((AggregateType)type).getType());
			return newType;
		}
		else if (type instanceof ReferenceType)
		{
			ReferenceType newType = Variation_DiffFactory.eINSTANCE.createReferenceType();
			newType.setLowerBound(((ReferenceType)type).getLowerBound());
			newType.setUpperBound(((ReferenceType)type).getUpperBound());
			newType.setType(((ReferenceType)type).getType());
			return newType;
		}

		return null;
	}

	/**
	 * Method used to get the first type of a given PTuple content in a string.
	 * @param type The PTuple content.
	 * @return A type in a string.
	 */
	private String getAType(String type)
	{
		String result = type;

		if (result.startsWith("PTuple["))
		{
			int startIndex = 6;
			int endIndex = result.length();

			if (result.contains(";"))
				endIndex = result.indexOf(";");
			else
				if (result.contains("]"))
					endIndex = result.indexOf("]");

			return getAType(result.substring(startIndex, endIndex));
		}

		return result;
	}

	/**
	 * Method used to check if all elements of an array are of the same type.
	 * @param typeList The type list to be checked.
	 * @param theType The type to check.
	 * @return True if all elements are of the same type as TheType or false otherwise.
	 */
	private boolean checkSameTypes(List<String> typeList, String theType)
	{
		for (String typeInList : typeList)
		{
			if (typeInList.startsWith("PTuple["))
			{
				List<String> eList = new ArrayList<String>();
				fillPTupleList(eList, typeInList.substring(6, typeInList.length() - 1));

				if (!checkSameTypes(eList, theType))
					return false;
			}
			else if (!typeInList.equals(theType))
				return false;		
		}

		return true;
	}

	/**
	 * Method used to parse a tuple contained in a string into a list of strings, one for each element.
	 * @param list The list to be filled.
	 * @param typeChain The tuple chain to be parsed.
	 */
	private void fillPTupleList(List<String> list, String typeChain)
	{
		String types = typeChain;

		if (types.startsWith(";"))
			types = types.substring(1);

		if (types.startsWith("PTuple["))
		{
			String auxVar = types.substring(6);
			int keyBalance = 1;
			boolean keepLooping = true;

			for (int i = 0; i < auxVar.length() && keepLooping; i++)
			{
				switch (String.valueOf(auxVar.charAt(i)))
				{
					case "[": {keyBalance++; break;}
					case "]": {keyBalance--; break;}
				}
				if (keyBalance == 0)
				{
					list.add("PTuple[" + auxVar.substring(0, i + 1));
					if (i + 1 < auxVar.length())
						fillPTupleList(list, auxVar.substring(i + 1, auxVar.length()));

					keepLooping = false;
				}
			}
		}
		else
		{
			if (types.contains(";"))
			{
				list.add(types.substring(0, types.indexOf(";")));
				fillPTupleList(list, types.substring(types.indexOf(";") + 1));
			}
			else
				list.add(types);
		}
	}
}
