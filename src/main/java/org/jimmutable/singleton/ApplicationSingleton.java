package org.jimmutable.singleton;

public class ApplicationSingleton 
{
	static private ApplicationSingleton singleton = new ApplicationSingleton();
	
	private UserAccountDB user_account_db;
	
	public ApplicationSingleton()
	{
		user_account_db = new UserAccountDB();
	}
	
	public UserAccountDB getSimpleUserAccountDB() { return user_account_db; }
	
	static public ApplicationSingleton get() { return singleton; }
}
