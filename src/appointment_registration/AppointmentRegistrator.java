package appointment_registration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.org.apache.xpath.internal.operations.Bool;
import core.Core;
import crud_patient.PatientDatabaseAcessor;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@objid("bc963c1a-6609-45c6-b930-52f898b817e2")
public class AppointmentRegistrator {
    public final static AppointmentRegistrator instance = new AppointmentRegistrator();

    private AppointmentRegistrator() {

    }

    @objid("1e524cbd-657c-4bd9-a56a-6b8cc2246354")
    public Boolean IsDateFree(final int doctorId, final Day day, final int hour) {
        try {
            Statement statement = Core.GetConnection().createStatement();
            String sql = "SELECT * FROM appointments WHERE doctor_id=" + doctorId + " and day_index=" + day.ordinal() +
                    " and hour=" + hour + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            return !resultSet.next();

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AppointmentRegistrator.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("884c44c7-13d2-4892-ab96-8e5e3cbcbd97")
    public boolean Register(final int doctorId, final Day day, final int hour, final int clientId, final int patientId) {
        try {
            Statement statement = Core.GetConnection().createStatement();
            String sql = "INSERT INTO appointments (appointment_id, doctor_id, client_id, patient_id," +
                    "day_index, hour) VALUES(" +
                    PatientDatabaseAcessor.instance.GetFreeID("appointments", "appointment_id") + ", " +
                    doctorId + "," +
                    clientId + ", " +
                    patientId + ", " +
                    day.ordinal() + ", " +
                    hour + ")";

            statement.execute(sql);
            return true;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(AppointmentRegistrator.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return false;
    }

}
