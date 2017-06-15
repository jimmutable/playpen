package org.jimmutable.api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jimmutable.JSONPostReader;

@WebServlet(name="DoLogin", value="/api/1.0/users/create-new-user-account.html")
public class ServeletCreateNewUserAccount extends HttpServlet 
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		JSONPostReader reader = new JSONPostReader(req);
		
		
	}

}
