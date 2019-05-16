package check;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import core.Core;
import crud_doctors.DoctorsDatabaseAccessor;
import crud_patient.Appointment;
import crud_patient.PatientData;
import crud_patient.PatientDatabaseAcessor;
import javafx.util.Pair;

@objid("85e5ecef-4c7e-433d-b188-ae9419be61ed")
public class CheckService {
    public final static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final CheckService instance = new CheckService();

    private CheckService() {

    }

    @objid("e2cf1b20-8e85-4359-a136-1b4946677f7c")
    public List<Appointment> GetAllClientChecks(final int clientId) {
        try {
            List<Appointment> results = new ArrayList<>();
            List<Pair<Integer, String>> patients = PatientDatabaseAcessor.instance.GetAllClientPatientsIDs(clientId);

            for (Pair<Integer, String> patient : patients) {
                PatientData data = PatientDatabaseAcessor.instance.ReceivePatientData(patient.getKey());
                results.addAll(data.veterinaryCard.appointments);
            }

            return results;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(CheckService.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("267ffdcd-f448-42c1-ba66-b58d084d9f67")
    public boolean MarkCheckPaid(final int checkId) {
        try {
            Statement statement = Core.GetConnection().createStatement();
            String sql = "UPDATE checks SET paid=b'1' WHERE check_id=" + checkId + ";";
            statement.execute(sql);
            return true;

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(CheckService.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return false;
    }

}
