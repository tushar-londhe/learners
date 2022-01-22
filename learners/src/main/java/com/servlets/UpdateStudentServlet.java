package com.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DataBaseDAO;

/**
 * Servlet implementation class AuthenticationServlet
 */
public class UpdateStudentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateStudentServlet()
	{
		super();		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		try
		{
			String strSelStudents = request.getParameter("selStudents");
			
			String []strSelStudArray = strSelStudents.split(",");
			
			Map<String, String> studentAndClassMap = new TreeMap<String, String>();
			
			for(String str : strSelStudArray)
			{
				if(!str.equals(""))
				{
					studentAndClassMap.put(str, request.getParameter("sel" + str));
				}
			}
			
			boolean flag = DataBaseDAO.updateStudentData(studentAndClassMap);
			
			if(flag)
			{
				response.sendRedirect(request.getContextPath() + "/dashboard?message=Students have been assigned to classes successfully !");
			}
			else
			{
				response.sendRedirect(request.getContextPath() + "/dashboard?message=Error while assigning Students to Classes !");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}
}