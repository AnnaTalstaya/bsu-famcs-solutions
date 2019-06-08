/**
 * Created by Anna Talstaya on 29.04.2019.
 */

package mypackage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * <p>Interface for RMI</p>
 *
 * @author Anna Talstaya
 * @version 1.0
 */

public interface RemoteService extends Remote {

    /**
     *Inserts a person into 'notebook' table
     *
     * @param person {@link Person} person, that we want to insert into 'notebook' table
     * @throws RemoteException rmi exception
     */
    void insertPerson(Person person) throws RemoteException;

    /**
     *Inserts mobile number into 'mobile_numbers' table
     *
     * @param mobileNumber {@link MobileNumber} mobile number, that we want to insert into 'mobile_numbers' table
     * @throws RemoteException rmi exception
     */
    void insertMobileNumber(MobileNumber mobileNumber) throws RemoteException;

    /**
     *Deletes person from 'notebook' table by id
     *
     * @param personId {@link Person} id of person in 'notebook' table
     * @throws RemoteException rmi exception
     */
    void deletePerson(int personId) throws RemoteException;

    /**
     *Deletes mobile number from 'mobile_numbers' table by id
     *
     * @param mobileNumberID {@link MobileNumber} id of mobile number in 'mobile numbers' table
     * @throws RemoteException rmi exception
     */
    void deleteMobileNumber(int mobileNumberID) throws RemoteException;

    /**
     * Gives people with their mobile numbers by surname
     *
     * @param surname {@link Person} surname of person by which mobile phones are shown
     * @return <pre> {@code List<Person>}</pre> {@link Person}s list
     * @throws RemoteException rmi exception
     */
    List<Person> showMobileNumberBySurname(String surname) throws RemoteException;

    /**
     * Gives person by mobile number
     *
     * @param mobile_number {@link MobileNumber} mobile number by witch person is shown
     * @return <pre> {@code List<Person>}</pre> {@link Person}s list
     * @throws RemoteException rmi exception
     */
    List<Person> showSurnameByMobileNumber(String mobile_number) throws RemoteException;

    /**
     * Gives max id in some database
     *
     * @param command SQL command of getting max id in some database
     * @return <pre> {@code int}</pre> max id
     * @throws RemoteException rmi exception
     */
    int getLastIndex(String command)throws RemoteException;

    /**
     * Gives List of people from 'notebook' table
     *
     * @return <pre> {@code List<Person>}</pre> {@link Person}s list
     * @throws RemoteException rmi exception
     */
    List<Person> getNotebook() throws RemoteException;

    /**
     * Gives List of mobile numbers from 'mobile_numbers' table
     *
     * @return <pre> {@code List<MobileNumber>}</pre> {@link MobileNumber}s list
     * @throws RemoteException rmi exception
     */
    List<MobileNumber> getMobileNumbers() throws RemoteException;
}
