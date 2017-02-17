package com.epam.library.service;

import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.BookDao;
import com.epam.library.dao.BookDaoImpl;
import com.epam.library.dao.EmployeeBookDao;
import com.epam.library.dao.ExceptionDao;
import com.epam.library.domain.Book;

public class BookServiceImpl implements BookService{
	
	private BookDao dao = new BookDaoImpl();
	

	@Override
	public List<Book> viewAllBook() {
		List<Book> books = null;
		try {
			books = dao.getAll();	
		} catch (ExceptionDao e) {
			System.out.println(e.getMessage());
		}
		return books;
	}


	@Override
	public Book viewBookById(int id) {
		Book book = null;
		if (id > 0){
			try {
				book = dao.getById(id);
			} catch (ExceptionDao e) {
				System.out.println(e.getMessage());
			}
		}
		return book;
	}
	
	public boolean addBook(Book book) {
		boolean result = false;
		if (book != null) {
			try {
				result = dao.create(book);
			} catch (ExceptionDao e) {
				System.out.println(e.getMessage());
			}
		}
        return result;
	}


	@Override
	public boolean deleteBook(int id) {
		boolean result = false;
		if (id > 0){
			try {
				result = dao.delete(id);
			} catch (ExceptionDao e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		return result;
	}
	
	@Override
	public List<Book> viewBookByTitle(String title) {
		List<Book> books = null;
		if (title != null) {
			try {
				books = dao.getByTitle(title);
			} catch (ExceptionDao e) {
				System.out.println(e.getMessage());
			}
		}
		return books;
	}
	
	@Override
	public boolean updateBookByTitle(String oldTitle, String newTitle) {
		boolean result = false;
		if ((oldTitle != null) || (newTitle != null)){
			try {
				result = dao.updateBookByTitle(oldTitle, newTitle);
			} catch (ExceptionDao e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	@Override
	public boolean fillTableEmployeeBook(){
		boolean result = false;
		EmployeeBookDao ebd = new EmployeeBookDao();
		try {
			result = ebd.fillEmployeeBookTable();
		} catch (ExceptionDao e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public List<String> viewEmployee(int operation) {
        EmployeeBookDao ebd = new EmployeeBookDao();
        List<String> employees = new ArrayList<String>();
		try {
			switch (operation){
			case 1: 
				employees = ebd.getEmployeeMoreOneBook();
				break;
			case 2:
				employees = ebd.getEmployeeLessTwoBook();
			}
		} catch (ExceptionDao e) {
			System.out.println(e.getMessage());
		}
		return employees;
	}
	
}
