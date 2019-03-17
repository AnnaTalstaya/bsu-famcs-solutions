package mypackage;

public class Phone {
    private int idMobilePhone;
    private String mobilePhone;

    public Phone(int idMobilePhone, String mobilePhone) {
        this.idMobilePhone = idMobilePhone;
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "idMobilePhone=" + idMobilePhone +
                ", mobilePhone='" + mobilePhone + '\'' +
                '}';
    }
}
