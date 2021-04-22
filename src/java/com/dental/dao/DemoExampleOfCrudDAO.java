package com.dental.dao;

import com.dental.models.Doctor;

import java.util.UUID;

public class DemoExampleOfCrudDAO {

    private CrudDAO<Doctor> crudDAO = new CrudDAO<>(Doctor.class);

    public void insertExample() {
        Doctor newDoctor = new Doctor(
                UUID.randomUUID().toString(),
                "name",
                'f',
                "2001-02-02",
                "ClinicAddress",
                "PaymentAddress",
                "123123123",
                "123123123",
                "email@gmail.com",
                1.2f
        );

        if (crudDAO.create(newDoctor)) {
            // If successfully inserted
            System.out.println("Success");
        } else {
            // If unsuccessfully inserted
            System.out.println("Failed");
        }
    }

}
