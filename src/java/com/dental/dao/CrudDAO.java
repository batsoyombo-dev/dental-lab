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

    /**
     * @return Tuhain <T> model - d hamaarah ogogdoliin sangiin husnegtnees buh ogogdoliig avna
     * */
    public List<T> list() {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, String> dao = DaoManager.createDao(con, this.cl);
            return dao.queryForAll();
        } catch(SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param object <T> modeliin buyu husnegtend nemeh shine object iig parameteraar avna
     * @return hervee amjilttai husnegt luu insert hiisen tohioldold true - g butsaan amjiltgu bol false - g butsaan
     * */
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

    /**
     * @param <E> Tuhain modeliin primary key - iin data type
     * @param pk Tuhain husnegteed avah gej bui mor bichlegiin primary key
     * @return parametreer avsan PK(ID) - tei mor bichlegiig <E> torliin object bolgon butsaan
     * */
    public <E> T retrieve(E pk) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, E> dao = DaoManager.createDao(con, this.cl);
            return dao.queryForId(pk);
        } catch(SQLException | IOException e) {
            return null;
        }
    }

    /**
     * @param object Oorchlogdson objectiig ogj yvuulna. Ene object ni
     *               huuchin update hiihees omno bsn PK(ID) ni adil bh yostoi
     * @return hervee amjilttai update hiisen bol true - g esergeeree bol false - g butsaan
     * */
    public boolean update(T object) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, String> dao = DaoManager.createDao(con, this.cl);
            dao.update(object);
            return true;
        } catch(SQLException | IOException e) {
            return false;
        }
    }

    /**
     * @param <E> Tuhain modeliin primary key - iin data type
     * @param pk ustgahiig hussen mor bichlegiin PK(ID) g ogj yvuulna
     * @return hervee amjilttai delete hiisen bol true - g butsaan amjiltgu bol false - g butsaan
     * */
    public <E> boolean delete(E pk) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, E> dao = DaoManager.createDao(con, this.cl);
            dao.deleteById(pk);
            return true;
        } catch(SQLException | IOException e) {
            return false;
        }
    }

    /**
     * @param column ... WHERE <column> = <value> <- ene hesegt bgaa colum - iin utagiig ogj tvuulna
     * @param value ... WHERE <column> = <value> <- ene hesegt bgaa value - iin utagiig ogj tvuulna
     * @return haih nohtsol taarsan buhiil mor bichlegiig list - d oruulj tuuniig butsaaan
     * */
    public List<T> search(String column, Object value) {
        try (ConnectionSource con = DBConnection.getConnectionSource();) {
            Dao<T, String> dao = DaoManager.createDao(con, this.cl);
            return dao.queryForEq(column, value);
        } catch(SQLException | IOException e) {
            return null;
        }
    }

    /**
     * @param column ... WHERE <column> > <valueA> and <column> < <valueB> <- ene hesegt bgaa colum - iin utagiig ogj tvuulna
     * @param valueA ... WHERE <column> > <valueA> and <column> < <valueB> <- ene hesegt bgaa valueA - iin utagiig ogj tvuulna
     * @param valueB ... WHERE <column> > <valueA> and <column> < <valueB> <- ene hesegt bgaa value - iin utagiig ogj tvuulna
     * @return haih nohtsol taarsan buhiil mor bichlegiig list - d oruulj tuuniig butsaaan
     * */
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