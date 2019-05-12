package crud_patient;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("db5aff97-1d97-486e-b721-63f892a996de")
public class PatientDatabaseAcessor {
    @objid ("ecd34a73-f2b3-4a6e-8abf-387be4bd69fc")
    public List<Integer> GetAllClientsIDs() {
        // TODO Auto-generated return
        return 0;
    }

    @objid ("54d02348-8805-4aa2-b800-c47056ef94ce")
    public List<Integer> GetAllPatientsIDs() {
        // TODO Auto-generated return
        return 0;
    }

    @objid ("063bc698-ab77-4ec2-ad2e-5ef9336ba359")
    public List<Integer> GetAllClientPatientsIDs(final int clientID) throws IDNotFoundException {
        // TODO Auto-generated return
        return 0;
    }

    @objid ("7784a0eb-37e8-49f3-a2bb-078363a2e63b")
    public ClientData RecieveClientData(final int clientID) throws IDNotFoundException {
        // TODO Auto-generated return
        return null;
    }

    @objid ("787f785e-2fe7-4b55-9fd7-7ec991088caa")
    public PatientData ReceivePatientData(final int patientId) throws IDNotFoundException {
        // TODO Auto-generated return
        return null;
    }

    @objid ("7912b80c-b182-4e37-af3f-bd61bc6faf10")
    public void AddClient(final ClientData client) throws IDConflictException, IncorrectDataExcaption {
    }

    @objid ("45ea396b-b5b1-4302-a99b-4fc310d9075d")
    public int GetFreeID() {
        // TODO Auto-generated return
        return 0;
    }

    @objid ("56018e94-ede3-4c1e-8b3b-38671488c6c3")
    public void AddPatient(final PatientData patient) throws IDConflictException, IncorrectDataExcaption {
    }

    @objid ("48afb474-5371-43ff-9821-b6e37e656ad6")
    public void ChangePatientField(final int patientId, final EditablePatientFields field, final String newValue) throws IDNotFoundException, IncorrectDataExcaption {
    }

    @objid ("8278126e-5c0f-4b2c-ad24-545b39d88c43")
    public void ChangeClientField(final int clientId, final EditableClientFields field, final String newValue) throws IDNotFoundException, IncorrectDataExcaption {
    }

}
