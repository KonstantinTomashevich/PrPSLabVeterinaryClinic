package crud_patient;

import com.modeliosoft.modelio.javadesigner.annotations.DataType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@DataType
@objid("9fafcd21-2188-4b85-a01d-ed68b878b3a6")
public class ClientData {
    @objid("6267e7ce-c3bc-489d-afa3-3cd572629cb7")
    public String officialName;

    @objid("f7d6eddf-1b17-4d9b-a916-0cda2a769e19")
    public String address;

    @objid("1fa3db62-4afc-4619-8978-052653217744")
    public String phoneNumber;

    @objid("799f22d3-5ef8-410a-894a-8644773f069c")
    public String email;

    @objid("1fb4019c-142d-4836-9990-638394fcf106")
    public float personalDiscount;

    @objid("3b062f80-9927-46ff-8b8b-c0a7bac66405")
    public int[] patientIds;

    @objid("fad80e0f-cd0b-4a23-bde4-60276813ca60")
    public int id;

    @objid("df4a50eb-11c1-4441-9fec-b08acc994ff6")
    public VeterinaryCard card;

}
