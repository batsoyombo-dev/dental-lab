package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.models.Doctor;
import com.dental.models.Lab;
import com.dental.utils.Navigateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class AddLabProfileScreenController implements Initializable, Navigateable {

    @FXML
    private TextField labPhoneTxt;

    @FXML
    private TextField labMobileTxt;

    @FXML
    private TextField labMailTxt;

    @FXML
    private TextField labVATTxt;

    @FXML
    private TextField labVatTinTxt;

    @FXML
    private TextField labPanTxt;

    @FXML
    private TextField labAddressTxt;

    @FXML
    private TextField labNameTxt;

    @FXML
    private TextField labCstTinTxt;

    private String labName;
    private String labAddress;
    private String labPhone;
    private String labMobile;
    private String labEmail;
    private String labVAT;
    private String labVatTin;
    private String labCstTin;
    private String labPan;

    private CrudDAO<Lab> crudDAO;

    public AddLabProfileScreenController(){
        this.crudDAO = new CrudDAO<>(Lab.class);
    }



    @FXML
    void handleCancelClick(ActionEvent event) {
        nullFields();
    }

    @FXML
    void handleCreateClick(ActionEvent event) {
        if(check()==1) {
            this.getValues();
            Lab lab = new Lab( labName, labAddress, labPhone, labMobile, labEmail, labVatTin, labCstTin, labPan);
            crudDAO.create(lab);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Lab Created!");
            alert.show();
            nullFields();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public int check(){
        if( labNameTxt.getText().trim().isEmpty() ||
                labAddressTxt.getText().trim().isEmpty() ||
                labPhoneTxt.getText().trim().isEmpty() ||
                labMobileTxt.getText().trim().isEmpty() ||
                labMailTxt.getText().trim().isEmpty() ||
                labVATTxt.getText().trim().isEmpty() ||
                labVatTinTxt.getText().trim().isEmpty() ||
                labCstTinTxt.getText().trim().isEmpty() ||
                labPanTxt.getText().trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all fields!");
            alert.show();
            return 0;
        }
        return 1;
    }
    public void getValues(){
        labName = labNameTxt.getText();
        labAddress = labAddressTxt.getText();
        labPhone = labPhoneTxt.getText();
        labMobile = labMobileTxt.getText();
        labEmail = labMailTxt.getText();
        labVAT = labVATTxt.getText();
        labVatTin = labVatTinTxt.getText();
        labCstTin = labCstTinTxt.getText();
        labPan = labPanTxt.getText();
    }
    public void nullFields(){
        labNameTxt.setText(null);
        labAddressTxt.setText(null);
        labPhoneTxt.setText(null);
        labMobileTxt.setText(null);
        labMailTxt.setText(null);
        labVATTxt.setText(null);
        labVatTinTxt.setText(null);
        labCstTinTxt.setText(null);
        labPanTxt.setText(null);
    }
}
