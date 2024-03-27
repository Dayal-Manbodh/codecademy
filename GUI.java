import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;

public class GUI extends Application {

    private Database database = new Database();
    private final ObservableList<Person> data = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {

        // button1.setOnAction((event) -> {
        // String email = TextFieldEmail.getText();
        // String naam = TextFieldNaam.getText();
        // String geboorteDatum = TextFieldGeboortedatum.getText();
        // Integer geslacht = Integer.valueOf(TextFieldGeslacht.getText());
        // String adres = TextFieldAdres.getText();
        // String woonplaats = TextFieldWoonplaats.getText();
        // String land = TextFieldLand.getText();

        // Database.setDataInDatabase(email, naam, geboorteDatum, geslacht, adres,
        // woonplaats, land);

        // });

        // button2.setOnAction((event) -> {
        // String email = TextFieldEmail.getText();
        // Database.setDataFromDatabase(TextFieldEmail, "Email", email);
        // Database.setDataFromDatabase(TextFieldNaam, "Naam", email);
        // Database.setDataFromDatabase(TextFieldGeboortedatum, "GeboorteDatum", email);
        // Database.setDataFromDatabase(TextFieldGeslacht, "Geslacht", email);
        // Database.setDataFromDatabase(TextFieldAdres, "Adres", email);
        // Database.setDataFromDatabase(TextFieldWoonplaats, "Woonplaats", email);
        // Database.setDataFromDatabase(TextFieldLand, "Land", email);

        // });

        // button3.setOnAction((event) -> {
        // String email = TextFieldEmail.getText();
        // String naam = TextFieldNaam.getText();
        // String geboorteDatum = TextFieldGeboortedatum.getText();
        // Integer geslacht = Integer.valueOf(TextFieldGeslacht.getText());
        // String adres = TextFieldAdres.getText();
        // String woonplaats = TextFieldWoonplaats.getText();
        // String land = TextFieldLand.getText();

        // Database.updateDataFromDatabase(email, naam, geboorteDatum, geslacht, adres,
        // woonplaats, land);
        // });

        // button4.setOnAction((event) -> {
        // String email = TextFieldEmail.getText();
        // Database.deleteDataFromDatabase(email);
        // });

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontale ruimte tussen de knoppen
        gridPane.setVgap(10); // Verticale ruimte tussen de knoppen
        gridPane.setPadding(new Insets(10)); // Ruimte rondom de GridPane
        gridPane.setAlignment(Pos.CENTER); // Center alignment

        Button button1 = new Button("Cursisten");
        Button button2 = new Button("Cursussen");
        Button button3 = new Button("Inschrijvingen");
        Button button4 = new Button("Certificaten");

        // Setting preferred size for buttons
        button1.setPrefSize(200, 100); // width, height
        button2.setPrefSize(200, 100);
        button3.setPrefSize(200, 100);
        button4.setPrefSize(200, 100);

        gridPane.add(button1, 0, 0); // Voeg Button 1 toe aan de eerste rij, eerste kolom
        gridPane.add(button2, 1, 0); // Voeg Button 2 toe aan de eerste rij, tweede kolom
        gridPane.add(button3, 0, 1); // Voeg Button 3 toe aan de tweede rij, eerste kolom
        gridPane.add(button4, 1, 1); // Voeg Button 4 toe aan de tweede rij, tweede kolom

        Scene scene = new Scene(gridPane, 600, 400); // Breedte en hoogte van de Scene
        primaryStage.setScene(scene);

        primaryStage.setTitle("Codecademy overzicht");
        primaryStage.show();

        button1.setOnAction((event) -> {
            openCursistStage(primaryStage); // Call method to open new stage
            primaryStage.close();
        });

        button2.setOnAction((event) -> {
            openCursusStage(primaryStage); // Call method to open new stage
            primaryStage.close();
        });

        button3.setOnAction((event) -> {
            openInschrijvingenStage(primaryStage);
            primaryStage.close();
        });

        button4.setOnAction((event) -> {
            openCertificatenStage(primaryStage);
            primaryStage.close();
        });
    }

