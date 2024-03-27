import java.sql.*;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

/**
 * Dit is een voorbeeld Java toepassing waarin je verbinding maakt met een
 * SQLServer database.
 */
public class Database {

    // private static String connectionUrl =
    // "jdbc:sqlserver://localhost;databaseName=CodeCademyDD;integratedSecurity=false;encrypt=true;trustServerCertificate=true;";
    private static String connectionUrl = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeCademyDD;user=Steven;password=Striker@2024;encrypt=true;trustServerCertificate=true;";
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String username = "Steven";
    private static String password = "Striker@2024";
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

    protected void setDataFromDatabase(ObservableList<Person> data) {
        try {
            Database Database = new Database();

            Connection con = Database.getConnection();

            String SQL = "SELECT * FROM cursist";

            PreparedStatement preparedStatement = con.prepareStatement(SQL);

            // preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("Naam");
                String lastName = resultSet.getString("Email");
                data.add(new Person(firstName, lastName));

                // textField.setText(resultSet.getString(culumName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected boolean setDataInDatabase(String email, String naam, String geboorteDatum, Integer geslacht, String adres,
            String woonplaats, String land) {
        try {
            Database Database = new Database();

            Connection con = Database.getConnection();

            String SQL = "INSERT INTO cursist values(?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(SQL);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, naam);
            preparedStatement.setString(3, geboorteDatum);
            preparedStatement.setInt(4, geslacht);
            preparedStatement.setString(5, adres);
            preparedStatement.setString(6, woonplaats);
            preparedStatement.setString(7, land);

            int r = preparedStatement.executeUpdate();

            System.out.println(r + " records inserted");

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void updateDataFromDatabase(String email, String naam, String geboorteDatum, Integer geslacht, String adres,
    String woonplaats, String land) {
        try {
            Database Database = new Database();

            Connection con = Database.getConnection();

            String SQL = "UPDATE cursist SET Email = ?, Naam = ?, GeboorteDatum = ?, Geslacht = ?, Adres = ?, Woonplaats = ?, Land = ? WHERE Email = ?";

            PreparedStatement preparedStatement = con.prepareStatement(SQL);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, naam);
            preparedStatement.setString(3, geboorteDatum);
            preparedStatement.setInt(4, geslacht);
            preparedStatement.setString(5, adres);
            preparedStatement.setString(6, woonplaats);
            preparedStatement.setString(7, land);
            preparedStatement.setString(8, email);

            int r = preparedStatement.executeUpdate();

            System.out.println(r + " records updated");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void deleteDataFromDatabase(String email) {
        try {
            Database Database = new Database();

            Connection con = Database.getConnection();

            String SQL = "DELETE FROM cursist WHERE Email=?";

            PreparedStatement preparedStatement = con.prepareStatement(SQL);

            preparedStatement.setString(1, email);

            int r = preparedStatement.executeUpdate();

            System.out.println(r + " records deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
