package com.dental.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "expenses")
public class Expenses {

    @DatabaseField(id = true, canBeNull = false, dataType = DataType.STRING)
    private String id;

    @DatabaseField(canBeNull = false, dataType = DataType.DATE_STRING)
    private String date;

    @DatabaseField(canBeNull = false, dataType = DataType.FLOAT)
    private float name;

    @DatabaseField(columnName = "payment_method", canBeNull = false, dataType = DataType.STRING)
    private String paymentMethod;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String description;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String comment;

    @DatabaseField(columnName = "paid_to", canBeNull = false, dataType = DataType.STRING)
    private String paidTo;

    public Expenses(String id, String date, float name, String paymentMethod, String description, String comment, String paidTo) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.description = description;
        this.comment = comment;
        this.paidTo = paidTo;
    }

    public Expenses() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getName() {
        return name;
    }

    public void setName(float name) {
        this.name = name;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

}
