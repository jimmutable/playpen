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
import org.jimmutable.exception.FormValidationException;

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
			
			response.setStatus(HttpStatus.SC_OK);
			
			response.getWriter().print("ok");
		}
		catch(FormValidationException e)
		{
			response.setStatus(HttpStatus.SC_BAD_REQUEST);
			response.getWriter().print(e.getSimpleErrorDetail().serialize(Format.JSON));
		}
	}

}
