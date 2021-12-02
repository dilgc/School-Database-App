package utils;

import java.io.File;
import java.io.FileInputStream;

import model.Instructor;
import model.Name;
import model.PersonBag;
import model.Student;
import model.Textbook;
import model.TextbookBag;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Utilities {

	private static ArrayList<String> isbnList;
	private static ArrayList<String> titleList;
	private static ArrayList<String> majorList;
	private static ArrayList<String> ranksList;
	private static ArrayList<String> firstNameList;
	private static ArrayList<String> lastNameList;

	public static void importBooks(TextbookBag textbookBag, String firstNameFileName, String lastNameFileName,
			String titleFileName, String isbnFileName) {
		String titlesAndIsbns[][] = Utilities.emitTitleAndIsbn(titleFileName, isbnFileName);
		for (int i = 0; i < titlesAndIsbns[0].length; i++) {
			Name name = Utilities.emitName(firstNameFileName, lastNameFileName);
			String title = titlesAndIsbns[0][i];
			String isbn = titlesAndIsbns[1][i];
			double price = Utilities.emitPrice();
			textbookBag.insert(new Textbook(name, isbn, title, price));
		}
	}

	public static void importStudents(PersonBag personBag, String firstNameFileName, String lastNameFileName,
			String majorFileName) {
		int numOfStudents = 1000;
		for (int i = 0; i < numOfStudents; i++) {
			Name name = Utilities.emitName(firstNameFileName, lastNameFileName);
			double gpa = Math.random() * 4;
			String major = majorList.get((int) (Math.random() * majorList.size()));
			personBag.insert(new Student(name, gpa, major));
		}
	}

	public static void importInstructors(PersonBag personBag, String firstNameFileName, String lastNameFileName,
			String rankFileName) {
		int numOfProfessors = 500;
		for (int i = 0; i < numOfProfessors; i++) {
			Name name = Utilities.emitName(firstNameFileName, lastNameFileName);
			double salary = 10000 + Math.random() * 90000;
			String rank = ranksList.get((int) (Math.random() * ranksList.size()));
			personBag.insert(new Instructor(name, rank, salary));
		}
	}

	public static double emitPrice() {
		return Math.random() * 200;
	}

	public static Name emitName(String firstNameFile, String lastNameFile) {
		return getRandomName(firstNameList, lastNameList);

	}

	private static ArrayList<String> convertToList(String fileName) {

		ArrayList<String> list = new ArrayList<>();
		File file = new File(fileName);

		try {
			Scanner scanner = new Scanner(file, "UTF-8");
			while (scanner.hasNextLine()) {
				list.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(list.size()==1) {
			list = new ArrayList<>(Arrays.asList(list.get(0).split(", ")));
		}
		return list;
	}

	private static Name getRandomName(ArrayList<String> firstNameList, ArrayList<String> lastNameList) {
		String firstName = firstNameList.get((int) (Math.random() * firstNameList.size()));
		String lastName = lastNameList.get((int) (Math.random() * lastNameList.size()));
		return new Name(firstName, lastName);
	}

	public static String[][] emitTitleAndIsbn(String titleFileName, String isbnFileName) {
		String[][] titleAndIsbn = new String[2][isbnList.size()];
		for (int i = 0; i < isbnList.size(); i++) {
			titleAndIsbn[0][i] = titleList.get(i);
			titleAndIsbn[1][i] = isbnList.get(i);
		}
		return titleAndIsbn;
	}

	public static void backup(TextbookBag bookBag) { //creates a file to back up the data on textbookbag
		try {
			FileOutputStream fis = new FileOutputStream("data/bookBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(bookBag);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static TextbookBag restoreTextbookBag() { //searches for the existing file and returns the bag if it exists
		ObjectInputStream ois = null;
		try {
			File file =  new File("data/bookBag.dat");
			if(!file.exists()) {
				return null;
			}
			FileInputStream fis = new FileInputStream("data/bookBag.dat");
			ois = new ObjectInputStream(fis);
			return(TextbookBag)ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(ois!=null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void backup(PersonBag personBag) { //creates a file to back up the data on personBag
		try {
			FileOutputStream fis = new FileOutputStream("data/personBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fis);
			oos.writeObject(personBag);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static PersonBag restorePersonBag() { //searches for the existing file and returns the bag if it exists
		ObjectInputStream ois = null;
		try {
			File file =  new File("data/personBag.dat");
			if(!file.exists()) {
				return null;
			}
			FileInputStream fis = new FileInputStream("data/personBag.dat");
			ois = new ObjectInputStream(fis);
			return(PersonBag)ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(ois!=null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void createLists(String isbnFileName, String titleFileName, String majorFileName, String rankFileName, String firstNameFile, String lastNameFile) {
		isbnList = Utilities.convertToList(isbnFileName);
		titleList = Utilities.convertToList(titleFileName);
		majorList = convertToList(majorFileName);
		ranksList = convertToList(rankFileName);
		firstNameList = convertToList(firstNameFile);
		lastNameList = convertToList(lastNameFile);
	}
}
