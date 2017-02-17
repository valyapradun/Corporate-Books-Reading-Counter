package com.epam.library.service;

import java.util.List;

import com.epam.library.domain.Book;


public interface BookService {
	public List<Book> viewAllBook();
	public Book viewBookById(int id);
	public boolean addBook(Book book);
	public boolean deleteBook(int id);
	public List<Book> viewBookByTitle(String title);
	public boolean updateBookByTitle(String oldTitle, String newTitle);
	public boolean fillTableEmployeeBook();
	public List<String> viewEmployee(int operation);
}
