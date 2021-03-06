package crud_patient;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@objid("c6967ef1-ab00-4bb5-9464-a3d514460349")
public class PatientDatabaseAcessorMainForm {
    private JPanel mainPanel;
    private JButton showClientsButton;
    private JButton addNewPatientButton;
    private JButton showAllPatientsButton;
    private JButton addNewClientButton;
    private JFrame frame_;

    public PatientDatabaseAcessorMainForm() {
        super();
        showClientsButton.addActionListener(e -> ViewClientsList());
        addNewClientButton.addActionListener(e -> AddClient());
        showAllPatientsButton.addActionListener(e -> ViewPatientsList());
        addNewPatientButton.addActionListener(e -> AddPatient());
    }

    @objid("b0d88fe9-6355-42c5-8919-f6a72cd20088")
    public void Show() {
        frame_ = new JFrame();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @objid("c3cf2210-6423-4962-a0ef-b76db599f3b7")
    public void ViewClientsList() {
        new ClientListViewForm().Show();
    }

    @objid("a87500dd-9f5e-4183-970d-1c9c0cada2db")
    public void ViewPatientsList() {
        new PatientListViewForm().Show();
    }

    @objid("406acaea-256d-4853-bad0-f10e7c57e5ee")
    public void AddClient() {
        new AddClientRecordForm().Show();
    }

    @objid("0617869d-4363-4b31-9028-244ea37316ce")
    public void AddPatient() {
        new AddPatientRecordForm().Show();
    }

    @objid("315a97fb-ab3e-40bf-8a63-cfe3a3adda82")
    public void Dispose() {
        frame_.setVisible(false);
    }

}
