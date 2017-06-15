package org.jimmutable;

import javax.servlet.http.HttpServletRequest;

import org.jimmutable.core.objects.StandardObject;
import org.jimmutable.core.serialization.FieldName;
import org.jimmutable.core.serialization.TypeName;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONPostReader 
{ 
	private boolean json_valid;
	private JSONObject json;
	 
	
	public JSONPostReader(HttpServletRequest req)
	{
		this(req,null);
	}
	
	public JSONPostReader(HttpServletRequest req, TypeName type)
	{
		try
		{
			json = new JSONObject(new JSONTokener(req.getReader()));
			
			if ( type != null && !json.has(FieldName.FIELD_NAME_TYPE_HINT.getSimpleName()) )
			{
				json.append(FieldName.FIELD_NAME_TYPE_HINT.getSimpleName(), type.getSimpleName());
			}
			
			json_valid = true;
		}
		catch(Exception e)
		{
			json = new JSONObject("{}");
			json_valid = false;
		}
	}
	
	public boolean getSimpleJsonValid() { return json_valid; }
	
	public boolean has(String key_name)
	{
		return json.has(key_name);
	}
	
	public String getOptionalString(String key_name, String default_value)
	{
		if ( key_name == null ) return default_value;
		
		if ( !json.has(key_name) ) return default_value;
		
		return json.getString(key_name);
	}
	
	public StandardObject getStandardImmutableObject(StandardObject default_value)
	{
		try
		{
			return StandardObject.deserialize(json.toString());
		}
		catch(Exception e)
		{
			return default_value;
		}
	}
}
