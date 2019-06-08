package mypackage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class MobileNumbers {

    @XmlElement(name = "MobileNumber")
    private List<MobileNumber> mobileNumbers;

    public MobileNumbers() {
        mobileNumbers = new ArrayList<>();
    }

    public MobileNumbers(List<MobileNumber> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public List<MobileNumber> getMobileNumbers() {
        return mobileNumbers;
    }
}
