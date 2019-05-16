package reports;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import appointment_registration.Day;
import appointment_result.AppointmentResultRegistrator;
import check.CheckService;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import core.Core;
import crud_doctors.DoctorsDatabaseAccessor;
import crud_patient.Appointment;
import crud_patient.PatientData;
import crud_patient.PatientDatabaseAcessor;
import javafx.util.Pair;

@objid("0feafbcf-29d2-4f54-ba6a-64d58d87fb3e")
public class ReportMaker {
    public static final ReportMaker instance = new ReportMaker();

    private ReportMaker() {

    }

    private class AppointmentInfo {
        Integer patientId;
        Integer clientId;
        Integer doctorId;
    }

    @objid("27ac7924-4e74-4e32-bd5c-6b6a83fdfd75")
    public String MakeAllDoctorsAppointmentsReport() {
        try {
            Map<Integer, String> doctorNames = new HashMap<>();
            Map<Integer, String> clientNames = new HashMap<>();
            Map<Integer, String> patientNames = new HashMap<>();

            for (Pair<Integer, String> info : DoctorsDatabaseAccessor.instance.GetDoctorsList()) {
                doctorNames.put(info.getKey(), info.getValue());
            }

            for (Pair<Integer, String> info : PatientDatabaseAcessor.instance.GetAllClientsIDs()) {
                clientNames.put(info.getKey(), info.getValue());
            }

            for (Pair<Integer, String> info : PatientDatabaseAcessor.instance.GetAllPatientsIDs()) {
                patientNames.put(info.getKey(), info.getValue());
            }

            Statement statement = Core.GetConnection().createStatement();
            String sql = "SELECT * FROM appointments";
            ResultSet resultSet = statement.executeQuery(sql);

            SortedMap<Integer, SortedMap<Integer, List<AppointmentInfo>>> perDayMap = new TreeMap<>();
            while (resultSet.next()) {
                int dayIndex = resultSet.getInt("day_index");
                if (perDayMap.get(dayIndex) == null) {
                    perDayMap.put(dayIndex, new TreeMap<>());
                }

                SortedMap<Integer, List<AppointmentInfo>> perHourMap = perDayMap.get(dayIndex);
                int hour = resultSet.getInt("hour");

                if (perHourMap.get(hour) == null) {
                    perHourMap.put(hour, new ArrayList<>());
                }

                AppointmentInfo info = new AppointmentInfo();
                info.clientId = resultSet.getInt("client_id");
                info.patientId = resultSet.getInt("patient_id");
                info.doctorId = resultSet.getInt("doctor_id");
                perHourMap.get(hour).add(info);
            }

            StringBuilder resultBuilder = new StringBuilder();
            for (int day : perDayMap.keySet()) {
                resultBuilder.append("Day: ").append(Day.values()[day].name()).append(".\n");
                SortedMap<Integer, List<AppointmentInfo>> perHourMap = perDayMap.get(day);

                for (int hour : perHourMap.keySet()) {
                    resultBuilder.append("    Hour: ").append(hour).append(".\n");
                    for (AppointmentInfo info : perHourMap.get(hour)) {
                        resultBuilder.append("    Doctor: ").append(doctorNames.get(info.doctorId)).append(".\n");
                        resultBuilder.append("    Client: ").append(clientNames.get(info.clientId)).append(".\n");
                        resultBuilder.append("    Patient: ").append(patientNames.get(info.patientId)).append(".\n\n");
                    }
                }
            }

            return resultBuilder.toString();

        } catch (Throwable throwable) {
            Logger lgr = Logger.getLogger(ReportMaker.class.getName());
            lgr.log(Level.SEVERE, throwable.getClass().getName() + ": " + throwable.getMessage(), throwable);
        }

        return null;
    }

    @objid("659a905a-2fb8-49d9-84f4-c8e361f5b3cc")
    public String GetPeriodServicesReportForPatient(final Date start, final Date end, final int clientId, final int patientId) {
        PatientData patientData = PatientDatabaseAcessor.instance.ReceivePatientData(patientId);
        AppointmentResultRegistrator tempServiceRecipeInfoGetter = new AppointmentResultRegistrator();
        StringBuilder textBuilder = new StringBuilder();

        for (Appointment appointment : patientData.veterinaryCard.appointments) {
            if (appointment.date.compareTo(start) >= 0 && appointment.date.compareTo(end) <= 0) {
                BuildText(tempServiceRecipeInfoGetter, appointment, textBuilder);
            }
        }

        return textBuilder.toString();
    }

    private void BuildText(AppointmentResultRegistrator tempServiceRecipeInfoGetter, Appointment appointment,
                           StringBuilder textBuilder) {
        textBuilder.append(CheckService.dataFormat.format(appointment.date)).append("\n");
        tempServiceRecipeInfoGetter.LoadAvailableServicesList();
        tempServiceRecipeInfoGetter.LoadAvailableRecipesList();

        if (!appointment.services.isEmpty()) {
            textBuilder.append("    Services:\n");
            for (int service : appointment.services) {
                Pair<String, Double> data = tempServiceRecipeInfoGetter.GetAvailableServicesList().get(service);

                textBuilder.append("    #").append(service).append(" ").
                        append(data.getKey()).append(" with cost: ").append(data.getValue()).append(".\n");
            }
        }

        if (!appointment.services.isEmpty()) {
            textBuilder.append("    Recipes:\n");
            for (int recipe : appointment.recipes) {
                textBuilder.append("        #").append(recipe).append(" ").
                        append(tempServiceRecipeInfoGetter.GetAvailableRecipesList().get(recipe)).append(".\n");
            }
        }

        textBuilder.append("\n");
    }

}
