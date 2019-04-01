package com.example.parkinggarage.users;

import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.users.Manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
