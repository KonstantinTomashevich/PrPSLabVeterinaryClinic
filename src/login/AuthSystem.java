package login;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import core.Core;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@objid("b412d867-094f-4f8e-8fd6-e0f53e7db49b")
public class AuthSystem {
    public static final AuthSystem instance = new AuthSystem();

    private AuthSystem() {
        StartLoginSession();
    }

    @objid("a1f24c5b-f8d1-429d-9d8d-cb1c3f6cd59d")
    private Integer attemptsLeft;

    @objid("9aef16c7-eee4-4132-844f-a8cce3c2f86e")
    private AuthToken correctAuthToken;

    @objid("9ee82f44-8acf-4341-933e-44ec601b0799")
    public AuthToken Login(final AuthAttemptData authAttemptData) {
        if (attemptsLeft == 0) {
            return null;
        }

        try {
            Statement statement = Core.GetConnection().createStatement();

            String sql = ("SELECT token, additionalData, isDoctor" +
                    " FROM login WHERE login.login=\"") +
                    authAttemptData.Login +
                    "\" AND login.password=\"" + authAttemptData.Password + "\";";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                correctAuthToken = new AuthToken();
                correctAuthToken.token = resultSet.getString("token");
                correctAuthToken.name = authAttemptData.Login;
                correctAuthToken.additionalData = resultSet.getInt("additionalData");
                correctAuthToken.isDoctor = resultSet.getBoolean("isDoctor");
                return correctAuthToken;
            } else {
                attemptsLeft--;
                return null;
            }

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("eeae8809-ce5f-4dab-a2f5-f480558e995b")
    public void StartLoginSession() {
        attemptsLeft = 3;
        correctAuthToken = null;
    }

    @objid("0cf058b9-6dff-423b-a8d7-340ff71d87e1")
    public AuthToken GetActiveAccessToken() {
        return correctAuthToken;
    }

    public Integer getAttemptsLeft() {
        return attemptsLeft;
    }
}
