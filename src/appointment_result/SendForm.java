package appointment_result;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import crud_patient.ClientData;
import crud_patient.PatientData;
import crud_patient.PatientDatabaseAcessor;
import javafx.util.Pair;

import javax.swing.*;

@objid("703df987-f971-4a79-9afa-0a99b4ec479d")
public class SendForm {
    private AppointmentResultRegistrator resultRegistrator_;
    private JTextArea resultTextArea;
    private JPanel mainPanel;
    private JButton sendButton;
    private JButton abortButton;
    private JFrame frame_;

    SendForm(AppointmentResultRegistrator registrator) {
        resultRegistrator_ = registrator;
        sendButton.addActionListener(e -> Confirm());
        abortButton.addActionListener(e -> Abort());
    }

    @objid("675d9fd5-5e72-4ed6-a8ae-287ce3ce9f3c")
    public void Show() {
        frame_ = new JFrame("Accept appointment registration");
        frame_.add(mainPanel);
        BuildText();
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void BuildText() {
        ClientData client = PatientDatabaseAcessor.instance.RecieveClientData(resultRegistrator_.GetClient());
        PatientData patient = PatientDatabaseAcessor.instance.ReceivePatientData(resultRegistrator_.GetPatient());

        StringBuilder textBuilder = new StringBuilder();
        textBuilder.append("Client: #").append(client.id).append(" ").append(client.officialName).append(".\n");
        textBuilder.append("Patient: #").append(patient.id).append(" ").append(patient.name).append(".\n");

        if (!resultRegistrator_.GetSelectedServices().isEmpty()) {
            textBuilder.append("Services:\n");
            for (int service : resultRegistrator_.GetSelectedServices()) {
                Pair<String, Double> data = resultRegistrator_.GetAvailableServicesList().get(service);

                textBuilder.append("    #").append(service).append(" ").
                        append(data.getKey()).append(" with cost: ").append(data.getValue()).append(".\n");
            }
        }

        if (!resultRegistrator_.GetSelectedRecipes().isEmpty()) {
            textBuilder.append("Recipes:\n");
            for (int recipe : resultRegistrator_.GetSelectedRecipes()) {
                textBuilder.append("    #").append(recipe).append(" ").
                        append(resultRegistrator_.GetAvailableRecipesList().get(recipe)).append(".\n");
            }
        }

        resultTextArea.setText(textBuilder.toString());
    }

    @objid("0cde4ee5-618f-4a42-9920-e56f6476ba40")
    public void Confirm() {
        if (!resultRegistrator_.SendAndCloseSession()) {
            JOptionPane.showMessageDialog(frame_, "Unable to send appointment registration!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }

        frame_.setVisible(false);
    }

    @objid("c6e35a1d-9c38-4b9c-b578-618482dac276")
    public void Abort() {
        resultRegistrator_.CloseSession();
        frame_.setVisible(false);
    }
}
