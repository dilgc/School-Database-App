package views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Instructor;
import model.Name;
import model.PersonBag;
import utils.Utilities;

public class InstructorView {
	private VBox root;
	
	public InstructorView(PersonBag theBag, Stage primaryStage) {
		
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
		
		TextField salaryField = new TextField();
		salaryField.setPromptText("Salary");
		Label salaryLbl = new Label("Salary");
		HBox salaryBox = new HBox(20);
		salaryBox.getChildren().addAll(salaryLbl, salaryField);
		salaryBox.setAlignment(Pos.CENTER);
		
		TextField rankField = new TextField();
		rankField.setPromptText("Rank");
		Label rankLbl = new Label("Rank");
		HBox rankBox = new HBox(20);
		rankBox.getChildren().addAll(rankLbl, rankField);
		rankBox.setAlignment(Pos.CENTER);



		HBox infoBox = new HBox(50);
		infoBox.setAlignment(Pos.CENTER);
		infoBox.getChildren().addAll(firstNameBox, lastNameBox, salaryBox, rankBox);

		TextArea showArea = new TextArea();

		HBox showBox = new HBox(10);
		showBox.setAlignment(Pos.CENTER);
		showBox.getChildren().add(showArea);

		Button insertBtn = new Button("Insert");
		insertBtn.setMaxSize(100, 50);
		insertBtn.setPrefSize(100, 50);

		insertBtn.setOnAction(e -> {
			Name instructorName = new Name(firstNameField.getText(), lastNameField.getText());
			Instructor instructor = new Instructor(instructorName, rankField.getText(), Double.valueOf(salaryField.getText()));
			theBag.insert(instructor);
			firstNameField.clear();
			lastNameField.clear();
			salaryField.clear();
			rankField.clear();
			System.out.println(theBag.display());

			Utilities.backup(theBag);
		});
		
		Button searchBtn = new Button("Search");
		searchBtn.setMaxSize(100, 50);
		searchBtn.setPrefSize(100, 50);
		
		searchBtn.setOnAction(e -> {
			String id = idField.getText();
			Instructor instructorFound = (Instructor)theBag.searchById(id);
			firstNameField.setText(instructorFound.getName().getFirstName());
			lastNameField.setText(instructorFound.getName().getLastName());
			salaryField.setText(String.valueOf(instructorFound.getSalary()));
			rankField.setText(instructorFound.getRank());
			showArea.clear();
			showArea.appendText(instructorFound.toString());
		});

		Button cancelBtn = new Button("Cancel");
		cancelBtn.setMaxSize(100, 50);
		cancelBtn.setPrefSize(100, 50);

		cancelBtn.setOnAction(e -> {
			firstNameField.clear();
			lastNameField.clear();
			salaryField.clear();
			rankField.clear();
			idField.clear();
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
			Instructor updating = (Instructor)theBag.searchById(idField.getText());
			updating.setSalary(Double.valueOf(salaryField.getText()));
			updating.setRank(rankField.getText());
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
