import login.LoginForm;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Core {
    private Connection sqlConnection = null;

    public Connection GetConnection() {
        return sqlConnection;
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
        String user = "testuser";
        String password = "test623";
        String query = "SELECT VERSION()";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {

                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(Core.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        new LoginForm().Show();
    }
}
