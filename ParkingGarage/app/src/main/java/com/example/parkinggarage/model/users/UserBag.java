package com.example.parkinggarage.model.users;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A UserBag contains a map of username Strings to Users. The map is a HashMap allowing for quick searching
 * of usernames and a swift sign in process.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public class UserBag implements Serializable {

	private Map<String, User> users;

	public UserBag() {
		this.users = new HashMap<>();
	}

	/**
	 * Will create and add a Manager to the HashMap
	 *
	 * @param firstName the first name of the manager
	 * @param lastName  the last name of the manager
	 * @param password  the password of the manager
	 * @return true if the user was added, false if not. Will only return false if the bag already contains the generated username
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
	 * Will add an already created Manager to the HashMap
	 *
	 * @param manager the created manager object
	 * @return true if the user was added, false if not. Will only return false if the bag already contains the generated username
	 */
	public boolean addManager(Manager manager) {
		if (users.containsKey(manager.getUsername())) {
			return false;
		}

		users.put(manager.getUsername(), manager);
		return true;
	}

	/**
	 * Will create and add an Attendant to the HashMap
	 *
	 * @param firstName the first name of the attendant
	 * @param lastName  the last name of the attendant
	 * @param password  the password of the attendant
	 * @return true if the user was added, false if not. Will only return false if the bag already contains the generated username
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
	 * Will add an already created Attendant to the HashMap
	 *
	 * @param attendant the created attendant object
	 * @return true if the user was added, false if not. Will only return false if the bag already contains the generated username
	 */
	public boolean addAttendant(Attendant attendant) {
		if (users.containsKey(attendant.getUsername())) {
			return false;
		}

		users.put(attendant.getUsername(), attendant);
		return true;
	}

	/**
	 * Will be used to check if the HashMap contains a given username
	 *
	 * @param username the username to check
	 * @return true if the HashMap contains the username, false if it does not
	 */
	public boolean verifyUsername(String username) {
		return users.containsKey(username);
	}

	/**
	 * Will be used to check if the password of the User with the given username matches the given password
	 *
	 * @param username the username to check
	 * @param password the password to check
	 * @return true if the passwords match, false if they don't
	 */
	public boolean verifyPassword(String username, String password) {
		return users.get(username).verifyPassword(password);
	}

	public User getUser(String username) {
		return users.get(username);
	}
}
