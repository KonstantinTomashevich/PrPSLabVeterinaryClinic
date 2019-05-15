package crud_patient;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import core.Core;
import javafx.util.Pair;
import login.AuthSystem;

@objid("db5aff97-1d97-486e-b721-63f892a996de")
public class PatientDatabaseAcessor {
    public final static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    public final static PatientDatabaseAcessor instance = new PatientDatabaseAcessor();

    private PatientDatabaseAcessor() {

    }

    @objid("ecd34a73-f2b3-4a6e-8abf-387be4bd69fc")
    public List<Pair<Integer, String>> GetAllClientsIDs() {
        try {
            ArrayList<Pair<Integer, String>> result = new ArrayList<>();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT client_id, name FROM clients;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result.add(
                        new Pair<>(resultSet.getInt("client_id"), resultSet.getString("name")));
            }

            return result;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("54d02348-8805-4aa2-b800-c47056ef94ce")
    public List<Pair<Integer, String>> GetAllPatientsIDs() {
        try {
            ArrayList<Pair<Integer, String>> result = new ArrayList<>();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT patient_id, name FROM patients;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result.add(
                        new Pair<>(resultSet.getInt("patient_id"), resultSet.getString("name")));
            }

            return result;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("063bc698-ab77-4ec2-ad2e-5ef9336ba359")
    public List<Pair<Integer, String>> GetAllClientPatientsIDs(final int clientID) {
        try {
            ArrayList<Pair<Integer, String>> result = new ArrayList<>();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT patient_id, name FROM patients WHERE client_id=" + clientID + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result.add(
                        new Pair<>(resultSet.getInt("patient_id"), resultSet.getString("name")));
            }

            return result;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("7784a0eb-37e8-49f3-a2bb-078363a2e63b")
    public ClientData RecieveClientData(final int clientID) {
        try {
            ClientData data = new ClientData();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT * FROM clients WHERE client_id=" + clientID + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                data.id = resultSet.getInt("client_id");
                data.officialName = resultSet.getString("name");
                data.address = resultSet.getString("address");
                data.phoneNumber = resultSet.getString("phoneNumber");
                data.email = resultSet.getString("email");
                data.personalDiscount = resultSet.getDouble("personalDiscount");
            }

            return data;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("787f785e-2fe7-4b55-9fd7-7ec991088caa")
    public PatientData ReceivePatientData(final int patientId) {
        try {
            PatientData data = new PatientData();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT * FROM patients WHERE patient_id=" + patientId + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                data.id = resultSet.getInt("client_id");
                data.name = resultSet.getString("name");
                data.speciesID = resultSet.getInt("species_id");
                data.sex = resultSet.getInt("gender_id") == 0 ? Sex.Male : Sex.Female;
                data.birthYear = resultSet.getInt("birthYear");
                data.ownerId = resultSet.getInt("client_id");
                data.veterinaryCard = ReceiveVeterinaryCard(resultSet.getInt("card_id"));
            }

            return data;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    public VeterinaryCard ReceiveVeterinaryCard(final int id) {
        try {
            VeterinaryCard card = new VeterinaryCard();
            card.id = id;
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT * FROM checks WHERE card_id=" + id + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.id = resultSet.getInt("check_id");
                appointment.paid = resultSet.getBoolean("paid");
                appointment.date = resultSet.getDate("date");
                appointment.services = ReceiveServices(resultSet.getInt("check_id"));
                appointment.recipes = ReceiveRecipes(resultSet.getInt("check_id"));
                card.appointments.add(appointment);
            }

            return card;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    public List<Integer> ReceiveServices(final int id) {
        try {
            ArrayList<Integer> result = new ArrayList<>();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT service_type_id FROM services WHERE check_id=" + id + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result.add(resultSet.getInt("service_type_id"));
            }

            return result;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    public List<Integer> ReceiveRecipes(final int id) {
        try {
            ArrayList<Integer> result = new ArrayList<>();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT medicine_type_id FROM medicines WHERE check_id=" + id + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result.add(resultSet.getInt("medicine_type_id"));
            }

            return result;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    public Map<Integer, Pair<String, Double>> GetServiceTypes() {
        try {
            HashMap<Integer, Pair<String, Double>> result = new HashMap<>();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT * FROM service_types";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result.put(resultSet.getInt("service_type_id"), new Pair<>(
                        resultSet.getString("description"), resultSet.getDouble("cost")));
            }

            return result;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    public Map<Integer, String> GetSpiciesTypes() {
        try {
            HashMap<Integer, String> result = new HashMap<>();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT * FROM species";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result.put(resultSet.getInt("species_id"), resultSet.getString("name"));
            }

            return result;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("7912b80c-b182-4e37-af3f-bd61bc6faf10")
    public boolean AddClient(final ClientData data) {
        try {
            Statement statement = Core.GetConnection().createStatement();

            String sql = (
                    "INSERT INTO clients (client_id, name, address, phoneNumber, email, personalDiscount)" +
                            " VALUES(" + data.id + ", \"" + data.officialName + "\", \"" +
                            data.address + "\", \"" +
                            data.phoneNumber + "\", \"" +
                            data.email + "\", " +
                            data.personalDiscount + ");");

            statement.execute(sql);
            return true;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return false;
    }

    @objid("45ea396b-b5b1-4302-a99b-4fc310d9075d")
    public int GetFreeID(final String table, final String idName) {
        try {
            Statement statement = Core.GetConnection().createStatement();
            String tableName = table.replace("DROP", ";");
            String sql = "SELECT max(" + idName + ") as maximum FROM " + tableName + ";";

            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("maximum") + 1;
            }

            return 0;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return 0;
    }

    @objid("56018e94-ede3-4c1e-8b3b-38671488c6c3")
    // NOTE: Patient veterinary card must be null there.
    public boolean AddPatient(final PatientData data) {
        try {
            Statement statement = Core.GetConnection().createStatement();
            int freeCardId = GetFreeID("veterinary_cards", "card_id");

            String sql = "INSERT INTO veterinary_cards (card_id) VALUES (" + freeCardId + ");";
            statement.execute(sql);

            sql = "INSERT INTO patients (patient_id, species_id, name, gender_id, birthYear, client_id, card_id)" +
                    " VALUES(" + data.id + ", " + data.speciesID + ", \"" +
                    data.name + "\", " +
                    (data.sex == Sex.Male ? 0 : 1) + ", " +
                    data.birthYear + ", " +
                    data.ownerId + ", " +
                    freeCardId + ");";

            statement.execute(sql);
            return true;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return false;
    }

    @objid("48afb474-5371-43ff-9821-b6e37e656ad6")
    public boolean ChangePatientField(final int patientId, final EditablePatientFields field, final String newValue) {
        try {
            String columnName = "columnnotselected";
            switch (field) {
                case Name:
                    columnName = "name";
                    break;
                case Sex:
                    columnName = "gender_id";
                    break;
                case BirthYear:
                    columnName = "birthYear";
                    break;
                case SpeciesID:
                    columnName = "species_id";
                    break;
            }

            Statement statement = Core.GetConnection().createStatement();
            String sql = ("UPDATE patients SET " + columnName + "=\"" + newValue +
                    "\" WHERE patient_id=" + patientId + ";");
            statement.execute(sql);
            return true;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return false;
    }

    @objid("8278126e-5c0f-4b2c-ad24-545b39d88c43")
    public boolean ChangeClientField(final int clientId, final EditableClientFields field, final String newValue) {
        try {
            String columnName = "columnnotselected";
            switch (field) {
                case OfficialName:
                    columnName = "name";
                    break;
                case Address:
                    columnName = "address";
                    break;
                case PhoneNumber:
                    columnName = "phoneNumber";
                    break;
                case Email:
                    columnName = "email";
                    break;
                case PersonalDiscount:
                    columnName = "personalDiscount";
                    break;
            }

            Statement statement = Core.GetConnection().createStatement();
            String sql = ("UPDATE clients SET " + columnName + "=\"" + newValue +
                    "\" WHERE client_id=" + clientId + ";");
            statement.execute(sql);
            return true;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return false;
    }

}
