/**
 * Created by Anna Talstaya on 29.04.2019.
 */

package mypackage;

import java.io.Serializable;

/**
 * <p>Class for storing mobile number into 'mobile_numbers' table</p>
 * implements {@link Serializable}
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class MobileNumber implements Serializable {
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
