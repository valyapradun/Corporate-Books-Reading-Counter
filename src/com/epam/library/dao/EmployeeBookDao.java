package com.epam.library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBookDao {
	
	public static final String DRIVER = "jdbc:mysql://localhost:3306/library?useSSL=false";
	public static final String LOGIN = "root";
	public static final String PASS = "123456";
	
	public static final String ID = "ID";
	
	
	public boolean fillEmployeeBookTable() throws ExceptionDao {
		boolean resultFill = false;
		int numberRowRandom = 0;
		numberRowRandom = (int)(100 + (Math.random() * (300 - 100)));
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DRIVER, LOGIN, PASS);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM employee ORDER BY RAND() LIMIT 1");
			result.next();
			int ignoredEmployeeId = result.getInt(ID);   // one user with no Book relations
			int randomEmployeeId; 
			int randomBookId;
			String  BatchInsertQuery = "INSERT INTO `library`.`employee_book` (`BOOK_ID`, `EMPLOYEE_ID`) VALUES (?, ?)";
			PreparedStatement pStatement = connection.prepareStatement(BatchInsertQuery);
			for (int i = 0; i < numberRowRandom; i++){
				result = statement.executeQuery("SELECT * FROM employee WHERE ID <> "+ ignoredEmployeeId + " ORDER BY RAND() LIMIT 1");
				result.next();
				randomEmployeeId = result.getInt(ID);
				pStatement.setInt(1, randomEmployeeId);
	                
				result = statement.executeQuery("SELECT * FROM book ORDER BY RAND() LIMIT 1");
				result.next();
				randomBookId = result.getInt(ID);
				pStatement.setInt(2, randomBookId);
	             
				pStatement.addBatch();
			}
			statement.executeBatch();
			resultFill = true;
		} catch (SQLException e) {
			throw new ExceptionDao("Library SQL Exception" + e.getMessage() + e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ExceptionDao("Not close connection" + e.getMessage() + e);
				}
			}
		}	
		return resultFill;
	}
	
	
	public List<String> getEmployeeMoreOneBook() throws ExceptionDao {
		List<String> employees = new ArrayList<String>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = DriverManager.getConnection(DRIVER, LOGIN, PASS);
			statement = connection.createStatement();
			String sql = "SELECT employee.NAME, COUNT(employee_book.EMPLOYEE_ID) AS NumberBooks "
                   	+ "FROM employee "
                   	+ "JOIN employee_book ON employee.ID = employee_book.EMPLOYEE_ID "
                   	+ "GROUP BY employee.ID "
                   	+ "ORDER BY NumberBooks";
			result = statement.executeQuery(sql);
			while(result.next()){
				String employee = result.getString("NAME");
				int count = result.getInt("NumberBooks");
				employees.add(employee + "\t" + count);
			} 
		} catch (SQLException e) {
			throw new ExceptionDao("SQL Exception" + e.getMessage() + e);
		} finally{
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ExceptionDao("SQL Exception" + e.getMessage() + e);
				}
			}
		}
		return employees;
	}

	public List<String> getEmployeeLessTwoBook() throws ExceptionDao {
		List<String> employees = new ArrayList<String>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			connection = DriverManager.getConnection(DRIVER, LOGIN, PASS);
			statement = connection.createStatement();
			String sql = "SELECT employee.NAME, employee.DATE_OF_BIRTH, COUNT(employee_book.EMPLOYEE_ID) AS NumberBooks "
					+ "FROM employee "
					+ "JOIN employee_book ON employee.ID = employee_book.EMPLOYEE_ID "
					+ "GROUP BY employee.ID "
					+ "HAVING NumberBooks <= 2 "
					+ "ORDER BY employee.DATE_OF_BIRTH, NumberBooks";
			result = statement.executeQuery(sql);
			while(result.next()){
				String employee = result.getString("NAME");
				Date dateBirth = result.getDate("DATE_OF_BIRTH");
				int count = result.getInt("NumberBooks");
				employees.add(employee + "\t" + dateBirth + "\t" + count);
			}
		} catch (SQLException e) {
			throw new ExceptionDao("SQL Exception" + e.getMessage() + e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new ExceptionDao("SQL Exception" + e.getMessage() + e);
				}
			}
		}
		return employees;
	}
}
