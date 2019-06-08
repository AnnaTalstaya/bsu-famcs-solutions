package mypackage;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MobileNumber implements Serializable {

    public static int lastId = -1;

    @XmlElement
    private String mobileNumber;

    @XmlElement
    private int id;

    public MobileNumber() {
    }

    public MobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        lastId += 1;
        this.id = lastId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public int getId() {
        return id;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setIdMobileNumber(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return mobileNumber;
    }
}
