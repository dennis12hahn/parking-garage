package com.example.parkinggarage.spaces;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpacesTest {

    static Space space1, space2, space3, space4, space5;
    static Vehicle vehicle1, vehicle2, vehicle3;

    @BeforeAll
    static void beforeAll() {
        space1 = new CarSpace(1, 1);
        space2 = new TruckSpace(1, 1);
        space3 = new CarSpace(1, 1);
        space4 = new MotorcycleSpace(1, 1);
        space5 = new MotorcycleSpace(1, 1);
        vehicle1 = new Car("123");
        vehicle2 = new Motorcycle("abc");
        vehicle3 = new Truck("truck");
    }

    @Test
    void testDistance() {
        assertEquals(space1.getDistanceToExit(), 1);
        assertEquals(space2.getDistanceToExit(), 2);
        assertEquals(space3.getDistanceToExit(), 3);
        assertEquals(space4.getDistanceToExit(), 4);
        assertEquals(space5.getDistanceToExit(), 5);
    }


}
