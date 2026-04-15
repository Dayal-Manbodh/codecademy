package com.codecademy.database;

import java.sql.*;

/**
 * Dit is een voorbeeld Java toepassing waarin je verbinding maakt met een
 * SQLServer database.
 */
public class Database {

    // private static String connectionUrl =
    // "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyDD;user=Steven;password=Striker@2024;encrypt=true;trustServerCertificate=true;";
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // private static String username = "Steven";
    // private static String password = "Striker@2024";
    private static String connectionUrl = "jdbc:sqlserver://localhost;databaseName=codecademy;integratedSecurity=false;encrypt=true;trustServerCertificate=true;";
    private static String username = "bibliotheek";
    private static String password = "dayal2002";
    private static Connection con = null;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(connectionUrl, username, password);
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }
        return con;
    }

    // protected void getDataFromDatabase(ObservableList<Student> data) {
    //     try {
    //         Database Database = new Database();

    //         Connection con = Database.getConnection();

    //         String SQL = "SELECT * FROM student";

    //         PreparedStatement preparedStatement = con.prepareStatement(SQL);

    //         // preparedStatement.setString(1, email);

    //         ResultSet resultSet = preparedStatement.executeQuery();

    //         while (resultSet.next()) {
    //             String email = resultSet.getString("emailAddress");
    //             String name = resultSet.getString("name");
    //             Date birthdate = resultSet.getDate("birthdate");
    //             String gender = resultSet.getString("gender");
    //             String address = resultSet.getString("address");
    //             String city = resultSet.getString("city");
    //             String country = resultSet.getString("country");

    //             data.add(new Student(email, name, birthdate, gender, address, city, country));

    //             // textField.setText(resultSet.getString(culumName));
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

//     protected void getCourseFromDatabase(ObservableList<Course> data) {
//         try {
//             Database Database = new Database();

//             Connection con = Database.getConnection();

//             String SQL = "SELECT * FROM course";

//             PreparedStatement preparedStatement = con.prepareStatement(SQL);

//             // preparedStatement.setString(1, email);

//             ResultSet resultSet = preparedStatement.executeQuery();

//             while (resultSet.next()) {
//                 String courseName = resultSet.getString("courseName");
//                 String subject = resultSet.getString("subject");
//                 String level = resultSet.getString("level");
//                 String introductionText = resultSet.getString("introductionText");

//                 data.add(new Course(courseName, subject, level, introductionText));

//                 // textField.setText(resultSet.getString(culumName));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }


//     protected void getModulesFromDatabase(ObservableList<Module> data) {
//         try {
//             Database database = new Database();
//             Connection connection = database.getConnection();
//             String SQL = "SELECT * FROM module";
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//             ResultSet resultSet = preparedStatement.executeQuery();
    
//             while (resultSet.next()) {
//                 int moduleID = resultSet.getInt("moduleID");
//                 String title = resultSet.getString("title");
//                 String version = resultSet.getString("version");
//                 String description = resultSet.getString("description");
//                 String contactPersonName = resultSet.getString("contactPersonName");
//                 String contactPersonEmail = resultSet.getString("contactPersonEmail");
//                 int courseID = resultSet.getInt("courseID");
    
//                 data.add(new Module(moduleID, title, version, description, contactPersonName, contactPersonEmail, courseID));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
    
//     protected void getWebcastsFromDatabase(ObservableList<Webcast> data) {
//         try {
//             Database database = new Database();
//             Connection connection = database.getConnection();
//             String SQL = "SELECT * FROM webcast";
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//             ResultSet resultSet = preparedStatement.executeQuery();
    
//             while (resultSet.next()) {
//                 int webcastID = resultSet.getInt("webcastID");
//                 String title = resultSet.getString("title");
//                 String description = resultSet.getString("description");
//                 String speakerName = resultSet.getString("speakerName");
//                 String speakerOrganization = resultSet.getString("speakerOrganization");
//                 // Assuming duration and publicationDate are stored as Date in the database
//                 Date duration = resultSet.getDate("duration");
//                 Date publicationDate = resultSet.getDate("publicationDate");
//                 String url = resultSet.getString("url");
    
//                 data.add(new Webcast(webcastID, title, description, speakerName, speakerOrganization, duration, publicationDate, url));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
    
