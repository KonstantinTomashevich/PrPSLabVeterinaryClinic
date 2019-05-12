package crud_patient;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.DataType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@DataType
@objid ("97e9407c-ba40-46a6-b418-943949a41ef2")
public class VeterinaryCard {
    @objid ("510e15cc-cd39-4ef6-ae3b-1816cb048630")
    public List<Appointment> appointments = new ArrayList<Appointment> ();
}
