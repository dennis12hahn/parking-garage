package com.example.parkinggarage.model.users;

/**
 * The Manager class is a subclass of the User class and the Attendant class.
 * A manager has the ability to create attendants as well as the abilities of
 * an Attendant.
 * This will be used upon the arrival of an attendant to the parking garage.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public class Manager extends Attendant {

	public Manager(String firstName, String lastName, String password) {
		super(firstName, lastName, password);
	}

	/**
	 * Creates and returns an attendant with the given first name, last name, and password.
	 *
	 * @param firstName the first name of the attendant
	 * @param lastName  the last name of the attendant
	 * @param password  the password of the attendant
	 * @return the created Attendant object
	 */
	public Attendant createAttendant(String firstName, String lastName, String password) {
		return new Attendant(firstName, lastName, password);
	}
}
