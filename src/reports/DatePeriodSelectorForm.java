package reports;

import check.CheckService;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

import javax.swing.*;
import java.text.ParseException;
import java.util.Date;
import java.util.function.BiConsumer;

@objid("392acd43-6331-4405-ae82-97ea4b8a3eb0")
public class DatePeriodSelectorForm {
    private JButton selectButton;
    private JTextField startDateTextField;
    private JTextField endDateTextField;
    private JPanel mainPanel;
    private JFrame frame_;

    @objid("f861f7cd-b3b1-477e-8c57-0f6914c36c49")
    public void Show(BiConsumer<Date, Date> callback) {
        frame_ = new JFrame();
        selectButton.addActionListener(e -> SelectClicked(callback));
        frame_.add(mainPanel);
        frame_.pack();
        frame_.setVisible(true);
        frame_.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void SelectClicked(BiConsumer<Date, Date> callback) {
        try {
            Date start = CheckService.dataFormat.parse(startDateTextField.getText());
            Date end = CheckService.dataFormat.parse(endDateTextField.getText());
            callback.accept(start, end);
            frame_.setVisible(false);
        } catch (ParseException exception) {
            JOptionPane.showMessageDialog(frame_, "Unable to parse dates!",
                    "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    @objid("0cbfd093-9989-4031-b83a-085d90c1a958")
    public void Hide() {
        frame_.setVisible(false);
    }

}
