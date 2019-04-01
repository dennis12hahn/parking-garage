package com.example.parkinggarage.model.vehicles;

public class Truck extends Vehicle {

    private final static int SIZE = 3;

    public Truck(String license, String make, String model, int year) {
        super(license, make, model, year, SIZE);
    }
}
