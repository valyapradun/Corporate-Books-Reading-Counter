package com.epam.library.dao;

public class ExceptionDao extends Exception{

	private static final long serialVersionUID = 2493306118735616748L;


	public ExceptionDao() {
		super();
	}

	public ExceptionDao(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ExceptionDao(String arg0) {
		super(arg0);
	}

	public ExceptionDao(Throwable arg0) {
		super(arg0);
	}

}
