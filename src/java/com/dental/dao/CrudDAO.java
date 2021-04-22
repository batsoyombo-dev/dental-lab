package com.dental.dao;

import com.dental.models.Doctor;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CrudDAO<T> {

    private Class<T> cl;

    public CrudDAO(Class<T> cl) {
        this.cl = cl;
    }

    public List<T> list() {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, String> dao = DaoManager.createDao(con, this.cl);
            return dao.queryForAll();
        } catch(SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean create(T object) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, String> dao = DaoManager.createDao(con, this.cl);
            dao.create(object);
            return true;
        } catch(SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public <E> T retrieve(E pk) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, E> dao = DaoManager.createDao(con, this.cl);
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

    public List<T> search(String column, Object value) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, String> dao = DaoManager.createDao(con, this.cl);
            return dao.queryForEq(column, value);
        } catch(SQLException | IOException e) {
            return null;
        }
    }

    public List<T> search(String column, Object valueA, Object valueB) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, String> dao = DaoManager.createDao(con, this.cl);
            QueryBuilder<T, String> queryBuilder = dao.queryBuilder();
            queryBuilder.where().between(column, valueA, valueB);
            return queryBuilder.query();
        } catch(SQLException | IOException e) {
            return null;
        }
    }

}