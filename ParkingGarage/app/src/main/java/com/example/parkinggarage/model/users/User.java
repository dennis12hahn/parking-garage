package com.example.parkinggarage.model.users;

import com.example.parkinggarage.model.utils.SingletonIncrementalDataContainer;

import java.io.Serializable;

public abstract class User implements Serializable {

	private String firstName, lastName, username, password, id;

	public User(String firstName, String lastName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;

		setId();
		setUsername();
	}

	private void setId() {
		int idCounter = SingletonIncrementalDataContainer.getDataContainer().getUserIdCounter(true);
		this.id = String.valueOf(idCounter);
		for (int i = 8; i > String.valueOf(idCounter).length(); i--) {
			id = '0' + id;
		}
	}

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
