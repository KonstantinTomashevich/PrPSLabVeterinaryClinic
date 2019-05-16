package check;

import appointment_result.PatientSelectionForm;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import crud_patient.Appointment;
import crud_patient.ClientListViewForm;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@objid("8ea834e3-25f5-4426-a90b-02cee9aeb3c0")
public class ViewClientChecksForm {
    private JPanel mainPanel;
    private JList checksList;
    private JButton viewButton;
    private List<Appointment> appointments_;
    private JFrame frame_;

    public ViewClientChecksForm() {
        super();
        viewButton.addActionListener(e -> ViewSelectedCheck(checksList.getSelectedIndex()));
    }

    @objid("d62841fe-ae10-4acf-8d8c-142e2ee1fd7a")
    public void Show() {
        new ClientListViewForm().ShowForSelection(this::ClientSelected);
    }

    public void ClientSelected(Pair<Integer, String> selected) {
        frame_ = new JFrame("Client " + selected.getValue() + "checks");
        FetchData(selected.getKey());
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void ViewSelectedCheck(int index) {
        new ViewCheckForm(appointments_.get(index)).Show();
    }

    public void FetchData(int clientId) {
        DefaultListModel<String> model = new DefaultListModel<>();
        appointments_ = CheckService.instance.GetAllClientChecks(clientId);

        if (appointments_ != null) {
            for (Appointment appointment : appointments_) {
                model.addElement("#" + appointment.id + " " + appointment.patientName + " " +
                        CheckService.dataFormat.format(appointment.date) + ".");
            }
        }

        checksList.setModel(model);
    }

    @objid("a78a3510-22d3-439d-9b36-8559b8201dd4")
    public void Hide() {
        frame_.setVisible(false);
    }

}
