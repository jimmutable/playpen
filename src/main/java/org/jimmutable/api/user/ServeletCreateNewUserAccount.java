package org.jimmutable.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.jimmutable.JSONPostReader;
import org.jimmutable.core.serialization.Format;
import org.jimmutable.data.UserAccount;
import org.jimmutable.exception.FormErrorDetail;
import org.jimmutable.exception.FormValidationException;
import org.jimmutable.singleton.ApplicationSingleton;
import org.jimmutable.singleton.UserAccountDB;

@WebServlet(name="CreateNewUserAccount", value="/api/1.0/users/create-new-user-account.html")
public class ServeletCreateNewUserAccount extends HttpServlet 
{
	public void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			JSONPostReader reader = new JSONPostReader(req, RequestNewUserAccount.TYPE_NAME);
			System.out.println("JSON VALID: "+reader.getSimpleJsonValid());
			System.out.println(reader);
			
			RequestNewUserAccount request = (RequestNewUserAccount)reader.getStandardImmutableObject();
			
			UserAccountDB db = ApplicationSingleton.get().getSimpleUserAccountDB();
			
			// Does a user with this email address already exist?
			if ( db.findByEmailAddress(request.getSimpleEmailAddress(), null) != null )
			{
				throw new FormValidationException(new FormErrorDetail(RequestNewUserAccount.FIELD_EMAIL_ADDRESS, "A user with this email adress already exists"));
			}
			
			UserAccount new_user_account = db.createNewUserAccount(request);
			
			ResponseNewUserAccount response_obj = new ResponseNewUserAccount(new_user_account, "asdfasdf");
			
			response.setStatus(HttpStatus.SC_OK);
			
			response.getWriter().print(response_obj.serialize(Format.JSON));
		}
		catch(FormValidationException e)
		{
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			response.getWriter().print(e.getSimpleErrorDetail().serialize(Format.JSON));
		}
	}

}
