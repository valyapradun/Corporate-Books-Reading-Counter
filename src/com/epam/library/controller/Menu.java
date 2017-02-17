package com.epam.library.controller;

import com.epam.library.service.BookService;
import com.epam.library.viewer.InputOutput;

public class Menu {
	public Menu (){  
	}

	public void choiseMenu(int number, BookService service){
		InputOutput view = new InputOutput();
		switch (number) {
			case 1: 
				view.printAllBook(service.viewAllBook());
				break;
			case 2: 
				view.printBook(service.viewBookById(view.inputId()));
				break;
			case 3: 
				view.answerOperation(service.addBook(view.inputBook()));
				break;
			case 4: 
				view.answerOperation(service.deleteBook(view.inputId()));
				break;
			case 5: 
				view.printAllBook(service.viewBookByTitle(view.inputTitle()));
				break;
			case 6: 
				view.answerOperation(service.updateBookByTitle(view.inputTitle(), view.inputTitle()));
				break;
			case 7: 
				view.answerOperation(service.fillTableEmployeeBook());
				break;
			case 8: 
				view.printEmployeeArray(service.viewEmployee(1));
				break;
			case 9: 
				view.printEmployeeArray(service.viewEmployee(2));
				break;
			default:
				System.out.println("Good bye!");
				break;
		}
		
	}
}
