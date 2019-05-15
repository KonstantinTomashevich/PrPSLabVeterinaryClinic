package appointment_result;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import core.Core;
import crud_doctors.DoctorsDatabaseAccessor;
import crud_patient.PatientData;
import crud_patient.PatientDatabaseAcessor;
import javafx.util.Pair;
import login.AuthSystem;
import org.omg.IOP.TransactionService;

@objid("d14d44d6-4656-4763-a74b-7c272cdc0960")
public class AppointmentResultRegistrator {
    @objid("82655b3d-c8b2-464c-80df-5118b23e317b")
    private static Map<Integer, Pair<String, Double>> servicesList = null;

    @objid("67dfca84-2d0f-4b05-b037-273105eb55db")
    private static Map<Integer, String> recipesList = null;

    @objid("1e76dfa2-4715-41b0-b280-664ab8b79d30")
    private AppointmentRegistrationSession activeSession = new AppointmentRegistrationSession();

    @objid("ff2ca26d-2bff-414d-ae7d-c96f81346b53")
    public void SelectClient(final int clientId) {
        activeSession.clientID = clientId;
    }

    @objid("d59c64b3-df61-49d2-8e7e-51b3130acb37")
    public void SelectPatient(final int patientId) {
        activeSession.patientID = patientId;
    }

    public int GetClient () {
        return activeSession.clientID;
    }

    public int GetPatient () {
        return activeSession.patientID;
    }

    public final List<Integer> GetSelectedServices () {
        return activeSession.selectedServicesList;
    }

    public final List<Integer> GetSelectedRecipes () {
        return activeSession.selectedRecipesList;
    }

    @objid("bc045214-131c-4d5c-a359-0d0c40dc4b3c")
    public Map<Integer, Pair<String, Double>> LoadAvailableServicesList() {
        try {
            servicesList = new HashMap<>();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT * FROM service_types";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                servicesList.put(resultSet.getInt("service_type_id"), new Pair<>(
                        resultSet.getString("description"), resultSet.getDouble("cost")));
            }

            return servicesList;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("fc1004ff-06c6-429c-9b99-7e9b05011d42")
    public Map<Integer, Pair<String, Double>> GetAvailableServicesList() {
        return servicesList;
    }

    @objid("003f484e-007d-46fd-ad35-b15a9aede1a3")
    public void AddService(final int serviceId) {
        activeSession.selectedServicesList.add(serviceId);
    }

    @objid("8249f314-9017-46f8-88aa-053e268eaace")
    public Map<Integer, String> LoadAvailableRecipesList() {
        try {
            recipesList = new HashMap<>();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT * FROM medicine_types";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                recipesList.put(resultSet.getInt("medicine_type_id"),
                        resultSet.getString("description"));
            }


            return recipesList;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("6a25206b-5815-4df3-9e3f-732145cc36c8")
    public Map<Integer, String> GetAvailableRecipesList() {
        return recipesList;
    }

    @objid("ef0a2021-e5b4-4c84-ac59-c6077375e4b2")
    public void AddRecipe(final int recipeId) {
        activeSession.selectedRecipesList.add(recipeId);
    }

    @objid("a8b08358-d7fc-409d-ac0f-432ebff79dfa")
    public boolean SendAndCloseSession() {
        try {
            // TODO: Remove from registered appointments?
            Statement statement = Core.GetConnection().createStatement();
            PatientData patient = PatientDatabaseAcessor.instance.ReceivePatientData(activeSession.patientID);

            int checkId = PatientDatabaseAcessor.instance.GetFreeID("checks", "check_id");
            String sql = "INSERT INTO checks (check_id, paid, card_id, date) VALUES (" + checkId +
                    ", b'0', " + patient.veterinaryCard.id + ", \"" +
                    DoctorsDatabaseAccessor.dataFormat.format(new Date()) + "\");";
            statement.execute(sql);

            for (int service : activeSession.selectedServicesList) {
                int id = PatientDatabaseAcessor.instance.GetFreeID("services", "service_id");
                sql = "INSERT INTO services (service_id, service_type_id, check_id) VALUES(" +
                        id + ", " + service + ", " + checkId + ");";
                statement.execute(sql);
            }

            for (int medicine : activeSession.selectedRecipesList) {
                int id = PatientDatabaseAcessor.instance.GetFreeID("medicines", "medicine_id");
                sql = "INSERT INTO medicines (medicine_id, medicine_type_id, check_id) VALUES(" +
                        id + ", " + medicine + ", " + checkId + ");";
                statement.execute(sql);
            }

            return true;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return false;
    }

    @objid("7c2f231c-6191-4875-87a5-f1266c4125ec")
    public void CloseSession() {
    }

    @objid("a917cc37-769a-44e3-85e7-62a878eed58a")
    private void ClearSession() {
        activeSession = new AppointmentRegistrationSession();
    }

}
