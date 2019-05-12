package login;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("b412d867-094f-4f8e-8fd6-e0f53e7db49b")
public class AuthSystem {
    @objid ("a1f24c5b-f8d1-429d-9d8d-cb1c3f6cd59d")
    public Integer attemptsLeft;

    @objid ("9aef16c7-eee4-4132-844f-a8cce3c2f86e")
    private AuthToken correctAuthToken;

    @objid ("dec182e9-a8ae-4b29-8f27-36a64ca8584d")
    public AuthToken ;

    @objid ("9ee82f44-8acf-4341-933e-44ec601b0799")
    public AuthToken Login(final String address, final AuthAttemptData authAttemptData) {
        // TODO Auto-generated return
        return null;
    }

    @objid ("eeae8809-ce5f-4dab-a2f5-f480558e995b")
    public void StartLoginSession() {
    }

    @objid ("0cf058b9-6dff-423b-a8d7-340ff71d87e1")
    public String GetActiveAccessToken() {
        // TODO Auto-generated return
        return null;
    }

}
