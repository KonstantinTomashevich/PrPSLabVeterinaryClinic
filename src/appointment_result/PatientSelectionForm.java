package appointment_result;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import crud_patient.PatientListViewForm;
import javafx.util.Pair;

@objid ("4450e12f-b814-4366-9306-2b1faf17cc2a")
public class PatientSelectionForm implements SelectionForm {
    private AppointmentResultRegistrator resultRegistrator_;

    public PatientSelectionForm(AppointmentResultRegistrator registrator) {
        resultRegistrator_ = registrator;
    }

    @objid ("10a89bfa-90ae-4908-b06b-897990c68d1f")
    public void Show() {
        new PatientListViewForm().ShowForSelection(resultRegistrator_.GetClient(), this::PatientSelected);
    }

    public void PatientSelected(Pair<Integer, String> selected) {
        resultRegistrator_.SelectPatient(selected.getKey());
        new ServicesAdditionForm(resultRegistrator_).Show();
    }

    @objid ("44b72ef7-2baa-414a-8f9a-0b57035db935")
    public void FetchData() {
    }

    @objid ("0b9bbe42-5f82-42d2-a445-15eed91b10ea")
    public void Select(final int id) {
    }

}
