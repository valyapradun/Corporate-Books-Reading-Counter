package com.epam.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.domain.Book;


public class BookDaoImpl implements BookDao{
	
	private static final String SELECTALL = "SELECT ID, AUTHOR, TITLE, PUBLISH_YEAR FROM book";
	private static final String SELECTBYID = "SELECT ID, AUTHOR, TITLE, PUBLISH_YEAR FROM book WHERE ID = ?";
	private static final String CREATE = "INSERT INTO book (TITLE, PUBLISH_YEAR, AUTHOR) VALUES (?, ?, ?)";
	private static final String DELETEBYID = "DELETE FROM book WHERE ID = ?";
	private static final String SELECTBYTITLE = "SELECT ID, AUTHOR, TITLE, PUBLISH_YEAR FROM book WHERE TITLE LIKE ?";
	private static final String RENAMEBYTITLE = "UPDATE book SET TITLE = ? WHERE TITLE LIKE ? and ID > 0";
	
	
	

	@Override
	public boolean create(Book book) throws ExceptionDao {
		boolean resultCreate = false;
		if (book != null) {
			Connection connection = null;
			PreparedStatement pStatement = null;
			try {
				connection = DriverManager.getConnection(DRIVER, LOGIN, PASS);
				pStatement = connection.prepareStatement(CREATE);
				pStatement.setString(1, book.getTitle());
				pStatement.setInt(2, book.getYearPublishing());
				pStatement.setString(3, book.getAuthor());
				int result = pStatement.executeUpdate();
				if (result != 0) {
					resultCreate = true;
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
		}	
		return resultCreate;
	}

	@Override
	public Book fetchById(int id) throws ExceptionDao {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet result = null;
		Book book = new Book();
		if (id > 0) {
			try {
				connection = DriverManager.getConnection(DRIVER, LOGIN, PASS);
				pStatement = connection.prepareStatement(SELECTBYID);
				pStatement.setInt(1, id);	
				result = pStatement.executeQuery();
				if (result.next()) {
					String author = result.getString(AUTHOR);
					String title = result.getString(TITLE);
					int year = result.getInt(YEAR);
					id = result.getInt(ID);
					book = new Book(id, title, author, year);
				} else {
					throw new ExceptionDao ("Book not found!");
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
		}
		return book;
	}

	@Override
	public List<Book> fetchAll() throws ExceptionDao {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		List<Book> books = new ArrayList<Book>();
		
		try {
			connection = DriverManager.getConnection(DRIVER, LOGIN, PASS);
			statement = connection.createStatement();
			result = statement.executeQuery(SELECTALL);
			while(result.next()){
				String author = result.getString(AUTHOR);
				String title = result.getString(TITLE);
				int year = result.getInt(YEAR);
				int id = result.getInt(ID);
				Book book = new Book(id, title, author, year);
				books.add(book);
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
		return books;
	}

	@Override
	public boolean delete(int id) throws ExceptionDao {
		boolean resultDelete = false;
		Connection connection = null;
		PreparedStatement pStatement = null;
		if (id > 0) {
			try {
				connection = DriverManager.getConnection(DRIVER, LOGIN, PASS);
				pStatement = connection.prepareStatement(DELETEBYID);
				pStatement.setInt(1, id);	
				int result = pStatement.executeUpdate();
				if (result != 0) {
					resultDelete = true;
				} else {
					throw new ExceptionDao ("Book not found!");
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
		}
		return resultDelete;
	}

	@Override
	public List<Book> getByTitle(String title) throws ExceptionDao {
		Connection connection = null;
		PreparedStatement pStatement = null;
		ResultSet result = null;
		List<Book> books = new ArrayList<Book>();
		if (title != null) {
			try {
				connection = DriverManager.getConnection(DRIVER, LOGIN, PASS);
				pStatement = connection.prepareStatement(SELECTBYTITLE);
				pStatement.setString(1, title + "%");	
				result = pStatement.executeQuery();
				while(result.next()){
					String author = result.getString(AUTHOR);
					title = result.getString(TITLE);
					int year = result.getInt(YEAR);
					int id = result.getInt(ID);
					Book book = new Book(id, title, author, year);
					books.add(book);
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
		}
		return books;
	}

	@Override
	public boolean updateBookByTitle(String oldtitle, String newtitle) throws ExceptionDao {
		boolean resultUpdate = false;
		Connection connection = null;
		PreparedStatement pStatement = null;
		if ((oldtitle != null) || (newtitle != null)){
			try {
				connection = DriverManager.getConnection(DRIVER, LOGIN, PASS);
				pStatement = connection.prepareStatement(RENAMEBYTITLE);
				pStatement.setString(1, newtitle);
				pStatement.setString(2, oldtitle + "%");	
				int result = pStatement.executeUpdate();
				if (result != 0) {
					resultUpdate = true;
				} else {
					throw new ExceptionDao ("Books not found!");
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
		}
		return resultUpdate;
	}

}
