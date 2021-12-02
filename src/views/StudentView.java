package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Name;
import model.PersonBag;
import model.Student;
import utils.Utilities;

public class StudentView {
	private VBox root;
	
	public StudentView(PersonBag theBag, Stage primaryStage) {
		
		TextField idField = new TextField();
		Label idLbl = new Label("ID");
		HBox idBox = new HBox(30);
		idBox.setAlignment(Pos.CENTER);
		idBox.getChildren().addAll(idLbl, idField);

		TextField firstNameField = new TextField();
		firstNameField.setPromptText("First Name");
		Label firstNameLbl = new Label("First Name:");
		HBox firstNameBox = new HBox(20);
		firstNameBox.getChildren().addAll(firstNameLbl, firstNameField);
		firstNameBox.setAlignment(Pos.CENTER);

		TextField lastNameField = new TextField();
		lastNameField.setPromptText("Last Name");
		Label lastNameLbl = new Label("Last Name:");
		HBox lastNameBox = new HBox(20);
		lastNameBox.getChildren().addAll(lastNameLbl, lastNameField);
		lastNameBox.setAlignment(Pos.CENTER);
		
		TextField gpaField = new TextField();
		gpaField.setPromptText("GPA");
		Label gpaLbl = new Label("GPA");
		HBox gpaBox = new HBox(20);
		gpaBox.getChildren().addAll(gpaLbl, gpaField);
		gpaBox.setAlignment(Pos.CENTER);
		
		TextField majorField = new TextField();
		majorField.setPromptText("Major");
		Label majorLbl = new Label("Major");
		HBox majorBox = new HBox(20);
		majorBox.getChildren().addAll(majorLbl, majorField);
		majorBox.setAlignment(Pos.CENTER);



		HBox infoBox = new HBox(50);
		infoBox.setAlignment(Pos.CENTER);
		infoBox.getChildren().addAll(firstNameBox, lastNameBox, gpaBox, majorBox);

		TextArea showArea = new TextArea();

		HBox showBox = new HBox(10);
		showBox.setAlignment(Pos.CENTER);
		showBox.getChildren().add(showArea);

		Button insertBtn = new Button("Insert");
		insertBtn.setMaxSize(100, 50);
		insertBtn.setPrefSize(100, 50);

		insertBtn.setOnAction(e -> {
			Name studentName = new Name(firstNameField.getText(), lastNameField.getText());
			Student student = new Student(studentName, Double.valueOf(gpaField.getText()), majorField.getText());
			theBag.insert(student);
			firstNameField.clear();
			lastNameField.clear();
			gpaField.clear();
			majorField.clear();
			System.out.println(theBag.display());

			Utilities.backup(theBag);
		});
		
		Button searchBtn = new Button("Search");
		searchBtn.setMaxSize(100, 50);
		searchBtn.setPrefSize(100, 50);
		
		searchBtn.setOnAction(e -> {
			String id = idField.getText();
			Student studentFound = (Student)theBag.searchById(id);
			firstNameField.setText(studentFound.getName().getFirstName());
			lastNameField.setText(studentFound.getName().getLastName());
			gpaField.setText(String.valueOf(studentFound.getGpa()));
			majorField.setText(studentFound.getMajor());
			showArea.clear();
			showArea.appendText(studentFound.toString());
		});

		Button cancelBtn = new Button("Cancel");
		cancelBtn.setMaxSize(100, 50);
		cancelBtn.setPrefSize(100, 50);

		cancelBtn.setOnAction(e -> {
			firstNameField.clear();
			lastNameField.clear();
			gpaField.clear();
			majorField.clear();
		});

		Button showBtn = new Button("Show");
		showBtn.setMaxSize(100, 50);
		showBtn.setPrefSize(100, 50);

		showBtn.setOnAction(e -> {
			showArea.clear();
			showArea.appendText(theBag.display());
		});
		
		Button removeBtn = new Button("Remove");
		removeBtn.setMaxSize(100, 50);
		removeBtn.setPrefSize(100, 50);

		removeBtn.setOnAction(e -> {
			theBag.deleteById(idField.getText());
			showArea.clear();
			
			Utilities.backup(theBag);
		});
		
		Button updateBtn = new Button("Update");
		updateBtn.setMaxSize(100, 50);
		updateBtn.setPrefSize(100, 50);

		updateBtn.setOnAction(e -> {
			Student updating = (Student)theBag.searchById(idField.getText());
			updating.setGpa(Double.valueOf(gpaField.getText()));
			updating.setMajor(majorField.getText());
			updating.setName(new Name(firstNameField.getText(), lastNameField.getText()));
			
			Utilities.backup(theBag);
		});

		HBox btnBox = new HBox(50);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(insertBtn, searchBtn, cancelBtn, showBtn, removeBtn, updateBtn);

		
		root = new VBox(10); 
		root.setAlignment(Pos.CENTER);
		
		root.getChildren().add(idBox);
		root.getChildren().add(infoBox);
		root.getChildren().add(showBox);
		root.getChildren().add(btnBox);
		
	}

	public VBox getRoot() {
		return root;
	}
}
