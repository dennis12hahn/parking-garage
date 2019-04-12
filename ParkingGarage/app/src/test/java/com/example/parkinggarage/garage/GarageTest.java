package com.example.parkinggarage.garage;

import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.spaces.CarSpace;
import com.example.parkinggarage.model.spaces.MotorcycleSpace;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.spaces.TruckSpace;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.users.Manager;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GarageTest {

    @Test
    void testAddSpaces() {
        Garage garage = new Garage("first", "last", "pass", 1, 2, 3);
        assertTrue(garage.addSpace(new CarSpace()));
        assertTrue(garage.addSpace(new MotorcycleSpace()));
        assertTrue(garage.addSpace(new TruckSpace()));
    }

    @Test
    void testSpacesOrder() {
        Space space1, space2, space3;
        space1 = new MotorcycleSpace();
        space2 = new TruckSpace();
        space3 = new CarSpace();

        space1.setOccupied(true);
        space2.setOccupied(true);

        Garage garage = new Garage("first", "last", "pass", 0, 0, 0);
        garage.addSpace(space1);
        garage.addSpace(space2);
        garage.addSpace(space3);

        assertEquals(garage.pollCarSpaces().getDistanceToExit(), 3);
        assertEquals(garage.pollMotorcycleSpaces().getDistanceToExit(), 1);
        assertEquals(garage.pollTruckSpaces().getDistanceToExit(), 2);
    }

    @Test
    void testParkAndExit() {
        Garage garage = new Garage("the", "manager", "password", 3, 3, 3);
        Manager manager = garage.getManager();
        Attendant attendant = manager.createAttendant("the", "attendant", "password");
        garage.getUserBag().addAttendant(attendant);

        Vehicle motorcycle = new Motorcycle("123");
        Vehicle car = new Car("abc");
        Vehicle truck = new Truck("456");

        Space space1 = garage.getClosestSpace(motorcycle);
        Space space2 = garage.getClosestSpace(car);
        Space space3 = garage.getClosestSpace(truck);
        attendant.park(motorcycle, space1);
        attendant.park(car, space2);
        attendant.park(truck, space3);

        Document receipt1 = attendant.exit("123", 10);
        Document receipt2 = attendant.exit("abc", 20);
        Document receipt3 = attendant.exit("456", 10);

        assertNotNull(receipt1);
        assertNotNull(receipt2);
        assertNotNull(receipt3);
    }

}
