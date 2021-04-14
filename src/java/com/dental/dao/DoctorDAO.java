package com.dental.dao;

import com.dental.models.Doctor;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;

import java.io.IOException;
import java.sql.SQLException;

public class DoctorDAO {

    public boolean addDoctor(Doctor doctor) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<Doctor, String> doctorDAO = DaoManager.createDao(con, Doctor.class);
            doctorDAO.create(doctor);
            return true;
        } catch(SQLException | IOException e) {
            return false;
        }
    }

    public Doctor getDoctor(String pk) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<Doctor, String> doctorDAO = DaoManager.createDao(con, Doctor.class);
            return doctorDAO.queryForId(pk);
        } catch(SQLException | IOException e) {
            return null;
        }
    }

}
