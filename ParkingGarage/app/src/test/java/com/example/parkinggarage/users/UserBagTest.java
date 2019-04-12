package com.example.parkinggarage.users;

import com.example.parkinggarage.model.users.UserBag;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserBagTest {

    private static UserBag userbag;

    @BeforeEach
    void beforeEach() {
        userbag = new UserBag();
    }

    @Test
    void testAddManager() {
        assertTrue(userbag.addManager("new", "manager", "pass"));
        for (int i = 0; i < 999; i++) {
            userbag.addManager("1000", "managers", "password");

        }
        assertFalse(userbag.addManager("new", "manager", "pass"));
    }

    @Test
    void testAddAttendant() {
        assertTrue(userbag.addAttendant("new", "attendant", "password"));
        for (int i = 0; i < 999; i++) {
            userbag.addAttendant("1000", "attendants", "password");

        }
        assertFalse(userbag.addAttendant("new", "attendant", "pass"));
    }

    @Test
    void testVerifyUsername() {
        userbag.addAttendant("dennis", "hahn", "password");
        assertTrue(userbag.verifyUsername("hahnd001"));
    }

    @Test
    void testVerifyPassword() {
        userbag.addAttendant("anne", "smith", "password");
        assertTrue(userbag.verifyPassword("smita001", "password"));
    }
}
