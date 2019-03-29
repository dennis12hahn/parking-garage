package com.example.parkinggarage;

import com.example.parkinggarage.model.Manager;
import com.example.parkinggarage.model.UserBag;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserBagTest {

    private static UserBag userbag;

    @BeforeEach
    public void beforeEach() {
        userbag = new UserBag();
    }

    @Test
    public void testAddManager() {
        assertTrue(userbag.addManager("new", "manager", "pass"));
        for (int i = 0; i < 999; i++) {
            userbag.addManager("1000", "managers", "password");

        }
        assertFalse(userbag.addManager("new", "manager", "pass"));
    }

    @Test
    public void testAddAttendant() {
        assertTrue(userbag.addAttendant("new", "attendant", "password"));
        for (int i = 0; i < 999; i++) {
            userbag.addAttendant("1000", "attendants", "password");

        }
        assertFalse(userbag.addAttendant("new", "attendant", "pass"));
    }

    @Test
    public void testVerifyUsername() {
        userbag.addAttendant("dennis", "hahn", "password");
        assertTrue(userbag.verifyUsername("hahnd001"));
    }

    @Test
    public void testVerifyPassword() {
        userbag.addAttendant("anne", "smith", "password");
        assertTrue(userbag.verifyPassword("smita001", "password"));
    }
}
