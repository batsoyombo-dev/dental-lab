package com.dental.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "lab")
public class Lab {

    @DatabaseField(id = true, canBeNull = false, dataType = DataType.STRING)
    private String id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String address;

    @DatabaseField(columnName = "phone_num", canBeNull = false, dataType = DataType.STRING)
    private String phoneNumber;

    @DatabaseField(columnName = "mobile_num", canBeNull = false, dataType = DataType.STRING)
    private String mobileNumber;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String email;

    @DatabaseField(columnName = "vat_tin", canBeNull = false, dataType = DataType.STRING, unique = true)
    private String vatTin;

    @DatabaseField(columnName = "cst_num", canBeNull = false, dataType = DataType.STRING, unique = true)
    private String cstNumber;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING, unique = true)
    private String pan;

    public Lab() {

    }

    public Lab(String id, String address, String phoneNumber, String mobileNumber, String email, String vatTin, String cstNumber, String pan) {
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.vatTin = vatTin;
        this.cstNumber = cstNumber;
        this.pan = pan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getVatTin() {
        return vatTin;
    }

    public void setVatTin(String vatTin) {
        this.vatTin = vatTin;
    }

    public String getCstNumber() {
        return cstNumber;
    }

    public void setCstNumber(String cstNumber) {
        this.cstNumber = cstNumber;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    @Override
    public String toString() {
        return "Lab{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", vatTin='" + vatTin + '\'' +
                ", cstNumber='" + cstNumber + '\'' +
                ", pan='" + pan + '\'' +
                '}';
    }
}
