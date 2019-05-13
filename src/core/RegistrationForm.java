package core;

import login.AuthSystem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm {
    private JPanel mainPanel_;
    private JButton requestReportButton;
    private JButton registerNewAppointmentButton;
    private JButton manageChecksButton;
    private JButton manageDoctorsButton;
    private JButton managePatientsAndClientsButton;
    private JLabel infoLabel;
    private JFrame frame_;

    public RegistrationForm() {
        super();
        frame_ = new JFrame();
        infoLabel.setText("Registration employee " + AuthSystem.instance.GetActiveAccessToken().name);

        frame_.add(mainPanel_);
        frame_.pack();
        frame_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_.setVisible(true);

        requestReportButton.addActionListener(e -> {
            // TODO: Implement.
        });

        registerNewAppointmentButton.addActionListener(e -> {
            // TODO: Implement.
        });

        manageChecksButton.addActionListener(e -> {
            // TODO: Implement.
        });

        manageDoctorsButton.addActionListener(e -> {
            // TODO: Implement.
        });

        managePatientsAndClientsButton.addActionListener(e -> {
            // TODO: Implement.
        });
    }
}
