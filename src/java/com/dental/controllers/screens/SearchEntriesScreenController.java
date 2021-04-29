package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.models.Doctor;
import com.dental.models.Registration;
import com.dental.models.WorkType;
import com.dental.utils.Navigateable;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.print.Doc;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class SearchEntriesScreenController implements Initializable, Navigateable {

    @FXML
    private ChoiceBox<String> cb_searchColumn;
    @FXML
    private ChoiceBox<Doctor> cb_doctors;
    @FXML
    private ChoiceBox<WorkType> cb_workTypes;
    @FXML
    private TextField
            inp_searchValueField,
            inp_patientNameField;
    @FXML
    private DatePicker
            dp_regDateField,
            dp_trialDate,
            dp_finishedDate;
    @FXML
    private TableView<Registration> tv_searchResult;
    @FXML
    private TableColumn<Registration, String>
            tc_regIdColumn,
            tc_regFateColumn,
            tc_regPatientNameColumn,
            tc_regDoctorIdColumn;


    private List<String> chosenTeeth;
    private Alert alert;

    private CrudDAO<Registration> crudDAO;
    private Registration chosenRegistration = null;
    public SearchEntriesScreenController() {
        this.alert = new Alert(Alert.AlertType.ERROR);
        this.chosenTeeth = new ArrayList<>();
        this.crudDAO = new CrudDAO<>(Registration.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tc_regIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tc_regFateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.tc_regPatientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        this.tc_regDoctorIdColumn.setCellValueFactory(val -> new SimpleStringProperty(val.getValue().getDoctor().getId()));

        Thread thread = new Thread(() -> {
            CrudDAO<Doctor> doctorCrudDAO = new CrudDAO<>(Doctor.class);
            CrudDAO<WorkType> workTypeCrudDAO = new CrudDAO<>(WorkType.class);
            List<Doctor> doctors = doctorCrudDAO.list();
            List<WorkType> workTypes = workTypeCrudDAO.list();
            Platform.runLater(() -> {
                this.cb_doctors.getItems().addAll(doctors);
                this.cb_workTypes.getItems().addAll(workTypes);
            });
        }, "data inflation");
        thread.start();

        this.tv_searchResult.getSelectionModel().selectedItemProperty().addListener((a, b, c) -> {
            this.chosenRegistration = this.tv_searchResult.getSelectionModel().getSelectedItem();
            this.inflateFields();
        });
    }

    public void handleSearchBtnClick(MouseEvent e) {
        String columnField = this.cb_searchColumn.getValue(),
                searchValue = this.inp_searchValueField.getText();

        if (columnField.isEmpty() || searchValue.isEmpty())
            return;

        this.tv_searchResult.getItems().clear();

        List<Registration> result = this.crudDAO.search(columnField, searchValue);
        for (Registration registration : result) {
            this.tv_searchResult.getItems().add(registration);
        }
    }

    public void handleUpdateBtnClick(MouseEvent e) {
        if (this.chosenRegistration == null)
            return;
        String patientName = this.inp_patientNameField.getText(),
                date = this.dp_regDateField.getValue().toString(),
                trialDate = this.dp_trialDate.getValue().toString(),
                finishedDate = this.dp_finishedDate.getValue().toString();

        Doctor doctor = this.cb_doctors.getValue();
        WorkType workType = this.cb_workTypes.getValue();

        if (patientName.isEmpty()
                || date.isEmpty()
                || trialDate.isEmpty()
                || finishedDate.isEmpty()
                || doctor == null
                || workType == null
                || this.chosenTeeth.size() == 0) {
            this.alert.setTitle("Field Error!");
            this.alert.setContentText("Fill all the fields");
            this.alert.show();
            return;
        }

        Registration registration = new Registration(
                this.chosenRegistration.getId(),
                patientName,
                date,
                trialDate,
                finishedDate,
                workType.getCharge(),
                doctor,
                workType,
                String.join(",", this.chosenTeeth)
        );
        if (crudDAO.update(registration)) {
            this.tv_searchResult.getItems().set(this.tv_searchResult.getSelectionModel().getSelectedIndex(), registration);
            this.clearFields();
        } else {
            this.alert.setTitle("Field Error!");
            this.alert.setContentText("There has been an error!");
            this.alert.show();
        }
    }

    public void handleDeleteBtnClick(MouseEvent e) {
        if (this.chosenRegistration == null)
            return;
        this.crudDAO.delete(this.chosenRegistration.getId());
        this.tv_searchResult.getItems().remove(this.chosenRegistration);
        this.clearFields();
    }

    public void handleTeethSelectionClick(MouseEvent e) {
        if (this.chosenRegistration == null)
            return;
        Button button = ((Button)e.getSource());
        ObservableList<String> styleClass = button.getStyleClass();
        if (styleClass.contains("selected")) {
            this.chosenTeeth.remove(button.getId());
            styleClass.remove("selected");
        } else {
            this.chosenTeeth.add(button.getId());
            styleClass.add("selected");
        }
    }

    public void handleCancelBtnClick(MouseEvent e) {
        this.clearFields();
    }

    private void inflateFields() {
        if (this.chosenRegistration == null)
            return;
        this.inp_patientNameField.setText(this.chosenRegistration.getPatientName());
        this.dp_regDateField.setValue(LocalDate.parse(this.chosenRegistration.getDate()));
        this.dp_finishedDate.setValue(LocalDate.parse(this.chosenRegistration.getFinishedDate()));
        this.dp_trialDate.setValue(LocalDate.parse(this.chosenRegistration.getTrialDate()));
        for (Doctor doctor : this.cb_doctors.getItems())
            if (doctor.getId().equals(this.chosenRegistration.getDoctor().getId()))
                this.cb_doctors.setValue(doctor);
        for (WorkType workType : this.cb_workTypes.getItems())
            if (workType.getId().equals(this.chosenRegistration.getWorkType().getId()))
                this.cb_workTypes.setValue(workType);
        this.chosenTeeth.addAll(Arrays.asList(this.chosenRegistration.getTeeth().split(",")));
        Scene scene = this.tv_searchResult.getScene();
        for (String tooth : chosenTeeth) {
            Button toothButton = (Button) scene.lookup("#" + tooth);
            toothButton.getStyleClass().add("selected");
        }
    }

    private void clearFields() {
        this.chosenRegistration = null;
        Scene scene = this.tv_searchResult.getScene();
        for (String tooth : this.chosenTeeth) {
            Button toothButton = (Button) scene.lookup("#" + tooth);
            toothButton.getStyleClass().remove("selected");
        }
        this.chosenTeeth.clear();
        this.inp_patientNameField.setText("");
        this.dp_regDateField.setValue(null);
        this.dp_finishedDate.setValue(null);
        this.dp_trialDate.setValue(null);
        this.cb_doctors.setValue(null);
        this.cb_workTypes.setValue(null);
    }

}
