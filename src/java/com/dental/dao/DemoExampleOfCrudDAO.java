package com.dental.dao;

import com.dental.models.Doctor;

import java.util.List;
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

    public void updateExample() {
        Doctor updatedDoctor = new Doctor(
                "d4567410-a2e3-435a-8ba6-ac8eebcfc0af", // insert eer uussen UUID
                "nameUpdated",
                'f',
                "2001-02-02",
                "ClinicAddressUpdated",
                "PaymentAddressUpdated",
                "123123123Updated",
                "123123123Updated",
                "email@gmail.comUpdated",
                1.2f
        );

        if (crudDAO.update(updatedDoctor)) {
            // If successfully inserted
            System.out.println("Success");
        } else {
            // If unsuccessfully inserted
            System.out.println("Failed");
        }
    }

    public void deleteExample() {
        if (crudDAO.delete("d4567410-a2e3-435a-8ba6-ac8eebcfc0af")) {
            // If successfully inserted
            System.out.println("Success");
        } else {
            // If unsuccessfully inserted
            System.out.println("Failed");
        }

    }

    public void searchExample() {
        List<Doctor> doctors = crudDAO.search("name", "dwqdqwq");
        if (doctors != null) {
            for (Doctor doctor : doctors)
                System.out.println(doctor);
        } else {
            System.out.println("Nothing");
        }
    }

    public void searchSecondExample() {
        List<Doctor> doctors = crudDAO.search("dob", "2011-02-05", "2011-02-20");
        if (doctors != null) {
            for (Doctor doctor : doctors)
                System.out.println(doctor);
        } else {
            System.out.println("Nothing");
        }
    }

}
