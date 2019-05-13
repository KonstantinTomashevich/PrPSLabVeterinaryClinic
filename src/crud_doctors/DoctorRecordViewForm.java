package crud_doctors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import crud_patient.RecordViewForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@objid("0c8c74ee-1c4d-4441-9f28-e8d806aa3967")
public class DoctorRecordViewForm implements RecordViewForm {
    private JTextField idTextField;
    private JTextField workingHoursTextField;
    private JTextField emailTextField;
    private JTextField nameTextField;
    private JTextField birthDateTextField;
    private JTextField experienceTextField;
    private JTextField phoneNumberTextField;
    private JButton updateNameButton;
    private JButton updateBirthDayButton;
    private JButton updateExperienceButton;
    private JButton updatePhoneNumberButton;
    private JButton updateEmailButton;
    private JButton updateWorkingHoursButton;
    private JPanel mainPanel;
    private JFrame frame_;
    private int doctorId_;

    DoctorRecordViewForm() {
        super();
        updateNameButton.addActionListener(e -> UpdateField(nameTextField, DoctorEditableField.Name));
        updateBirthDayButton.addActionListener(e -> UpdateField(birthDateTextField, DoctorEditableField.Birthday));
        updateExperienceButton.addActionListener(e -> UpdateField(experienceTextField, DoctorEditableField.WorkYears));

        updatePhoneNumberButton.addActionListener(e -> UpdateField(phoneNumberTextField, DoctorEditableField.PhoneNumber));
        updateEmailButton.addActionListener(e -> UpdateField(emailTextField, DoctorEditableField.Email));
        updateWorkingHoursButton.addActionListener(e -> UpdateField(workingHoursTextField, DoctorEditableField.WorkingHoursInfo));
    }

    private void UpdateField(JTextField source, DoctorEditableField field) {
        if (!DoctorsDatabaseAccessor.instance.UpdateDoctorRecord(doctorId_, field, source.getText())) {
            ShowUnableToChangeError();
        } else {
            FetchData();
        }
    }

    @objid("2797df4a-970e-4aa4-9e98-1df1ec2c9d94")
    public void ShowFor(final int id) {
        frame_ = new JFrame();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        doctorId_ = id;
        FetchData();
    }

    @objid("ac3342ac-3541-4b50-8312-95c431fd44da")
    public void FetchData() {
        DoctorData data = DoctorsDatabaseAccessor.instance.GetDoctorData(doctorId_);
        if (data != null) {
            idTextField.setText("" + data.id);
            nameTextField.setText(data.name);
            birthDateTextField.setText(DoctorsDatabaseAccessor.dataFormat.format(data.birthday));
            experienceTextField.setText("" + data.workYears);
            phoneNumberTextField.setText(data.phoneNumber);
            emailTextField.setText(data.email);
            workingHoursTextField.setText(data.workingHoursInfo);
        } else {
            JOptionPane.showMessageDialog(frame_, "Unable to load data!",
                    "Connection error!", JOptionPane.ERROR_MESSAGE);
            Dispose();
        }
    }

    @objid("f0877417-d0f0-4034-a675-7ef42f216692")
    public void SetSelectedFieldValue(final String value) {
    }

    @objid("4249d95d-fc97-4921-a1f8-08a1f5754e32")
    public void Dispose() {
        frame_.setVisible(false);
    }

    @objid("d1d8b725-196a-4ebf-bf1c-71c770178aff")
    public void ShowUnableToChangeError() {
        JOptionPane.showMessageDialog(frame_, "Incorrect format or data in the field!",
                "Unable to change!", JOptionPane.ERROR_MESSAGE);
    }
}
