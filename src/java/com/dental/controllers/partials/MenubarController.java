package com.dental.controllers.partials;

import com.dental.controllers.HomeController;
import com.dental.controllers.screens.*;
import com.dental.utils.Navigateable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenubarController implements Initializable {

    private HomeController homeController;
    private Navigateable navigateable;

    public MenubarController(HomeController homeController) {
        this.homeController = homeController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleNavigation(ActionEvent e) throws IOException {
        String path;
        switch (((MenuItem)e.getSource()).getId()) {
            case "doctor-profile" -> {
                if(this.navigateable instanceof DoctorProfileScreenController)
                    return;
                this.navigateable = new DoctorProfileScreenController();
                path = "/screen/doctor_profile_screen_layout.fxml";
            }
            case "work-type" -> {
                if(this.navigateable instanceof WorkTypeScreenController)
                    return;
                this.navigateable = new WorkTypeScreenController();
                path = "/screen/work_type_screen_layout.fxml";
            }
            case "supplier-profile" -> {
                if(this.navigateable instanceof SupplierProfileScreenController)
                    return;
                this.navigateable = new SupplierProfileScreenController();
                path = "/screen/supplier_profile_screen_layout.fxml";
            }
            case "employee-profile" -> {
                if(this.navigateable instanceof EmployeeProfileScreenController)
                    return;
                this.navigateable = new EmployeeProfileScreenController();
                path = "/screen/employee_profile_screen_layout.fxml";
            }
            case "add-lab-profile" -> {
                if(this.navigateable instanceof AddLabProfileScreenController)
                    return;
                this.navigateable = new AddLabProfileScreenController();
                path = "/screen/add_lab_profile_screen_layout.fxml";
            }
            case "add-user" -> {
                if(this.navigateable instanceof AddUserScreenController)
                    return;
                this.navigateable = new AddUserScreenController();
                path = "/screen/add_user_screen_layout.fxml";
            }
            case "records" -> {
                if(this.navigateable instanceof RecordScreenController)
                    return;
                this.navigateable = new RecordScreenController();
                path = "/screen/record_screen_layout.fxml";
            }
            case "about" -> {
                // TO-DO / HyperLink to Github
                System.out.println("about");
                return;
            }
            default -> {
                return;
            }
        }
        this.homeController.navigateTo(this.navigateable, path);
    }

}