    // Method to open a new stage
    private void openCursistStage(Stage previousStage) {
        Stage cursistStage = new Stage();

        Button addButton = new Button("Add");
        Button closeButton = new Button("Close");
        Button refreshButton = new Button("Verversen");

        addButton.setOnAction((event) -> {
            openAddCursistStage(cursistStage); // Call method to open new stage
            cursistStage.close();
        });

        closeButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                cursistStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        refreshButton.setOnAction(event -> refreshTable());

        HBox hBox = new HBox();

        hBox.getChildren().addAll(addButton, refreshButton, closeButton);

        // Voeg ruimte toe tussen knoppen
        hBox.setSpacing(10); // Stel de ruimte tussen knoppen in

        TableView<Person> tableView = new TableView();

        TableColumn<Person, String> column1 = new TableColumn<>("First Name");

        column1.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> column2 = new TableColumn<>("Last Name");

        column2.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        // tableView.getItems().add(
        // new Person("John", "Doe"));
        // tableView.getItems().add(
        // new Person("Jane", "Deer"));

        if (data.isEmpty()) {
            database.setDataFromDatabase(data);
        }

        tableView.setItems(data);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(hBox, tableView);
        vbox.setMinWidth(600);

        Scene scene = new Scene(vbox, 600, 400);
        cursistStage.setScene(scene);
        cursistStage.setTitle("Cursisten");
        cursistStage.show();
    }

