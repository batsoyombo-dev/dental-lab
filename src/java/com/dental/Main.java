package com.dental;

import com.dental.controllers.HomeController;
import com.dental.dao.CrudDAO;
import com.dental.models.Doctor;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;
import java.util.UUID;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new HomeController(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
