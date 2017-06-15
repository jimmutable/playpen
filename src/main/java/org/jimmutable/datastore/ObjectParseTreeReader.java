package org.jimmutable.datastore;

import org.jimmutable.core.serialization.reader.ObjectParseTree;

public class ObjectParseTreeReader extends ObjectReader 
{
	private ObjectParseTree t;
	
	public ObjectParseTreeReader(ObjectParseTree t)
	{
		this.t = t;
	}

	
	public Long getID(org.jimmutable.core.serialization.FieldDefinition.Long field) 
	{
		return getLong(field);
	}

	public String getString(org.jimmutable.core.serialization.FieldDefinition.String field) 
	{
		return t.getString(field);
	}

	
	public Long getLong(org.jimmutable.core.serialization.FieldDefinition.Long field) 
	{
		return t.getLong(field);
	}

	@Override
	public Double getDouble(org.jimmutable.core.serialization.FieldDefinition.Double field) 
	{
		return t.getDouble(field);
	}

	
	public Boolean getBoolean(org.jimmutable.core.serialization.FieldDefinition.Boolean field) 
	{
		return t.getBoolean(field);
	}
	
	
}
