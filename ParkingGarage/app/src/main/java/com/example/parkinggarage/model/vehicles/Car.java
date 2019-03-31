package com.example.parkinggarage.model.vehicles;

public class Car extends Vehicle {

    private final static int SIZE = 2;

    public Car(String license, String make, String model, int year, boolean parked) {
        super(license, make, model, year, SIZE, parked);
    }
}
