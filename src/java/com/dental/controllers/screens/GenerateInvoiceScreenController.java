package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.models.Doctor;
import com.dental.models.Invoice;
import com.dental.models.Registration;
import com.dental.utils.Navigateable;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class GenerateInvoiceScreenController implements Initializable, Navigateable {

    @FXML
    private ChoiceBox<Doctor> cb_doctors;
    @FXML
    private DatePicker dp_dateField;
    @FXML
    private TableColumn<Registration, String>
            tc_regIdColumn,
            tc_regFateColumn,
            tc_regPatientNameColumn,
            tc_regDoctorIdColumn,
            tc_regFinishedDateColumn,
            tc_regTotalChargesColumn;
    @FXML
    private TableView<Registration> tv_generateResult;

    private CrudDAO<Invoice> invoiceCrudDAO;
    private CrudDAO<Registration> registrationCrudDAO;
    private final Alert alert;
    private Registration chosenRegistration = null;

    public GenerateInvoiceScreenController() {
        alert = new Alert(Alert.AlertType.ERROR);
        this.invoiceCrudDAO = new CrudDAO<>(Invoice.class);
        this.registrationCrudDAO = new CrudDAO<>(Registration.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tc_regIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tc_regFateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.tc_regPatientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        this.tc_regFinishedDateColumn.setCellValueFactory(new PropertyValueFactory<>("finishedDate"));
        this.tc_regDoctorIdColumn.setCellValueFactory(val -> new SimpleStringProperty(val.getValue().getDoctor().getId()));
        this.tc_regTotalChargesColumn.setCellValueFactory(new PropertyValueFactory<>("charges"));;

        Thread thread = new Thread(() -> {
            CrudDAO<Doctor> doctorCrudDAO = new CrudDAO<>(Doctor.class);
            List<Doctor> doctors = doctorCrudDAO.list();
            Platform.runLater(() -> this.cb_doctors.getItems().addAll(doctors));
        });
        thread.start();

        this.tv_generateResult.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
            this.chosenRegistration = this.tv_generateResult.getSelectionModel().getSelectedItem();
        });
    }

    public void handleGenerateBtnClick(MouseEvent e) {
        LocalDate dateField = this.dp_dateField.getValue();
        Doctor chosenDoctor = this.cb_doctors.getValue();

        if (dateField == null || chosenDoctor == null) {
            this.alert.setTitle("Fill all the fields!");
            this.alert.setContentText("All the fields need some value to work properly!!!");
            this.alert.show();
            return;
        }

        this.tv_generateResult.getItems().clear();

        Map<String, Object> query = new HashMap<>();
        query.put("doctor_id", chosenDoctor.getId());
        query.put("date", dateField);
        List<Registration> registrations = this.registrationCrudDAO.search(query);
        this.tv_generateResult.getItems().addAll(registrations);
    }

    public void handleSaveInvoiceBtnClick(MouseEvent e) {
        if (this.chosenRegistration == null)
            return;

        if (this.invoiceCrudDAO.create(new Invoice(UUID.randomUUID().toString(), LocalDate.now().toString(), this.chosenRegistration))) {
            this.alert.setAlertType(Alert.AlertType.CONFIRMATION);
            this.alert.setTitle("Success!");
            this.alert.setContentText("Successfully generated the bill for the chosen registration!");
            this.alert.show();
        } else {
            this.alert.setTitle("Field Error!");
            this.alert.setContentText("There has been an error!");
            this.alert.show();
            this.alert.show();
        }
    }

}
