package com.example.parkinggarage.model.payment;

import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.time.LocalDateTime;
import java.time.ZoneId;


public class Document {

    private Attendant attendant;
    private Vehicle vehicle;
    private Space space;
    private LocalDateTime timeParked, timeRetrieved;
    private final static int EARLY_BIRD_HOUR = 8;
    private boolean earlyBird;
    private double paid;

    public Document(Attendant attendant, Vehicle vehicle, Space space) {
        this.attendant = attendant;
        this.vehicle = vehicle;
        this.space = space;
        this.timeParked = LocalDateTime.now();
        this.earlyBird = timeParked.getHour() < EARLY_BIRD_HOUR;
    }

    public String[] getTicketInfo() {
        return new String[]{
                attendant.getFirstName() + " " + attendant.getLastName(),
                vehicle.getLicense(),
                space.getClass().getSimpleName(),
                timeParked.toLocalDate() + " " + timeParked.toLocalTime(),
                earlyBird ? "Early Bird" : "Hourly"
        };
    }

    public String[] getReceiptInfo() {
        return new String[]{
                attendant.getFirstName() + " " + attendant.getLastName(),
                vehicle.getLicense(),
                space.getClass().getSimpleName(),
                timeParked.toLocalDate() + " " + timeParked.toLocalTime(),
                timeRetrieved.toLocalDate() + " " + timeRetrieved.toLocalTime(),
                earlyBird ? "Early Bird" : "Hourly",
                String.valueOf(calculateCharge()),
                String.valueOf(paid)
        };
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public void setTimeRetrieved(LocalDateTime timeRetrieved) {
        this.timeRetrieved = timeRetrieved;
    }

    public double calculateCharge() {
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
