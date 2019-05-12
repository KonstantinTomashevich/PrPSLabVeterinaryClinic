package appointment_result;

import com.modeliosoft.modelio.javadesigner.annotations.DataType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@DataType
@objid ("37581764-af19-42df-963a-1e26f354eb6e")
public class AppointmentRegistrationSession {
    @objid ("514ee2ca-05b9-490b-9955-1c76d1e1d828")
    public Integer clientID;

    @objid ("095bbb53-2dc1-4c48-ab40-39075b45a186")
    public Integer patientID;

    @objid ("82655b3d-c8b2-464c-80df-5118b23e317b")
    public int[] servicesList;

    @objid ("f2002d6d-f77d-4a84-9b87-d3890d2848f0")
    public int[] selectedServicesList;

    @objid ("67dfca84-2d0f-4b05-b037-273105eb55db")
    public int[] recipesList;

    @objid ("3a674721-69b8-453b-aae0-8ec13650fdf5")
    public int[] selectedRecipesList;

    @objid ("f92a0727-aa5f-4835-b77e-4ec28e59f5ff")
    public String sessionToken;

}
