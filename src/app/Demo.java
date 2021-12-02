package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PersonBag;
import model.TextbookBag;
import utils.Utilities;
import views.StudentView;

public class Demo extends Application {

	public static void main(String[] args) {
		launch(args);
		
////		double price1 = Utilities.emitPrice();
////		System.out.println(price1);
//		TextbookBag bookBag = new TextbookBag();
//		
//		Utilities.importBooks(bookBag, "First Names.txt", "Last Names.txt", "textbook_titles.txt", "textbook_isbns.txt");
//
//		
////		Name name = new Name("Test", "A");
////		Name name1 = new Name("Teste", "B");
////		Name name2 = new Name("Testee", "C");
////		Name name3 = new Name("Testeee", "D");
////		Name name4 = new Name("Testeeee", "E");
////		
////		Student student1 = new Student(name, 4.0, "major1");
////		Student student2 = new Student(name1, 3.0, "major2");
////		Student student3 = new Student(name2, 2.0, "major3");
////		Instructor instructor1 = new Instructor(name3, "rank 1", 100000.00);
////		Instructor instructor2 = new Instructor(name4, "rank 2", 90000.00);
////		
////		PersonBag personBag = new PersonBag(5);
////		personBag.insert(student1);
////		personBag.insert(student2);
////		personBag.insert(student3);
////		personBag.insert(instructor1);
////		personBag.insert(instructor2);
//		PersonBag personBag = new PersonBag(1500);
//		Utilities.importStudents(personBag, "data/First Names.txt", "data/Last Names.txt", "data/Majors.txt");
//		Utilities.importInstructors(personBag, "data/First Names.txt", "data/Last Names.txt", "data/Ranks.txt");
//		
//		System.out.println(personBag.display());
//		System.out.println("Success");
////		Person searched = personBag.searchById("0");
////		Person searched2 = personBag.searchById("1");
////		Person searched3 = personBag.searchById("2");
////		Person searched4 = personBag.searchById("3");
////		System.out.println();
////		
////		System.out.println(searched.getName().getFirstName());
////		System.out.println(searched2.getName().getFirstName());
////		System.out.println(searched3.getName().getFirstName());
////		System.out.println(searched4.getName().getFirstName());
////		
////		System.out.println("removing #2");
////		personBag.deleteById("1");
////		personBag.display();
////		
////		System.out.println();
////		System.out.println("Testing book things");
////		System.out.println();
////		
////		System.out.println("Removing isbn 978-0-12-379370-6");
////		System.out.println(bookBag.sequentialSearchByIsbn("978-0-12-379370-6"));
////		System.out.println();
////		bookBag.deleteByIsbn("978-0-12-379370-6");
////		System.out.println(bookBag.getBookList());
		
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Utilities.createLists("data/textbook_isbns.txt", "data/textbook_titles.txt", "data/Majors.txt", "data/Ranks.txt", "data/First Names.txt", "data/Last Names.txt");

		PersonBag theBag = Utilities.restorePersonBag();
		if(theBag==null) {
			theBag = new PersonBag(2000);
			Utilities.importStudents(theBag, "data/First Names.txt", "data/Last Names.txt", "data/Majors.txt");
			Utilities.importInstructors(theBag, "data/First Names.txt", "data/Last Names.txt", "data/Ranks.txt");
		}
		
		VBox root = new StudentView(theBag, primaryStage).getRoot();
		
		Scene scene = new Scene(root, 1000, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	

}
