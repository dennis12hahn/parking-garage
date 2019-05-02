package com.example.parkinggarage.model.garage;

import com.example.parkinggarage.model.spaces.SpaceBag;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Manager;
import com.example.parkinggarage.model.users.UserBag;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
