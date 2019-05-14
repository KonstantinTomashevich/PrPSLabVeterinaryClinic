package crud_patient;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import javax.swing.*;

@objid("8c68b29e-e693-4146-8a64-d45abc80f425")
public class PatientRecordViewForm implements RecordViewForm {
    private JPanel mainPanel;
    private JTextField idTextField;
    private JTextField speciesIDTextField;
    private JTextField nameTextField;
    private JTextField genderIDTextField;
    private JTextField birthYearTextField;
    private JTextField ownerIDTextField;
    private JButton updateNameButton;
    private JButton updateSpeciesIDButton;
    private JButton updateGenderIDButton;
    private JButton updateBirthYearButton;
    private JFrame frame_;
    private int patientId_;

    PatientRecordViewForm() {
        super();
        updateNameButton.addActionListener(e -> UpdateField(nameTextField, EditablePatientFields.Name));
        updateSpeciesIDButton.addActionListener(e -> UpdateField(speciesIDTextField, EditablePatientFields.SpeciesID));
        updateGenderIDButton.addActionListener(e -> UpdateField(genderIDTextField, EditablePatientFields.Sex));
        updateBirthYearButton.addActionListener(e -> UpdateField(birthYearTextField, EditablePatientFields.BirthYear));
    }

    private void UpdateField(JTextField source, EditablePatientFields field) {
        if (!PatientDatabaseAcessor.instance.ChangePatientField(patientId_, field, source.getText())) {
            ShowUnableToChangeError();
        } else {
            FetchData();
        }
    }

    @objid("0b84ecfa-5b0e-46fc-8a1d-f6480c93d020")
    public void ShowFor(final int id) {
        frame_ = new JFrame();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        patientId_ = id;
        FetchData();
    }

    @objid("dd0c65b0-41b4-4cd3-9e19-cbcd43cdedbd")
    public void FetchData() {
        PatientData data = PatientDatabaseAcessor.instance.ReceivePatientData(patientId_);
        if (data != null) {
            idTextField.setText("" + data.id);
            nameTextField.setText(data.name);
            speciesIDTextField.setText(data.speciesID + "");
            genderIDTextField.setText(data.sex == Sex.Male ? "0" : "1");
            birthYearTextField.setText(data.birthYear + "");
            ownerIDTextField.setText(data.ownerId + "");
        } else {
            JOptionPane.showMessageDialog(frame_, "Unable to load data!",
                    "Connection error!", JOptionPane.ERROR_MESSAGE);
            Dispose();
        }
    }

    @objid("b166a323-2397-4c90-888e-2f07d9790495")
    public void SetSelectedFieldValue(final String value) {
    }

    @objid("a685a504-9016-455e-9bdd-e4718ec5b629")
    public void Dispose() {
        frame_.setVisible(false);
    }

    @objid("2abaa153-aa0e-4fd7-9046-64d7d46a5e2a")
    public void ShowUnableToChangeError() {
        JOptionPane.showMessageDialog(frame_, "Incorrect format or data in the field!",
                "Unable to change!", JOptionPane.ERROR_MESSAGE);
    }

}
