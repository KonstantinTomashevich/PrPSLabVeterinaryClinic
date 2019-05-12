package crud_patient;

import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.DataType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@DataType
@objid ("e339f771-a813-46f9-9f62-733ac97192d5")
public class PatientData {
    @objid ("94e30d82-4771-49e4-90fe-b8cea3a714de")
    public PatientData speciesID;

    @objid ("e7181786-fa4b-48a3-9f0a-8b1f7bfc829f")
    public String name;

    @objid ("030d5684-b87d-4ce7-b1b7-27ef1222c59d")
    public Sex sex;

    @objid ("9fe6712a-b0b8-49fd-8981-a0012fc33601")
    public Date birthDate;

    @objid ("dd5330e7-edc3-422a-8895-68b497489928")
    public int ownerId;

    @objid ("d94b84f2-3ba5-49f7-a1fa-29f13f7ed916")
    public int id;

    @objid ("5596cb56-d241-49ba-b93d-5ea1ef1b3ccc")
    public VeterinaryCard veterinaryCard;

}
