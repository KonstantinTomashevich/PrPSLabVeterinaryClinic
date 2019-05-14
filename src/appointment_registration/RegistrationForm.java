package appointment_registration;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import crud_doctors.DoctorListViewForm;
import crud_patient.ClientListViewForm;
import crud_patient.PatientListViewForm;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@objid("a89c15ae-af29-43f6-b875-7da631750ae6")
public class RegistrationForm {
    private JPanel mainPanel;
    private JButton selectDoctorButton;
    private JLabel clientLabel;
    private JLabel doctorLabel;
    private JButton selectClientButton;
    private JLabel patientLabel;
    private JButton selectPatientButton;
    private JComboBox dayBox;
    private JComboBox hourBox;
    private JButton addAppointmentButton;

    private JFrame frame_;
    private Pair<Integer, String> selectedDoctorInfo = null;
    private Pair<Integer, String> selectedClientInfo = null;
    private Pair<Integer, String> selectedPatientInfo = null;

    public RegistrationForm() {
        super();
        selectDoctorButton.addActionListener(e -> new DoctorListViewForm().ShowForSelection(this::SelectDoctor));
        selectClientButton.addActionListener(e -> new ClientListViewForm().ShowForSelection(this::SelectClient));
        selectPatientButton.addActionListener(e -> new PatientListViewForm().ShowForSelection(
                selectedClientInfo == null ? -1 : selectedClientInfo.getKey(), this::SelectPatient));
        addAppointmentButton.addActionListener(e -> TryAddAppointment());
    }

    public void TryAddAppointment() {
        if (selectedDoctorInfo == null || selectedClientInfo == null || selectedPatientInfo == null) {
            ShowIncorrectInputError();
            return;
        }

        Boolean result = AppointmentRegistrator.instance.IsDateFree(selectedDoctorInfo.getKey(),
                Day.values()[dayBox.getSelectedIndex()], hourBox.getSelectedIndex());

        if (result == null) {
            ShowIncorrectInputError();
        } else if (result) {

            if (AppointmentRegistrator.instance.Register(selectedDoctorInfo.getKey(),
                    Day.values()[dayBox.getSelectedIndex()], hourBox.getSelectedIndex(),
                    selectedClientInfo.getKey(), selectedPatientInfo.getKey())) {

                Hide();
            } else {
                ShowIncorrectInputError();
            }

        } else {
            JOptionPane.showMessageDialog(frame_, "Please, select another day or time or doctor.",
                    "Time already registered!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ShowIncorrectInputError() {
        JOptionPane.showMessageDialog(frame_, "Incorrect format or data in one of the fields!",
                "Unable to send!", JOptionPane.ERROR_MESSAGE);
    }

    public void SelectDoctor(Pair<Integer, String> pair) {
        selectedDoctorInfo = pair;
        doctorLabel.setText("Doctor: " + pair.getValue() + " (#" + pair.getKey() + ").");
    }

    public void SelectClient(Pair<Integer, String> pair) {
        selectedClientInfo = pair;
        clientLabel.setText("Client: " + pair.getValue() + " (#" + pair.getKey() + ").");
    }

    public void SelectPatient(Pair<Integer, String> pair) {
        selectedPatientInfo = pair;
        patientLabel.setText("Patient: " + pair.getValue() + " (#" + pair.getKey() + ").");
    }

    @objid("9f2e0568-d9b6-4f13-8f4e-1af014eb60bf")
    public void Show() {
        frame_ = new JFrame("Appointment registration");
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @objid("5c3bb4a5-31f5-4c84-b30b-ef0b9044b13c")
    public void Hide() {
        frame_.setVisible(false);
    }

}
