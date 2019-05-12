package appointment_result;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("67a5dbda-95be-4e78-a5bf-d5bffcbd4577")
public interface AdditionForm {
    @objid ("86f42cf5-2f27-480e-8e9c-47b296b0b144")
    void Show();

    @objid ("6a4ba013-2483-4290-acb6-52c6f388a760")
    void FetchData();

    @objid ("4c63ed1b-19ca-4902-b632-f1cf162e6940")
    void AddOption(final int id);

}
