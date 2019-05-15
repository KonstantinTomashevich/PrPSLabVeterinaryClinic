package appointment_result;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javafx.util.Pair;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@objid("e51b5bc3-7204-4123-9ad1-17e8bb784166")
public class RecipesAdditionForm implements AdditionForm {
    private AppointmentResultRegistrator resultRegistrator_;
    private JList recipesList;
    private JPanel mainPanel;
    private JButton addButton;
    private JButton skipButton;
    private JFrame frame_;
    private ArrayList<Integer> keysIndices;

    RecipesAdditionForm(AppointmentResultRegistrator registrator) {
        resultRegistrator_ = registrator;
        addButton.addActionListener(e -> AddOption(keysIndices.get(recipesList.getSelectedIndex())));
        skipButton.addActionListener(e -> Skip());
    }

    @objid("79d3ca45-9de7-4168-89aa-314b1de12040")
    public void Show() {
        frame_ = new JFrame("Add recipe");
        FetchData();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @objid("19a2bf8d-7a5d-41c1-9ed1-deb05b3e183b")
    public void FetchData() {
        DefaultListModel<String> model = new DefaultListModel<>();
        Map<Integer, String> recipes = resultRegistrator_.LoadAvailableRecipesList();
        keysIndices = new ArrayList<>();

        if (recipes != null) {
            Set<Integer> keys = recipes.keySet();
            for (int key : keys) {
                model.addElement("#" + key + " " + recipes.get(key) + ".");
                keysIndices.add(key);
            }
        }

        recipesList.setModel(model);
    }

    private void Skip() {
        frame_.setVisible(false);
        new SendForm(resultRegistrator_).Show();
    }

    @objid("291dbc4f-66c4-4c8c-b67c-1e59443f4679")
    public void AddOption(final int id) {
        if (resultRegistrator_.GetAvailableRecipesList().get(id) != null) {
            resultRegistrator_.AddRecipe(id);

            if (JOptionPane.showConfirmDialog(frame_,
                    "Add another recipe?", "Confirmation", JOptionPane.YES_NO_OPTION) ==
                    JOptionPane.NO_OPTION) {
                frame_.setVisible(false);
                new SendForm(resultRegistrator_).Show();
            }
        }
    }

}
