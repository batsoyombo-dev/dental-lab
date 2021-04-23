package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.dao.DBConnection;
import com.dental.models.Doctor;
import com.dental.models.Expenses;
import com.dental.utils.Navigateable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

import javax.print.Doc;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class DoctorProfileScreenController implements Initializable, Navigateable {
    @FXML
    private TextField doctorNameTxtField;

    @FXML
    private DatePicker doctorDOBPicker;

    @FXML
    private TextField doctorCAddressTxtField;

    @FXML
    private TextField doctorPAddressTxtField;

    @FXML
    private TextField doctorMobileTxtField;

    @FXML
    private TextField doctorLandNoTxtField;

    @FXML
    private TextField doctorMailTxtField;

    @FXML
    private TextField doctorBalanceTxtField;

    @FXML
    private ChoiceBox<String> doctorGenderChoice;
    @FXML
    private TableView<Doctor> doctorTable;

    @FXML
    private TableColumn<Doctor, String> doctorSrNoCol;

    @FXML
    private TableColumn<Doctor, String> doctorNameCol;

    @FXML
    private TableColumn<Doctor, String > doctorAddressCol;

    @FXML
    private TableColumn<Doctor, String> doctorMobileCol;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;


    private String doctorName;
    private Character doctorGender;
    private String doctorDOB;
    private String doctorCAddress;
    private String doctorPAddress;
    private String doctorMobile;
    private String doctorLandNo;
    private String doctorMail;
    private Float doctorBalance;

    private CrudDAO<Doctor> crudDAO;
    private List<Doctor> doctorList;
    private Doctor updateDoctor;
    private Doctor deleteDoctor;
    public DoctorProfileScreenController(){
        this.crudDAO = new CrudDAO<>(Doctor.class);
        this.updateDoctor = new Doctor(UUID.randomUUID().toString(), "default", 'm', "2012-01-01", "default", "default", "123456", "123456", "default", 0);
    }


    @FXML
    public void handleAddClicked(ActionEvent event) {
        if(check()==1) {
            this.getValues();
            Doctor doctor = new Doctor(UUID.randomUUID().toString(), doctorName, doctorGender, doctorDOB, doctorCAddress, doctorPAddress, doctorMobile, doctorLandNo, doctorMail, doctorBalance);
            crudDAO.create(doctor);
            doctorTable.getItems().add(doctor);
        }
    }
    @FXML
    public void handleAddNewClick(ActionEvent event){
        doctorNameTxtField.setText(null);
        doctorGenderChoice.setValue("None");
        doctorDOBPicker.setValue(null);
        doctorCAddressTxtField.setText(null);
        doctorPAddressTxtField.setText(null);
        doctorMobileTxtField.setText(null);
        doctorLandNoTxtField.setText(null);
        doctorMailTxtField.setText(null);
        doctorBalanceTxtField.setText(null);
    }
    @FXML
    public void handleUpdateClick(ActionEvent event) {
        if(check()==1) {
            this.getValues();
            updateDoctor = doctorTable.getSelectionModel().getSelectedItem();
            updateDoctor.setName(doctorName);
            updateDoctor.setGender(doctorGender);
            updateDoctor.setDob(doctorDOB);
            updateDoctor.setClinicAddress(doctorCAddress);
            updateDoctor.setPaymentAddress(doctorPAddress);
            updateDoctor.setMobileNumber(doctorMobile);
            updateDoctor.setLandlineNumber(doctorLandNo);
            updateDoctor.setEmail(doctorMail);
            updateDoctor.setBalance(doctorBalance);
            crudDAO.update(updateDoctor);
            int index = doctorTable.getSelectionModel().getSelectedIndex();
            doctorTable.getItems().set(index, updateDoctor);
        }
    }
    @FXML
    void handleDelete(ActionEvent event) {
        check();
        this.getValues();
        deleteDoctor = doctorTable.getSelectionModel().getSelectedItem();
        crudDAO.delete(deleteDoctor.getId());
        int index = doctorTable.getSelectionModel().getSelectedIndex();
        doctorTable.getItems().remove(index);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctorSrNoCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("id"));
        doctorNameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("name"));
        doctorAddressCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("paymentAddress"));
        doctorMobileCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("mobileNumber"));


        doctorList = crudDAO.list();
        doctorTable.getItems().addAll(doctorList);
        doctorTable.getSelectionModel().selectedItemProperty().addListener((observable, a , b) ->{
            updateDoctor = doctorTable.getSelectionModel().getSelectedItem();
            if(updateDoctor == null)
                return;
            System.out.println(updateDoctor);
            doctorNameTxtField.setText(updateDoctor.getName());
            if(updateDoctor.getGender()== 'm')
                doctorGenderChoice.setValue("Male");
            if(updateDoctor.getGender()== 'f')
                doctorGenderChoice.setValue("Female");
            LocalDate dob = LocalDate.parse(updateDoctor.getDob());
            doctorDOBPicker.setValue(dob);
            doctorCAddressTxtField.setText(updateDoctor.getClinicAddress());
            doctorPAddressTxtField.setText(updateDoctor.getPaymentAddress());
            doctorMobileTxtField.setText(updateDoctor.getMobileNumber());
            doctorMailTxtField.setText(updateDoctor.getEmail());
            doctorLandNoTxtField.setText(updateDoctor.getLandlineNumber());
            doctorBalanceTxtField.setText(String.valueOf(updateDoctor.getBalance()));
        });
    }
    public void getValues(){
        doctorName = doctorNameTxtField.getText();
        if(doctorGenderChoice.getValue().equals("Male"))
            doctorGender = 'm';
        if(doctorGenderChoice.getValue().equals("Female"))
            doctorGender = 'f';
        if(doctorGenderChoice.getValue().equals("None"))
            doctorGender = 'n';
        doctorDOB = doctorDOBPicker.getValue().toString();
        doctorCAddress = doctorCAddressTxtField.getText();
        doctorPAddress = doctorPAddressTxtField.getText();
        doctorMobile = doctorMobileTxtField.getText();
        doctorLandNo = doctorLandNoTxtField.getText();
        doctorMail = doctorMailTxtField.getText();
        try {
            doctorBalance = Float.valueOf(doctorBalanceTxtField.getText());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public int check(){
        if( doctorNameTxtField.getText().trim().isEmpty() ||
                doctorDOBPicker.getValue().toString().trim().isEmpty() ||
                doctorGenderChoice.getValue().trim().equals("None") ||
                doctorCAddressTxtField.getText().trim().isEmpty() ||
                doctorPAddressTxtField.getText().trim().isEmpty() ||
                doctorMobileTxtField.getText().trim().isEmpty() ||
                doctorLandNoTxtField.getText().trim().isEmpty() ||
                doctorMailTxtField.getText().trim().isEmpty() ||
                doctorBalanceTxtField.getText().trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all fields!");
            alert.show();
            return 0;
        }
        return 1;
    }

}
