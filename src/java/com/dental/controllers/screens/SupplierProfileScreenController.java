package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.models.Doctor;
import com.dental.models.Supplier;
import com.dental.utils.Navigateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class SupplierProfileScreenController implements Initializable, Navigateable {
    @FXML
    private TextField suppNameTxtField;

    @FXML
    private TextField suppAddressTxtField;

    @FXML
    private TextField suppMobileTxtField;

    @FXML
    private TextField suppMailTxtField;

    @FXML
    private ChoiceBox<String> suppGenderCbox;

    @FXML
    private DatePicker suppDOBPicker;
    @FXML
    private TableView<Supplier> suppInfoTable;

    @FXML
    private TableColumn<Supplier, String> suppIdCol;

    @FXML
    private TableColumn<Supplier, String> suppNameCol;

    @FXML
    private TableColumn<Supplier, String> supAddressCol;

    @FXML
    private TableColumn<Supplier, String> suppMobileCol;

    private String supplierName;
    private Character supplierGender;
    private String supplierDOB;
    private String supplierAddress;
    private String supplierMobile;
    private String supplierEmail;

    private CrudDAO<Supplier> crudDAO;
    private List<Supplier> supplierList;
    private Supplier updateSupplier;
    private Supplier deleteSupplier;

    public SupplierProfileScreenController(){
        this.crudDAO = new CrudDAO<>(Supplier.class);
        this.updateSupplier = new Supplier(UUID.randomUUID().toString(), "default", 'f', "2000-01-01", "default", "123456", "default");
    }

    @FXML
    void handleAddClick(ActionEvent event) {
        if(check()==1) {
            this.getValues();
            Supplier supplier = new Supplier(UUID.randomUUID().toString(), supplierName, supplierGender, supplierDOB, supplierAddress, supplierMobile, supplierEmail);
            crudDAO.create(supplier);
            suppInfoTable.getItems().add(supplier);
        }
    }

    @FXML
    void handleClearClick(ActionEvent event) {
        suppNameTxtField.setText(null);
        suppGenderCbox.setValue("None");
        suppDOBPicker.setValue(null);
        suppAddressTxtField.setText(null);
        suppMobileTxtField.setText(null);
        suppMailTxtField.setText(null);
    }

    @FXML
    void handleDeleteClick(ActionEvent event) {
        check();
        this.getValues();
        deleteSupplier = suppInfoTable.getSelectionModel().getSelectedItem();
        crudDAO.delete(deleteSupplier.getId());
        int index = suppInfoTable.getSelectionModel().getSelectedIndex();
        suppInfoTable.getItems().remove(index);
    }

    @FXML
    void handleUpdateClick(ActionEvent event) {
        if(check()==1) {
            this.getValues();
            updateSupplier = suppInfoTable.getSelectionModel().getSelectedItem();
            updateSupplier.setName(supplierName);
            updateSupplier.setGender(supplierGender);
            updateSupplier.setDob(supplierDOB);
            updateSupplier.setAddress(supplierAddress);
            updateSupplier.setMobileNumber(supplierMobile);
            updateSupplier.setEmail(supplierEmail);
            crudDAO.update(updateSupplier);
            int index = suppInfoTable.getSelectionModel().getSelectedIndex();
            suppInfoTable.getItems().set(index, updateSupplier);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        suppIdCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("id"));
        suppNameCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("name"));
        supAddressCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("address"));
        suppMobileCol.setCellValueFactory(new PropertyValueFactory<Supplier, String>("mobileNumber"));

        supplierList = crudDAO.list();
        suppInfoTable.getItems().addAll(supplierList);
        suppInfoTable.getSelectionModel().selectedItemProperty().addListener((observable, a , b) ->{
            updateSupplier = suppInfoTable.getSelectionModel().getSelectedItem();
            if(updateSupplier== null)
                return;
            System.out.println(updateSupplier);
            suppNameTxtField.setText(updateSupplier.getName());
            if(updateSupplier.getGender()== 'm')
                suppGenderCbox.setValue("Male");
            if(updateSupplier.getGender()== 'f')
                suppGenderCbox.setValue("Female");
            LocalDate dob = LocalDate.parse(updateSupplier.getDob());
            suppDOBPicker.setValue(dob);
            suppAddressTxtField.setText(updateSupplier.getAddress());
            suppMobileTxtField.setText(updateSupplier.getMobileNumber());
            suppMailTxtField.setText(updateSupplier.getEmail());
        });

    }
    public int check(){
        if( suppNameTxtField.getText().trim().isEmpty() ||
                suppDOBPicker.getValue().toString().trim().isEmpty() ||
                suppGenderCbox.getValue().trim().equals("None") ||
                suppAddressTxtField.getText().trim().isEmpty() ||
                suppMobileTxtField.getText().trim().isEmpty() ||
                suppMailTxtField.getText().trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all fields!");
            alert.show();
            return 0;
        }
        return 1;
    }
    public void getValues(){
        supplierName = suppNameTxtField.getText();
        if(suppGenderCbox.getValue().equals("Male"))
            supplierGender = 'm';
        if(suppGenderCbox.getValue().equals("Female"))
            supplierGender = 'f';
        if(suppGenderCbox.getValue().equals("None"))
            supplierGender = 'n';
        supplierDOB = suppDOBPicker.getValue().toString();
        supplierAddress = suppAddressTxtField.getText();
        supplierMobile = suppMobileTxtField.getText();
        supplierEmail = suppMailTxtField.getText();
    }

}
