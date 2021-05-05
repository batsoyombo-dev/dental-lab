package com.dental.dao;

import com.dental.models.Doctor;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DBConnection {

    public static String USERNAME = "supersuper";
    public static String PASSWORD = "Super123@";
    public static String URL = "jdbc:mysql://52.141.57.195/dental?user=" + USERNAME + "&password=" + PASSWORD;

    public static ConnectionSource getConnectionSource() throws SQLException {
        return new JdbcConnectionSource(URL);
    }

}