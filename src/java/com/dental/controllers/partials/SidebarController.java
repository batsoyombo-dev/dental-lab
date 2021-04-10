package com.dental.controllers.partials;

import com.dental.controllers.HomeController;
import com.dental.controllers.screens.RegisterScreenController;
import com.dental.utils.Navigateable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {

    private HomeController homeController;
    private Navigateable navigateable;

    public SidebarController(HomeController homeController) {
        this.homeController = homeController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleNavigation(MouseEvent e) throws IOException {
        String path;
        switch(((Button)(e.getSource())).getId()) {
            case "register" -> {
                if(this.navigateable instanceof RegisterScreenController)
                    return;
                this.navigateable = new RegisterScreenController();
                path = "/screen/register_screen_layout.fxml";
            }
            default -> {
                return;
            }
        }
        homeController.navigateTo(this.navigateable, path);
    }
}
