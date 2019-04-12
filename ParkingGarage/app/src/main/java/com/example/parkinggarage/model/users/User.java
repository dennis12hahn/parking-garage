package com.example.parkinggarage.model.users;

import java.io.Serializable;

/**
 * The User class is not instantiable and is the super class of Attendants and the Manager
 * it contains information about a user such as first name, last name, the username
 * associated with the account, the user's password, and the unique id
 *
 * @author Dennis Hahn
 * @version 1, 03/28/2019
 */

public abstract class User implements Serializable {
    private String firstName, lastName, username, password, id;
    private static int idCounter = 0;

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;

        setId();
        setUsername();
    }


    /**
     * Creates a username to be associated with the user based off the last 4 letters of the
     * last name, the first letter of the first name, and the last 3 digits of id.
     * If the last name is less than or equal to 4 letters than the entire last name will be used.
     * The username will be entirely lowercase.
     *
     * @author Dennis Hahn
     */

    private void setUsername() {
        String lastNamePart;

        if (lastName.length() <= 4) {
            lastNamePart = lastName;
        } else {
            lastNamePart = lastName.substring(0, 4);
        }

        this.username = lastNamePart + firstName.charAt(0) + id.substring(5);
        this.username = username.toLowerCase();
    }

    /**
     * Sets the id for the user. The id is guaranteed to be unique as it will count
     * from 00000001 to 99999999 and it will always be 8 digits long.
     *
     * @author Dennis Hahn
     */

    private void setId() {
        this.id = String.valueOf(++idCounter);
        for (int i = 8; i > String.valueOf(idCounter).length(); i--) {
            id = '0' + id;
        }
    }

    /**
     * Verifies an incoming password with the password stored in the account.
     *
     * @param inputPassword: the String entered by the client
     * @return boolean value based on if the passwords matched. True if they did, false if the did not.
     */
    public boolean verifyPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
}
