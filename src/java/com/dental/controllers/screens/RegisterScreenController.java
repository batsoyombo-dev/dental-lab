package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.models.Doctor;
import com.dental.models.Registration;
import com.dental.models.WorkType;
import com.dental.utils.Navigateable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;


import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

public class RegisterScreenController implements Initializable, Navigateable {

    @FXML
    private ComboBox<String> choiceDoctor;

    @FXML
    private ComboBox<String> choiceWorkType;

    @FXML
    private TextField JobNo;

    @FXML
    private TextField patientN;

    @FXML
    private DatePicker dateP;

    @FXML
    private Button LR8;

    @FXML
    private TextField extraChar;

    @FXML
    private TextField TotalC;

    @FXML
    private DatePicker TrialD;

    @FXML
    private DatePicker finishedD;

    ObservableList<String> teethList = FXCollections.observableArrayList();
    private CrudDAO<Registration> crudDAO;
    private CrudDAO<Doctor> crudDAODoctor;
    private CrudDAO<WorkType> crudDAOWorkType;
    private List<Doctor> doctorList;
    private List<WorkType> workTypes;
    private Doctor doctor = new Doctor();
    private WorkType workType = new WorkType();
    private String inwardNo ;
    private String patient_name ;
    private String date ;
    private String trial_date ;
    private String finished_date ;
    private float charges ;
    private String doctor_id ;
    private String work_type_id ;
    private List<String> chosenTeeth;
    private List<Button> buttons;

    public RegisterScreenController()
    {
        this.crudDAO = new CrudDAO<>(Registration.class);
        this.crudDAODoctor = new CrudDAO<>(Doctor.class);
        this.crudDAOWorkType = new CrudDAO<>(WorkType.class);
        this.chosenTeeth = new ArrayList<>();
        this.buttons = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        doctorList = crudDAODoctor.list();
        workTypes = crudDAOWorkType.list();
        for (int i=0; i<=doctorList.size()-1; i++)
        {
            choiceDoctor.getItems().add(doctorList.get(i).getName());
        }
        for (int i=0; i<=workTypes.size()-1; i++)
        {
            choiceWorkType.getItems().add(workTypes.get(i).getWork());
        }
        extraChar.textProperty().addListener((v,value,oldValue)->
        {
            try
            {
                TotalC.setText(String.valueOf( Float.parseFloat(TotalC.getText()) + Float.parseFloat(oldValue)));
            }catch (Exception ex)
            {
                System.out.println("oops error");
            }
        });

    }
    @FXML
    void WorkTypeChoiseBox(ActionEvent event)
    {
        for (int i=0; i<=workTypes.size()-1; i++)
        {
            if (workTypes.get(i).getWork().equals(choiceWorkType.getValue()))
            {
                TotalC.setText(String.valueOf(workTypes.get(i).getCharge()));
                JobNo.setText(workTypes.get(i).getId());
                workType = workTypes.get(i);
            }
        }
    }
    @FXML
    void handleTeethSelectionClick(MouseEvent event)
    {
        Button button = ((Button)event.getSource());
        ObservableList<String> styleClass = button.getStyleClass();
        buttons.add(button);
        if (styleClass.contains("selected")) {
            this.chosenTeeth.remove(button.getId());
            styleClass.remove("selected");
        } else {
            this.chosenTeeth.add(button.getId());
            styleClass.add("selected");
        }
    }

    @FXML
    void cancel()
    {
        int i=0;
        for (String tooth : this.chosenTeeth)
        {
            buttons.get(i).setId("#" + tooth);
            buttons.get(i).getStyleClass().remove("selected");
            i++;
        }
        this.chosenTeeth.clear();
        JobNo.clear();
        finishedD.getEditor().clear();
        dateP.getEditor().clear();
        patientN.clear();
        extraChar.clear();
        TotalC.clear();
        TrialD.getEditor().clear();

    }

    @FXML
    void newEntry(ActionEvent event)
    {
        if(check())
        {
            this.getValues();
            Registration registration = new Registration(UUID.randomUUID().toString(), patient_name, date, trial_date, finished_date, workType.getCharge(), doctor, workType, String.join(",", this.chosenTeeth));
            if(crudDAO.create(registration))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Successfully register!");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK ||  result.get() == ButtonType.CANCEL) {
                    finishedD.getEditor().clear();
                    extraChar.clear();
                    TotalC.clear();
                    TrialD.getEditor().clear();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setHeaderText("Something went wrong!");
            }
        }

    }

    void getValues()
    {
        patient_name =  patientN.getText();
        date = String.valueOf(dateP.getValue());
        trial_date = String.valueOf(dateP.getValue());
        finished_date = String.valueOf(dateP.getValue());
        charges = Float.parseFloat(TotalC.getText()) ;
        work_type_id = JobNo.getText();
        for (int i =0; i<=doctorList.size()-1; i++)
        {
            if (doctorList.get(i).getName().equals(choiceDoctor.getValue()))
                doctor = doctorList.get(i);
        }
    }
    boolean check()
    {
        if (JobNo.getText().trim().isEmpty()||
            dateP.getEditor().getText().trim().isEmpty()||
            patientN.getText().trim().isEmpty()||
            TotalC.getText().trim().isEmpty()||
            TrialD.getEditor().getText().trim().isEmpty()||
            finishedD.getEditor().getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Aldaa garlaa!");
            alert.show();
            return false;
        }
        return true;

    }
}
