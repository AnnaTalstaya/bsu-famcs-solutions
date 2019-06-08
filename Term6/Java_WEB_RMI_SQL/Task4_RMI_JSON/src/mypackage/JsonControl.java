package mypackage;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class JsonControl extends UnicastRemoteObject implements RemoteService {

    private String fileNameMobileNumbers = "mobileNumbers.json";
    private String fileNameNotebook = "notebook.json";

    private File fileMobileNumbers;
    private File fileNotebook;

    public JsonControl() throws RemoteException {
        fileMobileNumbers = new File(fileNameMobileNumbers);
        fileNotebook = new File(fileNameNotebook);

        try {
            if (fileMobileNumbers.createNewFile()) {
                System.out.println("New fileMobileNumbers is created!");
            } else {
                System.out.println("fileMobileNumbers already exists.");
            }

            if (fileNotebook.createNewFile()) {
                System.out.println("New fileNotebook is created!");
            } else {
                System.out.println("fileNotebook already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertPerson(Person person) throws RemoteException {
        JSONArray jsonArray = getJSONArray(fileNotebook);
        if(jsonArray == null)
            jsonArray = new JSONArray();

        JSONObject personDetails = new JSONObject();
        personDetails.put("id", person.getId());
        personDetails.put("firstName", person.getFirstName());
        personDetails.put("surname", person.getSurname());
        personDetails.put("mobileNumberId", person.getMobileNumberId());

        JSONObject personObject = new JSONObject();
        personObject.put("person", personDetails);

        jsonArray.add(personObject);

        writeJsonToFile(fileNotebook, jsonArray);
    }

    @Override
    public void insertMobileNumber(MobileNumber mobileNumber) throws RemoteException {

        JSONArray jsonArray = getJSONArray(fileMobileNumbers);
        if(jsonArray == null)
            jsonArray = new JSONArray();

        JSONObject mobileNumberDetails = new JSONObject();
        mobileNumberDetails.put("id", mobileNumber.getId());
        mobileNumberDetails.put("number", mobileNumber.getMobileNumber());

        JSONObject mobileNumberObject = new JSONObject();
        mobileNumberObject.put("mobileNumber", mobileNumberDetails);

        jsonArray.add(mobileNumberObject);

        writeJsonToFile(fileMobileNumbers, jsonArray);

    }


    @Override
    public void deletePerson(int personId) throws RemoteException {
        JSONArray notebookList = getJSONArray(fileNotebook);

        for(Object person:notebookList){
            JSONObject personObject = (JSONObject)((JSONObject) person).get("person");
            if(((Long)personObject.get("id")).intValue() == personId){
                notebookList.remove(person);
                break;
            }
        }

        writeJsonToFile(fileNotebook, notebookList);
    }

    @Override
    public void deleteMobileNumber(int mobileNumberID) throws RemoteException {
        JSONArray mobileNumbersList = getJSONArray(fileMobileNumbers);

        for(Object mobileNumber:mobileNumbersList){
            JSONObject mobileNumberObject = (JSONObject)((JSONObject) mobileNumber).get("mobileNumber");
            if(((Long)mobileNumberObject.get("id")).intValue() == mobileNumberID){
                mobileNumbersList.remove(mobileNumber);
                break;
            }
        }

        writeJsonToFile(fileMobileNumbers, mobileNumbersList);
    }

    @Override
    public ArrayList<Person> showMobileNumberBySurname(String surname) throws RemoteException {

        ArrayList<Person> notebook = new ArrayList<>();
        JSONArray notebookList = getJSONArray(fileNotebook);

        JSONArray mobileNumbersList = getJSONArray(fileMobileNumbers);

        notebookList.forEach( person -> {

            JSONObject personObject = (JSONObject)((JSONObject) person).get("person");

            if(((String)personObject.get("surname")).equals(surname)){
                String mobileNumber = "";
                for(Object mobileNumberO:mobileNumbersList){
                    JSONObject mobileNumberObject = (JSONObject)((JSONObject) mobileNumberO).get("mobileNumber");
                    if(((Long)mobileNumberObject.get("id")).intValue() == ((Long)personObject.get("mobileNumberId")).intValue()){
                        mobileNumber = (String)mobileNumberObject.get("number");
                        break;
                    }
                }
                notebook.add(
                        new Person(((Long)personObject.get("id")).intValue(), (String)personObject.get("firstName"), (String)personObject.get("surname"), new MobileNumber(mobileNumber))
                );
            }
        });

        return notebook;
    }

    @Override
    public ArrayList<Person> showSurnameByMobileNumber(String mobile_number) throws RemoteException {
        ArrayList<Person> notebook = new ArrayList<>();
        JSONArray notebookList = getJSONArray(fileNotebook);

        JSONArray mobileNumbersList = getJSONArray(fileMobileNumbers);
        int id = -1;

        for(Object mobileNumber:mobileNumbersList){
            JSONObject mobileNumberObject = (JSONObject)((JSONObject) mobileNumber).get("mobileNumber");
            if(((String)mobileNumberObject.get("number")).equals(mobile_number)){
                id = ((Long)mobileNumberObject.get("id")).intValue();
                break;
            }
        }

        for(Object person:notebookList){
            JSONObject personObject = (JSONObject)((JSONObject) person).get("person");

            if(((Long)personObject.get("mobileNumberId")).intValue() == id){
                notebook.add(
                        new Person(((Long)personObject.get("id")).intValue(), (String)personObject.get("firstName"), (String)personObject.get("surname"), new MobileNumber(mobile_number))
                );
            }
        }

        return notebook;
    }

    @Override
    public List<Person> getNotebook() throws RemoteException {
        List<Person> notebook = new ArrayList<>();
        JSONArray notebookList = getJSONArray(fileNotebook);

        JSONArray mobileNumbersList = getJSONArray(fileMobileNumbers);

        notebookList.forEach( person -> {

            JSONObject personObject = (JSONObject)((JSONObject) person).get("person");
            String mobileNumber = "";

            for(Object mobileNumberO:mobileNumbersList){
                JSONObject mobileNumberObject = (JSONObject)((JSONObject) mobileNumberO).get("mobileNumber");
                if(((Long)mobileNumberObject.get("id")).intValue() == ((Long)personObject.get("mobileNumberId")).intValue()){
                    mobileNumber = (String)mobileNumberObject.get("number");
                    break;
                }
            }

            notebook.add(
                    new Person(((Long)personObject.get("id")).intValue(), (String)personObject.get("firstName"), (String)personObject.get("surname"), new MobileNumber(mobileNumber))
            );
        });
        return notebook;
    }

    @Override
    public List<MobileNumber> getMobileNumbers() throws RemoteException {

        List<MobileNumber> mobileNumbers = new ArrayList<>();
        JSONArray mobileNumbersList = getJSONArray(fileMobileNumbers);

        mobileNumbersList.forEach( mobileNumber -> {

            JSONObject mobileNumberObject = (JSONObject)((JSONObject) mobileNumber).get("mobileNumber");
            mobileNumbers.add(
                    new MobileNumber(((Long)mobileNumberObject.get("id")).intValue(), (String)mobileNumberObject.get("number"))
            );
        });
        return mobileNumbers;
    }

    public JSONArray getJSONArray(File file){
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = new JSONArray();
        try (FileReader reader = new FileReader(file)){
            Object obj = jsonParser.parse(reader);

            jsonArray = (JSONArray) obj;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public void writeJsonToFile(File file, JSONArray jsonArray){
        try (FileWriter fileWriter = new FileWriter(file)) {

            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
