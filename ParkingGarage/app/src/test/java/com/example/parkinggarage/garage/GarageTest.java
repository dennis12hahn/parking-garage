package com.example.parkinggarage.garage;

import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.payment.Document;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

        assertEquals(garage.pollCarSpaces().getDistanceToExit(), 3);
        assertEquals(garage.pollMotorcycleSpaces().getDistanceToExit(), 1);
        assertEquals(garage.pollTruckSpaces().getDistanceToExit(), 2);
    }

    @Test
    void testGenerateSpaces() {
        Garage garage = new Garage();
        garage.generateSpaces(10, 20, 10);

        for (int i = 10; i > 0; i--) {
            assertTrue(garage.peekMotorcycleSpace() instanceof MotorcycleSpace);
        }

        for (int i = 20; i > 0; i--) {
            assertTrue(garage.peekCarSpaces() instanceof CarSpace);
        }

        for (int i = 10; i > 0; i--) {
            assertTrue(garage.peekTruckSpace() instanceof TruckSpace);
        }
    }

    @Test
    public void testParkAndExit() {
        Garage garage = new Garage();
        Attendant attendant1 = new Attendant("first", "last", "password");
        Attendant attendant2 = new Attendant("attendant", "2", "password");
        Attendant attendant3 = new Attendant("attend", "3", "pass");
        Attendant attendant4 = new Attendant("attendant", "4", "password");
        Vehicle vehicle1 = new Car("123", "just a ", "car", 2);
        Vehicle vehicle2 = new Motorcycle("456", "small", "bike", 3);
        Vehicle vehicle3 = new Car("123456", "no more", "spaces", 6);
        Vehicle vehicle4 = new Truck("789", "big", "truck", 3);
        garage.generateSpaces(2, 1, 2);

        Space space = garage.getClosestSpace(vehicle1);
        Document doc1 = attendant1.park(vehicle1, space);
        assertNotNull(doc1);
        garage.addSpace(space);

        space = garage.getClosestSpace(vehicle1);
        Document doc2 = attendant1.park(vehicle1, space);
        assertNull(doc2);
        garage.addSpace(space);

        space = garage.getClosestSpace(vehicle2);
        Document doc3 = attendant2.park(vehicle2, space);
        assertNotNull(doc3);
        assertTrue(space instanceof MotorcycleSpace);
        garage.addSpace(space);

        space = garage.getClosestSpace(vehicle3);
        Document doc4 = attendant3.park(vehicle3, space);
        assertNotNull(doc4);
        assertTrue(space instanceof TruckSpace);
        garage.addSpace(space);

        space = garage.getClosestSpace(vehicle4);
        Document doc5 = attendant4.park(vehicle4, space);
        assertNotNull(doc5);
        garage.addSpace(space);

        garage.displayAllSpaces();
    }

}
