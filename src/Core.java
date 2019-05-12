import login.LoginForm;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Core {
    private static Connection sqlConnection = null;

    public Connection GetConnection() {
        return sqlConnection;
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/vetclinic?" +
                "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "VetclinicApp";
        String password = "Vet45Clinic_123";
        String query = "SELECT VERSION()";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                System.out.println("MySQL version: " + rs.getString(1) + ".");
            }

            sqlConnection = con;
            new LoginForm().Show();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Core.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
