package reports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import crud_patient.ClientListViewForm;
import crud_patient.PatientListViewForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@objid("2a388e16-a8de-429b-ba91-ea5acf7c7921")
public class RequestReportForm {
    private JButton doctorAppListButton;
    private JButton appInPeriodButton;
    private JPanel mainPanel;
    private JFrame frame_;

    public RequestReportForm() {
        doctorAppListButton.addActionListener(e -> DoctorAppointmentsClicked());
        appInPeriodButton.addActionListener(e -> AppointmentsInPeriodClicked());
    }

    @objid("7686d9bb-d9d0-48ae-ab92-e4e3492cdbf0")
    public void Show() {
        frame_ = new JFrame();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void DoctorAppointmentsClicked() {
        new ShowReportForm().Show(ReportMaker.instance.MakeAllDoctorsAppointmentsReport());
    }

    public void AppointmentsInPeriodClicked() {
        new ClientListViewForm().ShowForSelection(pair ->
                new PatientListViewForm().ShowForSelection(pair.getKey(), patientPair ->
                        new DatePeriodSelectorForm().Show((start, end) ->
                                new ShowReportForm().Show(ReportMaker.instance.GetPeriodServicesReportForPatient(
                                        start, end, pair.getKey(), patientPair.getKey())))));
    }

    @objid("d6f05b1d-10d0-48ec-9c87-18093e57bd25")
    public void Hide() {
        frame_.setVisible(false);
    }

}
