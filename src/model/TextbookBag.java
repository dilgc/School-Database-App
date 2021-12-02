package model;

import java.io.Serializable;
import java.util.ArrayList;

public class TextbookBag implements Serializable {
	private ArrayList<Textbook> bookList;
	
	public TextbookBag() {
		bookList = new ArrayList<>();
	}
	
	public void insert(Textbook textbook) {
		bookList.add(textbook);
	}
	
	public Textbook sequentialSearchByIsbn(String isbn) {
		for(Textbook book : bookList) {
			if(book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		System.out.println("No ISBN found");
		return null;
	}
	
	public boolean deleteByIsbn(String isbn) {
		int i = 0;
		for(Textbook book : bookList) {
			if(book.getIsbn().equals(isbn)) {
				return bookList.remove(book);
			}
		}
		return false;
	}
	
	public ArrayList<Textbook> getBookList(){
		return bookList;
	}
	
	public void display() {
		for(Textbook book : bookList) {
			System.out.println(book);
		}
		System.out.println();
	}
}
