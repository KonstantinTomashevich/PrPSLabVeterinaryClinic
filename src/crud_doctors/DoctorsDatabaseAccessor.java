package crud_doctors;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("95057fa3-8033-448c-a60b-35e231c0f859")
public class DoctorsDatabaseAccessor {
    @objid ("289c471f-4625-4ddd-b29f-1c49a8cd888b")
    public boolean RegisterNewDoctor(final DoctorData data) {
        // TODO Auto-generated return
        return false;
    }

    @objid ("e0d34df0-f018-4b23-a72c-7a25e54c765e")
    public boolean DeleteDoctor(final int id) {
        // TODO Auto-generated return
        return false;
    }

    @objid ("2121ffdc-29bf-48b9-9840-49a781b6ae78")
    public List<Integer> GetDoctorsList() {
        // TODO Auto-generated return
        return null;
    }

    @objid ("b7f854d7-d05d-43bd-83c7-dbf80440dd36")
    public DoctorData GetDoctorData(final int id) {
        // TODO Auto-generated return
        return null;
    }

    @objid ("120b535d-ac50-41ab-8f50-ae80d2207a10")
    public boolean UpdateDoctorRecord(final int id, final DoctorEditableField field, final String value) {
        // TODO Auto-generated return
        return false;
    }

}
