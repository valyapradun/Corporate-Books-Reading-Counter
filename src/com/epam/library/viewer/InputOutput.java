package com.epam.library.viewer;

import java.util.List;
import java.util.Scanner;

import com.epam.library.domain.Book;

public class InputOutput {
	
	public InputOutput(){
		
	}
	
	public void printAllBook(List<Book> books) {
		if (books != null) {
			StringBuilder builder = new StringBuilder();
			for (Book book: books){
				builder.append("book ");
				builder.append(book.toString());
				builder.append("\n");
			}
			System.out.println(builder);
		}
	}
	
	public int inputId(){
		int result = -1;
		System.out.print("Input ID of the book: ");
		Scanner sc = new Scanner(System.in);
		if (sc.hasNextInt()){
			result = sc.nextInt();
		} else {
			System.out.println("Wrong ID!");
		}
		return result;
	}
	
	public void printBook(Book book){
		if (book != null) {
			System.out.println(book);
		}
	}
	
	public Book inputBook(){
		Book book = new Book();
		Scanner sc = new Scanner(System.in);
		book.setId(-1);
		System.out.print("Input title of the book and press Enter: ");
		book.setTitle(sc.nextLine());
		System.out.print("Input author of the book and press Enter: ");
		book.setAuthor(sc.nextLine());
		System.out.print("Input year publishing of the book and press Enter: ");
		book.setYearPublishing(sc.nextInt());
		System.out.println("");
		return book;
	}
	
	public void answerOperation (boolean result) {
		if (result == false) {
			System.out.println("Operation isn't successful!");
		} else {
			System.out.println("Operation is successful!");
		}
	}
	
	public String inputTitle(){
		String title = "";
		String input = null;
		System.out.print("Input title of the book and press Enter: ");
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine();
		for (int i = 0; i < input.length(); i++){
			char ch = input.charAt(i);
			if (ch != '*') {
				title = title + ch;	
			} else {
				break;
			}
		}
		return title;
	}
	
	public void printEmployeeArray(List<String> employees) {
		if (employees != null) {
			if (employees.size() == 0) {
				System.out.println("There are not employees!");
			}
			for (int i = 0; i < employees.size(); i++){
				System.out.println(employees.get(i));
			}

		} 
	}
	
}
