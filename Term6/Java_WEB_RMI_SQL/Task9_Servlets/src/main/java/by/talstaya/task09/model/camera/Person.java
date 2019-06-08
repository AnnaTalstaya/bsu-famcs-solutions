package by.talstaya.task09.model.camera;

import java.util.Objects;

public class Person {
    private int id;
    private String firstName;
    private String surname;
    private MobileNumber mobileNumber;
    private int mobileNumberId;

    public Person(int id, String firstName, String surname, MobileNumber mobileNumber) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.mobileNumber = mobileNumber;
    }

    public Person(String firstName, String surname, int mobileNumberId) {
        this.firstName = firstName;
        this.surname = surname;
        this.mobileNumberId = mobileNumberId;
    }


    public Person(int id, String firstName, String surname) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
    }

    public Person(String firstName, String surname, MobileNumber mobileNumber) {
        this.firstName = firstName;
        this.surname = surname;
        this.mobileNumber = mobileNumber;
    }

    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id &&
                mobileNumberId == person.mobileNumberId &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(mobileNumber, person.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, surname, mobileNumber, mobileNumberId);
    }
}
