package com.epam.library.dao;

import java.util.List;

import com.epam.library.domain.Book;

public interface BookDao {
	
	public static final String DRIVER = "jdbc:mysql://localhost:3306/library?useSSL=false";
	public static final String LOGIN = "root";
	public static final String PASS = "123456";
	public static final String AUTHOR = "AUTHOR";
	public static final String TITLE = "TITLE";
	public static final String YEAR = "PUBLISH_YEAR";
	public static final String ID = "ID";
	
	
	public boolean create(Book book) throws ExceptionDao;
	public Book getById(int id) throws ExceptionDao;
	public List<Book> getAll() throws ExceptionDao;
	public boolean updateBookByTitle(String oldtitle, String newtitle) throws ExceptionDao;
	public boolean delete(int id) throws ExceptionDao;
	public List<Book> getByTitle(String title) throws ExceptionDao;
	

}
