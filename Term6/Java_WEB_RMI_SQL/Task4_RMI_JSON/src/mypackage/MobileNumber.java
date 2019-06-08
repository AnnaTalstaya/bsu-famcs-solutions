package mypackage;

import java.io.Serializable;

public class MobileNumber implements Serializable {

    public static int lastId = -1;

    private String mobileNumber;
    private int id;

    public MobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        lastId += 1;
        this.id = lastId;

    }

    public MobileNumber(int id, String mobileNumber) {
        this.mobileNumber = mobileNumber;
        this.id = id;
        lastId = id;
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
