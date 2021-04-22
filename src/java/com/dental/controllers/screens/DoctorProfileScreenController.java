package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.dao.DBConnection;
import com.dental.models.Doctor;
import com.dental.utils.Navigateable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.print.Doc;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
    public DoctorProfileScreenController(){
        this.crudDAO = new CrudDAO<>(Doctor.class);
    }


    @FXML
    public void handleAddClicked(ActionEvent event) {
        this.getValues();
        if( doctorName.isEmpty() ||
                doctorDOB.isEmpty() ||
                doctorGender.equals('n') ||
                doctorCAddress.isEmpty() ||
                doctorPAddress.isEmpty() ||
                doctorMobile.isEmpty() ||
                doctorLandNo.isEmpty() ||
                doctorMail.isEmpty() ||
                doctorBalance.toString().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all fields!");
            alert.show();
            return;
        }
        Doctor doctor = new Doctor(UUID.randomUUID().toString(), doctorName, doctorGender, doctorDOB, doctorCAddress, doctorPAddress, doctorMobile, doctorLandNo, doctorMail, doctorBalance);
        crudDAO.create(doctor);
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
        this.getValues();
        if( doctorName.isEmpty() ||
                doctorDOB.isEmpty() ||
                doctorGender.equals('n') ||
                doctorCAddress.isEmpty() ||
                doctorPAddress.isEmpty() ||
                doctorMobile.isEmpty() ||
                doctorLandNo.isEmpty() ||
                doctorMail.isEmpty() ||
                doctorBalance.toString().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all fields!");
            alert.show();
            return;
        }
        //update function here
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctorSrNoCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("id"));
        doctorNameCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("name"));
        doctorAddressCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("paymentAddress"));
        doctorMobileCol.setCellValueFactory(new PropertyValueFactory<Doctor, String>("mobileNumber"));


        doctorList = crudDAO.list();
        doctorTable.getItems().addAll(doctorList);
        for(Doctor doctor : doctorList){
            System.out.print(doctor);
        }

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

}
