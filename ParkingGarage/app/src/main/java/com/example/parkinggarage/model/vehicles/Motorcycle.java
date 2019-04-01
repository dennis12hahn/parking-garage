package com.example.parkinggarage.model.vehicles;

public class Motorcycle extends Vehicle {

    private final static int SIZE = 1;

    public Motorcycle(String license, String make, String model, int year) {
        super(license, make, model, year, SIZE);
    }
}
