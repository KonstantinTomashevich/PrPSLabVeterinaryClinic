package login;

import java.util.Date;

import com.modeliosoft.modelio.javadesigner.annotations.DataType;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@DataType
@objid("e05228ec-e2b7-4aae-9792-b154a23bc116")
public class AuthToken {
    @objid("fc37e367-9747-450c-87bc-1c782e2b6929")
    public String token;

    @objid("1d9d6893-e8b9-4767-b0a2-6f618c3ac1f6")
    public Date expireDate;

    public String name;
    public boolean isDoctor;
    public int additionalData;
}
