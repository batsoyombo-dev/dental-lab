package com.dental.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "employee")
public class Employee {

    @DatabaseField(canBeNull = false, id = true, dataType = DataType.STRING)
    private String id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String name;

    @DatabaseField(canBeNull = false, dataType = DataType.CHAR)
    private char gender;

    @DatabaseField(canBeNull = false, dataType = DataType.DATE_STRING)
    private String dob;

    @DatabaseField(columnName = "address", canBeNull = false, dataType = DataType.STRING)
    private String address;

    @DatabaseField(columnName = "designation", canBeNull = false, dataType = DataType.STRING)
    private String designation;

    @DatabaseField(columnName = "mobile_num", canBeNull = false, dataType = DataType.STRING)
    private String mobileNumber;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String email;

    @DatabaseField(canBeNull = false, dataType = DataType.INTEGER)
    private int salaryMonth;

    public Employee(String id, String name, char gender, String dob, String address, String designation, String mobileNumber, String email, int salaryMonth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.designation = designation;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.salaryMonth = salaryMonth;
    }

    public Employee() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public int getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(int salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

}
