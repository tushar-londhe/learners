package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DataBaseDAO;

/**
 * Servlet implementation class AuthenticationServlet
 */
public class UpdateTeachersAndSub extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTeachersAndSub()
	{
		super();		
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		try
		{
			String className=request.getParameter("selClasses");			
			String teachersName=request.getParameter("selTeachers");			
			String []subjectsArray = request.getParameterValues("chkSubjects");
			
			StringBuilder subjects = new StringBuilder();
			
			for(String sub : subjectsArray)
			{
				subjects.append(sub + ",");				
			}
			
			int lastComma = subjects.length()-1;
			
			subjects.deleteCharAt((lastComma));
					
			boolean flag = DataBaseDAO.insertClassTeacherAndSubjectData(className, teachersName, subjects.toString());
		
			if(flag)
			{	
				response.sendRedirect(request.getContextPath() + "/dashboard?message=Assignment of Teachers and Subjects to Classes done successfully !");
			}
			else
			{
				response.sendRedirect(request.getContextPath() + "/dashboard?message=Error while assigning Teachers & Subjects to Classes !");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}
}