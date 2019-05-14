package com.example.parkinggarage.model.tickets_and_receipts;

import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

/**
 * A Document is a ticket and a receipt. It contains the vehicle that has been parked/retrieved and its space.
 * The date and time for when it was parked and retrieved.
 * The early bird hour.
 * Whether or not the vehicle was parked before the early bird hour.
 * And the amount paid.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public class Document implements Serializable {

	private final static int EARLY_BIRD_HOUR = 8;
	private Vehicle vehicle;
	private Space space;
	private LocalDateTime timeParked, timeRetrieved;
	private boolean earlyBird;
	private double paid;

	public Document(Vehicle vehicle, Space space) {
		this.vehicle = vehicle;
		this.space = space;
		this.timeParked = LocalDateTime.now();
		this.earlyBird = timeParked.getHour() < EARLY_BIRD_HOUR;
	}

	public void setTimeRetrieved() {
		this.timeRetrieved = LocalDateTime.now();
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}

	/**
	 * @return an ArrayList containing the ticket info and the info for the receipt
	 */
	public ArrayList<String> getReceiptInfo() {
		ArrayList<String> receiptInfo = getTicketInfo();
		if (timeRetrieved == null) {
			receiptInfo.add(null);
			receiptInfo.add(null);
		} else {
			receiptInfo.add("Date Retrieved: " + timeRetrieved.toLocalDate());
			receiptInfo.add("Time Retrieved: " + formatTime(timeRetrieved));
			receiptInfo.add("Price: " + calculateCharge());
			receiptInfo.add("Amount Paid: " + paid);
		}
		return receiptInfo;
	}

	/**
	 * @return an ArrayList containing the ticket info
	 */
	public ArrayList<String> getTicketInfo() {
		String spaceType = space.getClass().getSimpleName();
		spaceType = spaceType.substring(0, spaceType.indexOf("Space"));

		ArrayList<String> ticketInfo = new ArrayList<>();

		ticketInfo.add("License: " + vehicle.getLicense());
		ticketInfo.add("Type of Space: " + spaceType);
		ticketInfo.add("Date Parked: " + timeParked.toLocalDate());
		ticketInfo.add("Time Parked: " + formatTime(timeParked));

		StringBuilder paymentScheme = new StringBuilder("Payment Scheme: ");
		if (earlyBird) {
			paymentScheme.append("Early Bird ($" + space.getEarlyBirdPrice() + ")");
		} else {
			paymentScheme.append("Hourly ($" + space.getRate() + "/hr)");
		}
		ticketInfo.add(paymentScheme.toString());

		return ticketInfo;
	}

	private String formatTime(LocalDateTime time) {
		StringBuilder builder = new StringBuilder();

		int hours = time.getHour();
		int minutes = time.getMinute();
		boolean pastNoon = hours > 12;

		builder.append(pastNoon ? hours - 12 : hours);
		builder.append(":").append(minutes < 10 ? "0" + minutes : minutes);
		builder.append(pastNoon ? " pm" : " am");

		return builder.toString();
	}

	/**
	 * Will calculate the proper amount to charge the customer.
	 *
	 * @return the early bird price of the space if the vehicle was parked before the early brid hour
	 * or the duration parked (in hours) times the hourly rate of the space
	 */
	private double calculateCharge() {
		if (earlyBird) {
			return space.getEarlyBirdPrice();
		}

		return getDurationParked() * space.getRate();
	}

	/**
	 * Example:
	 * <p>
	 * Vehicle parked at 10:00am
	 * Vehicle retrieved at 10:01am
	 * <p>
	 * will return 1
	 *
	 * @return the number of hours the vehicle was parked in the space.
	 * Rounds up to the nearest hour.
	 */
	private int getDurationParked() {
		long parked = timeParked.atZone(ZoneId.systemDefault()).toEpochSecond();
		long retrieved = timeRetrieved.atZone(ZoneId.systemDefault()).toEpochSecond();
		long diff = retrieved - parked;

		if (diff % 3600 != 0) {
			diff += 3600;
		}

		return (int) diff / 3600;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public Space getSpace() {
		return space;
	}
}
