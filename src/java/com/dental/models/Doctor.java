package com.dental.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import jdk.jfr.Relational;
@DatabaseTable(tableName = "doctor")
public class Doctor {

    @DatabaseField(id = true, canBeNull = false, dataType = DataType.STRING)
    private String id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String name;

    @DatabaseField(canBeNull = false, dataType = DataType.CHAR)
    private char gender;

    @DatabaseField(canBeNull = false)
    private String dob;

    @DatabaseField(columnName = "clinic_address", canBeNull = false, dataType = DataType.STRING)
    private String clinicAddress;

    @DatabaseField(columnName = "payment_address", canBeNull = false, dataType = DataType.STRING)
    private String paymentAddress;

    @DatabaseField(columnName = "mobile_num", canBeNull = false, dataType = DataType.STRING)
    private String mobileNumber;

    @DatabaseField(columnName = "landline_num", canBeNull = false, dataType = DataType.STRING)
    private String landlineNumber;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String email;

    @DatabaseField(canBeNull = false, dataType = DataType.FLOAT)
    private float balance;

    public Doctor() {

    }

    public Doctor(String id, String name, char gender, String dob, String clinicAddress, String paymentAddress, String mobileNumber, String landlineNumber, String email, float balance) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.clinicAddress = clinicAddress;
        this.paymentAddress = paymentAddress;
        this.mobileNumber = mobileNumber;
        this.landlineNumber = landlineNumber;
        this.email = email;
        this.balance = balance;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public String getPaymentAddress() {
        return paymentAddress;
    }

    public void setPaymentAddress(String paymentAddress) {
        this.paymentAddress = paymentAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}