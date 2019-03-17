package mypackage;

public class Person {
    private int id;
    private String firstName;
    private String surname;
    private String mobileNumber;
    private int mobileNumberId;

    public Person(int id, String firstName, String surname, String mobileNumber) {
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

    public Person(String firstName, String surname, String mobileNumber) {
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
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
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
