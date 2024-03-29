import java.beans.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;

public class GUI extends Application {

    private boolean Result;
    private Database database = new Database();
    private DataRetriever dataRetrieverStudent;
    private DataRetriever dataRetrieverCourse;
    private DataRetriever dataRetrieverEnrollment;
    private DataRetriever dataRetrieverModule;
    private DataRetriever dataRetrieverProgress;
    private DataRetriever dataRetrieverWebcast;
    private DataInserter dataInserter = new DataInserter();
    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private final ObservableList<Course> courses = FXCollections.observableArrayList();
    private final ObservableList<Enrollment> enrollments = FXCollections.observableArrayList();
    private final ObservableList<Module> modules = FXCollections.observableArrayList();
    private final ObservableList<Progress> progress = FXCollections.observableArrayList();
    private final ObservableList<Webcast> webcasts = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontale ruimte tussen de knoppen
        gridPane.setVgap(10); // Verticale ruimte tussen de knoppen
        gridPane.setPadding(new Insets(10)); // Ruimte rondom de GridPane
        gridPane.setAlignment(Pos.CENTER); // Center alignment

        Button button1 = new Button("Cursisten");
        Button button2 = new Button("Cursussen");
        Button button3 = new Button("Inschrijvingen");
        Button button4 = new Button("Modules");
        Button button5 = new Button("Webcasten");
        Button button6 = new Button("Voortgang");

        // Setting preferred size for buttons
        button1.setPrefSize(200, 100); // width, height
        button2.setPrefSize(200, 100);
        button3.setPrefSize(200, 100);
        button4.setPrefSize(200, 100);
        button5.setPrefSize(200, 100);
        button6.setPrefSize(200, 100);

        gridPane.add(button1, 0, 0); // Voeg Button 1 toe aan de eerste rij, eerste kolom
        gridPane.add(button2, 1, 0); // Voeg Button 2 toe aan de eerste rij, tweede kolom
        gridPane.add(button3, 0, 1); // Voeg Button 3 toe aan de tweede rij, eerste kolom
        gridPane.add(button4, 1, 1); // Voeg Button 4 toe aan de tweede rij, tweede kolom
        gridPane.add(button5, 2, 0); // Voeg Button 4 toe aan de tweede rij, tweede kolom
        gridPane.add(button6, 2, 1); // Voeg Button 4 toe aan de tweede rij, tweede kolom

        Scene scene = new Scene(gridPane, 700, 400); // Breedte en hoogte van de Scene
        primaryStage.setScene(scene);

