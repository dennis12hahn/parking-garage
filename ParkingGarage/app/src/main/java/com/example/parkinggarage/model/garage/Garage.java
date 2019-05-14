package com.example.parkinggarage.model.garage;

import com.example.parkinggarage.model.spaces.SpaceBag;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Manager;
import com.example.parkinggarage.model.users.UserBag;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * A Garage contains a map of documents that links a license key to a Document stack value.
 * A manager to control the garage.
 * A user bag to hold all the managers and attendants in the garage.
 * A space bag to hold all the priority queues of the types of spaces.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public class Garage implements Serializable {

	private Map<String, Stack<Document>> ticketsAndReceipts;
	private Manager manager;
	private UserBag userBag;
	private SpaceBag spaceBag;

	public Garage(String managerFirstName, String managerLastName, String managerPassword) {
		this.spaceBag = new SpaceBag();
		this.userBag = new UserBag();
		this.ticketsAndReceipts = new HashMap<>();
		this.manager = createManager(managerFirstName, managerLastName, managerPassword);
	}

	/**
	 * Will create and add a manager to the user bag
	 *
	 * @param managerFirstName the first name of the manager
	 * @param managerLastName  the last name of the manager
	 * @param managerPassword  the password of the manager
	 * @return the created manager object
	 */
	private Manager createManager(String managerFirstName, String managerLastName, String managerPassword) {
		Manager manager = new Manager(managerFirstName, managerLastName, managerPassword);
		userBag.addManager(manager);
		return manager;
	}

	public SpaceBag getSpaceBag() {
		return spaceBag;
	}

	public Manager getManager() {
		return manager;
	}

	public UserBag getUserBag() {
		return userBag;
	}

	public Map<String, Stack<Document>> getTicketsAndReceipts() {
		return ticketsAndReceipts;
	}

}
