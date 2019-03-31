package com.example.parkinggarage.model.users;

import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.users.Manager;
import com.example.parkinggarage.model.users.User;

import java.util.HashMap;
import java.util.Map;

/**
 * The UserBag class contains a HashMap of users. A user can be an Attendant or a Manager.
 * The HashMap contains a key of type String which will be the username of the user and a value of type
 * User which will be the actual user object. As of right now there is no maximum size.
 *
 * @author Dennis Hahn
 * @version 1, 03/29/2019
 */

public class UserBag {

    private Map<String, User> users;

    public UserBag() {
        this.users = new HashMap<>();
    }

    /**
     * Will add a Manager object to the userbag.
     *
     * @param firstName
     * @param lastName
     * @param password
     * @return true if the user was added, false if not. Will only return false if the bag already contains the generated username.
     */

    public boolean addManager(String firstName, String lastName, String password) {
        Manager manager = new Manager(firstName, lastName, password);

        if (users.containsKey(manager.getUsername())) {
            return false;
        }

        users.put(manager.getUsername(), manager);
        return true;
    }

    /**
     * Will add an Attendant object to the userbag.
     *
     * @param firstName
     * @param lastName
     * @param password
     * @return true if the user was added, false if not. Will only return false if the bag already contains the generated username.
     */

    public boolean addAttendant(String firstName, String lastName, String password) {
        Attendant attendant = new Attendant(firstName, lastName, password);

        if (users.containsKey(attendant.getUsername())) {
            return false;
        }

        users.put(attendant.getUsername(), attendant);
        return true;
    }

    /**
     * To be used when creating accounts and logging in.
     * If the userbag already contains the given username, return true, false otherwise
     *
     * @param username
     * @return true if the userbag contains the username, false if it does not
     */
    public boolean verifyUsername(String username) {
        return users.containsKey(username);
    }

    /**
     * To be used when logging in.
     * If the password of the user mapped to the given username matches the supplied password, return true
     * false otherwise
     *
     * @param username
     * @param password
     * @return true if the passwords match
     */
    public boolean verifyPassword(String username, String password) {
        return users.get(username).verifyPassword(password);
    }
}
