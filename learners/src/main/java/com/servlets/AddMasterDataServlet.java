package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DataBaseDAO;
import com.dao.Queries;
import com.utilities.CommonUtilities;

public class AddMasterDataServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6055552109683758690L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getSession().getAttribute("userName") != null)
		{
			String studName = request.getParameter("txtStudentName");
			String teacherName = request.getParameter("txtTeacherName");
					
			
			if(!CommonUtilities.checkNullOrEmpty(studName))
			{
				boolean flag = DataBaseDAO.addMasterData(Queries.ADD_STUDENT, studName);
				if(flag)
				{
					response.sendRedirect(request.getContextPath() + "/dashboard?message=Student "+studName+" have been added successfully !");
				}
				else
				{
					response.sendRedirect(request.getContextPath() + "/dashboard?message=Error while adding Student !");
				}
			}			
			else
			if(!CommonUtilities.checkNullOrEmpty(teacherName))
			{
				boolean flag = DataBaseDAO.addMasterData(Queries.ADD_TEACHER, teacherName);
				if(flag)
				{
					response.sendRedirect(request.getContextPath() + "/dashboard?message=Teacher "+teacherName+" have been added successfully !");
				}
				else
				{
					response.sendRedirect(request.getContextPath() + "/dashboard?message=Error while adding Teacher !");
				}
			}
			else
			{
				response.sendRedirect(request.getContextPath() + "/dashboard?message=Please provide Details to Add !");
			}
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/Login.jsp?message=Please login first !");
		}
	}

}
