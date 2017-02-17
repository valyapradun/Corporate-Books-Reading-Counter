package com.epam.library.domain;

public class Book extends Entity {

	private static final long serialVersionUID = 381284536547273180L;
	private String title;
	private String author;
	private int yearPublishing;

	public Book(int id, String title, String author, int yearPublishing) {
		super(id);
		this.title = title;
		this.author = author;
		this.yearPublishing = yearPublishing;
	}
	
	public Book(){
		super();
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYearPublishing() {
		return yearPublishing;
	}

	public void setYearPublishing(int yearPublishing) {
		this.yearPublishing = yearPublishing;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + yearPublishing;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (yearPublishing != other.yearPublishing)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + title + ", " + author + ", " + yearPublishing;
	}
}
