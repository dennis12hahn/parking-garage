package com.example.parkinggarage.model.users;

import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.util.Map;
import java.util.Stack;

public class Attendant extends User {

	public Attendant(String firstName, String lastName, String password) {
		super(firstName, lastName, password);
	}

	public Document park(Vehicle vehicle, Garage garage) {
		if (vehicle.isParked()) {
			return null;
		}
		
		String license = vehicle.getLicense();
		Map<String, Stack<Document>> ticketsAndReceipts = garage.getTicketsAndReceipts();

		Space space = garage.getSpaceBag().getClosestSpace(vehicle, "poll");
		space.setOccupied(true);
		garage.getSpaceBag().addSpace(space);

		vehicle.setParked(true);

		Document doc = new Document(vehicle, space);
		Stack<Document> stack;
		
		if (ticketsAndReceipts.containsKey(license)) {
			stack = ticketsAndReceipts.get(license);
		} else {
			stack = new Stack<>();
			ticketsAndReceipts.put(license, stack);
		}

		stack.push(doc);
		return doc;
	}

	public Document retrieve(String license, Garage garage, double payment) {
		Map<String, Stack<Document>> ticketsAndReceipts = garage.getTicketsAndReceipts();
		
		if (!ticketsAndReceipts.containsKey(license)) {
			return null;
		}

		Document doc = ticketsAndReceipts.get(license).peek();
		Space space = doc.getSpace();

		doc.setTimeRetrieved();
		doc.setPaid(payment);
		doc.getVehicle().setParked(false);

		garage.getSpaceBag().removeSpace(space);
		space.setOccupied(false);
		garage.getSpaceBag().addSpace(space);
		return doc;
	}

}