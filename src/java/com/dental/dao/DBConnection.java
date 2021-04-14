package com.dental.dao;

import com.dental.models.Doctor;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DBConnection {

    public static String USERNAME = "super";
    public static String PASSWORD = "";
    public static String URL = "jdbc:mysql://localhost:3306/dental?user=super";

    public static ConnectionSource getConnectionSource() throws SQLException {
        return new JdbcConnectionSource(URL);
    }

}