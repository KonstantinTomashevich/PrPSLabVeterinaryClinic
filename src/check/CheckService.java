package check;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import crud_patient.Appointment;

@objid ("85e5ecef-4c7e-433d-b188-ae9419be61ed")
public class CheckService {
    @objid ("e2cf1b20-8e85-4359-a136-1b4946677f7c")
    public List<Appointment> GetAllClientChecks(final int clientId) {
        // TODO Auto-generated return
        return null;
    }

    @objid ("267ffdcd-f448-42c1-ba66-b58d084d9f67")
    public boolean MarkCheckPaid(final int checkId) {
        // TODO Auto-generated return
        return false;
    }

}
