package mypackage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class XmlControl extends UnicastRemoteObject implements RemoteService{

    private String mobileNumbersFileName = "mobile_numbers.xml";
    private String notebookFileName = "notebook.xml";

    private File fileMobileNumbers;
    private File fileNotebook;

    XmlControl() throws RemoteException {
        fileMobileNumbers = new File(mobileNumbersFileName);
        fileNotebook = new File(notebookFileName);

        try {
            if (fileMobileNumbers.createNewFile()) {
                System.out.println("New fileMobileNumbers is created!");
            } else {
                System.out.println("fileMobileNumbers already exists.");
            }

            if (fileNotebook.createNewFile()) {
                System.out.println("New fileNotebookXsd is created!");
            } else {
                System.out.println("fileNotebookXsd already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> readNotebook(File fileNotebook) throws RemoteException {
        ArrayList<Person> list = new ArrayList<>();
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Notebook.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Notebook notebook = (Notebook) jaxbUnmarshaller.unmarshal(fileNotebook);
            list.addAll(notebook.getRecords());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    private void writeNotebook(ArrayList<Person> records) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Notebook.class); //создаем JAXB-контекст, куда передаем список классов
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();  //Marshaller for serialization
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //эта строчка задает необходимость форматирования (XML будет формировтаться не в одну строку, а в человеко-читаемом виде)
            jaxbMarshaller.marshal(new Notebook(records), fileNotebook); //marshal - размещать

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<MobileNumber> readMobileNumbers(File fileMobileNumbers) throws RemoteException {
        List<MobileNumber> list = new ArrayList<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MobileNumbers.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            MobileNumbers mobileNumbers = (MobileNumbers) jaxbUnmarshaller.unmarshal(fileMobileNumbers);
            list.addAll(mobileNumbers.getMobileNumbers());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    private void writeMobileNumbers(List<MobileNumber> records) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(MobileNumbers.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new MobileNumbers(records), fileMobileNumbers);


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertPerson(Person person) throws RemoteException {
        ArrayList<Person> notebook = readNotebook(fileNotebook);
        notebook.add(person);
        writeNotebook(notebook);
    }

    @Override
    public void insertMobileNumber(MobileNumber mobileNumber) throws RemoteException {
        List<MobileNumber> mobileNumbers = readMobileNumbers(fileMobileNumbers);
        mobileNumbers.add(mobileNumber);
        writeMobileNumbers(mobileNumbers);
    }

    @Override
    public void deletePerson(int personId) throws RemoteException {
        ArrayList<Person> notebook = readNotebook(fileNotebook);
        notebook.removeIf(person -> person.getId() == personId);
        writeNotebook(notebook);
    }

    @Override
    public void deleteMobileNumber(int mobileNumberID) throws RemoteException {
        List<MobileNumber> mobileNumbers = readMobileNumbers(fileMobileNumbers);
        mobileNumbers.removeIf(mobileNumber -> mobileNumber.getId() == mobileNumberID);
        writeMobileNumbers(mobileNumbers);
    }

    @Override
    public List<Person> showMobileNumberBySurname(String surname) throws RemoteException {
        List<Person> notebookList = readNotebook(fileNotebook);
        List<Person> notebook = new ArrayList<>();

        List<MobileNumber> mobileNumbers = readMobileNumbers(fileMobileNumbers);

        notebookList.forEach( person -> {
            if(person.getSurname().equals(surname)){
                getListOfPeopleWithMobileNumbers(notebook, mobileNumbers, person);
            }
        });

        return notebook;
    }

    @Override
    public List<Person> showSurnameByMobileNumber(String mobile_number) throws RemoteException {
        List<Person> notebookList = readNotebook(fileNotebook);
        List<Person> notebook = new ArrayList<>();

        List<MobileNumber> mobileNumbers = readMobileNumbers(fileMobileNumbers);

        int id = -1;

        for(MobileNumber mobileNumber:mobileNumbers){
            if(mobileNumber.getMobileNumber().equals(mobile_number)){
                id = mobileNumber.getId();
                break;
            }
        }

        for(Person person:notebookList){
            if(person.getMobileNumberId() == id){
                notebook.add(
                        new Person(person.getId(), person.getFirstName(), person.getSurname(), new MobileNumber(mobile_number))
                );
            }
        }

        return notebook;
    }

    @Override
    public List<Person> getNotebook() throws RemoteException {
        List<Person> notebook = new ArrayList<>();
        List<Person> notebookList = readNotebook(fileNotebook);

        List<MobileNumber> mobileNumbers = readMobileNumbers(fileMobileNumbers);

        notebookList.forEach( person -> {
            getListOfPeopleWithMobileNumbers(notebook, mobileNumbers, person);
        });
        return notebook;
    }

     private void getListOfPeopleWithMobileNumbers(List<Person> notebook, List<MobileNumber> mobileNumbers, Person person){
         String mn = "";

         for(MobileNumber mobileNumber:mobileNumbers){
             if(mobileNumber.getId() == person.getMobileNumberId()){
                 mn = mobileNumber.getMobileNumber();
                 break;
             }
         }
         notebook.add(
                 new Person(person.getId(), person.getFirstName(), person.getSurname(), new MobileNumber(mn))
         );
     }


    @Override
    public List<MobileNumber> getMobileNumbers() throws RemoteException {
        return readMobileNumbers(fileMobileNumbers);
    }
}
