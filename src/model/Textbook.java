package model;

import java.io.Serializable;

public class Textbook implements Serializable {
	private Name author;
	private String isbn;
	private String title;
	private Double price;
	public Textbook(Name author, String isbn, String title, Double price) {
		super();
		this.author = author;
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}
	public Name getAuthor() {
		return author;
	}
	public void setAuthor(Name author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Textbook [Author=" + author + ", isbn=" + isbn + ", title=" + title + ", price=" + price + "]";
	}
	
	
}
