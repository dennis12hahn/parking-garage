package com.example.parkinggarage.spaces;

import com.example.parkinggarage.model.spaces.CarSpace;
import com.example.parkinggarage.model.spaces.SpaceBag;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpaceBagTest {

	private static Vehicle vehicle1, vehicle2, vehicle3;

	@BeforeAll
	static void beforeAll() {
		vehicle1 = new Car("123");
		vehicle2 = new Motorcycle("abc");
		vehicle3 = new Truck("truck");
	}

	@Test
	void testGetClosest() {
		SpaceBag spaceBag = new SpaceBag();
		spaceBag.generateSpaces(20, 20, 20);
		assertEquals(spaceBag.getClosestSpace(vehicle2, "peek").getDistanceToExit(), 1);
	}

	@Test
	void testAdd() {
		SpaceBag spaceBag = new SpaceBag();
		spaceBag.generateSpaces(20, 20, 20);
		assertTrue(spaceBag.addSpace(new CarSpace(12, 15)));
		assertTrue(spaceBag.addSpace(new CarSpace(15, 123)));
		assertNotNull(spaceBag.getClosestSpace(vehicle1, "peek"));
	}

	@Test
	void testRemove() {
		SpaceBag spaceBag = new SpaceBag();
		spaceBag.generateSpaces(20, 20, 20);
		spaceBag.removeSpace(spaceBag.getClosestSpace(vehicle1, "peek"));
		assertEquals(spaceBag.getCarSpaces().size(), 19);
	}
}
