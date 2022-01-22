package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.constants.Database;

public class DataBaseDAO
{
	public static boolean validateUser(String userName, String password)
	{
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		boolean flag = false;

		try
		{
			Class.forName(Database.DB_CLASS_NAME);
			con = DriverManager.getConnection(Database.DB_URL, Database.DB_USER, Database.DB_PASS);

			pStmt = con.prepareStatement(Queries.USERS_SQL);
			pStmt.setString(1, userName);
			pStmt.setString(2, password);

			rs = pStmt.executeQuery();

			while (rs.next())
			{
				int val = rs.getInt(1);
				if (val > 0)
				{
					flag = true;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (con != null)
			{
				try
				{
					con.close();
					pStmt.close();
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return flag;
	}
	
	public static Map<String, String> getData(String sql)
	{
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		Map<String, String> collection = new TreeMap<String, String>();
		
		try
		{
			Class.forName(Database.DB_CLASS_NAME);
			con = DriverManager.getConnection(Database.DB_URL, Database.DB_USER, Database.DB_PASS);

			pStmt = con.prepareStatement(sql);
			
			rs = pStmt.executeQuery();

			while (rs.next())
			{
				collection.put(rs.getString(1), rs.getString(2));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (con != null)
			{
				try
				{
					con.close();
					pStmt.close();
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return collection;
	}
	
	public static boolean updateStudentData(Map<String, String> studentAndClassMap)
	{
		Connection con = null;
		PreparedStatement pStmt = null;
		boolean flag = false;
				
		try
		{
			Class.forName(Database.DB_CLASS_NAME);
			con = DriverManager.getConnection(Database.DB_URL, Database.DB_USER, Database.DB_PASS);

			pStmt = con.prepareStatement(Queries.UPDATE_STUDENTS);
			
			for(Map.Entry<String, String> entry :studentAndClassMap.entrySet())
			{
				pStmt.setString(1, entry.getValue());
				pStmt.setString(2, entry.getKey());				
				pStmt.addBatch();
			}					
			
			pStmt.executeBatch();
			
			flag = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (con != null)
			{
				try
				{
					con.close();
					pStmt.close();				
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return flag;
	}
	
	
	
	public static boolean insertClassTeacherAndSubjectData(String className, String teacher, String subjects)
	{
		Connection con = null;
		PreparedStatement pStmt = null;		
		boolean flag = false;
				
		try
		{
			Class.forName(Database.DB_CLASS_NAME);
			con = DriverManager.getConnection(Database.DB_URL, Database.DB_USER, Database.DB_PASS);

			pStmt = con.prepareStatement(Queries.CLASS_TEACH_SUB);
			
			pStmt.setString(1, className);
			pStmt.setString(2, teacher);
			pStmt.setString(3, subjects);		
			
			pStmt.execute();
			
			flag = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (con != null)
			{
				try
				{
					con.close();
					pStmt.close();					
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return flag;
	}
	
	
	public static TreeMap<String, ArrayList<String>> getClassTeachSubData()
	{
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		TreeMap<String, ArrayList<String>> collection = new TreeMap<String, ArrayList<String>>();
		ArrayList<String> data = null;
		
		try
		{
			Class.forName(Database.DB_CLASS_NAME);
			con = DriverManager.getConnection(Database.DB_URL, Database.DB_USER, Database.DB_PASS);

			pStmt = con.prepareStatement(Queries.GET_CLASS_TEACH_SUB);
			
			rs = pStmt.executeQuery();

			while (rs.next())
			{
				data = new ArrayList<String>();
				
				data.add(0, rs.getString(2));
				data.add(1, rs.getString(3));
				data.add(2, rs.getString(4));
				
				collection.put(rs.getString(1), data);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (con != null)
			{
				try
				{
					con.close();
					pStmt.close();
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return collection;
	}
	
	public static TreeMap<String, String> getClassAndStudData()
	{
		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		TreeMap<String, String> collection = new TreeMap<String, String>();
		StringBuilder sBldr = null;
				
		try
		{
			Class.forName(Database.DB_CLASS_NAME);
			con = DriverManager.getConnection(Database.DB_URL, Database.DB_USER, Database.DB_PASS);

			pStmt = con.prepareStatement(Queries.GET_CLASS_STUD);
			
			rs = pStmt.executeQuery();

			while (rs.next())
			{
				if(collection.containsKey(rs.getString(1)))
				{
					sBldr = new StringBuilder();
					sBldr = sBldr.append(collection.get(rs.getString(1)));
					sBldr.append(",");
					sBldr.append(rs.getString(2));					
					collection.put(rs.getString(1), sBldr.toString());					
				}
				else
				{
					collection.put(rs.getString(1), rs.getString(2));
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (con != null)
			{
				try
				{
					con.close();
					pStmt.close();
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return collection;
	}
	
	
	public static boolean addMasterData(String sql, String name)
	{
		Connection con = null;
		PreparedStatement pStmt = null;		
		boolean flag = false;
				
		try
		{
			Class.forName(Database.DB_CLASS_NAME);
			con = DriverManager.getConnection(Database.DB_URL, Database.DB_USER, Database.DB_PASS);

			pStmt = con.prepareStatement(sql);
			
			pStmt.setString(1, name);
						
			pStmt.execute();
			
			flag = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (con != null)
			{
				try
				{
					con.close();
					pStmt.close();					
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		return flag;
	}
}
