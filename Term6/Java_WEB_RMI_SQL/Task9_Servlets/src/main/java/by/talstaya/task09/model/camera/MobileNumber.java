package by.talstaya.task09.model.camera;

import java.util.Objects;

public class MobileNumber {
    private int idMobileNumber;
    private String mobileNumber;

    public MobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public MobileNumber(int idMobileNumber, String mobileNumber) {
        this.idMobileNumber = idMobileNumber;
        this.mobileNumber = mobileNumber;
    }

    public int getIdMobileNumber() {
        return idMobileNumber;
    }

    public void setIdMobileNumber(int idMobileNumber) {
        this.idMobileNumber = idMobileNumber;
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
                "idMobileNumber=" + idMobileNumber +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MobileNumber)) return false;
        MobileNumber that = (MobileNumber) o;
        return idMobileNumber == that.idMobileNumber &&
                Objects.equals(mobileNumber, that.mobileNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMobileNumber, mobileNumber);
    }
}
