package core;

import appointment_result.ClientSelectionForm;
import login.AuthSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorForm {
    private JPanel mainPanel_;
    private JButton requestReportButton;
    private JButton registerAppointmentResultsButton;
    private JLabel infoLabel;
    private JFrame frame_;

    public DoctorForm() {
        super();
        frame_ = new JFrame();
        infoLabel.setText("Doctor " + AuthSystem.instance.GetActiveAccessToken().name);

        frame_.add(mainPanel_);
        frame_.pack();
        frame_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_.setVisible(true);

        requestReportButton.addActionListener(e -> {
            // TODO: Implement.
        });

        registerAppointmentResultsButton.addActionListener(e -> {
            new ClientSelectionForm().Show();
        });
    }
}
