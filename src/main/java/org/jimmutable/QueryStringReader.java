package org.jimmutable;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class QueryStringReader 
{
	private boolean query_string_valid;
	private Map<String,String> values;
	
	public QueryStringReader(String query_string)
	{
		query_string_valid = false;
		values = new HashMap();
		
		// Empty query string is OK!
		if ( query_string == null ) 
		{
			query_string_valid = true;
			return;
		}

		try
		{
			String[] pairs = query_string.split("&");

			for (String pair : pairs) 
			{
				int idx = pair.indexOf("=");
				values.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8").toLowerCase().trim(), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
			}
		}
		catch(Exception e)
		{
			query_string_valid = false;
			values.clear();
		}
	}
	
	public QueryStringReader(HttpServletRequest request)
	{
		this(request.getQueryString());
	}
	
	public boolean getSimpleQueryStringValid() { return query_string_valid; }
	
	public boolean has(String key_name)
	{
		if ( key_name == null ) return false;
		return values.containsKey(key_name.toLowerCase());
	}
	
	public String getOptionalString(String key_name, String default_value)
	{
		if ( key_name == null ) return default_value;
		key_name = key_name.toLowerCase();
		
		String value = values.get(key_name);
		if ( value == null ) return default_value;
		
		return value;
	}
}
