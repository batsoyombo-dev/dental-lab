package com.dental.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "registration")
public class Registration {

    @DatabaseField(id = true, canBeNull = false, dataType = DataType.STRING)
    private String id;

    @DatabaseField(columnName = "patient_name", canBeNull = false, dataType = DataType.STRING)
    private String patientName;

    @DatabaseField(canBeNull = false)
    private String date;

    @DatabaseField(columnName = "trial_date", canBeNull = false)
    private String trialDate;

    @DatabaseField(columnName = "finished_date", canBeNull = false)
    private String finishedDate;

    @DatabaseField(canBeNull = false, dataType = DataType.FLOAT)
    private float charges;

    @DatabaseField(columnName = "doctor_id", canBeNull = false, foreign = true)
    private Doctor doctor;

    @DatabaseField(columnName = "work_type_id", canBeNull = false, foreign = true)
    private WorkType workType;

    @DatabaseField(canBeNull = false)
    private String teeth;

    public Registration() {

    }

    public Registration(String id, String patientName, String date, String trialDate, String finishedDate, float charges, Doctor doctor, WorkType workType, String teeth) {
        this.id = id;
        this.patientName = patientName;
        this.date = date;
        this.trialDate = trialDate;
        this.finishedDate = finishedDate;
        this.charges = charges;
        this.doctor = doctor;
        this.workType = workType;
        this.teeth = teeth;
    }

    public String getTeeth() {
        return teeth;
    }

    public void setTeeth(String teeth) {
        this.teeth = teeth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTrialDate() {
        return trialDate;
    }

    public void setTrialDate(String trialDate) {
        this.trialDate = trialDate;
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = finishedDate;
    }

    public float getCharges() {
        return charges;
    }

    public void setCharges(float charges) {
        this.charges = charges;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id='" + id + '\'' +
                ", patientName='" + patientName + '\'' +
                ", date='" + date + '\'' +
                ", trialDate='" + trialDate + '\'' +
                ", finishedDate='" + finishedDate + '\'' +
                ", charges=" + charges +
                ", doctor=" + doctor +
                ", workType=" + workType +
                '}';
    }
}
