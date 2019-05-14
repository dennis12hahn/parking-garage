package com.example.parkinggarage.model.users;

import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.util.Map;
import java.util.Stack;

/**
 * The Attendant class is a subclass of the User class. An Attendant has the ability to park
 * and retrieve vehicles.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public class Attendant extends User {

	public Attendant(String firstName, String lastName, String password) {
		super(firstName, lastName, password);
	}

	/**
	 * Will park the given vehicle if it is not currently parked
	 * and there is an available space to park in. If the vehicle can be parked,
	 * then the nearest space for that vehicle is taken from the garage, set as occupied, and put back in.
	 * The vehicle is set as parked.
	 * A new document is created with the details of the space and the vehicle.
	 * If the garage already has a record of the vehicle, the document is appended to the stack.
	 * If it doesn't then a new element in the map is created with the document.
	 *
	 * @param vehicle the vehicle that is desired to be parked
	 * @param garage  the garage which contains the spaces and the tickets
	 * @return the created document object when the vehicle is parked
	 */
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

	/**
	 * Will retrieve the vehicle with the given license plate from the garage if it contains a record of the vehicle.
	 * If it does, the most recent document is peeked from the stack and updated.
	 * The vehicle is set as not parked.
	 * The space is removed from the garage, set as not occupied, and added back to the garage.
	 * This is done in order to keep the queue properly ordered.
	 *
	 * @param license the license of the vehicle desired to be retrieved
	 * @param garage  the garage containing the record of tickets and receipts and the spaces
	 * @param payment the amount being paid
	 * @return the updated, most recent document associated with the given license plate
	 */
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