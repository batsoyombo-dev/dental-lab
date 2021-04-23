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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
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
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void inflateScreen(String screen) throws IOException {
        String path;
        switch(screen) {
            case "register" -> {
                if(this.navigateable instanceof RegisterScreenController)
                    return;
                this.navigateable = new RegisterScreenController();
                path = "/screen/register_screen_layout.fxml";
            }
            case "search-entries" -> {
                if(this.navigateable instanceof SearchEntriesScreenController)
                    return;
                this.navigateable = new SearchEntriesScreenController();
                path = "/screen/search_entries_screen_layout.fxml";
            }
            case "analysis" -> {
                if(this.navigateable instanceof AnalysisScreenController)
                    return;
                this.navigateable = new AnalysisScreenController();
                path = "/screen/analysis_screen_layout.fxml";
            }
            case "gen-invoice" -> {
                if(this.navigateable instanceof GenerateInvoiceScreenController)
                    return;
                this.navigateable = new GenerateInvoiceScreenController();
                path = "/screen/generate_invoice_screen_layout.fxml";
            }
            case "daily-expenses" -> {
                if(this.navigateable instanceof DailyExpensesScreenController)
                    return;
                this.navigateable = new DailyExpensesScreenController();
                path = "/screen/daily_expenses_screen_layout.fxml";
            }
            case "today-delivery" -> {
                if(this.navigateable instanceof TodayDeliveryScreenController)
                    return;
                this.navigateable = new TodayDeliveryScreenController();
                path = "/screen/today_delivery_screen_layout.fxml";
            }
            case "ledger-account" -> {
                if(this.navigateable instanceof LedgerAccountScreenController)
                    return;
                this.navigateable = new LedgerAccountScreenController();
                path = "/screen/ledger_account_screen_layout.fxml";
            }
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
