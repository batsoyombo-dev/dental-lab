package com.dental.dao;

import com.dental.models.Doctor;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;

public class CrudDAO<T> {

    private Class<T> cl;

    public CrudDAO(Class<T> cl) {
        this.cl = cl;
    }

    public boolean create(T object) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, String> dao = DaoManager.createDao(con, this.cl);
            dao.create(object);
            return true;
        } catch(SQLException | IOException e) {
            return false;
        }
    }

    public T retrieve(String pk) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, String> dao = DaoManager.createDao(con, this.cl);
            return dao.queryForId(pk);
        } catch(SQLException | IOException e) {
            return null;
        }
    }

    public boolean update(T object) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, String> dao = DaoManager.createDao(con, this.cl);
            dao.update(object);
            return true;
        } catch(SQLException | IOException e) {
            return false;
        }
    }

    public <E> boolean delete(E pk) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, E> dao = DaoManager.createDao(con, this.cl);
            dao.deleteById(pk);
            return true;
        } catch(SQLException | IOException e) {
            return false;
        }
    }

}