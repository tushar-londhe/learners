package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DataBaseDAO;
import com.utilities.CommonUtilities;

/**
 * Servlet implementation class AuthenticationServlet
 */
public class AuthenticationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthenticationServlet()
	{
		super();		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String userName = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
	
		if(CommonUtilities.checkNullOrEmpty(userName) || CommonUtilities.checkNullOrEmpty(password))
		{	
			response.sendRedirect(request.getContextPath() + "/Login.jsp?message=Please login !");
		}
		else
		{
			boolean flag = DataBaseDAO.validateUser(userName, password);
			
			if(flag)
			{
				request.getSession().setAttribute("userName", userName);
				response.sendRedirect(request.getContextPath() + "/dashboard");
			}
			else
			{
				response.sendRedirect(request.getContextPath() + "/Login.jsp?message=Authentication Failed !");
			}
		}		
	}
}