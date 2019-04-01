package com.example.parkinggarage.model.vehicles;

import java.util.HashMap;
import java.util.Map;

public class VehicleBag {

    private Map<String, Vehicle> vehicles;

    public VehicleBag() {
        this.vehicles = new HashMap<>();
    }

    public boolean addMotorcycle(String license, String make, String model, int year) {
        Vehicle motorcycle = new Motorcycle(license, make, model, year);

        if (vehicles.containsKey(license)) {
            return false;
        }

        vehicles.put(license, motorcycle);
        return true;
    }

    public boolean addCar(String license, String make, String model, int year) {
        Vehicle motorcycle = new Car(license, make, model, year);

        if (vehicles.containsKey(license)) {
            return false;
        }

        vehicles.put(license, motorcycle);
        return true;
    }

    public boolean addTruck(String license, String make, String model, int year) {
        Vehicle motorcycle = new Truck(license, make, model, year);

        if (vehicles.containsKey(license)) {
            return false;
        }

        vehicles.put(license, motorcycle);
        return true;
    }


    public boolean addVehicle(Vehicle vehicle) {
        if (vehicles.containsKey(vehicle.getLicense())) {
            return false;
        }

        vehicles.put(vehicle.getLicense(), vehicle);
        return true;
    }
}
