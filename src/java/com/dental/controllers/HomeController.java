package com.dental.controllers;

import com.dental.controllers.partials.MenubarController;
import com.dental.controllers.partials.SidebarController;
import com.dental.controllers.screens.*;
import com.dental.utils.LayoutInflater;
import com.dental.utils.Navigateable;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private BorderPane root;
    private Navigateable navigateable;
    private String currentScreen;

    public HomeController(Stage stage) throws IOException {
        this.root = (BorderPane) LayoutInflater.inflate(this, super.getClass().getResource("/layout/home_layout.fxml"));
        this.root.setLeft(LayoutInflater.inflate(new SidebarController(this), super.getClass().getResource("/layout/partials/side_bar_partial.fxml")));
        this.root.setTop(LayoutInflater.inflate(new MenubarController(this), super.getClass().getResource("/layout/partials/menu_bar_partial.fxml")));
        Scene scene = new Scene(this.root);
        stage.setScene(scene);
        stage.setTitle("Dental Lab");
        stage.setMaximized(true);
        stage.getIcons().add(new Image("/images/tooth128.png"));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void inflateScreen(String screen) throws IOException, URISyntaxException {
        String path;
        if (this.currentScreen == null || !this.currentScreen.equals(screen))
            this.currentScreen = screen;
        else return;
        switch(screen) {
            case "register" -> {
                this.navigateable = new RegisterScreenController();
                path = "/screen/register_screen_layout.fxml";
            }
            case "search-entries" -> {
                this.navigateable = new SearchEntriesScreenController();
                path = "/screen/search_entries_screen_layout.fxml";
            }
            case "analysis" -> {
                this.navigateable = new AnalysisScreenController();
                path = "/screen/analysis_screen_layout.fxml";
            }
            case "gen-invoice" -> {
                this.navigateable = new GenerateInvoiceScreenController();
                path = "/screen/generate_invoice_screen_layout.fxml";
            }
            case "daily-expenses" -> {
                this.navigateable = new DailyExpensesScreenController();
                path = "/screen/daily_expenses_screen_layout.fxml";
            }
            case "today-delivery" -> {
                this.navigateable = new TodayDeliveryScreenController();
                path = "/screen/today_delivery_screen_layout.fxml";
            }
            case "ledger-account" -> {
                this.navigateable = new LedgerAccountScreenController();
                path = "/screen/ledger_account_screen_layout.fxml";
            }
            case "doctor-profile" -> {
                this.navigateable = new DoctorProfileScreenController();
                path = "/screen/doctor_profile_screen_layout.fxml";
            }
            case "work-type" -> {
                this.navigateable = new WorkTypeScreenController();
                path = "/screen/work_type_screen_layout.fxml";
            }
            case "supplier-profile" -> {
                this.navigateable = new SupplierProfileScreenController();
                path = "/screen/supplier_profile_screen_layout.fxml";
            }
            case "employee-profile" -> {
                this.navigateable = new EmployeeProfileScreenController();
                path = "/screen/employee_profile_screen_layout.fxml";
            }
            case "add-lab-profile" -> {
                this.navigateable = new AddLabProfileScreenController();
                path = "/screen/add_lab_profile_screen_layout.fxml";
            }
            case "add-user" -> {
                this.navigateable = new AddUserScreenController();
                path = "/screen/add_user_screen_layout.fxml";
            }
            case "records" -> {
                this.navigateable = new RecordScreenController();
                path = "/screen/record_screen_layout.fxml";
            }
            case "about" -> {
                URI uri = new URI("https://github.com/batsoyombo-dev/dental-lab");
                Desktop.getDesktop().browse(uri);
                return;
            }
            case "exit" -> {
                Platform.exit();
                return;
            }
            default -> {
                return;
            }
        }
        this.navigateTo(this.navigateable, path);
    }

    private void navigateTo(Navigateable navigateable, String layoutPath) throws IOException {
        this.root.setCenter(LayoutInflater.inflate(navigateable, super.getClass().getResource("/layout" + layoutPath)));
    }

}
