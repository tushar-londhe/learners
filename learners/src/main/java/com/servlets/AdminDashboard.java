package com.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DataBaseDAO;
import com.dao.Queries;

public class AdminDashboard extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5271019426024360460L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (request.getSession().getAttribute("userName") != null)
		{
			Map<String, Map<String, String>> dataMap = new HashMap<String, Map<String, String>>();

			Map<String, String> mapOfClasses = DataBaseDAO.getData(Queries.GET_CLASSES);
			Map<String, String> mapOfStudents = DataBaseDAO.getData(Queries.GET_STUDENTS);
			Map<String, String> mapOfTeachers = DataBaseDAO.getData(Queries.GET_TEACHERS);
			Map<String, String> mapOfSubjects = DataBaseDAO.getData(Queries.GET_SUBJECTS);
			Map<String, ArrayList<String>> mapOfClassTeachSub = DataBaseDAO.getClassTeachSubData();
			Map<String, String> mapOfClassStudents = DataBaseDAO.getClassAndStudData();
			
			dataMap.put("classes", mapOfClasses);
			dataMap.put("students", mapOfStudents);
			dataMap.put("teachers", mapOfTeachers);
			dataMap.put("subjects", mapOfSubjects);
			
			request.setAttribute("dataMap", dataMap);
			request.setAttribute("mapOfClassTeachSub", mapOfClassTeachSub);
			request.setAttribute("mapOfClassStudents", mapOfClassStudents);

			request.getRequestDispatcher("/jsp/AdminDashboard.jsp").forward(request, response);
		}
		else
		{
			response.sendRedirect(request.getContextPath() + "/Login.jsp?message=Please login first !");
		}
	}
}