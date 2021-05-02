package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.models.Doctor;
import com.dental.models.Employee;
import com.dental.models.Registration;
import com.dental.utils.Navigateable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecordScreenController implements Initializable, Navigateable {
    @FXML
    private TableView<Doctor> DoctorTable;

    @FXML
    private TableColumn<Doctor, String> id;

    @FXML
    private TableColumn<Doctor, String> DoctorName;

    @FXML
    private TableColumn<Doctor, String> address;

    @FXML
    private TableColumn<Doctor, String> MobileNumber;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, String> SrNo;

    @FXML
    private TableColumn<Employee, String> name;

    @FXML
    private TableColumn<Employee, String> DOB;

    @FXML
    private TableColumn<Employee, String> Designation;

    @FXML
    private TableColumn<Employee, String> salary;

    @FXML
    private TableColumn<Employee, String> MobileNu;

    private List<Doctor> DoctorList;
    private List<Employee> employeeList;
    private CrudDAO<Doctor> crudDAODoctor;
    private CrudDAO<Employee> crudDAOEmployee;
    public RecordScreenController()
    {
        DoctorList = new ArrayList<>();
        employeeList = new ArrayList<>();
        this.crudDAODoctor = new CrudDAO<>(Doctor.class);
        this.crudDAOEmployee = new CrudDAO<>(Employee.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.DoctorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.address.setCellValueFactory(new PropertyValueFactory<>("clinicAddress"));
        this.MobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        this.SrNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.name.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.DOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        this.Designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        this.salary.setCellValueFactory(new PropertyValueFactory<>("salaryMonth"));
        this.MobileNu.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        DoctorList = crudDAODoctor.list();
        employeeList = crudDAOEmployee.list();
        DoctorTable.getItems().addAll(DoctorList);
        employeeTable.getItems().addAll(employeeList);
    }
}
