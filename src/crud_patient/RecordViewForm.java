package crud_patient;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("eb01b10e-3959-4ece-86c7-4d8bd13ead47")
public interface RecordViewForm {
    @objid ("6aa180ac-1263-439c-8320-ff61f14258bd")
    void ShowFor(final int id);

    @objid ("176ec8df-44e2-44b8-ac3c-62ec5d1fdac6")
    void FetchData();

    @objid ("07037e17-1c6d-4953-a0c5-b1f0daf53196")
    void SetSelectedFieldValue(final String value);

    @objid ("f1c44730-2ed1-4c3c-a2e2-898735171a95")
    void Dispose();

    @objid ("d39302e5-ecd4-4c97-9a77-7e06bdaeca48")
    void ShowUnableToChangeError();

}
