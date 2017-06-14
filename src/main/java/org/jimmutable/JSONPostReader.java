package org.jimmutable;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONPostReader 
{ 
	private boolean json_valid;
	private JSONObject json;
	
	public JSONPostReader(HttpServletRequest req)
	{
		try
		{
			json = new JSONObject(new JSONTokener(req.getReader())); 
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
}
