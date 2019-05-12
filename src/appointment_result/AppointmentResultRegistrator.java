package appointment_result;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("d14d44d6-4656-4763-a74b-7c272cdc0960")
public class AppointmentResultRegistrator {
    @objid ("1e76dfa2-4715-41b0-b280-664ab8b79d30")
    public AppointmentRegistrationSession activeSession;

    @objid ("ff2ca26d-2bff-414d-ae7d-c96f81346b53")
    public void SelectClient(final int clientId) throws SessionNotFoundException {
    }

    @objid ("d59c64b3-df61-49d2-8e7e-51b3130acb37")
    public void SelectPatient(final int patientId) throws PatientNotOwnerBySelectedClientException, SessionNotFoundException {
    }

    @objid ("bc045214-131c-4d5c-a359-0d0c40dc4b3c")
    public List<Integer> LoadAvailableServicesList() throws SessionNotFoundException {
        // TODO Auto-generated return
        return null;
    }

    @objid ("fc1004ff-06c6-429c-9b99-7e9b05011d42")
    public String GetAvailableServicesList() throws AvailableServicesNotLoadedException, SessionNotFoundException {
        // TODO Auto-generated return
        return null;
    }

    @objid ("003f484e-007d-46fd-ad35-b15a9aede1a3")
    public void AddService(final int serviceId) throws AvailableServicesNotLoadedException, ServiceNotAvailableException, SessionNotFoundException {
    }

    @objid ("8249f314-9017-46f8-88aa-053e268eaace")
    public List<Integer> LoadAvailableRecipesList() throws SessionNotFoundException {
        // TODO Auto-generated return
        return null;
    }

    @objid ("6a25206b-5815-4df3-9e3f-732145cc36c8")
    public List<Integer> GetAvailableRecipesList() throws AvailableRecipesNotLoadedException, SessionNotFoundException {
        // TODO Auto-generated return
        return null;
    }

    @objid ("ef0a2021-e5b4-4c84-ac59-c6077375e4b2")
    public void AddRecipe(final int recipeId) throws AvailableRecipesNotLoadedException, RecipeNotAvailableException, SessionNotFoundException {
    }

    @objid ("a8b08358-d7fc-409d-ac0f-432ebff79dfa")
    public void SendAndCloseSession() throws ErrorsInSessionDataException, SessionNotFoundException {
    }

    @objid ("7c2f231c-6191-4875-87a5-f1266c4125ec")
    public void CloseSession() throws SessionNotFoundException {
    }

    @objid ("a917cc37-769a-44e3-85e7-62a878eed58a")
    private void ClearSession() {
    }

}
