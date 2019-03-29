package com.example.parkinggarage;

import com.example.parkinggarage.model.Attendant;
import com.example.parkinggarage.model.Manager;
import com.example.parkinggarage.model.User;
import com.example.parkinggarage.model.UserBag;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManagerTest {

    private static Manager manager;

    @BeforeAll
    public static void beforeAll() {
        manager = new Manager("first", "last", "password");
    }

    @Test
    public void testCreateAttendant() {
        Attendant attendant = manager.createAttendant("new", "attendant", "password1");
        assertNotNull(attendant);
        assertEquals(attendant.getUsername(), "atten002");
    }

}
