package com.dental;

import com.dental.controllers.HomeController;
import com.dental.dao.CrudDAO;
import com.dental.dao.DemoExampleOfCrudDAO;
import com.dental.models.Doctor;
import com.dental.models.Employee;
import com.dental.models.Expenses;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        DemoExampleOfCrudDAO demo = new DemoExampleOfCrudDAO();
        demo.insertExample();

        new HomeController(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
