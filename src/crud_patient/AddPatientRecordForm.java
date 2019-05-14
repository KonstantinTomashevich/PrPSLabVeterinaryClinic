package crud_patient;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import javax.swing.*;

@objid ("e28f086b-8945-47e4-98ed-334e45b4168e")
public class AddPatientRecordForm implements AddRecordForm {
    private JPanel mainPanel;
    private JTextField idTextField;
    private JButton addRecordButton;
    private JTextField speciesIDTextField;
    private JTextField nameTextField;
    private JTextField genderIDTextField;
    private JTextField birthYearTextField;
    private JTextField ownerIDTextField;
    private JFrame frame_;

    AddPatientRecordForm() {
        super();
        addRecordButton.addActionListener(e -> TrySend());
    }

    @objid ("277b42cc-123a-440a-bc41-473f52d78765")
    public void Show() {
        frame_ = new JFrame();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @objid ("9358a4fa-fa47-4c33-bc05-92f543d4abcf")
    public void SetSelectedFieldValue(final String value) {
    }

    @objid ("65cab14a-da08-4f63-b5ac-813fbc94806e")
    public void TrySend() {
        try {
            // TODO: Verify input.
            PatientData data = new PatientData();
            data.id = Integer.parseInt(idTextField.getText());
            data.name = nameTextField.getText();
            data.speciesID = Integer.parseInt(speciesIDTextField.getText());
            data.sex = Integer.parseInt(genderIDTextField.getText()) == 0 ? Sex.Male : Sex.Female;
            data.birthYear = Integer.parseInt(birthYearTextField.getText());
            data.ownerId = Integer.parseInt(ownerIDTextField.getText());

            if (!PatientDatabaseAcessor.instance.AddPatient(data)) {
                ShowIncorrectValueFormatError();
            } else {
                frame_.setVisible(false);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            ShowIncorrectValueFormatError();
        }
    }

    @objid ("58b3ec8b-0d21-4115-a612-c3d47fc69453")
    public void Abort() {
        frame_.setVisible(false);
    }

    @objid ("7787b90f-a746-4d6d-a45a-1773a4426bf3")
    public void ShowIncorrectValueFormatError() {
        JOptionPane.showMessageDialog(frame_, "Incorrect format or data in one of the fields!",
                "Unable to send!", JOptionPane.ERROR_MESSAGE);
    }

}
