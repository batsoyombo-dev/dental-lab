package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.models.WorkType;
import com.dental.utils.Navigateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.dental.models.WorkType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class WorkTypeScreenController implements Initializable, Navigateable {

    @FXML
    private TextField WorkTypeText;

    @FXML
    private TextField ChargeText;




    @FXML
    private TableView<WorkType> WorkTypeWork;

    @FXML
    private TableColumn<WorkType, Float> Charge;

    @FXML
    private TableColumn<WorkType, String> srNo;

    @FXML
    private TableColumn<WorkType, String> WorkType;

    private CrudDAO<WorkType> crudDAO;
    private List<WorkType> workTypeList;
    private String workType;
    private float charge;
    private WorkType workTypes ;
    private WorkType updateWorkType;
    private WorkType DeleteWorkType;


    public WorkTypeScreenController()
    {
        crudDAO = new CrudDAO<>(WorkType.class);
        workTypeList = new ArrayList<>();
    }

    @FXML
    void handleClickCancel(ActionEvent event)
    {
        WorkTypeText.clear();
        ChargeText.clear();
    }

    @FXML
    void handleClickDelete(ActionEvent event)
    {
        if (check())
        {
            this.setValue();
            DeleteWorkType = WorkTypeWork.getSelectionModel().getSelectedItem();
            crudDAO.delete(DeleteWorkType.getId());
            int index = WorkTypeWork.getSelectionModel().getSelectedIndex();
            WorkTypeWork.getItems().remove(index);
        }


    }

    @FXML
    void handleClickSave(ActionEvent event)
    {
        setValue();
        workTypes = new WorkType(UUID.randomUUID().toString(),this.workType,UUID.randomUUID().toString(),this.charge);
        this.crudDAO.create(workTypes);
        WorkTypeWork.getItems().clear();
        workTypeList = crudDAO.list();
        WorkTypeWork.getItems().addAll(workTypeList);
        workType = WorkTypeText.getText();
        charge = Float.parseFloat(ChargeText.getText());
        WorkTypeText.clear();
        ChargeText.clear();
    }

    @FXML
    void handleClickUpdate(ActionEvent event)
    {

            this.setValue();
            updateWorkType = WorkTypeWork.getSelectionModel().getSelectedItem();
            updateWorkType.setWork(workType);
            updateWorkType.setCharge(charge);
            crudDAO.update(updateWorkType);
            int index = WorkTypeWork.getSelectionModel().getSelectedIndex();
            WorkTypeWork.getItems().set(index, updateWorkType);
    }

    private void setValue()
    {
        workType = WorkTypeText.getText();
        charge = Float.parseFloat(ChargeText.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.srNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.WorkType.setCellValueFactory(new PropertyValueFactory<>("work"));
        this.Charge.setCellValueFactory(new PropertyValueFactory<>("charge"));
        workTypeList = crudDAO.list();
        WorkTypeWork.getItems().addAll(workTypeList);
    WorkTypeWork.getSelectionModel().selectedItemProperty().addListener((observable, a , b) ->
    {
        updateWorkType = WorkTypeWork.getSelectionModel().getSelectedItem();
        if (updateWorkType == null)
        {
            return;
        }
        WorkTypeText.setText(updateWorkType.getWork());
        ChargeText.setText(String.valueOf(updateWorkType.getCharge()));
    });
    }
    private Boolean check()
    {
        if (WorkTypeText.getText().trim().isEmpty() || ChargeText.getText().trim().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Алдаа ");
            alert.setHeaderText("Алдаа гарлаа ");
            return false;
        }
        return true;
    }
}
