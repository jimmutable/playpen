package org.jimmutable;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

@WebServlet(name="HelloAppEngine", value="/hello")
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    response.setContentType("text/plain");
    
    
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
	KeyFactory keyFactory = datastore.newKeyFactory().setKind("experimental_changelog");
	
	Query q = Query.newEntityQueryBuilder()
			.setKind("experimental_changelog")
			.setFilter(PropertyFilter.eq("user_id", 389789))
			//.setOrderBy(OrderBy.asc("timestamp"))
			//.setLimit(100)
			.build();
	
	QueryResults<Entity> results = datastore.run(q);
	
	long t1 = System.currentTimeMillis();
	
	StringBuilder builder = new StringBuilder();
	
	int count = 0;
	while (results.hasNext()) 
	{
		Entity entity = results.next();
		builder.append(entity.getKey()+", "+entity.getString("short_description"));
		builder.append("\n");
		
		count++;
	}
	
	
	long t2 = System.currentTimeMillis();
	
	builder.append(String.format("Time to read %,d elements %,d",count,t2-t1));
    
    
    response.getWriter().println(builder.toString());
  }

  public static String getInfo() {
    return "Version: " + System.getProperty("java.version")
          + " OS: " + System.getProperty("os.name")
          + " User: " + System.getProperty("user.name");
  }
}
