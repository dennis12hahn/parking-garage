package com.example.parkinggarage.users;

import com.example.parkinggarage.model.spaces.CarSpace;
import com.example.parkinggarage.model.spaces.MotorcycleSpace;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.spaces.TruckSpace;
import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AttendantTest {

    static Attendant attendant1, attendant2, attendant3, attendant4;
    static Vehicle car1, motorcycle1, truck1, car2, car3;
    static Space carSpace1, truckSpace, carSpace2, motorcycleSpace;

    @BeforeAll
    static void beforeAll() {
        attendant1 = new Attendant("first", "last", "password");
        attendant2 = new Attendant("Dennis", "Hahn", "password1");
        attendant3 = new Attendant("abc", "longerthan4", "password5");
        attendant4 = new Attendant("wertyuoijp", "as", "pass");
        car1 = new Car("123");
        motorcycle1 = new Motorcycle("abc");
        truck1 = new Truck("456");
        car2 = new Car("def");
        car3 = new Car("def");
        carSpace1 = new CarSpace(1, 2);
        truckSpace = new TruckSpace(1, 1);
        carSpace2 = new CarSpace(1, 1);
        motorcycleSpace = new MotorcycleSpace(1, 1);
    }

    @Test
    void testId() {
        assertEquals(attendant1.getId(), "00000001");
        assertEquals(attendant2.getId(), "00000002");
    }

    @Test
    void testUsername() {
        assertEquals(attendant1.getUsername(), "lastf001");
        assertEquals(attendant2.getUsername(), "hahnd002");
        assertEquals(attendant3.getUsername(), "longa003");
        assertEquals(attendant4.getUsername(), "asw004");
    }

    @Test
    void testVerifyPassword() {
        assertTrue(attendant1.verifyPassword("password"));
        assertTrue(attendant2.verifyPassword("password1"));
        assertFalse(attendant3.verifyPassword("password2"));
    }

    @Test
    void testCreateAttendant() {
        assertNotNull(attendant1);
    }


}
