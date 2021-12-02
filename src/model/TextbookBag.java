package model;

import java.io.Serializable;
import java.util.ArrayList;

public class TextbookBag implements Serializable {
	private Textbook[] bookArr;
	private int nElems;
	
	public TextbookBag(int maxSize) {
		bookArr = new Textbook[maxSize];
	}
	
	public void insert(Textbook textbook) {
		bookArr[nElems++] = textbook;
	}
	
	public Textbook sequentialSearchByIsbn(String isbn) {
		for(Textbook book : bookArr) {
			if(book.getIsbn().equals(isbn)) {
				return book;
			}
		}
		System.out.println("No ISBN found");
		return null;
	}
	
	public Textbook deleteByIsbn(String isbn) {
		int searched;
		for(searched = 0; searched < nElems; searched++) {
			if(bookArr[searched].getIsbn().equals(isbn)) {
				break;
			}
		}
		if(searched==nElems) {
			System.out.println("Not found");
			return null;
		} else {
			Textbook temp = bookArr[searched];
			for(int j = searched; j<nElems - 1; j++) {
				bookArr[j] = bookArr[j+1];
			}
			nElems--;
			return temp;
		}
	}
	
	public Textbook[] getBookList(){
		return bookArr;
	}
	
	public String display() {
		String str = "";
		for(int i = 0; i < nElems; i++) {
			str += bookArr[i] + "\n";
			System.out.println(i);
		}
		return str;
	}
}
