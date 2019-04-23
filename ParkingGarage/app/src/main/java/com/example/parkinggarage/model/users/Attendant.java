package com.example.parkinggarage.model.users;

import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.vehicles.Vehicle;

public class Attendant extends User {

	public Attendant(String firstName, String lastName, String password) {
		super(firstName, lastName, password);
	}

	public Document park(Vehicle vehicle, Garage garage) {
		if (garage.getTicketsAndReceipts().containsKey(vehicle.getLicense())) {
			return null;
		}

		Space space = garage.getSpaceBag().getClosestSpace(vehicle, "peek");

		if (space.isOccupied()) {
			return null;
		}

		space = garage.getSpaceBag().getClosestSpace(vehicle, "poll");
		space.setOccupied(true);
		garage.getSpaceBag().addSpace(space);
		Document doc = new Document(vehicle, space);
		garage.getTicketsAndReceipts().put(vehicle.getLicense(), doc);
		return doc;
	}

	public Document retrieve(String license, Garage garage, double payment) {
		if (!garage.getTicketsAndReceipts().containsKey(license)) {
			return null;
		}

		Document doc = garage.getTicketsAndReceipts().remove(license);
		Space space = doc.getSpace();
		doc.setTimeRetrieved();
		doc.setPaid(payment);
		garage.getSpaceBag().removeSpace(space);
		space.setOccupied(false);
		garage.getSpaceBag().addSpace(space);
		return doc;
	}

}