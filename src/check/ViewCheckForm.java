package check;

import appointment_result.AppointmentResultRegistrator;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import crud_patient.Appointment;
import crud_patient.ClientData;
import crud_patient.PatientData;
import crud_patient.PatientDatabaseAcessor;
import javafx.util.Pair;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@objid("73d27be1-79b0-470a-8243-35754d18c313")
public class ViewCheckForm {
    private JPanel mainPanel;
    private JTextArea infoText;
    private JButton markPaidButton;
    private Appointment appointment_;
    private JFrame frame_;

    public ViewCheckForm(Appointment appointment) {
        super();
        appointment_ = appointment;
        markPaidButton.addActionListener(e -> MarkPaidClicked());
        markPaidButton.setVisible(!appointment.paid);
    }

    private void MarkPaidClicked() {
        if (CheckService.instance.MarkCheckPaid(appointment_.id)) {
            JOptionPane.showMessageDialog(frame_, "Marked as paid!",
                    "Successful!", JOptionPane.INFORMATION_MESSAGE);
            Hide();
        } else {
            JOptionPane.showMessageDialog(frame_, "Unable to mark as paid!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @objid("4c72324f-bce6-4695-92c2-ebaf0157a713")
    public void Show() {
        frame_ = new JFrame();
        BuildText();
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void BuildText() {
        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append("Patient: #").append(appointment_.patientId).append(" ").
                append(appointment_.patientName).append(".\n");

        AppointmentResultRegistrator tempServiceRecipeInfoGetter = new AppointmentResultRegistrator();
        tempServiceRecipeInfoGetter.LoadAvailableServicesList();
        tempServiceRecipeInfoGetter.LoadAvailableRecipesList();

        if (!appointment_.services.isEmpty()) {
            textBuilder.append("Services:\n");
            for (int service : appointment_.services) {
                Pair<String, Double> data = tempServiceRecipeInfoGetter.GetAvailableServicesList().get(service);

                textBuilder.append("    #").append(service).append(" ").
                        append(data.getKey()).append(" with cost: ").append(data.getValue()).append(".\n");
            }
        }

        if (!appointment_.services.isEmpty()) {
            textBuilder.append("Recipes:\n");
            for (int recipe : appointment_.recipes) {
                textBuilder.append("    #").append(recipe).append(" ").
                        append(tempServiceRecipeInfoGetter.GetAvailableRecipesList().get(recipe)).append(".\n");
            }
        }

        textBuilder.append("Paid: ").append(appointment_.paid ? "yes" : "no").append(".\n");
        infoText.setText(textBuilder.toString());
    }

    @objid("3dac2933-51ce-4c98-83d7-2bd97da62265")
    public void Hide() {
    }

}
