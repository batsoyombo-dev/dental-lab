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

    public MenubarController(HomeController homeController) {
        this.homeController = homeController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleNavigation(ActionEvent e) throws IOException {
        this.homeController.inflateScreen(((MenuItem)(e.getSource())).getId());
    }

}
