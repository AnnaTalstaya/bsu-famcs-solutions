package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mypackage.MobileNumber;
import mypackage.NotebookDatabase;
import mypackage.Person;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import mypackage.Util.SQL_Commands;

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

    private NotebookDatabase notebookDatabase;
    private List<Person> notebook;
    private List<MobileNumber> mobileNumbers;

    @FXML
    void addMobileNumber(ActionEvent event) {
        MobileNumber mobileNumber = new MobileNumber(notebookDatabase.getLastIndex(SQL_Commands.GET_LAST_ID) + 1, mobileNumberTextField.getText());
        if(!mobileNumber.getMobileNumber().equals("")) {
            notebookDatabase.insertMobileNumber(mobileNumber);
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
    void addPerson(ActionEvent event) {

        String firstName = firstNameNotebookTextField.getText();
        String surname = surnameNotebookTextField.getText();
        int mobileNumberId = -1;
        mobileNumberId = mobileNumberTable.getSelectionModel().getSelectedIndex();

        int idPerson = notebookDatabase.getLastIndex(SQL_Commands.GET_LAST_ID_IN_NOTEBOOK) + 1;

        if(!firstName.equals("") && !surname.equals("")){
            if(mobileNumberId != -1) {
                int mobileNumberIdInDatabase = mobileNumbers.get(mobileNumberId).getId();

                notebookDatabase.insertPerson(new Person(firstName, surname, mobileNumberIdInDatabase));
                notebook.add(new Person(idPerson, firstName, surname, mobileNumbers.get(mobileNumberId)));
                notebookTable.setItems(FXCollections.observableArrayList(notebook));
            }else{
                createAlert("Error", "Can't add this person", "You didn't choose mobile number");
            }
        }else{
            createAlert("Error", "Can't add this person", "Field \"First name\" or \"Surname\" is empty");
        }



    }

    @FXML
    void findBySurname(ActionEvent event) {

        String surname = surnameFindTextField.getText();

        List<Person> people = notebookDatabase.showMobileNumberBySurname(surname);
        findTable.setItems(FXCollections.observableArrayList(people));
    }

    @FXML
    void findByMobileNumber(ActionEvent event) {
        String mobileNumber = mobileNumberFindTextField.getText();

        ArrayList<Person> surnames = notebookDatabase.showSurnameByMobileNumber(mobileNumber);
        findTable.setItems(FXCollections.observableArrayList(surnames));
    }

    @FXML
    void removeMobileNumber(ActionEvent event) {
        int mobileNumberId = mobileNumberTable.getSelectionModel().getSelectedIndex();
        int mobileNumberIdInDatabase = mobileNumbers.get(mobileNumberId).getId();

        mobileNumbers.remove(mobileNumberId);
        notebookDatabase.deleteMobileNumber(mobileNumberIdInDatabase);

        mobileNumberTable.setItems(FXCollections.observableArrayList(mobileNumbers));

        notebook = notebookDatabase.getNotebook();
        notebookTable.setItems(FXCollections.observableArrayList(notebook));
    }

    @FXML
    void removePerson(ActionEvent event) {
        int personId = notebookTable.getSelectionModel().getSelectedIndex();
        int personIdInDatabase = notebook.get(personId).getId();

        notebook.remove(personId);
        notebookDatabase.deletePerson(personIdInDatabase);

        notebookTable.setItems(FXCollections.observableArrayList(notebook));
    }


    @FXML
    void initialize() {

        notebookDatabase = new NotebookDatabase();

        mobileNumbers = notebookDatabase.getMobileNumbers();
        mobileNumberColumn.setCellValueFactory(
                new PropertyValueFactory<MobileNumber, String>("mobileNumber"));
        mobileNumberTable.setItems(FXCollections.observableArrayList(mobileNumbers));

        notebook = notebookDatabase.getNotebook();
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

