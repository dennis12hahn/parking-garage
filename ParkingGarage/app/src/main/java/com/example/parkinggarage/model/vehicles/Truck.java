package com.example.parkinggarage.model.vehicles;

public class Truck extends Vehicle {

    private final static int SIZE = 3;

    public Truck(String license) {
        super(license, SIZE);
    }
}
