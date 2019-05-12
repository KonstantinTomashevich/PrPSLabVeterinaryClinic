package appointment_result;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("08226eeb-3a70-40c5-b427-495e1916a3a5")
public interface SelectionForm {
    @objid ("47d9b72b-9fae-4ce4-b01a-e0b4329d9007")
    void Show();

    @objid ("b18e27fd-44d5-4831-9988-b61d350be8d9")
    void FetchData();

    @objid ("6f0aef06-8d7c-452e-b829-5177c704f614")
    void Select(final int id);

}
