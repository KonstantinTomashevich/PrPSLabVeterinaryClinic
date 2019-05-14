package crud_patient;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import javax.swing.*;

@objid("8edba20c-0bef-4da8-9c58-2a61acf77e30")
public class AddClientRecordForm implements AddRecordForm {
    private JPanel mainPanel;
    private JTextField idTextField;
    private JButton addRecordButton;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField personalDiscountTextField;
    private JFrame frame_;

    AddClientRecordForm() {
        super();
        addRecordButton.addActionListener(e -> TrySend());
    }

    @objid("cf0055a5-b66d-42c7-9d53-f11f7ccbb35c")
    public void Show() {
        frame_ = new JFrame();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @objid("0c6b2889-2d1e-41f9-ae4d-f470f2ed4332")
    public void SetSelectedFieldValue(final String value) {
    }

    @objid("71141a4b-9ecd-479c-a967-ea6ad056ccd9")
    public void TrySend() {
        try {
            // TODO: Verify input.
            ClientData data = new ClientData();
            data.id = Integer.parseInt(idTextField.getText());
            data.officialName = nameTextField.getText();
            data.personalDiscount = Double.parseDouble(personalDiscountTextField.getText());
            data.email = emailTextField.getText();
            data.phoneNumber = phoneNumberTextField.getText();
            data.address = addressTextField.getText();

            if (!PatientDatabaseAcessor.instance.AddClient(data)) {
                ShowIncorrectValueFormatError();
            } else {
                frame_.setVisible(false);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            ShowIncorrectValueFormatError();
        }
    }

    @objid("b6e319da-b23e-44d6-8019-8a96de84c728")
    public void Abort() {
        frame_.setVisible(false);
    }

    @objid("652569a2-55b6-46fb-93d4-dfc2e348304a")
    public void ShowIncorrectValueFormatError() {
        JOptionPane.showMessageDialog(frame_, "Incorrect format or data in one of the fields!",
                "Unable to send!", JOptionPane.ERROR_MESSAGE);
    }

}
