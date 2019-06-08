package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import mypackage.MobileNumber;
import mypackage.Person;
import mypackage.RemoteService;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label firstNameNotebookLabel;

    @FXML
    private TableView<Person> notebookTable;

    @FXML
    private TableColumn<Person, String> firstNameNotebookColumn;

    @FXML
    private TableColumn<Person, String> surnameNotebookColumn;

    @FXML
    private TableColumn<Person, MobileNumber> mobileNumberNotebookColumn;

    @FXML
    private TableView<MobileNumber> mobileNumberTable;

    @FXML
    private TableColumn<MobileNumber, String> mobileNumberColumn;

    @FXML
    private Button addMobileNumberButton;

    @FXML
    private Button removeMobileNumberButton;

    @FXML
    private TextField mobileNumberTextField;

    @FXML
    private Label surnameNotebookLabel;

    @FXML
    private TextField firstNameNotebookTextField;

    @FXML
    private TextField surnameNotebookTextField;

    @FXML
    private Button addPersonButton;

    @FXML
    private Button removePersonButton;

    @FXML
    private TableView<Person> findTable;

    @FXML
    private TableColumn<Person, String> firstNameFindColumn;

    @FXML
    private TableColumn<Person, String> surnameFindColumn;

    @FXML
    private TableColumn<Person, MobileNumber> mobileNumberFindColumn;

    @FXML
    private Label surnameFindLabel;

    @FXML
    private Label mobileNumberFindLabel;

    @FXML
    private TextField surnameFindTextField;

    @FXML
    private TextField mobileNumberFindTextField;

    @FXML
    private Button findBySurnameButton;

    @FXML
    private Button findByMobileNumberButton;

    private RemoteService service;
    private List<Person> notebook;
    private List<MobileNumber> mobileNumbers;

    @FXML
    void addMobileNumber(ActionEvent event) throws RemoteException{
        MobileNumber mobileNumber = new MobileNumber(mobileNumberTextField.getText());
        if(!mobileNumber.getMobileNumber().equals("")) {
            service.insertMobileNumber(mobileNumber);
            mobileNumbers.add(mobileNumber);
            mobileNumberTable.setItems(FXCollections.observableArrayList(mobileNumbers));


        }else{
            createAlert("Error", "Can't add this number", "Field is empty");
        }
    }

    public void createAlert(String title, String headerText, String contentText){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    @FXML
    void addPerson(ActionEvent event) throws RemoteException{

        String firstName = firstNameNotebookTextField.getText();
        String surname = surnameNotebookTextField.getText();
        int mobileNumberId = -1;
        mobileNumberId = mobileNumberTable.getSelectionModel().getSelectedIndex();

        if(!firstName.equals("") && !surname.equals("")){
            if(mobileNumberId != -1) {
                int mobileNumberIdInDatabase = mobileNumbers.get(mobileNumberId).getId();

                Person newPerson = new Person(firstName, surname, mobileNumberIdInDatabase);

                service.insertPerson(newPerson);
                notebook.add(new Person(newPerson.getId(), firstName, surname, mobileNumbers.get(mobileNumberId)));
                notebookTable.setItems(FXCollections.observableArrayList(notebook));
            }else{
                createAlert("Error", "Can't add this person", "You didn't choose mobile number");
            }
        }else{
            createAlert("Error", "Can't add this person", "Field \"First name\" or \"Surname\" is empty");
        }



    }

    @FXML
    void findBySurname(ActionEvent event) throws RemoteException {

        String surname = surnameFindTextField.getText();

        List<Person> people = service.showMobileNumberBySurname(surname);
        findTable.setItems(FXCollections.observableArrayList(people));
    }

    @FXML
    void findByMobileNumber(ActionEvent event) throws RemoteException {
        String mobileNumber = mobileNumberFindTextField.getText();

        ArrayList<Person> surnames = service.showSurnameByMobileNumber(mobileNumber);
        findTable.setItems(FXCollections.observableArrayList(surnames));
    }

    @FXML
    void removeMobileNumber(ActionEvent event) throws RemoteException {
        int mobileNumberId = mobileNumberTable.getSelectionModel().getSelectedIndex();
        int mobileNumberIdInDatabase = mobileNumbers.get(mobileNumberId).getId();

        mobileNumbers.remove(mobileNumberId);
        service.deleteMobileNumber(mobileNumberIdInDatabase);

        mobileNumberTable.setItems(FXCollections.observableArrayList(mobileNumbers));

        notebook = service.getNotebook();
        notebookTable.setItems(FXCollections.observableArrayList(notebook));
    }

    @FXML
    void removePerson(ActionEvent event) throws RemoteException{
        int personId = notebookTable.getSelectionModel().getSelectedIndex();
        int personIdInDatabase = notebook.get(personId).getId();

        notebook.remove(personId);
        service.deletePerson(personIdInDatabase);

        notebookTable.setItems(FXCollections.observableArrayList(notebook));
    }


    @FXML
    void initialize() throws RemoteException {

        try{
            Registry reg = LocateRegistry.getRegistry("127.0.0.1",1099);
            service = (RemoteService) reg.lookup("Anna");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        mobileNumbers = service.getMobileNumbers();
        mobileNumberColumn.setCellValueFactory(
                new PropertyValueFactory<MobileNumber, String>("mobileNumber"));
        mobileNumberTable.setItems(FXCollections.observableArrayList(mobileNumbers));


        notebook = service.getNotebook();
        firstNameNotebookColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
        surnameNotebookColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("surname"));
        mobileNumberNotebookColumn.setCellValueFactory(
                new PropertyValueFactory<Person, MobileNumber>("mobileNumber"));
        if (notebook.size() > 0) {
            notebookTable.setItems(FXCollections.observableArrayList(notebook));
        }

        firstNameFindColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
        surnameFindColumn.setCellValueFactory(
                new PropertyValueFactory<Person, String>("surname"));
        mobileNumberFindColumn.setCellValueFactory(
                new PropertyValueFactory<Person, MobileNumber>("mobileNumber"));

    }

}

