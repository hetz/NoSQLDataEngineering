package es.um.nosql.streaminginference.json2dbschema.intermediate.firsto;

import org.apache.commons.lang3.Range;

/**
 * @author dsevilla
 *
 */
public interface Ranged
{
	public abstract Range<? extends Comparable<?>> getRange();
}