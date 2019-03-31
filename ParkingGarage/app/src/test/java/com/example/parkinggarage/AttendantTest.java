package com.example.parkinggarage;

import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.users.User;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttendantTest {

    private static Attendant attendant1, attendant2, attendant3, attendant4;

    @BeforeAll
    public static void beforeAll() {
        attendant1 = new Attendant("first", "last", "password");
        attendant2 = new Attendant("Dennis", "Hahn", "password1");
        attendant3 = new Attendant("abc", "longerthan4", "password5");
        attendant4 = new Attendant("wertyuoijp", "as", "pass");
    }

    @Test
    public void testCreateUser() {
        assertNotNull(attendant1);
        assertNotNull(attendant2);
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


}
