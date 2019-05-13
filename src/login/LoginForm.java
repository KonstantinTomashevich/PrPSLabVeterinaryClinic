package login;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import core.DoctorForm;
import core.RegistrationForm;

import javax.swing.*;
import java.time.Clock;

@objid("a7dacc21-4a1a-4b40-b034-0fa21a39f56d")
public class LoginForm {
    private JPanel mainPanel_;
    private JLabel attemptsLeftLabel_;
    private JTextField inputLoginTextField;
    private JButton loginButton;
    private JPasswordField inputPasswordPasswordField;
    private JFrame frame_;

    private void UpdateAttemptsLabel() {
        attemptsLeftLabel_.setText("Attempts left: " + AuthSystem.instance.getAttemptsLeft() + ".");
    }

    public LoginForm() {
        super();
        loginButton.addActionListener(e -> {
            AuthAttemptData data = new AuthAttemptData();
            data.Login = inputLoginTextField.getText();
            data.Password = inputPasswordPasswordField.getText();
            AttemptToLogin(data);
        });
    }

    @objid("29eec764-0fc8-4131-8cb5-c2dd61e62eac")
    public void Show() {
        AuthSystem.instance.StartLoginSession();

        if (frame_ != null) {
            Dispose();
        }

        frame_ = new JFrame();
        frame_.add(mainPanel_);
        frame_.pack();
        frame_.setVisible(true);

        UpdateAttemptsLabel();
        frame_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @objid("7d87a4a5-794f-47cc-94f0-fc7499cd7bcb")
    public void Dispose() {
        frame_.setVisible(false);
    }

    @objid("df17dd36-fb4d-4960-b5b3-0c98905df127")
    public void AttemptToLogin(final AuthAttemptData attempt) {
        if (AuthSystem.instance.Login(attempt) != null) {
            ShowLoginSuccessful();
        } else {
            ShowLoginError();
            if (AuthSystem.instance.getAttemptsLeft() == 0) {
                Block();
            }

            UpdateAttemptsLabel();
        }
    }

    @objid("445a7d4c-2bc8-4589-85fd-119a32bcc9c8")
    public void ShowLoginSuccessful() {
        JOptionPane.showMessageDialog(frame_, ("Successfully logged as " +
                        (AuthSystem.instance.GetActiveAccessToken().isDoctor ? "doctor" : "registration employee") +
                        " " + inputLoginTextField.getText() + "."), "Login successful!",
                JOptionPane.INFORMATION_MESSAGE);
        Dispose();

        if (AuthSystem.instance.GetActiveAccessToken().isDoctor) {
            new DoctorForm();
        } else {
            new RegistrationForm();
        }
    }

    @objid("63a4c90e-7d27-4a4a-a239-7d1dadbc2e19")
    public void ShowLoginError() {
        JOptionPane.showMessageDialog(frame_, "Check login and password correctness!", "Login failed!",
                JOptionPane.ERROR_MESSAGE);
    }

    @objid("3b31ddfa-6697-4318-b44a-b510dda6fa65")
    public void Block() {
        long start = Clock.systemUTC().millis();
        while (Clock.systemUTC().millis() - start < 1000 * 60 * 10) {
            JOptionPane.showMessageDialog(frame_, "No more attempts left, wait 10 minutes!",
                    "Access blocked!",
                    JOptionPane.ERROR_MESSAGE);
        }

        AuthSystem.instance.StartLoginSession();
    }

}
