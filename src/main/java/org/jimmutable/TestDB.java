package org.jimmutable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.ProjectionEntity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;



// You need to authenticate to the cloud first...
// gcloud auth application-default login
// http://googlecloudplatform.github.io/google-cloud-java/0.19.0/index.html
// http://googlecloudplatform.github.io/google-cloud-java/0.19.0/apidocs/index.html

public class TestDB 
{
	static private Random rnd = new Random();
	
	static public void main(String args[])
	{
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
		
		System.out.println(builder);
	}
	static public void createBulkEntries()
	{
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		KeyFactory keyFactory = datastore.newKeyFactory().setKind("experimental_changelog");
		
		long t1 = System.currentTimeMillis();
		
		List<FullEntity> buffer = new ArrayList();
		int i;
		
		for ( i = 0; i < 100_000; i++ )
		{
			buffer.add(generateRandomEntry(datastore, keyFactory));
			
			if ( buffer.size() > 490 )
			{
				putAllInBuffer(datastore, buffer);
				buffer.clear();
			}
			
			if ( i % 1000 == 0 )
			{
				System.out.println(String.format("Time to create %,d elements %,d",i,System.currentTimeMillis()-t1));
			}
		}
		
		putAllInBuffer(datastore, buffer);
	
		
		long t2 = System.currentTimeMillis();
		
		System.out.println(String.format("Time to create %,d elements %,d",i,t2-t1));
	}
	
	static private void putAllInBuffer(Datastore datastore, List<FullEntity> buffer)
	{
		if ( buffer.isEmpty() || buffer == null ) return;
		
		FullEntity arr[] = new FullEntity[buffer.size()];
		
		for ( int i = 0; i < arr.length; i++ )
		{
			arr[i] = buffer.get(i);
		}
		
		datastore.put(arr);
	}
	
	
	static public FullEntity generateRandomEntry(Datastore datastore, KeyFactory keyFactory)
	{
		String short_description_choices[] = new String[]{"Added a billable account", "Accepting advertising from Intercounty Appliance Corporation", "Accepting Advertising from Whilrpool", "Deleted user john.doe@me.com", "Updated company logo"};
		String long_description_words[] = new String[]{"lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing", "elit", "sed", "do", "eiusmod", "tempor", "incididunt"};
		
		long user_id[] = new long[]{11248, 22374, 389789, 489798, 58979879, 608897, 71312312, 8324234, 923432};
		
		
		java.util.Date rnd_date = new java.util.Date(115+rnd.nextInt(5), rnd.nextInt(12), rnd.nextInt(27));
		
		String short_description = short_description_choices[rnd.nextInt(short_description_choices.length)];
		
		int long_desc_length = rnd.nextInt(150);
		StringBuilder long_description = new StringBuilder();
		
		for ( int i = 0; i < long_desc_length; i++ )
		{
			if ( long_description.length() != 0 ) long_description.append(" ");
			long_description.append(long_description_words[rnd.nextInt(long_description_words.length)]);
		}
		
		IncompleteKey key = keyFactory.setKind("experimental_changelog").newKey();
		 
		FullEntity.Builder<IncompleteKey> builder = FullEntity.newBuilder(key);
		
		builder.set("user_id", user_id[rnd.nextInt(user_id.length)]);
		builder.set("timestamp", rnd_date.getTime());
		builder.set("short_description", short_description);
		builder.set("long_description", long_description.toString());
	
		return builder.build();	
	}
}
