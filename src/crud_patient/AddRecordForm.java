package crud_patient;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("619c3211-1c40-4914-a08d-d742c5b593c5")
public interface AddRecordForm {
    @objid ("905ea6f5-c3b6-431b-870b-a93174d9b7a8")
    void Show();

    @objid ("1de8a0a1-d579-4ed5-bbd3-4e787b8b881b")
    void SetSelectedFieldValue(final String value);

    @objid ("856de406-7f34-49eb-a287-e0614c3499de")
    void TrySend();

    @objid ("329ea2a1-870c-4eaf-a81b-851eb3d9c70a")
    void Abort();

    @objid ("828577fb-238e-4138-8e68-446dcd39d797")
    void ShowIncorrectValueFormatError();

}
