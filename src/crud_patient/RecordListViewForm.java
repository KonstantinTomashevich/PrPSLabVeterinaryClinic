package crud_patient;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("3ee19d79-f7e2-43d4-bcf5-c49d512ec03e")
public interface RecordListViewForm {
    @objid ("8032a504-a623-47a9-9397-320f855e2770")
    void Show();

    @objid ("aa5bc268-ef58-4cd7-9b0c-8e6aebc951f7")
    void FetchData();

    @objid ("1f78442d-914f-42b6-bdde-c0269b3de59e")
    void ViewSelectedRecord();

    @objid ("155a2718-a0e5-4176-8ee0-bd3d9e7933e7")
    void Dispose();

}
