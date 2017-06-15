package org.jimmutable.datastore;

import org.jimmutable.core.serialization.FieldDefinition;

abstract public class ObjectReader 
{
	abstract public java.lang.Long getID(FieldDefinition.Long field);
	
	abstract public java.lang.String getString(FieldDefinition.String field);
	
	abstract public java.lang.Long getLong(FieldDefinition.Long field);

	abstract public java.lang.Double getDouble(FieldDefinition.Double field);
	
	abstract public java.lang.Boolean getBoolean(FieldDefinition.Boolean field);
}
