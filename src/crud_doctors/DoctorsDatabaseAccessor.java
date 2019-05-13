package crud_doctors;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import core.Core;
import javafx.util.Pair;
import login.AuthSystem;

@objid("95057fa3-8033-448c-a60b-35e231c0f859")
public class DoctorsDatabaseAccessor {
    public final static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    public final static DoctorsDatabaseAccessor instance = new DoctorsDatabaseAccessor();

    private DoctorsDatabaseAccessor() {

    }

    @objid("289c471f-4625-4ddd-b29f-1c49a8cd888b")
    public boolean RegisterNewDoctor(final DoctorData data) {
        try {
            Statement statement = Core.GetConnection().createStatement();

            String sql = (
                    "INSERT INTO doctors (doctor_id, name, birthDate, experience, phoneNumber, email, workingHoursInfo)" +
                            " VALUES(" + data.id + ", \"" + data.name + "\", \"" +
                            dataFormat.format(data.birthday) + "\", " +
                            data.workYears + ", \"" +
                            data.phoneNumber + "\", \"" +
                            data.email + "\", \"" +
                            data.workingHoursInfo + "\");");

            statement.execute(sql);
            return true;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return false;
    }

    @objid("e0d34df0-f018-4b23-a72c-7a25e54c765e")
    public boolean DeleteDoctor(final int id) {
        try {
            Statement statement = Core.GetConnection().createStatement();
            String sql = ("DELETE FROM doctors WHERE doctor_id=" + id + ";");
            return statement.execute(sql);

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return false;
    }

    @objid("2121ffdc-29bf-48b9-9840-49a781b6ae78")
    public List<Pair<Integer, String>> GetDoctorsList() {
        try {
            ArrayList<Pair<Integer, String>> result = new ArrayList<>();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT doctor_id, name FROM doctors;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                result.add(
                        new Pair<>(resultSet.getInt("doctor_id"), resultSet.getString("name")));
            }

            return result;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("b7f854d7-d05d-43bd-83c7-dbf80440dd36")
    public DoctorData GetDoctorData(final int id) {
        try {
            DoctorData data = new DoctorData();
            Statement statement = Core.GetConnection().createStatement();

            String sql = "SELECT * FROM doctors WHERE doctor_id=" + id + ";";
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                data.id = resultSet.getInt("doctor_id");
                data.name = resultSet.getString("name");
                data.birthday = resultSet.getDate("birthDate");
                data.workYears = resultSet.getInt("experience");
                data.email = resultSet.getString("email");
                data.phoneNumber = resultSet.getString("phoneNumber");
                data.workingHoursInfo = resultSet.getString("workingHoursInfo");
            }

            return data;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("120b535d-ac50-41ab-8f50-ae80d2207a10")
    public boolean UpdateDoctorRecord(final int id, final DoctorEditableField field, final String value) {
        try {
            String columnName = "columnnotselected";
            switch (field) {
                case Name:
                    columnName = "name";
                    break;
                case Email:
                    columnName = "email";
                    break;
                case Birthday:
                    columnName = "birthDate";
                    break;
                case WorkYears:
                    columnName = "experience";
                    break;
                case PhoneNumber:
                    columnName = "phoneNumber";
                    break;
                case WorkingHoursInfo:
                    columnName = "workingHoursInfo";
                    break;
            }

            Statement statement = Core.GetConnection().createStatement();
            String sql = ("UPDATE doctors SET " + columnName + "=\"" + value + "\" WHERE doctor_id=" + id + ";");
            statement.execute(sql);
            return true;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AuthSystem.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return false;
    }

}
