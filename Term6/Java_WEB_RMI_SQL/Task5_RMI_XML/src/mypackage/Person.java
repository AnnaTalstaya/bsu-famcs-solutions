package mypackage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Serializable {

    public static int lastId = -1;

    @XmlElement
    private int id;

    @XmlElement
    private String firstName;

    @XmlElement
    private String surname;

    private MobileNumber mobileNumber;

    @XmlElement
    private int mobileNumberId;

    public Person() {
    }

    public Person(int id, String firstName, String surname, MobileNumber mobileNumber) {
        this.id = id;
        lastId = id;
        this.firstName = firstName;
        this.surname = surname;
        this.mobileNumber = mobileNumber;
    }

    public Person(String firstName, String surname, int mobileNumberId) {
        lastId += 1;
        this.id = lastId;
        this.firstName = firstName;
        this.surname = surname;
        this.mobileNumberId = mobileNumberId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public MobileNumber getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(MobileNumber mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getMobileNumberId() {
        return mobileNumberId;
    }

    public void setMobileNumberId(int mobileNumberId) {
        this.mobileNumberId = mobileNumberId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", " + mobileNumber + '\'' +
                '}';
    }
}
