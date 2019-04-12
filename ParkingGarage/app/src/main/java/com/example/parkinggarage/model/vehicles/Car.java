package com.example.parkinggarage.model.vehicles;

public class Car extends Vehicle {

    private final static int SIZE = 2;

    public Car(String license) {
        super(license, SIZE);
    }
}
