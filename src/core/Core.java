package core;

import login.LoginForm;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Core {
    private static Connection sqlConnection = null;

    public static Connection GetConnection() {
        return sqlConnection;
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        String url = "jdbc:mysql://localhost:3306/vetclinic?" +
                "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "VetclinicApp";
        String password = "Vet45Clinic_123";
        String query = "SELECT VERSION()";

        try {
            sqlConnection = DriverManager.getConnection(url, user, password);
            Statement st = sqlConnection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                System.out.println("MySQL version: " + rs.getString(1) + ".");
            }

            new LoginForm().Show();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Core.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
