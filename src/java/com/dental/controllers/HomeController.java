package com.dental.controllers;

import com.dental.controllers.partials.SidebarController;
import com.dental.utils.LayoutInflater;
import com.dental.utils.Navigateable;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private BorderPane root;

    public HomeController(Stage stage) throws IOException {
        this.root = (BorderPane) LayoutInflater.inflate(this, super.getClass().getResource("/layout/home_layout.fxml"));
        this.root.setLeft(LayoutInflater.inflate(new SidebarController(this), super.getClass().getResource("/layout/partials/sidebar_layout.fxml")));

        Scene scene = new Scene(this.root);
        stage.setScene(scene);
        stage.setTitle("Dental Lab");
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void navigateTo(Navigateable navigateable, String layoutPath) throws IOException {
        this.root.setCenter(LayoutInflater.inflate(navigateable, super.getClass().getResource("/layout" + layoutPath)));
    }

}
