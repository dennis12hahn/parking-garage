package com.example.parkinggarage.model.tickets_and_receipts;

import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

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

	private double calculateCharge() {
		if (earlyBird) {
			return space.getEarlyBirdPrice();
		}

		return getDurationParked() * space.getRate();
	}

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
