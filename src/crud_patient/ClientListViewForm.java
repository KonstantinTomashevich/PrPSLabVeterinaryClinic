package crud_patient;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;

@objid("dcaaf93e-293a-4f76-a66f-276c4e477c97")
public class ClientListViewForm implements RecordListViewForm {
    private JPanel mainPanel;
    private JList clientsList_;
    private JButton viewSelectedClientPatientsButton;
    private JButton viewSelectedClientButton;
    private JFrame frame_;
    private List<Pair<Integer, String>> currentClientsList_ = null;

    public ClientListViewForm() {
        super();
        viewSelectedClientButton.addActionListener(e -> ViewSelectedRecord());
        viewSelectedClientPatientsButton.addActionListener(e -> ShowSelectedClientPatients());
    }

    @objid("adf088be-a139-4245-a440-03a513b4431f")
    public void ShowSelectedClientPatients() {
        int index = clientsList_.getSelectedIndex();
        if (index >= 0 && index < currentClientsList_.size()) {
            new PatientListViewForm().ShowOnlyClientPatients(currentClientsList_.get(index).getKey());
        }
    }

    public void ShowForSelection(Consumer<Pair<Integer, String>> callback) {
        for (ActionListener al : viewSelectedClientButton.getActionListeners()) {
            viewSelectedClientButton.removeActionListener(al);
        }

        viewSelectedClientButton.setText("Select");
        viewSelectedClientButton.addActionListener(e -> AcceptSelection(callback));
        viewSelectedClientPatientsButton.setVisible(false);
        Show();
    }

    public void AcceptSelection(Consumer<Pair<Integer, String>> callback) {
        int index = clientsList_.getSelectedIndex();
        if (index >= 0 && index < currentClientsList_.size()) {
            callback.accept(currentClientsList_.get(index));
        }

        Dispose();
    }

    @objid("b579122e-1b6e-4d25-9206-f210fe4b1383")
    public void Show() {
        frame_ = new JFrame("Clients list");
        FetchData();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @objid("be47620e-c78b-469a-b7f3-a33f8ce49dbc")
    public void FetchData() {
        DefaultListModel<String> model = new DefaultListModel<>();
        currentClientsList_ = PatientDatabaseAcessor.instance.GetAllClientsIDs();

        if (currentClientsList_ != null) {
            for (Pair<Integer, String> data : currentClientsList_) {
                model.addElement(data.getKey() + ": " + data.getValue());
            }
        }

        clientsList_.setModel(model);
    }

    @objid("9c61c599-7db2-4df8-9fac-ed11887dc222")
    public void ViewSelectedRecord() {
        int index = clientsList_.getSelectedIndex();
        if (index >= 0 && index < currentClientsList_.size()) {
            new ClientRecordViewForm().ShowFor(currentClientsList_.get(index).getKey());
        }
    }

    @objid("4f34436e-44b2-4017-8b23-759f0affb765")
    public void Dispose() {
        frame_.setVisible(false);
    }

}
