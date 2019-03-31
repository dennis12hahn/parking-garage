package com.example.parkinggarage.model.vehicles;

/**
 * The Vehicle class consists of Strings representing the license, make, and model of a vehicle object.
 * It also consists of the year of the vehicle and the size of the vehicle. The size will be an integer
 * of sizes 1, 2, or 3 signifying a motorcycle, car, or truck respectively. A vehicle will also contain
 * a boolean value to represent if it is parked in a space or not.
 *
 * @author Dennis Hahn
 * @version 1, 03/31/2019
 */

public abstract class Vehicle implements Parkable {

    private String license, make, model;
    private int year, size;
    private boolean parked;

    public Vehicle(String license, String make, String model, int year, int size, boolean parked) {
        this.license = license;
        this.make = make;
        this.model = model;
        this.year = year;
        this.size = size;
        this.parked = parked;
    }

    public String getLicense() {
        return license;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isParked() {
        return parked;
    }

    public void setParked(boolean parked) {
        this.parked = parked;
    }
}
