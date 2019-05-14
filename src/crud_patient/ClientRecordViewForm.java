package crud_patient;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import javax.swing.*;

@objid("6c93c900-ce85-4aa3-b5bd-276e3a4ad978")
public class ClientRecordViewForm implements RecordViewForm {
    private JPanel mainPanel;
    private JTextField idTextField;
    private JTextField nameTextField;
    private JTextField addressTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField personalDiscountTextField;
    private JButton updateNameButton;
    private JButton updateAddressButton;
    private JButton updatePhoneNumberButton;
    private JButton updateEmailButton;
    private JButton updatePersonalDiscountButton;
    private JFrame frame_;
    private int clientId_;

    ClientRecordViewForm() {
        super();
        updateNameButton.addActionListener(e -> UpdateField(nameTextField, EditableClientFields.OfficialName));
        updateAddressButton.addActionListener(e -> UpdateField(addressTextField, EditableClientFields.Address));
        updatePhoneNumberButton.addActionListener(e -> UpdateField(phoneNumberTextField, EditableClientFields.PhoneNumber));
        updateEmailButton.addActionListener(e -> UpdateField(emailTextField, EditableClientFields.Email));
        updatePersonalDiscountButton.addActionListener(e -> UpdateField(personalDiscountTextField, EditableClientFields.PersonalDiscount));
    }

    private void UpdateField(JTextField source, EditableClientFields field) {
        if (!PatientDatabaseAcessor.instance.ChangeClientField(clientId_, field, source.getText())) {
            ShowUnableToChangeError();
        } else {
            FetchData();
        }
    }


    @objid("9ec228b1-8fff-428a-92b8-7becc521033f")
    public void ShowFor(final int id) {
        frame_ = new JFrame();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        clientId_ = id;
        FetchData();
    }

    @objid("a237275b-1577-4b04-9a02-eda93cfa4d6c")
    public void FetchData() {
        ClientData data = PatientDatabaseAcessor.instance.RecieveClientData(clientId_);
        if (data != null) {
            idTextField.setText("" + data.id);
            nameTextField.setText(data.officialName);
            addressTextField.setText(data.address);
            phoneNumberTextField.setText(data.phoneNumber);
            emailTextField.setText(data.email);
            personalDiscountTextField.setText(data.personalDiscount + "");
        } else {
            JOptionPane.showMessageDialog(frame_, "Unable to load data!",
                    "Connection error!", JOptionPane.ERROR_MESSAGE);
            Dispose();
        }
    }

    @objid("2ac52b3d-f59f-4120-bc48-3a05c9554d1d")
    public void SetSelectedFieldValue(final String value) {
    }

    @objid("001aad29-35d1-4cb3-b37b-b10e3fd4a0ac")
    public void Dispose() {
        frame_.setVisible(false);
    }

    @objid("4d8f3525-8784-44cd-b74f-0fab707bb718")
    public void ShowUnableToChangeError() {
        JOptionPane.showMessageDialog(frame_, "Incorrect format or data in the field!",
                "Unable to change!", JOptionPane.ERROR_MESSAGE);
    }

}
