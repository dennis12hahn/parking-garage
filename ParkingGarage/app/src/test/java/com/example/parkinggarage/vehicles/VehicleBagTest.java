package com.example.parkinggarage.vehicles;

import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;
import com.example.parkinggarage.model.vehicles.VehicleBag;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VehicleBagTest {

    private static Vehicle vehicle1, vehicle2, vehicle3, vehicle4, vehicle5, vehicle6,
            vehicle7, vehicle8, vehicle9;
    private static VehicleBag bag;

    @BeforeAll
    public static void beforeAll() {
        bag = new VehicleBag();
        vehicle1 = new Motorcycle("abc", "brand", "bike", 2000);
        vehicle2 = new Car("def", "brand", "bike", 2000);
        vehicle3 = new Truck("ghi", "brand", "bike", 2000);
        vehicle4 = new Motorcycle("jkl", "brand", "bike", 2000);
        vehicle5 = new Car("mno", "brand", "bike", 2000);
        vehicle6 = new Truck("pqr", "brand", "bike", 2000);
        vehicle7 = new Motorcycle("ghi", "brand", "bike", 2000);
        vehicle8 = new Car("abc", "brand", "bike", 2000);
        vehicle9 = new Truck("def", "brand", "bike", 2000);
    }

    @Test
    public void testAddMotorcycle() {
        assertTrue(bag.addMotorcycle("license", null, null, 4));
        assertFalse(bag.addMotorcycle("license", "dup", "licate", 7));
    }

    @Test
    public void testAddVehicle() {
        assertTrue(bag.addVehicle(vehicle1));
        assertTrue(bag.addVehicle(vehicle2));
        assertTrue(bag.addVehicle(vehicle3));
        assertTrue(bag.addVehicle(vehicle4));
        assertTrue(bag.addVehicle(vehicle5));
        assertTrue(bag.addVehicle(vehicle6));
        assertFalse(bag.addVehicle(vehicle7));
        assertFalse(bag.addVehicle(vehicle8));
        assertFalse(bag.addVehicle(vehicle9));
    }
}
