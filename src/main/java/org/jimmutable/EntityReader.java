package org.jimmutable;

import org.jimmutable.core.serialization.FieldDefinition;

import com.google.cloud.datastore.Entity;

public class EntityReader
{
	private Entity entity;
	
	public EntityReader(Entity entity)
	{
		this.entity = entity;
	}
	
	public long getSimpleID() { return entity.getKey().getId(); }
	
	public String getString(FieldDefinition.String field)
	{
		String ret = entity.getString(field.getSimpleFieldName().toString());
		
		if ( ret == null ) return field.getSimpleUnsetValue();
		return ret;
	}
	
	public Boolean getBoolean(FieldDefinition.Boolean field)
	{
		Boolean foo = entity.getBoolean(field.getSimpleFieldName().toString());
		
		if ( foo == null ) return field.getSimpleUnsetValue();
		
		return foo;
	}
}
