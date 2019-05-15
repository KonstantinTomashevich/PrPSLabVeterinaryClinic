package appointment_result;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import crud_patient.ClientListViewForm;
import javafx.util.Pair;

@objid ("db075325-93cd-4ede-8234-ad180249d503")
public class ClientSelectionForm implements SelectionForm {
    private AppointmentResultRegistrator resultRegistrator_;

    @objid ("19f0cce1-67b8-4dc6-a898-8a5620ed758c")
    public void Show() {
        resultRegistrator_ = new AppointmentResultRegistrator();
        new ClientListViewForm().ShowForSelection(this::ClientSelected);
    }

    public void ClientSelected(Pair<Integer, String> selected) {
        resultRegistrator_.SelectClient(selected.getKey());
        new PatientSelectionForm(resultRegistrator_).Show();
    }

    @objid ("8cda1d98-56aa-47e4-9eb3-43e73dd9de1a")
    public void FetchData() {
    }

    @objid ("53357b71-f201-4423-8f61-2c336aac3bb3")
    public void Select(final int id) {
    }

}
