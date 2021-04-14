package com.dental;

import com.dental.controllers.HomeController;
import com.dental.dao.CrudDAO;
import com.dental.dao.DBConnection;
import com.dental.dao.DoctorDAO;
import com.dental.models.Doctor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new HomeController(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
