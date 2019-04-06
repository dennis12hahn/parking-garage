package com.example.parkinggarage.garage;

import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.spaces.CarSpace;
import com.example.parkinggarage.model.spaces.MotorcycleSpace;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.spaces.TruckSpace;
import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GarageTest {

    @Test
    public void testAddSpaces() {
        Garage garage = new Garage();
        assertTrue(garage.addSpace(new CarSpace()));
        assertTrue(garage.addSpace(new MotorcycleSpace()));
        assertTrue(garage.addSpace(new TruckSpace()));
    }

    @Test
    public void testSpacesOrder() {
        Space space1, space2, space3;
        space1 = new MotorcycleSpace();
        space2 = new TruckSpace();
        space3 = new CarSpace();

        space1.setOccupied(true);
        space2.setOccupied(true);

        Garage garage = new Garage();
        garage.addSpace(space1);
        garage.addSpace(space2);
        garage.addSpace(space3);

        assertEquals(garage.pollSpaces().getDistanceToExit(), 3);
        assertEquals(garage.pollSpaces().getDistanceToExit(), 1);
        assertEquals(garage.pollSpaces().getDistanceToExit(), 2);
    }

    @Test
    public void testGenerateSpaces() {
        Garage garage = new Garage();
        garage.generateSpaces(10, 20, 10);

        for (int i = 10; i > 0; i--) {
            assertTrue(garage.peekSpaces() instanceof MotorcycleSpace);
        }

        for (int i = 20; i > 0; i--) {
            assertTrue(garage.peekSpaces() instanceof CarSpace);
        }

        for (int i = 10; i > 0; i--) {
            assertTrue(garage.peekSpaces() instanceof TruckSpace);
        }
    }

    @Test
    public void testPark() {
        Garage garage = new Garage();
        Attendant attendant = new Attendant("first", "last", "password");
        Vehicle vehicle1 = new Car("123", "just a ", "car", 2);
        Vehicle vehicle2 = new Motorcycle("456", "small", "bike", 3);
        Vehicle vehicle3 = new Truck("789", "big", "truck", 3);

        garage.generateSpaces(10, 10, 10);


    }

}
