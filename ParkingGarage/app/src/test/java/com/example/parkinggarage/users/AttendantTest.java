package com.example.parkinggarage.users;

import com.example.parkinggarage.model.spaces.CarSpace;
import com.example.parkinggarage.model.spaces.MotorcycleSpace;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.spaces.TruckSpace;
import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.users.User;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttendantTest {

    private static Attendant attendant1, attendant2, attendant3, attendant4;
    private static Vehicle car1, motorcycle1, truck1, car2, car3;
    private static Space carSpace1, truckSpace, carSpace2, motorcycleSpace;

    @BeforeAll
    public static void beforeAll() {
        attendant1 = new Attendant("first", "last", "password");
        attendant2 = new Attendant("Dennis", "Hahn", "password1");
        attendant3 = new Attendant("abc", "longerthan4", "password5");
        attendant4 = new Attendant("wertyuoijp", "as", "pass");
        car1 = new Car("123", "make", "model", 2000);
        motorcycle1 = new Motorcycle("abc", "make", "model", 2000);
        truck1 = new Truck("456", "make", "model", 2000);
        car2 = new Car("def", "make", "model", 2000);
        car3 = new Car("def", "make", "model", 2000);
        carSpace1 = new CarSpace();
        truckSpace = new TruckSpace();
        carSpace2 = new CarSpace();
        motorcycleSpace = new MotorcycleSpace();
    }

    @Test
    public void testId() {
        assertEquals(attendant1.getId(), "00000001");
        assertEquals(attendant2.getId(), "00000002");
    }

    @Test
    public void testUsername() {
        assertEquals(attendant1.getUsername(), "lastf001");
        assertEquals(attendant2.getUsername(), "hahnd002");
        assertEquals(attendant3.getUsername(), "longa003");
        assertEquals(attendant4.getUsername(), "asw004");
    }

    @Test
    public void testVerifyPassword() {
        assertTrue(attendant1.verifyPassword("password"));
        assertTrue(attendant2.verifyPassword("password1"));
        assertFalse(attendant3.verifyPassword("password2"));
    }

    @Test
    public void testCreateAttendant() {
        assertNotNull(attendant1);
        assertTrue(attendant1 instanceof User);
    }

    @Test
    public void testAmountReceivable() {
        attendant1.charge(50);
        attendant1.pay(25);
        assertEquals(attendant1.getAmountReceivable(), 25.0);
    }

    @Test
    public void testPay() {
        attendant2.charge(100);
        assertEquals(attendant2.pay(1000), 900.0);
        assertEquals(attendant2.getAmountReceivable(), 0.0);
        assertEquals(attendant2.pay(-12), -1.0);
    }

    @Test
    public void testAddVehicle() {
        assertTrue(attendant1.addVehicle(car1));
        assertTrue(attendant2.addVehicle(car1));
    }

    @Test
    public void testAddDuplicate() {
        assertTrue(attendant1.addVehicle(car2));
        assertFalse(attendant1.addVehicle(car3));
    }

    @Test
    public void testPark() {
        assertFalse(car1.isParked());
        assertFalse(carSpace1.isOccupied());
        String[] tokens = attendant3.park(car1, carSpace1).getTicketInfo();
        assertNotNull(tokens);
        assertTrue(car1.isParked());
        assertTrue(carSpace1.isOccupied());
        assertNull(attendant3.park(car1, carSpace1));
        assertNull(attendant3.park(truck1, carSpace2));
        assertEquals(tokens[0], "abc longerthan4");
        assertEquals(tokens[1], "123");
        assertEquals(tokens[2], "CarSpace");
        assertTrue(tokens[3].contains(LocalDateTime.now().toLocalDate().toString()));
        assertEquals(tokens[4], LocalDateTime.now().getHour() < 8 ? "Early Bird" : "Hourly");
    }

    @Test
    public void testExit() throws InterruptedException {
        attendant1.park(car2, carSpace2);
        Thread.sleep(1000);
        String[] tokens = attendant1.exit(1).getReceiptInfo();
        System.out.println(Arrays.toString(tokens));
        assertNotNull(tokens);
        assertFalse(car2.isParked());
        assertFalse(carSpace2.isOccupied());
        assertEquals(tokens[0], "first last");
        assertEquals(tokens[1], "def");
        assertEquals(tokens[2], "CarSpace");
        assertTrue(tokens[3].contains(LocalDateTime.now().toLocalDate().toString()));
        assertTrue(tokens[4].contains(LocalDateTime.now().toLocalDate().toString()));
        assertEquals(tokens[5], LocalDateTime.now().getHour() < 8 ? "Early Bird" : "Hourly");
        assertEquals(tokens[6], "2.5");
        assertEquals(tokens[7], "1.0");
    }


}
