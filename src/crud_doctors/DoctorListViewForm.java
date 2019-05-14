package crud_doctors;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import crud_patient.RecordListViewForm;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;

@objid("42ac6194-9199-4748-b7ad-6a0a4f5b02ec")
public class DoctorListViewForm implements RecordListViewForm {
    private JList doctorsList_;
    private JPanel mainPanel_;
    private JButton addNewDoctorButton;
    private JButton deleteSelectedDoctorButton;
    private JButton viewSelectedDoctorButton;
    private JButton reloadDataButton;
    private JFrame frame_;
    private List<Pair<Integer, String>> currentDoctorsList_ = null;

    public DoctorListViewForm() {
        super();
        viewSelectedDoctorButton.addActionListener(e -> ViewSelectedRecord());
        deleteSelectedDoctorButton.addActionListener(e -> DeleteSelectedRecord());
        addNewDoctorButton.addActionListener(e -> new AddDoctorRecordForm().Show());
        reloadDataButton.addActionListener(e -> FetchData());
    }

    public void DeleteSelectedRecord() {
        int index = doctorsList_.getSelectedIndex();
        if (index >= 0 && index < currentDoctorsList_.size()) {
            DoctorsDatabaseAccessor.instance.DeleteDoctor(currentDoctorsList_.get(index).getKey());
            FetchData();
        }
    }

    public void ShowForSelection(Consumer<Pair<Integer, String>> callback) {
        for (ActionListener al : viewSelectedDoctorButton.getActionListeners()) {
            viewSelectedDoctorButton.removeActionListener(al);
        }

        viewSelectedDoctorButton.setText("Select");
        viewSelectedDoctorButton.addActionListener(e -> AcceptSelection(callback));

        deleteSelectedDoctorButton.setVisible(false);
        addNewDoctorButton.setVisible(false);
        reloadDataButton.setVisible(false);
        Show();
    }

    public void AcceptSelection(Consumer<Pair<Integer, String>> callback) {
        int index = doctorsList_.getSelectedIndex();
        if (index >= 0 && index < currentDoctorsList_.size()) {
            callback.accept(currentDoctorsList_.get(index));
        }

        Dispose();
    }

    @objid("cb322765-7093-43b8-83f8-b29f5dd4c51d")
    public void Show() {
        frame_ = new JFrame("Doctors list");
        FetchData();
        frame_.add(mainPanel_);
        frame_.pack();

        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @objid("c8522736-ef73-443c-b0e4-85a3e2384481")
    public void FetchData() {
        DefaultListModel<String> model = new DefaultListModel<>();
        currentDoctorsList_ = DoctorsDatabaseAccessor.instance.GetDoctorsList();

        if (currentDoctorsList_ != null) {
            for (Pair<Integer, String> data : currentDoctorsList_) {
                model.addElement(data.getKey() + ": " + data.getValue());
            }
        }

        doctorsList_.setModel(model);
    }

    @objid("50f89b3a-7502-45f7-83b1-b2b7c8fcf816")
    public void ViewSelectedRecord() {
        int index = doctorsList_.getSelectedIndex();
        if (index >= 0 && index < currentDoctorsList_.size()) {
            new DoctorRecordViewForm().ShowFor(currentDoctorsList_.get(index).getKey());
        }
    }

    @objid("ebdf45a7-68b9-46d1-b337-ce8b58175532")
    public void Dispose() {
        frame_.setVisible(false);
    }

}
