package com.dental.models;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "work_type")
public class WorkType {

    @DatabaseField(canBeNull = false, id = true, dataType = DataType.STRING)
    private String id;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String work;

    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String description;

    @DatabaseField(canBeNull = false, dataType = DataType.FLOAT)
    private float charge;

    public WorkType() {

    }

    public WorkType(String id, String work, String description, float charge) {
        this.id = id;
        this.work = work;
        this.description = description;
        this.charge = charge;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return this.work;
    }
}
