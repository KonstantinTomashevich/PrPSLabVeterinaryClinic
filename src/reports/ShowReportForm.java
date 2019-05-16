package reports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import javax.swing.*;

@objid("15f1a238-40dc-4064-b8a8-7b4ed7ce0301")
public class ShowReportForm {
    private JTextArea reportArea;
    private JPanel mainPanel;
    private JFrame frame_;

    @objid("125fcbc1-9ace-4fb8-957d-1dfdd03e7cca")
    public void Show(String report) {
        frame_ = new JFrame();
        reportArea.setText(report);
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @objid("ac5880e9-0673-4478-897c-4cbd5a545769")
    public void Hide() {
        frame_.setVisible(false);
    }

}
