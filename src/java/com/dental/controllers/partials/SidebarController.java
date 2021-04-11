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
            case "exit" -> {
                Platform.exit();
                return;
            }
            default -> {
                return;
            }
        }
        homeController.navigateTo(this.navigateable, path);
    }
}
