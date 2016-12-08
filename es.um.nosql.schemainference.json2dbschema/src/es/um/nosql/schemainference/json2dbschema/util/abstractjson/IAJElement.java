package es.um.nosql.schemainference.json2dbschema.util.abstractjson;

import java.util.Iterator;

public interface IAJElement
extends Iterable<IAJElement>, IAJIdentify
{
	public IAJElement get(int index);
	public IAJElement get(String fieldName);

	public Iterator<String> getFieldNames();
}
