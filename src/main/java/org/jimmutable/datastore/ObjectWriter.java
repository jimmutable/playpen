package org.jimmutable.datastore;

import org.jimmutable.core.serialization.FieldDefinition;

abstract public class ObjectWriter 
{
	abstract public void setID(long id);

	abstract public void writeString(FieldDefinition.String field, java.lang.String value);
	
	abstract public void writeLong(FieldDefinition.Long field, java.lang.Long value);

	abstract public void writeDouble(FieldDefinition.Double field, java.lang.Double value);
	
	abstract public void writeBoolean(FieldDefinition.Boolean field, java.lang.Boolean value);
}
