package crud_patient;

import java.util.Date;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("78622440-5ac8-4403-8b0c-146e4efb4586")
public class Appointment {
    @objid ("6b31b3b0-f530-4d95-bdb7-22acb8c83216")
    public boolean paid;

    @objid ("0ae8cefe-10f8-4864-bc51-7437d7f8f357")
    public int[] services;

    @objid ("c168d706-31f1-49a1-b31f-2b811f8202d2")
    public int[] recipes;

    @objid ("03ec3c0f-405e-4436-962f-e73bfa4a4a36")
    public int id;

    @objid ("2306acee-b46b-4a19-9225-543be76349ad")
    public Date date;

}
