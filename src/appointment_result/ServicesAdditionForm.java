package appointment_result;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javafx.util.Pair;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@objid("4f5db3a5-ad28-479f-b428-72ba4957017d")
public class ServicesAdditionForm implements AdditionForm {
    private AppointmentResultRegistrator resultRegistrator_;
    private JPanel mainPanel;
    private JList servicesList;
    private JButton addButton;
    private JButton skipButton;
    private JFrame frame_;
    private ArrayList<Integer> keysIndices;

    public ServicesAdditionForm(AppointmentResultRegistrator registrator) {
        resultRegistrator_ = registrator;
        addButton.addActionListener(e -> AddOption(keysIndices.get(servicesList.getSelectedIndex())));
        skipButton.addActionListener(e -> Skip());
    }

    @objid("2963d495-0070-429b-9d5a-f9e29ec5ecd5")
    public void Show() {
        frame_ = new JFrame("Add services");
        FetchData();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @objid("0fd0ef79-5ec4-4262-8d4e-ddc98cb574dc")
    public void FetchData() {
        DefaultListModel<String> model = new DefaultListModel<>();
        Map<Integer, Pair<String, Double>> services = resultRegistrator_.LoadAvailableServicesList();
        keysIndices = new ArrayList<>();

        if (services != null) {
            Set<Integer> keys = services.keySet();
            for (int key : keys) {
                Pair<String, Double> data = services.get(key);
                model.addElement("#" + key + " " + data.getKey() + ", cost: " + data.getValue() + ".");
                keysIndices.add(key);
            }
        }

        servicesList.setModel(model);
    }

    private void Skip () {
        frame_.setVisible(false);
        new RecipesAdditionForm(resultRegistrator_).Show();
    }

    @objid("da790fcc-8552-4a67-9ec8-9fcde2036788")
    public void AddOption(final int id) {
        if (resultRegistrator_.GetAvailableServicesList().get(id) != null) {
            resultRegistrator_.AddService(id);

            if (JOptionPane.showConfirmDialog(frame_,
                    "Add another service?", "Confirmation", JOptionPane.YES_NO_OPTION) ==
                    JOptionPane.NO_OPTION) {
                frame_.setVisible(false);
                new RecipesAdditionForm(resultRegistrator_).Show();
            }
        }
    }

}
