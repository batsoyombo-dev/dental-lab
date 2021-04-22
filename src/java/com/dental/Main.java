package com.dental;

import com.dental.controllers.HomeController;
import com.dental.dao.CrudDAO;
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

//        CrudDAO<Employee> crudDAO = new CrudDAO<>(Employee.class);
        CrudDAO<Expenses> crudDAO = new CrudDAO<>(Expenses.class);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2001, Calendar.JANUARY, 21);
//        Employee employee = new Employee(UUID.randomUUID().toString(), "dwq", 'f', calendar.getTime(), "dwq", "dwq", "dwq", "dwq", 21);
        Expenses expenses = new Expenses("d9c6f0a5-898c-4ad1-9261-ed953ee004f5", "2001-02-20", 1.2f, "updated", "dwq", "dwq", "dwq");
        if (crudDAO.update(expenses))
            System.out.println("dwqdqwwdq");

//        Employee employee = crudDAO.retrieve("4182275d-47d0-4c48-87f4-e1e579fc7e95");
//        if (employee != null) {
//            Date date = employee.getDob();
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(date);
//            System.out.println(calendar.get(Calendar.YEAR));
//        }

        new HomeController(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