//     protected void getEnrollmentsFromDatabase(ObservableList<Enrollment> data) {
//         try {
//             Database database = new Database();
//             Connection connection = database.getConnection();
//             String SQL = "SELECT * FROM enrollment";
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//             ResultSet resultSet = preparedStatement.executeQuery();
    
//             while (resultSet.next()) {
//                 int enrollmentID = resultSet.getInt("enrollmentID");
//                 String studentEmailAddress = resultSet.getString("studentEmailAddress");
//                 int courseID = resultSet.getInt("courseID");
//                 // Assuming enrollmentDate is stored as Date in the database
//                 Date enrollmentDate = resultSet.getDate("enrollmentDate");
    
//                 data.add(new Enrollment(enrollmentID, studentEmailAddress, courseID, enrollmentDate));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
    
//     protected void getProgressesFromDatabase(ObservableList<Progress> data) {
//         try {
//             Database database = new Database();
//             Connection connection = database.getConnection();
//             String SQL = "SELECT * FROM progress";
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//             ResultSet resultSet = preparedStatement.executeQuery();
    
//             while (resultSet.next()) {
//                 int progressID = resultSet.getInt("progressID");
//                 int enrollmentID = resultSet.getInt("enrollmentID");
//                 int contentItemID = resultSet.getInt("contentItemID");
//                 double percentageWatched = resultSet.getDouble("percentageWatched");
    
//                 data.add(new Progress(progressID, enrollmentID, contentItemID, percentageWatched));
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
    

//     protected boolean setDataInDatabase(String email, String naam, Date geboorteDatum, String geslacht, String adres,
//             String woonplaats, String land) {
//         try {
//             Database Database = new Database();

//             Connection con = Database.getConnection();

//             String SQL = "INSERT INTO student values(?,?,?,?,?,?,?)";

//             PreparedStatement preparedStatement = con.prepareStatement(SQL);

//             preparedStatement.setString(1, email);
//             preparedStatement.setString(2, naam);
//             preparedStatement.setDate(3, geboorteDatum);
//             preparedStatement.setString(4, geslacht);
//             preparedStatement.setString(5, adres);
//             preparedStatement.setString(6, woonplaats);
//             preparedStatement.setString(7, land);

//             int r = preparedStatement.executeUpdate();

//             System.out.println(r + " records inserted");

//             return true;

//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     protected boolean setDataInCourse(String courseName, String subject, String introductionText, String level) {
//         try {
//             Database Database = new Database();

//             Connection con = Database.getConnection();

//             String SQL = "INSERT INTO course values(?,?,?,?)";

//             PreparedStatement preparedStatement = con.prepareStatement(SQL);

//             preparedStatement.setString(1, courseName);
//             preparedStatement.setString(2, subject);
//             preparedStatement.setString(3, introductionText);
//             preparedStatement.setString(4, level);

//             int r = preparedStatement.executeUpdate();

//             System.out.println(r + " records inserted");

//             return true;

//         } catch (SQLException e) {
//             e.printStackTrace();
//             return false;
//         }
//     }

//     protected void updateDataFromDatabase(String email, String naam, String geboorteDatum, Integer geslacht,
//             String adres,
//             String woonplaats, String land) {
//         try {
//             Database Database = new Database();

//             Connection con = Database.getConnection();

//             String SQL = "UPDATE cursist SET Email = ?, Naam = ?, GeboorteDatum = ?, Geslacht = ?, Adres = ?, Woonplaats = ?, Land = ? WHERE Email = ?";

//             PreparedStatement preparedStatement = con.prepareStatement(SQL);

//             preparedStatement.setString(1, email);
//             preparedStatement.setString(2, naam);
//             preparedStatement.setString(3, geboorteDatum);
//             preparedStatement.setInt(4, geslacht);
//             preparedStatement.setString(5, adres);
//             preparedStatement.setString(6, woonplaats);
//             preparedStatement.setString(7, land);
//             preparedStatement.setString(8, email);

//             int r = preparedStatement.executeUpdate();

//             System.out.println(r + " records updated");

//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     protected void deleteDataFromDatabase(String email) {
//         try {
//             Database Database = new Database();

//             Connection con = Database.getConnection();

//             String SQL = "DELETE FROM cursist WHERE Email=?";

//             PreparedStatement preparedStatement = con.prepareStatement(SQL);

//             preparedStatement.setString(1, email);

//             int r = preparedStatement.executeUpdate();

//             System.out.println(r + " records deleted");

//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }
}
