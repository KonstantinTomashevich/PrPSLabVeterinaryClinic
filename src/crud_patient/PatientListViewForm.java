package crud_patient;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;

@objid("f29d7042-afc2-42e3-9f13-5d956e4cdd1a")
public class PatientListViewForm implements RecordListViewForm {
    private JPanel mainPanel;
    private JList patientsList_;
    private JButton viewSelectedPatientButton;
    private JFrame frame_;
    private List<Pair<Integer, String>> currentPatientsList_ = null;
    private int selectedClient = -1;

    public PatientListViewForm() {
        super();
        viewSelectedPatientButton.addActionListener(e -> ViewSelectedRecord());
    }

    public void ShowForSelection(final int clientId, Consumer<Pair<Integer, String>> callback) {
        for (ActionListener al : viewSelectedPatientButton.getActionListeners()) {
            viewSelectedPatientButton.removeActionListener(al);
        }

        viewSelectedPatientButton.setText("Select");
        viewSelectedPatientButton.addActionListener(e -> AcceptSelection(callback));
        ShowOnlyClientPatients(clientId);
    }

    public void AcceptSelection(Consumer<Pair<Integer, String>> callback) {
        int index = patientsList_.getSelectedIndex();
        if (index >= 0 && index < currentPatientsList_.size()) {
            callback.accept(currentPatientsList_.get(index));
        }

        Dispose();
    }

    @objid("535c5382-f8af-415f-8a75-a70b6e581c95")
    public void ShowOnlyClientPatients(final int clientId) {
        selectedClient = clientId;
        Show();
    }

    @objid("dbb3e46d-a7b3-459b-9c47-cc866e66dc35")
    public void Show() {
        frame_ = new JFrame("Patient list" + (selectedClient != -1 ? "for client " + selectedClient : ""));
        FetchData();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @objid("d7c1f888-2707-4a2c-8928-ca6e9580dbb1")
    public void FetchData() {
        DefaultListModel<String> model = new DefaultListModel<>();
        currentPatientsList_ = selectedClient == -1 ?
                PatientDatabaseAcessor.instance.GetAllPatientsIDs() :
                PatientDatabaseAcessor.instance.GetAllClientPatientsIDs(selectedClient);

        if (currentPatientsList_ != null) {
            for (Pair<Integer, String> data : currentPatientsList_) {
                model.addElement(data.getKey() + ": " + data.getValue());
            }
        }

        patientsList_.setModel(model);
    }

    @objid("4abb0da3-b531-488f-9953-7528a1a6a663")
    public void ViewSelectedRecord() {
        int index = patientsList_.getSelectedIndex();
        if (index >= 0 && index < currentPatientsList_.size()) {
            new PatientRecordViewForm().ShowFor(currentPatientsList_.get(index).getKey());
        }
    }

    @objid("5022babe-bff1-4376-a2f6-12f1f45568da")
    public void Dispose() {
        frame_.setVisible(false);
    }

}
