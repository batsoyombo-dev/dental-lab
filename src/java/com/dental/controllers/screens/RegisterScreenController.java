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
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;


import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

public class RegisterScreenController implements Initializable, Navigateable {

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


    @FXML
    private ComboBox<String> choiceDoctor;

    @FXML
    private ComboBox<String> choiceWorkType;

    @FXML
    private TextField JobNo;

    @FXML
    private DatePicker dateP;
    @FXML
    private TextField patientN;
    @FXML
    private TextField extraChar;

    @FXML
    private TextField TotalC;

    @FXML
    private DatePicker TrialD;

    @FXML
    private DatePicker FinishedD;

    @FXML
    private Button Ubtn8;

    @FXML
    private Button Ubtn7;

    @FXML
    private Button Ubtn6;

    @FXML
    private Button Ubtn5;

    @FXML
    private Button Ubtn4;

    @FXML
    private Button Ubtn3;

    @FXML
    private Button Ubtn2;

    @FXML
    private Button Ubtn1;

    @FXML
    private Button Ubtn1_1;

    @FXML
    private Button Ubtn2_2;

    @FXML
    private Button Ubtn3_3;

    @FXML
    private Button Ubtn4_4;

    @FXML
    private Button Ubtn5_5;

    @FXML
    private Button Ubtn6_6;

    @FXML
    private Button Ubtn7_7;

    @FXML
    private Button Ubtn8_8;
    @FXML
    private Button Lbtn8;

    @FXML
    private Button Lbtn7;

    @FXML
    private Button Lbtn6;

    @FXML
    private Button Lbtn5;

    @FXML
    private Button Lbtn4;

    @FXML
    private Button Lbtn3;

    @FXML
    private Button Lbtn2;

    @FXML
    private Button Lbtn1;

    @FXML
    private Button Lbtn1_1;

    @FXML
    private Button Lbtn2_2;

    @FXML
    private Button Lbtn3_3;

    @FXML
    private Button Lbtn4_4;

    @FXML
    private Button Lbtn5_5;

    @FXML
    private Button Lbtn6_6;

    @FXML
    private Button Lbtn7_7;

    @FXML
    private Button Lbtn8_8;

    ObservableList<String> teethList = FXCollections.observableArrayList();

