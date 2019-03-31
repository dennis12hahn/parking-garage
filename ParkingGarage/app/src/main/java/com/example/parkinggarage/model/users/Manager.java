package com.example.parkinggarage.model.users;

/**
 * The Manager class is a subclass of the User class. A manager has the ability to create attendants.
 * This will be used upon the arrival of an attendant to the parking garage.
 *
 * @author Dennis Hahn
 * @version 1, 03/29/2019
 */
public class Manager extends User {

    public Manager(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
    }

    /**
     * Creates and returns an attendant with the given first name, last name, and password.
     *
     * @param firstName
     * @param lastName
     * @param password
     * @return the created Attendant object
     */
    public Attendant createAttendant(String firstName, String lastName, String password) {
        return new Attendant(firstName, lastName, password);
    }
}
