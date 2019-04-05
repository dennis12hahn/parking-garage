package com.example.parkinggarage.garage;

import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.spaces.CarSpace;
import com.example.parkinggarage.model.spaces.MotorcycleSpace;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.spaces.TruckSpace;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpacePriorityQueueTest {

    @Test
    public void testAdd() {
        Garage garage = new Garage();
        assertTrue(garage.addSpace(new CarSpace()));
        assertTrue(garage.addSpace(new MotorcycleSpace()));
        assertTrue(garage.addSpace(new TruckSpace()));
    }

    @Test
    public void testOrder() {
        Garage garage = new Garage();
        garage.addSpace(new CarSpace());
        garage.addSpace(new MotorcycleSpace());
        garage.addSpace(new TruckSpace());

    }

}
