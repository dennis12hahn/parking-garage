package com.example.parkinggarage.vehicles;

import com.example.parkinggarage.model.spaces.CarSpace;
import com.example.parkinggarage.model.spaces.MotorcycleSpace;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.spaces.TruckSpace;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VehicleTest {

    private static Space carSpace, truckSpace, motorcycleSpace;
    private static Vehicle car, motorcycle, truck;

    @BeforeAll
    public static void beforeAll() {
        carSpace = new CarSpace();
        truckSpace = new TruckSpace();
        motorcycleSpace = new MotorcycleSpace();
        car = new Car("123", "ford", "edge", 3000);
        motorcycle = new Motorcycle("abc", "asdf", "uiop", 9);
        truck = new Truck("truck", "big", "truck", 95);
    }


    @Test
    public void testSizeRequirements() {
        assertFalse(car.isParkable(motorcycleSpace));
        assertTrue(car.isParkable(carSpace));
        assertTrue(car.isParkable(truckSpace));
        assertTrue(motorcycle.isParkable(motorcycleSpace));
        assertTrue(motorcycle.isParkable(carSpace));
        assertTrue(motorcycle.isParkable(truckSpace));
        assertFalse(truck.isParkable(motorcycleSpace));
        assertFalse(truck.isParkable(carSpace));
        assertTrue(truck.isParkable(truckSpace));
    }
}
