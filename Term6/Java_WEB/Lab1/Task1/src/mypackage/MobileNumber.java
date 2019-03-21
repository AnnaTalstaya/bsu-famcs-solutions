package mypackage;

public class MobileNumber {
    private String mobileNumber;

    public MobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "MobileNumber{" +
                "mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
