package mypackage;

public class MobileNumber {
    private String mobileNumber;
    private int id;

    public MobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public MobileNumber(int id, String mobileNumber) {
        this.mobileNumber = mobileNumber;
        this.id = id;
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
