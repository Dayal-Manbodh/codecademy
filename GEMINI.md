# Project: Codecademy Desktop Application

## Project Overview

This project is a desktop application developed using JavaFX, designed to manage an educational platform similar to Codecademy. It allows for the management and viewing of data related to students, courses, modules, webcasts, enrollments, and their progress. The application interacts with a Microsoft SQL Server database to persist all data.

## Package Structure

The project has been refactored into a clear package structure to improve organization and adhere to the Single Responsibility Principle:

*   **`com.codecademy.app`**: Contains the main application entry point (`Main.java`).
*   **`com.codecademy.gui`**: Contains all JavaFX GUI-related classes (`GUI.java`).
*   **`com.codecademy.model`**: Contains all data model classes such as `Course`, `Enrollment`, `Module`, `Progress`, `Student`, and `Webcast`.
*   **`com.codecademy.database`**: Manages database connection and core CRUD (Create, Read, Update, Delete) operations through classes like `Database`, `DataInserter`, `DataRetriever`, and `DataUpdater`.
*   **`com.codecademy.validation`**: Contains validation logic (`Validation.java`).
*   **`com.codecademy.test`**: Contains unit tests for various functionalities.

## Technologies Used

*   **Language:** Java
*   **User Interface:** JavaFX
*   **Database:** Microsoft SQL Server
*   **Database Connectivity:** JDBC
*   **Testing Framework:** JUnit

## Database Setup

The application connects to a Microsoft SQL Server database named `codecademy`. To set up the database:

1.  **Create the Database and Schema:** Execute the SQL script located at `DB2/codecademy.sql` (or `DB2/database_createsql.sql` for a more basic schema). This script will create the `codecademy` database, define all necessary tables (student, course, module, webcast, enrollment, progress), and set up foreign key relationships.

2.  **Create Database User:** The application is configured to connect using the username `bibliotheek` and password `dayal2002`. The `DB2/codecademy.sql` script also includes commands to create this user. Ensure this user has appropriate permissions to access and modify the `codecademy` database.

3.  **Update Connection Details (if necessary):** The database connection string and credentials are hardcoded in `com/codecademy/database/Database.java`.
    *   **File:** `com/codecademy/database/Database.java`
    *   **Connection URL:** `jdbc:sqlserver://localhost;databaseName=codecademy;integratedSecurity=false;encrypt=true;trustServerCertificate=true;`
    *   **Username:** `bibliotheek`
    *   **Password:** `dayal2002`

    If your SQL Server instance is not running on `localhost`, or if you prefer different credentials, you will need to update these values in `com/codecademy/database/Database.java` accordingly.

    A visual representation of the database schema can be found in `DB2/codeacademydatabaseERD.drawio (1).png`.

## Building and Running

This project is a standard Java application using JavaFX. It does not appear to use a build automation tool like Maven or Gradle.

### Prerequisites

*   Java Development Kit (JDK) 8 or higher (JavaFX typically requires specific JDK versions or external libraries).
*   JavaFX SDK (downloadable from [https://gluonhq.com/products/javafx/](https://gluonhq.com/products/javafx/)). Ensure the SDK version is compatible with your JDK.
*   Microsoft SQL Server instance with the `codecademy` database configured as described above.

### Running from an IDE (Recommended)

The easiest and most recommended way to run this project is by importing it into a Java IDE such as IntelliJ IDEA or Eclipse. These IDEs provide excellent support for JavaFX projects and simplify dependency management.

1.  **Import Project:** Import the project as an existing Java project.
2.  **Configure JavaFX SDK:** Configure your IDE to use the downloaded JavaFX SDK. This usually involves adding the JavaFX SDK's `lib` directory as a library to your project's module path.
3.  **Run `com.codecademy.app.Main`:** Execute the `main` method in `com.codecademy.app.Main.java` to start the application.

### Running from the Command Line (Advanced)

Compiling and running JavaFX applications from the command line can be complex due to module path requirements. Ensure your `JAVA_HOME` environment variable is set correctly.

1.  **Compile Java files:**
    Navigate to the project root directory. You will need to specify the path to your JavaFX SDK. Replace `<path_to_javafx_sdk_lib>` with the actual path to the `lib` directory within your JavaFX SDK installation.

    ```bash
    # Example for Windows PowerShell (adjust as per your shell and OS)
    $javafx_sdk_lib = "C:\path\to\javafx-sdk-VERSION\lib"
    $java_files = (Get-ChildItem -Path com -Recurse -Include *.java | ForEach-Object -Process { $_.FullName })

    foreach ($file in $java_files) {
        javac -d bin -sourcepath . --module-path $javafx_sdk_lib --add-modules javafx.controls,javafx.fxml,javafx.graphics $file
    }
    ```

    *Note: If you encounter "module not found" errors, double-check your JavaFX SDK path and ensure it's compatible with your JDK. You may also need to add more JavaFX modules depending on specific UI components used.*

2.  **Run the application:**
    Once compiled, you can run the application. Again, replace `<path_to_javafx_sdk_lib>` with your JavaFX SDK `lib` path.

    ```bash
    # Example for Windows PowerShell (adjust as per your shell and OS)
    $javafx_sdk_lib = "C:\path\to\javafx-sdk-VERSION\lib"
    java --module-path $javafx_sdk_lib --add-modules javafx.controls,javafx.fxml,javafx.graphics -cp bin com.codecademy.app.Main
    ```

## Testing

Unit tests are located in the `com/codecademy/test` package. The project uses JUnit for testing.

To run the tests:

*   **From an IDE:** Most IDEs provide integrated test runners. Simply navigate to the test files (e.g., `DateTests.java`, `EmailTests.java`, `ZipcodeTests.java`) and use the IDE's functionality to run them.
*   **From the Command Line (Advanced):** Running JUnit tests from the command line without a build tool can be intricate, requiring manual management of the classpath for JUnit and Hamcrest libraries. It's generally recommended to use an IDE or a build tool for running tests.

## Development Conventions

*   **UI Framework:** JavaFX is used for building the graphical user interface.
*   **Database Interaction:** Direct JDBC calls are used for database operations, primarily managed through classes in the `com.codecademy.database` package.
*   **Object-Relational Mapping (ORM):** No explicit ORM framework is used; data is mapped manually between Java objects and SQL results.
*   **Validation:** Custom validation logic is implemented in the `com.codecademy.validation.Validation` class.
