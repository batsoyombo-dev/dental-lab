package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.models.Doctor;
import com.dental.models.Registration;
import com.dental.utils.Navigateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TodayDeliveryScreenController implements Initializable, Navigateable
{
    String date;
    SimpleDateFormat simpleDateFormat;
    public TodayDeliveryScreenController()
    {
        String pattern = "yyyy-MM-dd";
        simpleDateFormat = new SimpleDateFormat(pattern);
         date = simpleDateFormat.format(new Date());
        this.crudDAO = new CrudDAO<>(Registration.class);
    }

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<Registration> myTable;

    @FXML
    private TableColumn<Registration, String> InwardNo;

    @FXML
    private TableColumn<Registration, String> PatientName;

    @FXML
    private TableColumn<Registration, String> DateP;

    @FXML
    private TableColumn<Registration, String> trialDate;

    @FXML
    private TableColumn<Registration, String> FinishDate;

    @FXML
    private TableColumn<Registration, Float> charges;

    @FXML
    private TableColumn<Registration, String> DoctorID;

    @FXML
    private TableColumn<Registration, String> WorkTypeID;

    private List<Registration> registrationsList;
    private CrudDAO<Registration> crudDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        datePicker.setValue(LOCAL_DATE(date));
        InwardNo.setCellValueFactory(new PropertyValueFactory<Registration, String>("id"));
        PatientName.setCellValueFactory(new PropertyValueFactory<Registration, String>("patientName"));
        DateP.setCellValueFactory(new PropertyValueFactory<Registration, String>("date"));
        trialDate.setCellValueFactory(new PropertyValueFactory<Registration, String>("trialDate"));
        FinishDate.setCellValueFactory(new PropertyValueFactory<Registration, String>("finishedDate"));
        charges.setCellValueFactory(new PropertyValueFactory<Registration, Float>("charges"));
        DoctorID.setCellValueFactory(new PropertyValueFactory<Registration, String>("doctor"));
        WorkTypeID.setCellValueFactory(new PropertyValueFactory<Registration, String>("workType"));

        registrationsList = crudDAO.search("finished_date",date);
        myTable.getItems().addAll(registrationsList);
    }

    private LocalDate LOCAL_DATE(String s)
    {
        LocalDate localDate = LocalDate.parse(s);
        return localDate;
    }

    @FXML
    void OnDatePicker(ActionEvent event)
    {
        myTable.getItems().clear();
        registrationsList = crudDAO.search("finished_date",datePicker.getValue());
        myTable.getItems().addAll(registrationsList);
    }
}
