package appointment_result;

import com.modeliosoft.modelio.javadesigner.annotations.DataType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

@DataType
@objid("37581764-af19-42df-963a-1e26f354eb6e")
public class AppointmentRegistrationSession {
    @objid("514ee2ca-05b9-490b-9955-1c76d1e1d828")
    public Integer clientID;

    @objid("095bbb53-2dc1-4c48-ab40-39075b45a186")
    public Integer patientID;

    @objid("f2002d6d-f77d-4a84-9b87-d3890d2848f0")
    public List<Integer> selectedServicesList = new ArrayList<>();

    @objid("3a674721-69b8-453b-aae0-8ec13650fdf5")
    public List<Integer> selectedRecipesList = new ArrayList<>();
}