    public RegisterScreenController()
    {
        this.crudDAO = new CrudDAO<>(Registration.class);
        this.crudDAODoctor = new CrudDAO<>(Doctor.class);
        this.crudDAOWorkType = new CrudDAO<>(WorkType.class);
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
    void LonBtn(MouseEvent event)
    {

        if(event.getSource() == Lbtn1 )
        {
            removeAndAdd(Lbtn1);
        }
        else if (event.getSource()==Lbtn2)
        {
            removeAndAdd(Lbtn2);
        }
        else if (event.getSource()==Lbtn3)
        {
            removeAndAdd(Lbtn3);
        }
        else if (event.getSource()==Lbtn4)
        {
            removeAndAdd(Lbtn4);
        }
        else if (event.getSource()==Lbtn5)
        {
            removeAndAdd(Lbtn5);
        }
        else if (event.getSource()==Lbtn6)
        {
            removeAndAdd(Lbtn6);
        }
        else if (event.getSource()==Lbtn7)
        {
            removeAndAdd(Lbtn7);
        }
        else if (event.getSource()==Lbtn8)
        {
            removeAndAdd(Lbtn8);
        }
        else if (event.getSource()==Lbtn1_1)
        {
            removeAndAdd(Lbtn1_1);
        }
        else if (event.getSource()==Lbtn2_2)
        {
            removeAndAdd(Lbtn2_2);
        }
        else if (event.getSource()==Lbtn3_3)
        {
            removeAndAdd(Lbtn3_3);
        }
        else if (event.getSource()==Lbtn4_4)
        {
            removeAndAdd(Lbtn4_4);
        }
        else if (event.getSource()==Lbtn5_5)
        {
            removeAndAdd(Lbtn5_5);
        }
        else if (event.getSource()==Lbtn6_6)
        {
            removeAndAdd(Lbtn6_6);
        }
        else if (event.getSource()==Lbtn7_7)
        {
            removeAndAdd(Lbtn7_7);
        }
        else if (event.getSource()==Lbtn8_8)
        {
            removeAndAdd(Lbtn8_8);
        }
    }
    @FXML
    void UonBtn(MouseEvent event)
    {

        if(event.getSource() == Ubtn1 )
        {
            removeAndAdd(Ubtn1);
        }
        else if (event.getSource()==Ubtn2)
        {
            removeAndAdd(Ubtn2);
        }
        else if (event.getSource()==Ubtn3)
        {
            removeAndAdd(Ubtn3);
        }
        else if (event.getSource()==Ubtn4)
        {
            removeAndAdd(Ubtn4);
        }
        else if (event.getSource()==Ubtn5)
        {
            removeAndAdd(Ubtn5);
        }
        else if (event.getSource()==Ubtn6)
        {
            removeAndAdd(Ubtn6);
        }
        else if (event.getSource()==Ubtn7)
        {
            removeAndAdd(Ubtn7);
        }
        else if (event.getSource()==Ubtn8)
        {
            removeAndAdd(Ubtn8);
        }
        else if (event.getSource()==Ubtn1_1)
        {
            removeAndAdd(Ubtn1_1);
        }
        else if (event.getSource()==Ubtn2_2)
        {
            removeAndAdd(Ubtn2_2);
        }
        else if (event.getSource()==Ubtn3_3)
        {
            removeAndAdd(Ubtn3_3);
        }
        else if (event.getSource()==Ubtn4_4)
        {
            removeAndAdd(Ubtn4_4);
        }
        else if (event.getSource()==Ubtn5_5)
        {
            removeAndAdd(Ubtn5_5);
        }
        else if (event.getSource()==Ubtn6_6)
        {
            removeAndAdd(Ubtn6_6);
        }
        else if (event.getSource()==Ubtn7_7)
        {
            removeAndAdd(Ubtn7_7);
        }
        else if (event.getSource()==Ubtn8_8)
        {
            removeAndAdd(Ubtn8_8);
        }

    }

    void removeAndAdd(Button button)
    {

        int i;
        boolean a = true;
        for (i = 0; i<=teethList.size()-1; i++)
        {
            if (teethList.get(i).equals(button+""))
            {
                a = false;
                button.getStyleClass().remove("mainbtn");
                teethList.remove(button+"");
            }
        }
        if(i==0 || a)
        {
            button.getStyleClass().add("mainbtn");
            teethList.add(button+"");
        }

    }
    @FXML
    void cancel()
    {
        JobNo.clear();
        FinishedD.getEditor().clear();
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
            Registration registration = new Registration(UUID.randomUUID().toString(),patient_name,date,trial_date,finished_date,charges,doctor,workType);
            if(crudDAO.create(registration))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Амжилтай");
                alert.setHeaderText("Амжилтай боллоо :D");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK ||  result.get() == ButtonType.CANCEL)
                {
                    JobNo.clear();
                    FinishedD.getEditor().clear();
                    dateP.getEditor().clear();
                    patientN.clear();
                    extraChar.clear();
                    TotalC.clear();
                    TrialD.getEditor().clear();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Алдаа ");
                alert.setHeaderText("Алдаа гарлаа ");
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
        System.out.println(choiceDoctor.getValue());
        for (int i =0; i<=doctorList.size()-1; i++)
        {
            if (doctorList.get(i).getName().equals(choiceDoctor.getValue()))
                doctor = doctorList.get(i);
        }
    }
    boolean check()
    {
        if (
                JobNo.getText().trim().isEmpty()||
                dateP.getEditor().getText().trim().isEmpty()||
                patientN.getText().trim().isEmpty()||
                TotalC.getText().trim().isEmpty()||
                TrialD.getEditor().getText().trim().isEmpty()||
                FinishedD.getEditor().getText().trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Aldaa garlaa!");
            alert.show();
            return false;
        }
        return true;

    }
}
