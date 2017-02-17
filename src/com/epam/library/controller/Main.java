package com.epam.library.controller;

import java.util.Scanner;

import com.epam.library.service.BookService;
import com.epam.library.service.BookServiceImpl;

public class Main {
	
	private static final int EXIT = 10;

	public static void main(String[] args) {
		welcomScreen();
		BookService service = new BookServiceImpl();
		inputNumberMenu(service);	
		
	}
		
	public static void welcomScreen(){
		System.out.println("Your can do it: ");
		System.out.println("1 - To view all books");
		System.out.println("2 - To view one of the book (input id)");
		System.out.println("3 - To add the book (input title, athor, year publishing: for example, 'Sbornik', 'Lermontov M.U.', 2013)");
		System.out.println("4 - To delete the book (input id)");
		System.out.println("5 - To view books (input title, you can use 'input mask', for example, 'Sborn*')");
		System.out.println("6 - To rename the book (input old title, new title and press Enter)");	
		System.out.println("7 - To fill table 'employee_book' randomly generated values (user_id and book_id)");
		System.out.println("8 - To view employees who have read more than 1 book");
		System.out.println("9 - To view employees who have read less than or equal to 2 books");
		System.out.println("10 - Exit");
		System.out.print("Yours choise: ");
	}
		
		public static void inputNumberMenu(BookService service){
			Scanner sc = new Scanner(System.in);
			int numberOperation = sc.nextInt();
			if (numberOperation == EXIT) {
				System.out.println("Good bye!");
			} else {
				Menu menu = new Menu();
				menu.choiseMenu(numberOperation, service);
				welcomScreen();
				inputNumberMenu(service);
			}
			if (sc != null) {
			sc.close();
			}
		}
}
	
	
	
	
	
	
	


