package crud_doctors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import crud_patient.AddRecordForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

@objid("f8bc5b9f-35cc-4655-afa0-d95156614372")
public class AddDoctorRecordForm implements AddRecordForm {
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField workingHoursTextField;
    private JTextField emailTextField;
    private JTextField phoneNumberTextField;
    private JTextField experienceTextField;
    private JTextField birthDateTextField;
    private JButton addRecordButton;
    private JPanel mainPanel;
    private JFrame frame_;

    AddDoctorRecordForm() {
        super();
        addRecordButton.addActionListener(e -> TrySend());
    }

    @objid("b13c3ecc-5837-43b0-b162-bcd213f807ff")
    public void Show() {
        frame_ = new JFrame();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @objid("973b47f6-4ca8-420d-ac2b-3e2cef52f7a7")
    public void SetSelectedFieldValue(final String value) {
    }

    @objid("07d450c9-9c98-46a4-a85e-89ebe2b1b0b2")
    public void TrySend() {
        try {
            // TODO: Verify input.
            DoctorData data = new DoctorData();
            data.id = Integer.parseInt(idTextField.getText());
            data.name = nameTextField.getText();
            data.birthday = DoctorsDatabaseAccessor.dataFormat.parse(birthDateTextField.getText());
            data.workYears = Integer.parseInt(experienceTextField.getText());
            data.email = emailTextField.getText();
            data.phoneNumber = phoneNumberTextField.getText();
            data.workingHoursInfo = workingHoursTextField.getText();

            if (!DoctorsDatabaseAccessor.instance.RegisterNewDoctor(data)) {
                ShowIncorrectValueFormatError();
            } else {
                frame_.setVisible(false);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            ShowIncorrectValueFormatError();
        }
    }

    @objid("b6705475-87a8-4014-bf58-dfebc6790bc6")
    public void Abort() {
        frame_.setVisible(false);
    }

    @objid("11891b23-7d20-44db-b18c-03d7ad17ea61")
    public void ShowIncorrectValueFormatError() {
        JOptionPane.showMessageDialog(frame_, "Incorrect format or data in one of the fields!",
                "Unable to send!", JOptionPane.ERROR_MESSAGE);
    }

}
