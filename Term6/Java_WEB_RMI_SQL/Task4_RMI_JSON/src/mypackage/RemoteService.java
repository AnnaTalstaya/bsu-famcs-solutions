package mypackage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface RemoteService extends Remote {
    void insertPerson(Person person) throws RemoteException;

    void insertMobileNumber(MobileNumber mobileNumber) throws RemoteException;

    void deletePerson(int personId) throws RemoteException;

    void deleteMobileNumber(int mobileNumberID) throws RemoteException;

    ArrayList<Person> showMobileNumberBySurname(String surname) throws RemoteException;

    ArrayList<Person> showSurnameByMobileNumber(String mobile_number) throws RemoteException;

    List<Person> getNotebook() throws RemoteException;

    List<MobileNumber> getMobileNumbers() throws RemoteException;
}