    private void openAddCursistStage(Stage previousStage) {
        Stage addCursistStage = new Stage();

        Label Email = new Label("Email");
        Label Naam = new Label("Naam");
        Label Geboortedatum = new Label("Geboortedatum");
        Label Geslacht = new Label("Geslacht");
        Label Adres = new Label("Adres");
        Label Woonplaats = new Label("Woonplaats");
        Label Land = new Label("Land");

        Button addButton = new Button("Add");
        Button closeButton = new Button("Close");

        TextField TextFieldEmail = new TextField();
        TextField TextFieldNaam = new TextField();
        TextField TextFieldGeboortedatum = new TextField();
        TextField TextFieldGeslacht = new TextField();
        TextField TextFieldAdres = new TextField();
        TextField TextFieldWoonplaats = new TextField();
        TextField TextFieldLand = new TextField();

        TextFieldEmail.setPrefWidth(250);
        TextFieldNaam.setPrefWidth(250);
        TextFieldGeboortedatum.setPrefWidth(250);
        TextFieldGeslacht.setPrefWidth(250);
        TextFieldAdres.setPrefWidth(250);
        TextFieldWoonplaats.setPrefWidth(250);
        TextFieldLand.setPrefWidth(250);

        addButton.setOnAction((event) -> {

            Database database = new Database();

            String email = TextFieldEmail.getText();
            String naam = TextFieldNaam.getText();
            String geboorteDatum = TextFieldGeboortedatum.getText();
            Integer geslacht = Integer.valueOf(TextFieldGeslacht.getText());
            String adres = TextFieldAdres.getText();
            String woonplaats = TextFieldWoonplaats.getText();
            String land = TextFieldLand.getText();

            if (database.setDataInDatabase(email, naam, geboorteDatum, geslacht, adres,woonplaats, land)) {
                showAlert("Succes", "Record toegevoegd", "Het toevoegen van het record is gelukt.");
            } else {
                showAlert("Fout", "Toevoegen mislukt", "Er is een fout opgetreden bij het toevoegen van het record.");
            }
        });

        closeButton.setOnAction((event) -> {
            // openAddCursistStage(stage); // Call method to open new stage
            if (previousStage != null) {
                previousStage.show();
                addCursistStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        VBox vBox = new VBox();
        vBox.setSpacing(19);
        vBox.getChildren().addAll(Email, Naam, Geboortedatum, Geslacht, Adres, Woonplaats, Land);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);
        vBox2.getChildren().addAll(TextFieldEmail, TextFieldNaam, TextFieldGeboortedatum, TextFieldGeslacht,
                TextFieldAdres, TextFieldWoonplaats, TextFieldLand);

        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.setPadding(new Insets(25));
        hb1.getChildren().addAll(vBox, vBox2);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        buttonPane.getChildren().addAll(
                addButton,
                closeButton);

        // VBox vb = new VBox();
        // vb.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, hb6, hb7);
        // vb.setSpacing(20);
        // vb.setPadding(new Insets(10));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonPane, hb1);

        // Creëer een Scene met de VBox
        Scene scene = new Scene(vbox, 600, 400);

        // Zet de Scene op het primaire podium
        addCursistStage.setScene(scene);

        // Zet de titel van het podium
        addCursistStage.setTitle("Add cursist");

        // Laat het podium zien
        addCursistStage.show();
    }

    private void openCursusStage(Stage previousStage) {
        Stage cursusStage = new Stage();

        Button addButton = new Button("Add");
        Button closeButton = new Button("Close");
        Button refreshButton = new Button("Verversen");

        addButton.setOnAction((event) -> {
            openAddCursusStage(cursusStage); // Call method to open new stage
            cursusStage.close();
        });

        closeButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                cursusStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        refreshButton.setOnAction(event -> refreshTable());

        HBox hBox = new HBox();

        hBox.getChildren().addAll(addButton, refreshButton, closeButton);

        // Voeg ruimte toe tussen knoppen
        hBox.setSpacing(10); // Stel de ruimte tussen knoppen in

        TableView<Person> tableView = new TableView();

        TableColumn<Person, String> column1 = new TableColumn<>("First Name");

        column1.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> column2 = new TableColumn<>("Last Name");

        column2.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        // tableView.getItems().add(
        // new Person("John", "Doe"));
        // tableView.getItems().add(
        // new Person("Jane", "Deer"));

        if (data.isEmpty()) {
            database.setDataFromDatabase(data);
        }

        tableView.setItems(data);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(hBox, tableView);
        vbox.setMinWidth(600);

        Scene scene = new Scene(vbox, 600, 400);
        cursusStage.setScene(scene);
        cursusStage.setTitle("Cursussen");
        cursusStage.show();
    }

    private void openAddCursusStage(Stage previousStage) {
        Stage addCursusStage = new Stage();

        Label Email = new Label("Email");
        Label Naam = new Label("Naam");
        Label Geboortedatum = new Label("Geboortedatum");
        Label Geslacht = new Label("Geslacht");
        Label Adres = new Label("Adres");
        Label Woonplaats = new Label("Woonplaats");
        Label Land = new Label("Land");

        Button addButton = new Button("Add");
        Button closeButton = new Button("Close");

        TextField TextFieldEmail = new TextField();
        TextField TextFieldNaam = new TextField();
        TextField TextFieldGeboortedatum = new TextField();
        TextField TextFieldGeslacht = new TextField();
        TextField TextFieldAdres = new TextField();
        TextField TextFieldWoonplaats = new TextField();
        TextField TextFieldLand = new TextField();

        TextFieldEmail.setPrefWidth(250);
        TextFieldNaam.setPrefWidth(250);
        TextFieldGeboortedatum.setPrefWidth(250);
        TextFieldGeslacht.setPrefWidth(250);
        TextFieldAdres.setPrefWidth(250);
        TextFieldWoonplaats.setPrefWidth(250);
        TextFieldLand.setPrefWidth(250);

        addButton.setOnAction((event) -> {

            Database database = new Database();

            String email = TextFieldEmail.getText();
            String naam = TextFieldNaam.getText();
            String geboorteDatum = TextFieldGeboortedatum.getText();
            Integer geslacht = Integer.valueOf(TextFieldGeslacht.getText());
            String adres = TextFieldAdres.getText();
            String woonplaats = TextFieldWoonplaats.getText();
            String land = TextFieldLand.getText();

            if (database.setDataInDatabase(email, naam, geboorteDatum, geslacht, adres,woonplaats, land)) {
                showAlert("Succes", "Record toegevoegd", "Het toevoegen van het record is gelukt.");
            } else {
                showAlert("Fout", "Toevoegen mislukt", "Er is een fout opgetreden bij het toevoegen van het record.");
            }
        });

        closeButton.setOnAction((event) -> {
            // openAddCursistStage(stage); // Call method to open new stage
            if (previousStage != null) {
                previousStage.show();
                addCursusStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        VBox vBox = new VBox();
        vBox.setSpacing(19);
        vBox.getChildren().addAll(Email, Naam, Geboortedatum, Geslacht, Adres, Woonplaats, Land);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);
        vBox2.getChildren().addAll(TextFieldEmail, TextFieldNaam, TextFieldGeboortedatum, TextFieldGeslacht,
                TextFieldAdres, TextFieldWoonplaats, TextFieldLand);

        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.setPadding(new Insets(25));
        hb1.getChildren().addAll(vBox, vBox2);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        buttonPane.getChildren().addAll(
                addButton,
                closeButton);

        // VBox vb = new VBox();
        // vb.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, hb6, hb7);
        // vb.setSpacing(20);
        // vb.setPadding(new Insets(10));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonPane, hb1);

        // Creëer een Scene met de VBox
        Scene scene = new Scene(vbox, 600, 400);

        // Zet de Scene op het primaire podium
        addCursusStage.setScene(scene);

        // Zet de titel van het podium
        addCursusStage.setTitle("Add cursus");

        // Laat het podium zien
        addCursusStage.show();
    }

    private void openInschrijvingenStage(Stage previousStage) {
        Stage inschrijvingenStage = new Stage();

        Button addButton = new Button("Add");
        Button closeButton = new Button("Close");
        Button refreshButton = new Button("Verversen");

        addButton.setOnAction((event) -> {
            openAddInschrijvingenStage(inschrijvingenStage); // Call method to open new stage
            inschrijvingenStage.close();
        });

        closeButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                inschrijvingenStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        refreshButton.setOnAction(event -> refreshTable());

        HBox hBox = new HBox();

        hBox.getChildren().addAll(addButton, refreshButton, closeButton);

        // Voeg ruimte toe tussen knoppen
        hBox.setSpacing(10); // Stel de ruimte tussen knoppen in

        TableView<Person> tableView = new TableView();

        TableColumn<Person, String> column1 = new TableColumn<>("First Name");

        column1.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> column2 = new TableColumn<>("Last Name");

        column2.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        // tableView.getItems().add(
        // new Person("John", "Doe"));
        // tableView.getItems().add(
        // new Person("Jane", "Deer"));

        if (data.isEmpty()) {
            database.setDataFromDatabase(data);
        }

        tableView.setItems(data);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(hBox, tableView);
        vbox.setMinWidth(600);

        Scene scene = new Scene(vbox, 600, 400);
        inschrijvingenStage.setScene(scene);
        inschrijvingenStage.setTitle("Inschrijvingen");
        inschrijvingenStage.show();
    }

    private void openAddInschrijvingenStage(Stage previousStage) {
        Stage addInschrijvingenStage = new Stage();

        Label Email = new Label("Email");
        Label Naam = new Label("Naam");
        Label Geboortedatum = new Label("Geboortedatum");
        Label Geslacht = new Label("Geslacht");
        Label Adres = new Label("Adres");
        Label Woonplaats = new Label("Woonplaats");
        Label Land = new Label("Land");

        Button addButton = new Button("Add");
        Button closeButton = new Button("Close");

        TextField TextFieldEmail = new TextField();
        TextField TextFieldNaam = new TextField();
        TextField TextFieldGeboortedatum = new TextField();
        TextField TextFieldGeslacht = new TextField();
        TextField TextFieldAdres = new TextField();
        TextField TextFieldWoonplaats = new TextField();
        TextField TextFieldLand = new TextField();

        TextFieldEmail.setPrefWidth(250);
        TextFieldNaam.setPrefWidth(250);
        TextFieldGeboortedatum.setPrefWidth(250);
        TextFieldGeslacht.setPrefWidth(250);
        TextFieldAdres.setPrefWidth(250);
        TextFieldWoonplaats.setPrefWidth(250);
        TextFieldLand.setPrefWidth(250);

        addButton.setOnAction((event) -> {

            Database database = new Database();

            String email = TextFieldEmail.getText();
            String naam = TextFieldNaam.getText();
            String geboorteDatum = TextFieldGeboortedatum.getText();
            Integer geslacht = Integer.valueOf(TextFieldGeslacht.getText());
            String adres = TextFieldAdres.getText();
            String woonplaats = TextFieldWoonplaats.getText();
            String land = TextFieldLand.getText();

            if (database.setDataInDatabase(email, naam, geboorteDatum, geslacht, adres,woonplaats, land)) {
                showAlert("Succes", "Record toegevoegd", "Het toevoegen van het record is gelukt.");
            } else {
                showAlert("Fout", "Toevoegen mislukt", "Er is een fout opgetreden bij het toevoegen van het record.");
            }
        });

        closeButton.setOnAction((event) -> {
            // openAddCursistStage(stage); // Call method to open new stage
            if (previousStage != null) {
                previousStage.show();
                addInschrijvingenStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        VBox vBox = new VBox();
        vBox.setSpacing(19);
        vBox.getChildren().addAll(Email, Naam, Geboortedatum, Geslacht, Adres, Woonplaats, Land);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);
        vBox2.getChildren().addAll(TextFieldEmail, TextFieldNaam, TextFieldGeboortedatum, TextFieldGeslacht,
                TextFieldAdres, TextFieldWoonplaats, TextFieldLand);

        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.setPadding(new Insets(25));
        hb1.getChildren().addAll(vBox, vBox2);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        buttonPane.getChildren().addAll(
                addButton,
                closeButton);

        // VBox vb = new VBox();
        // vb.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, hb6, hb7);
        // vb.setSpacing(20);
        // vb.setPadding(new Insets(10));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonPane, hb1);

        // Creëer een Scene met de VBox
        Scene scene = new Scene(vbox, 600, 400);

        // Zet de Scene op het primaire podium
        addInschrijvingenStage.setScene(scene);

        // Zet de titel van het podium
        addInschrijvingenStage.setTitle("Add Inschrijvingen");

        // Laat het podium zien
        addInschrijvingenStage.show();
    }

    private void openCertificatenStage(Stage previousStage) {
        Stage certificatenStage = new Stage();

        Button addButton = new Button("Add");
        Button closeButton = new Button("Close");
        Button refreshButton = new Button("Verversen");

        addButton.setOnAction((event) -> {
            openAddCertificatenStage(certificatenStage); // Call method to open new stage
            certificatenStage.close();
        });

        closeButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                certificatenStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        refreshButton.setOnAction(event -> refreshTable());

        HBox hBox = new HBox();

        hBox.getChildren().addAll(addButton, refreshButton, closeButton);

        // Voeg ruimte toe tussen knoppen
        hBox.setSpacing(10); // Stel de ruimte tussen knoppen in

        TableView<Person> tableView = new TableView();

        TableColumn<Person, String> column1 = new TableColumn<>("First Name");

        column1.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));

        TableColumn<Person, String> column2 = new TableColumn<>("Last Name");

        column2.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        // tableView.getItems().add(
        // new Person("John", "Doe"));
        // tableView.getItems().add(
        // new Person("Jane", "Deer"));

        if (data.isEmpty()) {
            database.setDataFromDatabase(data);
        }

        tableView.setItems(data);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(hBox, tableView);
        vbox.setMinWidth(600);

        Scene scene = new Scene(vbox, 600, 400);
        certificatenStage.setScene(scene);
        certificatenStage.setTitle("Certificaten");
        certificatenStage.show();
    }

    private void openAddCertificatenStage(Stage previousStage) {
        Stage addCertificatenStage = new Stage();

        Label Email = new Label("Email");
        Label Naam = new Label("Naam");
        Label Geboortedatum = new Label("Geboortedatum");
        Label Geslacht = new Label("Geslacht");
        Label Adres = new Label("Adres");
        Label Woonplaats = new Label("Woonplaats");
        Label Land = new Label("Land");

        Button addButton = new Button("Add");
        Button closeButton = new Button("Close");

        TextField TextFieldEmail = new TextField();
        TextField TextFieldNaam = new TextField();
        TextField TextFieldGeboortedatum = new TextField();
        TextField TextFieldGeslacht = new TextField();
        TextField TextFieldAdres = new TextField();
        TextField TextFieldWoonplaats = new TextField();
        TextField TextFieldLand = new TextField();

        TextFieldEmail.setPrefWidth(250);
        TextFieldNaam.setPrefWidth(250);
        TextFieldGeboortedatum.setPrefWidth(250);
        TextFieldGeslacht.setPrefWidth(250);
        TextFieldAdres.setPrefWidth(250);
        TextFieldWoonplaats.setPrefWidth(250);
        TextFieldLand.setPrefWidth(250);

        addButton.setOnAction((event) -> {

            Database database = new Database();

            String email = TextFieldEmail.getText();
            String naam = TextFieldNaam.getText();
            String geboorteDatum = TextFieldGeboortedatum.getText();
            Integer geslacht = Integer.valueOf(TextFieldGeslacht.getText());
            String adres = TextFieldAdres.getText();
            String woonplaats = TextFieldWoonplaats.getText();
            String land = TextFieldLand.getText();

            if (database.setDataInDatabase(email, naam, geboorteDatum, geslacht, adres,woonplaats, land)) {
                showAlert("Succes", "Record toegevoegd", "Het toevoegen van het record is gelukt.");
            } else {
                showAlert("Fout", "Toevoegen mislukt", "Er is een fout opgetreden bij het toevoegen van het record.");
            }
        });

        closeButton.setOnAction((event) -> {
            // openAddCursistStage(stage); // Call method to open new stage
            if (previousStage != null) {
                previousStage.show();
                addCertificatenStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        VBox vBox = new VBox();
        vBox.setSpacing(19);
        vBox.getChildren().addAll(Email, Naam, Geboortedatum, Geslacht, Adres, Woonplaats, Land);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);
        vBox2.getChildren().addAll(TextFieldEmail, TextFieldNaam, TextFieldGeboortedatum, TextFieldGeslacht,
                TextFieldAdres, TextFieldWoonplaats, TextFieldLand);

        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.setPadding(new Insets(25));
        hb1.getChildren().addAll(vBox, vBox2);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        buttonPane.getChildren().addAll(
                addButton,
                closeButton);

        // VBox vb = new VBox();
        // vb.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, hb6, hb7);
        // vb.setSpacing(20);
        // vb.setPadding(new Insets(10));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonPane, hb1);

        // Creëer een Scene met de VBox
        Scene scene = new Scene(vbox, 600, 400);

        // Zet de Scene op het primaire podium
        addCertificatenStage.setScene(scene);

        // Zet de titel van het podium
        addCertificatenStage.setTitle("Add Certificaten");

        // Laat het podium zien
        addCertificatenStage.show();
    }


    private void refreshTable() {
        data.clear();
        database.setDataFromDatabase(data);
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}