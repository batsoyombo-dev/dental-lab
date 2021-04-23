package com.dental.controllers.partials;

import com.dental.controllers.HomeController;
import com.dental.controllers.screens.*;
import com.dental.utils.Navigateable;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {

    private HomeController homeController;

    public SidebarController(HomeController homeController) {
        this.homeController = homeController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleNavigation(MouseEvent e) throws IOException {
        homeController.inflateScreen(((Button)(e.getSource())).getId());
    }
}
