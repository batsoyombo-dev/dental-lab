package com.dental.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "doctor")
public class Doctor {

    @DatabaseField(id = true)
    private String id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private char gender;

    @DatabaseField(canBeNull = false)
    private String dob;

    @DatabaseField(columnName = "clinic_address", canBeNull = false)
    private String clinicAddress;

    @DatabaseField(columnName = "payment_address", canBeNull = false)
    private String paymentAddress;

    @DatabaseField(columnName = "mobile_num", canBeNull = false)
    private String mobileNumber;

    @DatabaseField(columnName = "landline_num", canBeNull = false)
    private String landlineNumber;

    @DatabaseField(canBeNull = false)
    private String email;

    @DatabaseField(canBeNull = false)
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
        return "Doctor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", dob='" + dob + '\'' +
                ", clinicAddress='" + clinicAddress + '\'' +
                ", paymentAddress='" + paymentAddress + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", landlineNumber='" + landlineNumber + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}