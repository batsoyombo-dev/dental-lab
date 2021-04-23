package com.dental.controllers.screens;

import com.dental.dao.CrudDAO;
import com.dental.models.Expenses;
import com.dental.utils.Navigateable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class DailyExpensesScreenController implements Initializable, Navigateable {

    @FXML
    private DatePicker dp_expenseDateField;
    @FXML
    private TextField
            inp_expenseAmountField,
            inp_expensePaidToField,
            inp_expenseDescField;
    @FXML
    private ComboBox<String> cb_expensePaymentMethodField;
    @FXML
    private TextArea inp_expenseCommentField;
    @FXML
    private TableView<Expenses> tv_expensesList;
    @FXML
    private TableColumn<Expenses, String>
            tc_expenseIdColumn,
            tc_expenseDateColumn,
            tc_expensePaidToColumn,
            tc_expenseDescColumn,
            tc_expenseCommentColumn,
            tc_expensePaidByColumn,
            tc_expenseAmountColumn;

    private CrudDAO<Expenses> crudDAO;

    public DailyExpensesScreenController() {
        this.crudDAO = new CrudDAO<>(Expenses.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tc_expenseIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tc_expenseDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.tc_expensePaidToColumn.setCellValueFactory(new PropertyValueFactory<>("paidTo"));
        this.tc_expenseDescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.tc_expenseCommentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        this.tc_expensePaidByColumn.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        this.tc_expenseAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        this.tv_expensesList.getSelectionModel().selectedItemProperty().addListener((obs, a, b) -> {
            Expenses expenses = this.tv_expensesList.getSelectionModel().getSelectedItem();
            if (expenses == null)
                return;
            try {
                this.inflateFields(expenses);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        List<Expenses> expenses = this.crudDAO.list();
        this.tv_expensesList.getItems().addAll(expenses);
    }

    public void handleUpdateExpenseClick(MouseEvent e) {
        Expenses expenses = this.tv_expensesList.getSelectionModel().getSelectedItem();
        if (expenses == null)
            return;
        Expenses updatedExpenses = createExpenses();
        if (updatedExpenses == null)
            return;
        updatedExpenses.setId(expenses.getId());
        if (this.crudDAO.update(updatedExpenses)) {
            this.tv_expensesList.getItems().set(this.tv_expensesList.getSelectionModel().getSelectedIndex(), updatedExpenses);
        } else {
            System.out.println("error");
        }
    }

    public void handleDeleteExpenseClick(MouseEvent e) {
        Expenses expenses = this.tv_expensesList.getSelectionModel().getSelectedItem();
        if (expenses == null)
            return;
        if (this.crudDAO.delete(expenses.getId())) {
            this.tv_expensesList.getItems().remove(expenses);
        } else {
            System.out.println("error");
        }
    }

    public void handleAddExpenseClick(MouseEvent e) {
        Expenses expenses = this.createExpenses();
        if (expenses != null && this.crudDAO.create(expenses)) {
            this.tv_expensesList.getItems().add(expenses);
            this.cleanFields();
        } else {
            // Alert
            System.out.println("dqwdqw");
        }
    }

    public void handleCleanClick(MouseEvent e) {
        this.cleanFields();
    }

    private Expenses createExpenses() {
        String expenseDate = this.dp_expenseDateField.getValue().toString(),
                expenseAmount = this.inp_expenseAmountField.getText(),
                expensePaidTo = this.inp_expensePaidToField.getText(),
                expensePaymentMethod = this.cb_expensePaymentMethodField.getValue(),
                expenseDesc = this.inp_expenseDescField.getText(),
                expenseComment = this.inp_expenseCommentField.getText();

        if (expenseDate.isEmpty()
                || expenseAmount.isEmpty()
                || expensePaidTo.isEmpty()
                || expensePaymentMethod.isEmpty()
                || expenseDesc.isEmpty()
                || expenseComment.isEmpty())
            return null;

        try {
            float amount = Float.parseFloat(expenseAmount);
            return new Expenses(
                    UUID.randomUUID().toString(),
                    expenseDate,
                    amount,
                    expensePaymentMethod,
                    expenseDesc,
                    expenseComment,
                    expensePaidTo
            );
        } catch (NumberFormatException err) {
            return null;
        }
    }

    private void inflateFields(Expenses expenses) throws ParseException {
        this.dp_expenseDateField.setValue(LocalDate.parse(expenses.getDate()));
        this.inp_expenseAmountField.setText(expenses.getAmount() + "");
        this.inp_expensePaidToField.setText(expenses.getPaidTo());
        this.cb_expensePaymentMethodField.setValue(expenses.getPaymentMethod());
        this.inp_expenseDescField.setText(expenses.getDescription());
        this.inp_expenseCommentField.setText(expenses.getComment());
    }

    private void cleanFields() {
        this.dp_expenseDateField.setValue(LocalDate.now());
        this.inp_expenseAmountField.setText("");
        this.inp_expensePaidToField.setText("");
        this.cb_expensePaymentMethodField.setValue("Cash");
        this.inp_expenseDescField.setText("");
        this.inp_expenseCommentField.setText("");
    }

}
