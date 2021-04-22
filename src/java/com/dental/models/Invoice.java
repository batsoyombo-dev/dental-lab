package com.dental.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "invoice")
public class Invoice {

    @DatabaseField(id = true, canBeNull = false, dataType = DataType.STRING)
    private String id;

    @DatabaseField(columnName = "generated_date", canBeNull = false)
    private String generatedDate;

    @DatabaseField(columnName = "registration_id", canBeNull = false, foreign = true)
    private Registration registration;

    public Invoice() {

    }

    public Invoice(String id, String generatedDate, Registration registration) {
        this.id = id;
        this.generatedDate = generatedDate;
        this.registration = registration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(String generatedDate) {
        this.generatedDate = generatedDate;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", generatedDate='" + generatedDate + '\'' +
                ", registration=" + registration +
                '}';
    }
}