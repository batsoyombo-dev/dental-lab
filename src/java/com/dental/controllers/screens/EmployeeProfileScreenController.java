package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.models.Doctor;
import com.dental.models.Employee;
import com.dental.utils.Navigateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class EmployeeProfileScreenController  implements Initializable, Navigateable {

    @FXML
    private TextField employeeNameTxtField;

    @FXML
    private TextField employeeDesignationTxtField;

    @FXML
    private TextField employeeMobileTxtField;

    @FXML
    private TextField employeeMailTxtField;

    @FXML
    private TextField employeeSalaryTxtField;

    @FXML
    private DatePicker employeeDOBPicker;

    @FXML
    private TextField employeeAddressTxtField;

    @FXML
    private TextField employeeIDProofTxtField;

    @FXML
    private TextField employeeIDProofNoTxtField;

    @FXML
    private ChoiceBox<String> employeeGenderCBox;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, String> employeeIDCol;

    @FXML
    private TableColumn<Employee, String> employeeNameCol;

    @FXML
    private TableColumn<Employee, String> employeeDOBCol;

    @FXML
    private TableColumn<Employee, String> employeeDesignationCol;

    @FXML
    private TableColumn<Employee, Integer> employeeSalaryCol;

    @FXML
    private TableColumn<Employee, String> employeeMobileCol;

    private String employeeName;
    private String employeeDOB;
    private String employeeDesignation;
    private String employeeMobile;
    private String employeeMail;
    private int employeeSalary;
    private Character employeeGender;
    private String employeeAddress;
    private String employeeIDProof;
    private String employeeIDProofNo;

    private CrudDAO<Employee> crudDAO;
    private List<Employee> employeeList;
    private Employee updateEmployee;
    private Employee deleteEmployee;

    public EmployeeProfileScreenController(){
        this.crudDAO = new CrudDAO<>(Employee.class);
        this.updateEmployee = new Employee(UUID.randomUUID().toString(), "default", 'm', "1991-01-01", "default", "default", "123456", "default", 600);
    }
    @FXML
    void handleAddClick(ActionEvent event) {
        if(check()==1) {
            this.getValues();
            Employee employee = new Employee(UUID.randomUUID().toString(), employeeName, employeeGender, employeeDOB, employeeAddress, employeeDesignation, employeeMobile, employeeMail, employeeSalary);
            crudDAO.create(employee);
            employeeTable.getItems().add(employee);
        }
    }

    @FXML
    void handleCancelClick(ActionEvent event) {
        employeeNameTxtField.setText(null);
        employeeGenderCBox.setValue("None");
        employeeDOBPicker.setValue(null);
        employeeDesignationTxtField.setText(null);
        employeeIDProofTxtField.setText(null);
        employeeMobileTxtField.setText(null);
        employeeIDProofNoTxtField.setText(null);
        employeeMailTxtField.setText(null);
        employeeSalaryTxtField.setText(null);
    }

    @FXML
    void handleDeleteClick(ActionEvent event) {
        check();
        this.getValues();
        deleteEmployee = employeeTable.getSelectionModel().getSelectedItem();
        crudDAO.delete(deleteEmployee.getId());
        int index = employeeTable.getSelectionModel().getSelectedIndex();
        employeeTable.getItems().remove(index);
    }

    @FXML
    void handleUpdateClick(ActionEvent event) {
        if(check()==1) {
            this.getValues();
            updateEmployee = employeeTable.getSelectionModel().getSelectedItem();
            updateEmployee.setName(employeeName);
            updateEmployee.setGender(employeeGender);
            updateEmployee.setDob(employeeDOB);
            updateEmployee.setAddress(employeeAddress);
            updateEmployee.setMobileNumber(employeeMobile);
            updateEmployee.setDesignation(employeeDesignation);
            updateEmployee.setEmail(employeeMail);
            updateEmployee.setSalaryMonth(employeeSalary);
            crudDAO.update(updateEmployee);
            int index = employeeTable.getSelectionModel().getSelectedIndex();
            employeeTable.getItems().set(index, updateEmployee);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeeIDCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        employeeNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        employeeDOBCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        employeeDesignationCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("designation"));
        employeeSalaryCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salaryMonth"));
        employeeMobileCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("mobileNumber"));

        employeeList = crudDAO.list();
        employeeTable.getItems().addAll(employeeList);
        employeeTable.getSelectionModel().selectedItemProperty().addListener((observable, a , b) ->{
            updateEmployee = employeeTable.getSelectionModel().getSelectedItem();
            if(updateEmployee == null)
                return;
            System.out.println(updateEmployee);
            employeeNameTxtField.setText(updateEmployee.getName());
            if(updateEmployee.getGender()== 'm')
                employeeGenderCBox.setValue("Male");
            if(updateEmployee.getGender()== 'f')
                employeeGenderCBox.setValue("Female");
            LocalDate dob = LocalDate.parse(updateEmployee.getDob());
            employeeDOBPicker.setValue(dob);
            employeeAddressTxtField.setText(updateEmployee.getAddress());
            employeeDesignationTxtField.setText(updateEmployee.getDesignation());
            employeeMobileTxtField.setText(updateEmployee.getMobileNumber());
            employeeMailTxtField.setText(updateEmployee.getEmail());
            employeeSalaryTxtField.setText(String.valueOf(updateEmployee.getSalaryMonth()));
        });
    }
    public void getValues(){
        employeeName = employeeNameTxtField.getText();
        if(employeeGenderCBox.getValue().equals("Male"))
            employeeGender = 'm';
        if(employeeGenderCBox.getValue().equals("Female"))
            employeeGender = 'f';
        if(employeeGenderCBox.getValue().equals("None"))
            employeeGender = 'n';
        employeeDOB = employeeDOBPicker.getValue().toString();
        employeeAddress = employeeAddressTxtField.getText();
        employeeDesignation = employeeDesignationTxtField.getText();
        employeeIDProof = employeeIDProofTxtField.getText();
        employeeIDProofNo = employeeIDProofNoTxtField.getText();
        employeeMobile = employeeMobileTxtField.getText();
        employeeMail = employeeMailTxtField.getText();
        try {
            employeeSalary = Integer.parseInt(employeeSalaryTxtField.getText());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public int check(){
        if( employeeNameTxtField.getText().trim().isEmpty() ||
                employeeDOBPicker.getValue().toString().trim().isEmpty() ||
                employeeGenderCBox.getValue().trim().equals("None") ||
                employeeAddressTxtField.getText().trim().isEmpty() ||
                employeeDesignationTxtField.getText().trim().isEmpty() ||
                employeeMobileTxtField.getText().trim().isEmpty() ||
                employeeMailTxtField.getText().trim().isEmpty() ||
                employeeSalaryTxtField.getText().trim().isEmpty() ||
                employeeIDProofTxtField.getText().trim().isEmpty() ||
                employeeIDProofNoTxtField.getText().trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Fill all fields!");
            alert.show();
            return 0;
        }
        return 1;
    }
}
