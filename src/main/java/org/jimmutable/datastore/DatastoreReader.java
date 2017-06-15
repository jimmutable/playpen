package org.jimmutable.datastore;

import org.jimmutable.core.serialization.FieldDefinition;
import org.jimmutable.core.serialization.FieldDefinition.Boolean;
import org.jimmutable.core.serialization.FieldDefinition.Float;
import org.jimmutable.core.serialization.FieldDefinition.Integer;
import org.jimmutable.core.serialization.FieldDefinition.Long;

import com.google.cloud.datastore.Entity;

public class DatastoreReader extends ObjectReader 
{
	private Entity entity;
	
	public DatastoreReader(Entity entity)
	{
		this.entity = entity;
	}

	
	public java.lang.Long getID(Long field) 
	{
		if ( !entity.hasKey() ) return field.getSimpleUnsetValue();
		
		return entity.getKey().getId();
	}

	
	public String getString(org.jimmutable.core.serialization.FieldDefinition.String field) 
	{
		try
		{
			return entity.getString(field.getSimpleFieldName().toString());
		}
		catch(Exception e)
		{
			return field.getSimpleUnsetValue();
		}
	}

	public java.lang.Long getLong(Long field) 
	{
		try
		{
			return entity.getLong(field.getSimpleFieldName().toString());
		}
		catch(Exception e)
		{
			return field.getSimpleUnsetValue();
		}
	}

	@Override
	public Double getDouble(org.jimmutable.core.serialization.FieldDefinition.Double field) 
	{
		try
		{
			return entity.getDouble(field.getSimpleFieldName().toString());
		}
		catch(Exception e)
		{
			return field.getSimpleUnsetValue();
		}
	}

	@Override
	public java.lang.Boolean getBoolean(Boolean field) 
	{
		try
		{
			return entity.getBoolean(field.getSimpleFieldName().toString());
		}
		catch(Exception e)
		{
			return field.getSimpleUnsetValue();
		}
	}
	
	

}
