package com.example.parkinggarage.model.users;

import com.example.parkinggarage.model.utils.SingletonIncrementalDataContainer;

import java.io.Serializable;

/**
 * The User class contains String values for the first name, the last name,
 * the generated username, the password, and the unique id.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public abstract class User implements Serializable {

	private String firstName, lastName, username, password, id;

	public User(String firstName, String lastName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;

		setId();
		setUsername();
	}

	/**
	 * Sets the id of the user.
	 * The id has a range of 00000000 to 99999999
	 */
	private void setId() {
		int idCounter = SingletonIncrementalDataContainer.getDataContainer().getUserIdCounter(true);
		this.id = String.valueOf(idCounter);
		for (int i = 8; i > String.valueOf(idCounter).length(); i--) {
			id = '0' + id;
		}
	}

	/**
	 * Sets the username of the user.
	 * Uses the last 4 letters of the last name (the entire last name if its shorter than 4 characters),
	 * the first letter of the first name, and the last 3 characters from the id.
	 * <p>
	 * Example:
	 * <p>
	 * firstname = dennis
	 * lastname = hahn
	 * id = 00000001
	 * <p>
	 * username = hahnd001
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
	 * Checks the given password with the password of the user.
	 *
	 * @param inputPassword the password to check
	 * @return true if the passwords match, false if they don't
	 */
	public boolean verifyPassword(String inputPassword) {
		return password.equals(inputPassword);
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
}