        primaryStage.setTitle("Codecademy overzicht Dayal Manbodh Studentnummer: 2219243, Mikail Sari Studentnummer: 2215079");
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
            openModuleStage(primaryStage);
            primaryStage.close();
        });

        button5.setOnAction((event) -> {
            openWebcastStage(primaryStage);
            primaryStage.close();
        });

        button6.setOnAction((event) -> {
            openProgressStage(primaryStage);
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

        TableView<Student> tableView = new TableView();

        TableColumn<Student, String> email = new TableColumn<>("Email");

        email.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        TableColumn<Student, String> name = new TableColumn<>("Naam");

        name.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<Student, String> birthdate = new TableColumn<>("Geboortedatum");

        birthdate.setCellValueFactory(
                new PropertyValueFactory<>("birthdate"));

        TableColumn<Student, String> gender = new TableColumn<>("Geslacht");

        gender.setCellValueFactory(
                new PropertyValueFactory<>("gender"));
        TableColumn<Student, String> address = new TableColumn<>("Adres");

        address.setCellValueFactory(
                new PropertyValueFactory<>("address"));

        TableColumn<Student, String> city = new TableColumn<>("Woonplaats");

        city.setCellValueFactory(
                new PropertyValueFactory<>("city"));

        TableColumn<Student, String> county = new TableColumn<>("Land");

        county.setCellValueFactory(
                new PropertyValueFactory<>("country"));

        tableView.getColumns().add(email);
        tableView.getColumns().add(name);
        tableView.getColumns().add(birthdate);
        tableView.getColumns().add(gender);
        tableView.getColumns().add(address);
        tableView.getColumns().add(city);
        tableView.getColumns().add(county);

        if (students.isEmpty()) {
            dataRetrieverStudent = new DataRetriever("student", Student.class);
            try {
                dataRetrieverStudent.retrieveData(students);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        tableView.setItems(students);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(hBox, tableView);
        vbox.setMinWidth(600);

        Scene scene = new Scene(vbox, 700, 400);
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

            String email = TextFieldEmail.getText();
            String naam = TextFieldNaam.getText();
            Date geboorteDatum = Date.valueOf(TextFieldGeboortedatum.getText());
            String geslacht = TextFieldGeslacht.getText();
            String adres = TextFieldAdres.getText();
            String woonplaats = TextFieldWoonplaats.getText();
            String land = TextFieldLand.getText();

            try {
                // Voorbeeld van het toevoegen van een student
                Student addstudent = new Student(email, naam, geboorteDatum, geslacht, adres, woonplaats, land);
                dataInserter.insert(addstudent);
                System.out.println("Student inserted successfully.");

                Result = true;

                // Voeg meer objecten toe zoals Module, Webcast, Enrollment, Progress, etc.
            } catch (SQLException e) {
                System.err.println("Error inserting data: " + e.getMessage());

                Result = true;
            }

            if (Result) {
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
        Scene scene = new Scene(vbox, 700, 400);

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

        TableView<Course> tableView = new TableView();

        TableColumn<Course, String> courseName = new TableColumn<>("Naam");

        courseName.setCellValueFactory(
                new PropertyValueFactory<>("courseName"));

        TableColumn<Course, String> subject = new TableColumn<>("Onderwerp");

        subject.setCellValueFactory(
                new PropertyValueFactory<>("subject"));

        TableColumn<Course, String> introductionText = new TableColumn<>("Niveau");

        introductionText.setCellValueFactory(
                new PropertyValueFactory<>("introductionText"));

        TableColumn<Course, String> level = new TableColumn<>("Introductietekst");

        level.setCellValueFactory(
                new PropertyValueFactory<>("level"));

        tableView.getColumns().add(courseName);
        tableView.getColumns().add(subject);
        tableView.getColumns().add(introductionText);
        tableView.getColumns().add(level);

        if (courses.isEmpty()) {
            dataRetrieverCourse = new DataRetriever("course", Course.class);
            try {
                dataRetrieverCourse.retrieveData(courses);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        tableView.setItems(courses);

        tableView.setOnMouseClicked(event -> {

            if (event.getClickCount() == 1) { // Detect single click
                Course cursus = tableView.getSelectionModel().getSelectedItem();
                // if (cursus != null) {
                //     System.out.println("Selected cursus: " + cursus.getCourseID());
                // }
                
                openAverageProgressStage(cursusStage,cursus);
                cursusStage.close();

            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(hBox, tableView);
        vbox.setMinWidth(700);

        Scene scene = new Scene(vbox, 700, 400);
        cursusStage.setScene(scene);
        cursusStage.setTitle("Cursussen");
        cursusStage.show();
    }

    private void openAddCursusStage(Stage previousStage) {
        Stage addCursusStage = new Stage();

        Label labelCourseName = new Label("Naam");
        Label labelSubject = new Label("Onderwerp");
        Label labelIntroductionText = new Label("Introductietekst");
        Label labelLevel = new Label("Niveau");

        Button addButton = new Button("Add");
        Button closeButton = new Button("Close");

        TextField TextFieldCourseName = new TextField();
        TextField TextFieldSubject = new TextField();
        TextField TextFieldIntroductionText = new TextField();
        TextField TextFieldLevel = new TextField();

        TextFieldCourseName.setPrefWidth(250);
        TextFieldSubject.setPrefWidth(250);
        TextFieldIntroductionText.setPrefWidth(250);
        TextFieldLevel.setPrefWidth(250);

        addButton.setOnAction((event) -> {

            String courseName = TextFieldCourseName.getText();
            String subject = TextFieldSubject.getText();
            String introductionText = TextFieldIntroductionText.getText();
            String level = TextFieldLevel.getText();

            try {
                // Voorbeeld van het toevoegen van een Course
                Course addCourse = new Course(courseName, subject, introductionText, level);
                dataInserter.insert(addCourse);
                System.out.println("Course inserted successfully.");

                Result = true;

            } catch (SQLException e) {
                System.err.println("Error inserting data: " + e.getMessage());

                Result = true;
            }

            if (Result) {
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
        vBox.getChildren().addAll(labelCourseName, labelSubject, labelIntroductionText, labelLevel);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);
        vBox2.getChildren().addAll(TextFieldCourseName, TextFieldSubject, TextFieldIntroductionText, TextFieldLevel);

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
        Scene scene = new Scene(vbox, 700, 400);

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
            openAddInschrijvingenStage(inschrijvingenStage); // Call method to open new
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

        TableView<Enrollment> tableView = new TableView<>();

        // Maak kolommen aan voor de TableView voor de Enrollment-klasse
        TableColumn<Enrollment, Integer> enrollmentIDColumn = new TableColumn<>("Inschrijvingsnummer");
        enrollmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("enrollmentID"));

        TableColumn<Enrollment, String> studentEmailAddressColumn = new TableColumn<>("E-mailadres student");
        studentEmailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("studentEmailAddress"));

        TableColumn<Enrollment, Integer> courseIDColumn = new TableColumn<>("Cursus ID");
        courseIDColumn.setCellValueFactory(new PropertyValueFactory<>("courseID"));

        TableColumn<Enrollment, Date> enrollmentDateColumn = new TableColumn<>("Inschrijvingsdatum");
        enrollmentDateColumn.setCellValueFactory(new PropertyValueFactory<>("enrollmentDate"));

        // Voeg de kolommen toe aan de TableView
        tableView.getColumns().add(enrollmentIDColumn);
        tableView.getColumns().add(studentEmailAddressColumn);
        tableView.getColumns().add(courseIDColumn);
        tableView.getColumns().add(enrollmentDateColumn);

        if (enrollments.isEmpty()) {
            dataRetrieverEnrollment = new DataRetriever("enrollment", Enrollment.class);
            try {
                dataRetrieverEnrollment.retrieveData(enrollments);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        tableView.setItems(enrollments);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(hBox, tableView);
        vbox.setMinWidth(700);

        Scene scene = new Scene(vbox, 700, 400);
        inschrijvingenStage.setScene(scene);
        inschrijvingenStage.setTitle("Inschrijvingen");
        inschrijvingenStage.show();
    }

    private void openAddInschrijvingenStage(Stage previousStage) {
        Stage addInschrijvingenStage = new Stage();

        Label studentEmailAddressLabel = new Label("Student Email Address");
        Label courseIDLabel = new Label("Course ID");
        Label enrollmentDateLabel = new Label("Enrollment Date");

        Button addButton = new Button("Add");
        Button closeButton = new Button("Close");

        TextField studentEmailAddressField = new TextField();
        TextField courseIDField = new TextField();
        TextField enrollmentDateField = new TextField();

        studentEmailAddressField.setPrefWidth(250);
        courseIDField.setPrefWidth(250);
        enrollmentDateField.setPrefWidth(250);

        addButton.setOnAction((event) -> {
            String studentEmailAddress = studentEmailAddressField.getText();
            int courseID = Integer.valueOf(courseIDField.getText());
            Date enrollmentDate = Date.valueOf(enrollmentDateField.getText());

            try {
                // Voorbeeld van het toevoegen van een Enrollment
                Enrollment addEnrollment = new Enrollment(studentEmailAddress, courseID, enrollmentDate);
                dataInserter.insert(addEnrollment);
                System.out.println("Enrollment inserted successfully.");

                Result = true;

                // Voeg meer objecten toe zoals Module, Webcast, Enrollment, Progress, etc.
            } catch (SQLException e) {
                System.err.println("Error inserting data: " + e.getMessage());

                Result = true;
            }

            if (Result) {
                showAlert("Succes", "Record toegevoegd", "Het toevoegen van het record is gelukt.");
            } else {
                showAlert("Fout", "Toevoegen mislukt", "Er is een fout opgetreden bij het toevoegen van het record.");
            }

        });

        closeButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                addInschrijvingenStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        VBox vBox = new VBox();
        vBox.setSpacing(19);
        vBox.getChildren().addAll(studentEmailAddressLabel, courseIDLabel, enrollmentDateLabel);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);
        vBox2.getChildren().addAll(studentEmailAddressField, courseIDField, enrollmentDateField);

        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.setPadding(new Insets(25));
        hb1.getChildren().addAll(vBox, vBox2);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        buttonPane.getChildren().addAll(addButton, closeButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonPane, hb1);

        Scene scene = new Scene(vbox, 700, 400);

        addInschrijvingenStage.setScene(scene);
        addInschrijvingenStage.setTitle("Add Inschrijvingen");
        addInschrijvingenStage.show();
    }

    public void openModuleStage(Stage previousStage) {
        Stage moduleStage = new Stage();

        Button addButton = new Button("Add");
        Button refreshButton = new Button("Verversen");
        Button closeButton = new Button("Close");

        TableView<Module> tableView = new TableView<>();

        // Create columns for the TableView for the Module class
        TableColumn<Module, String> titleColumn = new TableColumn<>("Titel");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Module, String> versionColumn = new TableColumn<>("Versie");
        versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));

        TableColumn<Module, String> descriptionColumn = new TableColumn<>("Beschrijving");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Module, String> contactPersonNameColumn = new TableColumn<>("Contactpersoon Naam");
        contactPersonNameColumn.setCellValueFactory(new PropertyValueFactory<>("contactPersonName"));

        TableColumn<Module, String> contactPersonEmailColumn = new TableColumn<>("Contactpersoon E-mail");
        contactPersonEmailColumn.setCellValueFactory(new PropertyValueFactory<>("contactPersonEmail"));

        // Add the columns to the TableView
        tableView.getColumns().addAll(titleColumn, versionColumn, descriptionColumn, contactPersonNameColumn,
                contactPersonEmailColumn);

        if (modules.isEmpty()) {
            dataRetrieverModule = new DataRetriever("module", Module.class);
            try {
                dataRetrieverModule.retrieveData(modules);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        tableView.setItems(modules);

        addButton.setOnAction((event) -> {
            openAddModuleStage(moduleStage);
            moduleStage.close();
        });

        refreshButton.setOnAction((event) -> {
            refreshTable();
        });

        closeButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                moduleStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(addButton, refreshButton, closeButton);

        buttonBox.setSpacing(10); // Stel de ruimte tussen knoppen in

        VBox vBox = new VBox();
        vBox.getChildren().addAll(buttonBox, tableView);

        Scene scene = new Scene(vBox, 700, 400);

        moduleStage.setScene(scene);
        moduleStage.setTitle("Modules");
        moduleStage.show();
    }

    private void openAddModuleStage(Stage previousStage) {
        Stage addModuleStage = new Stage();

        Label titleLabel = new Label("Titel");
        Label versionLabel = new Label("Versie");
        Label descriptionLabel = new Label("Beschrijving");
        Label contactPersonNameLabel = new Label("Contactpersoon naam");
        Label contactPersonEmailLabel = new Label("Contactpersoon e-mail");

        Button addButton = new Button("Add");
        Button closeButton = new Button("Close");

        TextField titleField = new TextField();
        TextField versionField = new TextField();
        TextField descriptionField = new TextField();
        TextField contactPersonNameField = new TextField();
        TextField contactPersonEmailField = new TextField();

        titleField.setPrefWidth(250);
        versionField.setPrefWidth(250);
        descriptionField.setPrefWidth(250);
        contactPersonNameField.setPrefWidth(250);
        contactPersonEmailField.setPrefWidth(250);

        addButton.setOnAction((event) -> {
            String title = titleField.getText();
            String version = versionField.getText();
            String description = descriptionField.getText();
            String contactPersonName = contactPersonNameField.getText();
            String contactPersonEmail = contactPersonEmailField.getText();

            try {
                // Voorbeeld van het toevoegen van een Module
                Module addModule = new Module(title, version, description, contactPersonName, contactPersonEmail);
                dataInserter.insert(addModule);
                System.out.println("Module inserted successfully.");

                Result = true;

                // Voeg meer objecten toe zoals Module, Webcast, Enrollment, Progress, etc.
            } catch (SQLException e) {
                System.err.println("Error inserting data: " + e.getMessage());

                Result = true;
            }

            if (Result) {
                showAlert("Succes", "Record toegevoegd", "Het toevoegen van het record is gelukt.");
            } else {
                showAlert("Fout", "Toevoegen mislukt", "Er is een fout opgetreden bij het toevoegen van het record.");
            }

            // Voeg hier de logica toe om de Module-gegevens toe te voegen aan de database
        });

        closeButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                addModuleStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        VBox vBox = new VBox();
        vBox.setSpacing(19);
        vBox.getChildren().addAll(titleLabel, versionLabel, descriptionLabel, contactPersonNameLabel,
                contactPersonEmailLabel);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);
        vBox2.getChildren().addAll(titleField, versionField, descriptionField, contactPersonNameField,
                contactPersonEmailField);

        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.setPadding(new Insets(25));
        hb1.getChildren().addAll(vBox, vBox2);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        buttonPane.getChildren().addAll(addButton, closeButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonPane, hb1);

        Scene scene = new Scene(vbox, 700, 400);

        addModuleStage.setScene(scene);
        addModuleStage.setTitle("Add Module");
        addModuleStage.show();
    }

    public void openWebcastStage(Stage previousStage) {
        Stage webcastStage = new Stage();

        Button addButton = new Button("Add");
        Button refreshButton = new Button("Verversen");
        Button closeButton = new Button("Close");

        TableView<Webcast> tableView = new TableView<>();

        // Create columns for the TableView for the Webcast class
        TableColumn<Webcast, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("webcastID"));

        TableColumn<Webcast, String> titleColumn = new TableColumn<>("Titel");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Webcast, String> descriptionColumn = new TableColumn<>("Beschrijving");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Webcast, String> speakerNameColumn = new TableColumn<>("Spreker Naam");
        speakerNameColumn.setCellValueFactory(new PropertyValueFactory<>("speakerName"));

        TableColumn<Webcast, String> speakerOrganizationColumn = new TableColumn<>("Spreker Organisatie");
        speakerOrganizationColumn.setCellValueFactory(new PropertyValueFactory<>("speakerOrganization"));

        TableColumn<Webcast, Time> durationColumn = new TableColumn<>("Duur");
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

        TableColumn<Webcast, Date> publicationDateColumn = new TableColumn<>("Publicatiedatum");
        publicationDateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));

        TableColumn<Webcast, String> urlColumn = new TableColumn<>("URL");
        urlColumn.setCellValueFactory(new PropertyValueFactory<>("url"));

        // Add the columns to the TableView
        tableView.getColumns().addAll(idColumn, titleColumn, descriptionColumn, speakerNameColumn,
                speakerOrganizationColumn,
                durationColumn, publicationDateColumn, urlColumn);

        // If webcasts list is empty, retrieve data
        if (webcasts.isEmpty()) {
            dataRetrieverWebcast = new DataRetriever("webcast", Webcast.class);
            try {
                dataRetrieverWebcast.retrieveData(webcasts);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        tableView.setItems(webcasts);

        addButton.setOnAction((event) -> {
            openAddWebcastStage(webcastStage);
            webcastStage.close();
        });

        refreshButton.setOnAction((event) -> {
            webcasts.clear(); // Clear the existing data
            refreshTable();
        });

        closeButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                webcastStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(addButton, refreshButton, closeButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(buttonBox, tableView);

        Scene scene = new Scene(vBox, 700, 400);

        webcastStage.setScene(scene);
        webcastStage.setTitle("Webcasts");
        webcastStage.show();
    }

    public void openProgressStage(Stage previousStage) {
        Stage progressStage = new Stage();

        Button addButton = new Button("Add");
        Button refreshButton = new Button("Verversen");
        Button closeButton = new Button("Close");

        TableView<Progress> tableView = new TableView<>();

        // Create columns for the TableView for the Progress class
        TableColumn<Progress, Integer> progressIDColumn = new TableColumn<>("Voortgangsnummer");
        progressIDColumn.setCellValueFactory(new PropertyValueFactory<>("progressID"));

        TableColumn<Progress, Integer> enrollmentIDColumn = new TableColumn<>("Inschrijvingsnummer");
        enrollmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("enrollmentID"));

        TableColumn<Progress, Integer> contentItemIDColumn = new TableColumn<>("Inhoudsitemnummer");
        contentItemIDColumn.setCellValueFactory(new PropertyValueFactory<>("contentItemID"));

        TableColumn<Progress, Double> percentageWatchedColumn = new TableColumn<>("Percentage bekeken");
        percentageWatchedColumn.setCellValueFactory(new PropertyValueFactory<>("percentageWatched"));

        // Add the columns to the TableView
        tableView.getColumns().addAll(progressIDColumn, enrollmentIDColumn, contentItemIDColumn,
                percentageWatchedColumn);

        // If progresses list is empty, retrieve data

        if (progress.isEmpty()) {
            dataRetrieverProgress = new DataRetriever("progress", Progress.class);
            try {
                dataRetrieverProgress.retrieveData(progress);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        tableView.setItems(progress);

        addButton.setOnAction((event) -> {
            openAddProgressStage(progressStage);
            progressStage.close();
        });

        refreshButton.setOnAction((event) -> {
            progress.clear(); // Clear the existing data
            refreshTable();
        });

        closeButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                progressStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);

        buttonBox.getChildren().addAll(addButton, refreshButton, closeButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(buttonBox, tableView);

        Scene scene = new Scene(vBox, 700, 400);

        progressStage.setScene(scene);
        progressStage.setTitle("Voortgang");
        progressStage.show();
    }

    private void openAddWebcastStage(Stage previousStage) {
        Stage addWebcastStage = new Stage();

        Label titleLabel = new Label("Titel");
        Label descriptionLabel = new Label("Beschrijving");
        Label speakerNameLabel = new Label("Spreker Naam");
        Label speakerOrganizationLabel = new Label("Spreker Organisatie");
        Label durationLabel = new Label("Duur");
        Label publicationDateLabel = new Label("Publicatiedatum");
        Label urlLabel = new Label("URL");

        Button addButton = new Button("Toevoegen");
        Button closeButton = new Button("Sluiten");

        TextField titleField = new TextField();
        TextField descriptionField = new TextField();
        TextField speakerNameField = new TextField();
        TextField speakerOrganizationField = new TextField();
        TextField durationField = new TextField();
        TextField publicationDateField = new TextField();
        TextField urlField = new TextField();

        titleField.setPrefWidth(250);
        descriptionField.setPrefWidth(250);
        speakerNameField.setPrefWidth(250);
        speakerOrganizationField.setPrefWidth(250);
        durationField.setPrefWidth(250);
        publicationDateField.setPrefWidth(250);
        urlField.setPrefWidth(250);

        addButton.setOnAction((event) -> {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String speakerName = speakerNameField.getText();
            String speakerOrganization = speakerOrganizationField.getText();
            Time duration = Time.valueOf(durationField.getText());
            Date publicationDate = Date.valueOf(publicationDateField.getText());
            String url = urlField.getText();

            try {
                // Voorbeeld van het toevoegen van een Webcast
                Webcast addWebcast = new Webcast(title, description, speakerName, speakerOrganization, duration,
                        publicationDate, url);
                dataInserter.insert(addWebcast);
                System.out.println("Module inserted successfully.");

                Result = true;

            } catch (SQLException e) {
                System.err.println("Error inserting data: " + e.getMessage());

                Result = true;
            }

            if (Result) {
                showAlert("Succes", "Record toegevoegd", "Het toevoegen van het record is gelukt.");
            } else {
                showAlert("Fout", "Toevoegen mislukt", "Er is een fout opgetreden bij het toevoegen van het record.");
            }

        });

        closeButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                addWebcastStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        VBox vBox = new VBox();
        vBox.setSpacing(19);
        vBox.getChildren().addAll(titleLabel, descriptionLabel, speakerNameLabel, speakerOrganizationLabel,
                durationLabel, publicationDateLabel, urlLabel);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);
        vBox2.getChildren().addAll(titleField, descriptionField, speakerNameField, speakerOrganizationField,
                durationField, publicationDateField, urlField);

        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.setPadding(new Insets(25));
        hb1.getChildren().addAll(vBox, vBox2);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        buttonPane.getChildren().addAll(addButton, closeButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonPane, hb1);

        Scene scene = new Scene(vbox, 700, 400);

        addWebcastStage.setScene(scene);
        addWebcastStage.setTitle("Add Webcast");
        addWebcastStage.show();
    }

    private void openAddProgressStage(Stage previousStage) {
        Stage addProgressStage = new Stage();

        // Labels
        Label enrollmentIDLabel = new Label("Inschrijvingsnummer");
        Label contentItemIDLabel = new Label("Content Item ID");
        Label percentageWatchedLabel = new Label("Percentage Bekeken");

        // Buttons
        Button addButton = new Button("Toevoegen");
        Button closeButton = new Button("Sluiten");

        // TextFields
        TextField enrollmentIDField = new TextField();
        TextField contentItemIDField = new TextField();
        TextField percentageWatchedField = new TextField();

        // Set preferred widths
        enrollmentIDField.setPrefWidth(250);
        contentItemIDField.setPrefWidth(250);
        percentageWatchedField.setPrefWidth(250);

        addButton.setOnAction((event) -> {
            int enrollmentID = Integer.parseInt(enrollmentIDField.getText());
            int contentItemID = Integer.parseInt(contentItemIDField.getText());
            double percentageWatched = Double.parseDouble(percentageWatchedField.getText());

            try {
                // Voorbeeld van het toevoegen van een Progress
                Progress addProgress = new Progress(contentItemID, enrollmentID, contentItemID, percentageWatched);
                dataInserter.insert(addProgress);
                System.out.println("Progress inserted successfully.");

                Result = true;

            } catch (SQLException e) {
                System.err.println("Error inserting data: " + e.getMessage());

                Result = true;
            }

            if (Result) {
                showAlert("Succes", "Record toegevoegd", "Het toevoegen van het record is gelukt.");
            } else {
                showAlert("Fout", "Toevoegen mislukt", "Er is een fout opgetreden bij het toevoegen van het record.");
            }

        });

        closeButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                addProgressStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        // Layout
        VBox vBox = new VBox();
        vBox.setSpacing(19);
        vBox.getChildren().addAll(enrollmentIDLabel, contentItemIDLabel, percentageWatchedLabel);

        VBox vBox2 = new VBox();
        vBox2.setSpacing(10);
        vBox2.getChildren().addAll(enrollmentIDField, contentItemIDField, percentageWatchedField);

        HBox hb1 = new HBox();
        hb1.setSpacing(10);
        hb1.setPadding(new Insets(25));
        hb1.getChildren().addAll(vBox, vBox2);

        HBox buttonPane = new HBox();
        buttonPane.setSpacing(10);
        buttonPane.getChildren().addAll(addButton, closeButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonPane, hb1);

        // Scene
        Scene scene = new Scene(vbox, 700, 400);

        // Set stage
        addProgressStage.setScene(scene);
        addProgressStage.setTitle("Add Progress");
        addProgressStage.show();
    }

    public void openAverageProgressStage(Stage previousStage, Course cursus) {
        Stage averageProgressStage = new Stage();

        Button averageProgressStagecloseButton = new Button("Close");

        averageProgressStagecloseButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                averageProgressStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        TableView<Module> aTableView = new TableView<>();
        ObservableList<Module> moduleData = FXCollections.observableArrayList();

        TableColumn<Module, Integer> moduleIDCol = new TableColumn<>("Module ID");
        moduleIDCol.setCellValueFactory(new PropertyValueFactory<>("moduleID"));

        TableColumn<Module, String> moduleTitleCol = new TableColumn<>("Module Title");
        moduleTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Module, Double> averageProgressPercentageCol = new TableColumn<>("Average Progress Percentage");
        averageProgressPercentageCol.setCellValueFactory(new PropertyValueFactory<>("averageProgressPercentage"));

        aTableView.getColumns().addAll(moduleIDCol, moduleTitleCol, averageProgressPercentageCol);

        try {

            Connection connection = database.getConnection();

            String query = "SELECT " +
                    "moduleID, " +
                    "title AS ModuleTitle, " +
                    "AVG(percentageWatched) AS AverageProgressPercentage " +
                    "FROM " +
                    "module " +
                    "INNER JOIN " +
                    "progress ON moduleID = contentItemID " +
                    "WHERE " +
                    "courseID = ? " +
                    "GROUP BY " +
                    "moduleID,title;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, cursus.getCourseID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int moduleID = resultSet.getInt("moduleID");
                String moduleTitle = resultSet.getString("ModuleTitle");
                double averageProgressPercentage = resultSet.getDouble("AverageProgressPercentage");
                moduleData.add(new Module(moduleID, moduleTitle, averageProgressPercentage));
            }
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        }

        aTableView.setItems(moduleData);

        VBox hBoxavarageProgress = new VBox();
        hBoxavarageProgress.getChildren().addAll(averageProgressStagecloseButton, aTableView);

        Scene scene = new Scene(hBoxavarageProgress, 700, 400);
        averageProgressStage.setScene(scene);
        averageProgressStage.setTitle("Gemmidelde percentage per cursus");
        averageProgressStage.show();
    }

   

    public void openTop3WebcastStage(Stage previousStage, Webcast webcast) {
        Stage top3WebcastStage = new Stage();

        Button top3WebcastStagecloseButton = new Button("Close");

        top3WebcastStagecloseButton.setOnAction((event) -> {
            if (previousStage != null) {
                previousStage.show();
                top3WebcastStage.close();
            } else {
                System.out.println("Geen vorige stage gevonden.");
            }
        });

        TableView<Webcast> aTableView = new TableView<>();
        ObservableList<Webcast> webcastData = FXCollections.observableArrayList();

        TableColumn<Module, Integer> webcastIDCol = new TableColumn<>("Webcast ID");
        webcastIDCol.setCellValueFactory(new PropertyValueFactory<>("webcastID"));

        TableColumn<Module, String> webcastTitleCol = new TableColumn<>("Webcast Title");
        webcastTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        aTableView.getColumns().addAll(webcastIDCol, webcastTitleCol);

        try {

            Connection connection = database.getConnection();

            String query = "SELECT TOP 3" + 
                                "    webcastID," + 
                                "    title," + 
                                "    SUM(percentageWatched * DATEDIFF(minute, '00:00:00', duration)) AS totalWatchTime_minutes" + 
                                "FROM" + 
                                "    webcast" + 
                                "JOIN" + 
                                "    progress ON webcastID = contentItemID" + 
                                "GROUP BY" + 
                                "    webcastID," + 
                                "    title" + 
                                "ORDER BY" + 
                                "    totalWatchTime_minutes DESC;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, webcast.getWebcastID());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int webcastID = resultSet.getInt("webcastID");
                String webcastTitle = resultSet.getString("webcastTitle");
                webcastData.add(new Webcast(webcastID, webcastTitle));
            }
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
        }

        aTableView.setItems(webcastData);

        VBox hBoxavarageProgress = new VBox();
        hBoxavarageProgress.getChildren().addAll(top3WebcastStagecloseButton, aTableView);

        Scene scene = new Scene(hBoxavarageProgress, 700, 400);
        top3WebcastStage.setScene(scene);
        top3WebcastStage.setTitle("De top 3 Webcasts");
        top3WebcastStage.show();
    }

    private void refreshTable() {
        students.clear();
        courses.clear();
        enrollments.clear();
        modules.clear();
        progress.clear();
        webcasts.clear();

        dataRetrieverStudent = new DataRetriever("student", Student.class);
        try {
            dataRetrieverStudent.retrieveData(students);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dataRetrieverCourse = new DataRetriever("course", Course.class);
        try {
            dataRetrieverCourse.retrieveData(courses);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dataRetrieverEnrollment = new DataRetriever("enrollment", Enrollment.class);
        try {
            dataRetrieverEnrollment.retrieveData(enrollments);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dataRetrieverModule = new DataRetriever("module", Module.class);
        try {
            dataRetrieverModule.retrieveData(modules);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dataRetrieverProgress = new DataRetriever("progress", Progress.class);
        try {
            dataRetrieverProgress.retrieveData(progress);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dataRetrieverWebcast = new DataRetriever("webcast", Webcast.class);
        try {
            dataRetrieverWebcast.retrieveData(webcasts);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}