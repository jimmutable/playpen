package org.jimmutable.singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jimmutable.api.user.UserAccount;

public class UserAccountDB 
{
	private Map<Long,UserAccount> accounts = new ConcurrentHashMap();
	
	public UserAccountDB()
	{
		
	}
	
	public void upsertAccount(UserAccount account)
	{
		if ( account == null ) return;
		if ( account.getSimpleID() == UserAccount.ID_NOT_ASSIGNED_YET ) return;
		
		accounts.put(account.getSimpleID(), account);
	}
	
	public UserAccount getOptionalUserAccount(long id, UserAccount default_value)
	{
		UserAccount ret = accounts.get(id);
		if ( ret == null ) return default_value;
		
		return ret;
	}
	
	public UserAccount findByEmailAddress(String complete_email_address, UserAccount default_value)
	{
		if ( complete_email_address == null ) return default_value;
		
		complete_email_address = complete_email_address.trim().toLowerCase();
		
		for ( UserAccount account : accounts.values() )
		{
			if ( account.getSimpleEmailAddress().equals(complete_email_address) )
				return account;
		}
		
		return default_value;
	}
}
