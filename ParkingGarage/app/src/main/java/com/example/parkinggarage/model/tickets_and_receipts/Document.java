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

	public void setTimeRetrieved() {
		this.timeRetrieved = LocalDateTime.now();
	}

	private double calculateCharge() {
		if (earlyBird) {
			return space.getEarlyBirdPrice();
		}

		return getDurationParked() * space.getRate();
	}

	public ArrayList<String> getTicketInfo() {
		String spaceType = space.getClass().getSimpleName();
		spaceType = spaceType.substring(0, spaceType.indexOf("Space"));

		ArrayList<String> ticketInfo = new ArrayList<>();

		ticketInfo.add("License: " + vehicle.getLicense());
		ticketInfo.add("Type of Space: " + spaceType);
		ticketInfo.add("Date Parked: " + timeParked.toLocalDate());
		ticketInfo.add("Time Parked: " + formatTime(timeParked));
		ticketInfo.add("Payment Scheme: " + (earlyBird ? "Early Bird" : "Hourly"));
		return ticketInfo;
	}

	public ArrayList<String> getReceiptInfo() {
		ArrayList<String> receiptInfo = getTicketInfo();
		receiptInfo.add(4, "Date Retrieved: " + timeRetrieved.toLocalDate());
		receiptInfo.add("Time Retrieved: " + formatTime(timeRetrieved));
		receiptInfo.add("Price: " + calculateCharge());
		receiptInfo.add("Amount Paid: " + paid);
		return receiptInfo;
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

	public void setPaid(double paid) {
		this.paid = paid;
	}
}
