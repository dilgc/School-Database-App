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
import model.TextbookBag;
import model.Textbook;
import utils.Utilities;

public class TextbookView {
	private VBox root;
	
	public TextbookView(TextbookBag theBag, Stage primaryStage) {
		
		TextField isbnField = new TextField();
		Label isbnLbl = new Label("ISBN");
		HBox isbnBox = new HBox(30);
		isbnBox.setAlignment(Pos.CENTER);
		isbnBox.getChildren().addAll(isbnLbl, isbnField);

		TextField authorFirstNameField = new TextField();
		authorFirstNameField.setPromptText("Author's First Name");
		Label authorFirstNameLbl = new Label("Author's First Name:");
		HBox authorFirstNameBox = new HBox(20);
		authorFirstNameBox.getChildren().addAll(authorFirstNameLbl, authorFirstNameField);
		authorFirstNameBox.setAlignment(Pos.CENTER);

		TextField authorLastNameField = new TextField();
		authorLastNameField.setPromptText("Author's Last Name");
		Label authorLastNameLbl = new Label("Author's Last Name:");
		HBox authorLastNameBox = new HBox(20);
		authorLastNameBox.getChildren().addAll(authorLastNameLbl, authorLastNameField);
		authorLastNameBox.setAlignment(Pos.CENTER);
		
		TextField priceField = new TextField();
		priceField.setPromptText("Price");
		Label priceLbl = new Label("Price");
		HBox priceBox = new HBox(20);
		priceBox.getChildren().addAll(priceLbl, priceField);
		priceBox.setAlignment(Pos.CENTER);
		
		TextField titleField = new TextField();
		titleField.setPromptText("Title");
		Label titleLbl = new Label("Title");
		HBox titleBox = new HBox(20);
		titleBox.getChildren().addAll(titleLbl, titleField);
		titleBox.setAlignment(Pos.CENTER);



		HBox infoBox = new HBox(50);
		infoBox.setAlignment(Pos.CENTER);
		infoBox.getChildren().addAll(authorFirstNameBox, authorLastNameBox, priceBox, titleBox);

		TextArea showArea = new TextArea();

		HBox showBox = new HBox(10);
		showBox.setAlignment(Pos.CENTER);
		showBox.getChildren().add(showArea);

		Button insertBtn = new Button("Insert");
		insertBtn.setMaxSize(100, 50);
		insertBtn.setPrefSize(100, 50);

		insertBtn.setOnAction(e -> {
			Name authorName = new Name(authorFirstNameField.getText(), authorLastNameField.getText());
			Textbook book = new Textbook(authorName, isbnField.getText(), titleField.getText(), Double.valueOf(priceField.getText()));
			theBag.insert(book);
			authorFirstNameField.clear();
			authorLastNameField.clear();
			priceField.clear();
			titleField.clear();
			System.out.println(theBag.display());

			Utilities.backup(theBag);
		});
		
		Button searchBtn = new Button("Search");
		searchBtn.setMaxSize(100, 50);
		searchBtn.setPrefSize(100, 50);
		
		searchBtn.setOnAction(e -> {
			String isbn = isbnField.getText();
			Textbook textbookFound = (Textbook)theBag.sequentialSearchByIsbn(isbn);
			authorFirstNameField.setText(textbookFound.getAuthor().getFirstName());
			authorLastNameField.setText(textbookFound.getAuthor().getLastName());
			priceField.setText(String.valueOf(textbookFound.getPrice()));
			titleField.setText(textbookFound.getTitle());
			showArea.clear();
			showArea.appendText(textbookFound.toString());
		});

		Button cancelBtn = new Button("Cancel");
		cancelBtn.setMaxSize(100, 50);
		cancelBtn.setPrefSize(100, 50);

		cancelBtn.setOnAction(e -> {
			authorFirstNameField.clear();
			authorLastNameField.clear();
			priceField.clear();
			titleField.clear();
			isbnField.clear();
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
			theBag.deleteByIsbn(isbnField.getText());
			showArea.clear();
			
			Utilities.backup(theBag);
		});
		
		Button updateBtn = new Button("Update");
		updateBtn.setMaxSize(100, 50);
		updateBtn.setPrefSize(100, 50);

		updateBtn.setOnAction(e -> {
			Textbook updating = (Textbook)theBag.sequentialSearchByIsbn(isbnField.getText());
			updating.setPrice(Double.valueOf(priceField.getText()));
			updating.setTitle(titleField.getText());
			updating.getAuthor().setFirstName(authorFirstNameField.getText());
			updating.getAuthor().setLastName(authorLastNameField.getText());
			
			Utilities.backup(theBag);
		});

		HBox btnBox = new HBox(50);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(insertBtn, searchBtn, cancelBtn, showBtn, removeBtn, updateBtn);

		
		root = new VBox(10); 
		root.setAlignment(Pos.CENTER);
		
		root.getChildren().add(isbnBox);
		root.getChildren().add(infoBox);
		root.getChildren().add(showBox);
		root.getChildren().add(btnBox);
		
	}

	public VBox getRoot() {
		return root;
	}
}
