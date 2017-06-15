package time;

import java.util.Set;

import org.joda.time.DateTimeZone;

public class TimeZones 
{
	static public void main(String args[])
	{
		Set<String> ids = DateTimeZone.getAvailableIDs();
        
		for (String id : ids)
		{
			if ( id.startsWith("US") ) continue;
			
			System.out.println(String.format("<option value=\"%s\">%s</option>", id, id));
		}
	}
}
