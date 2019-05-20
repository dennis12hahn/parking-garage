package com.example.parkinggarage.garage;

import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.users.Manager;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class GarageTest {

	@Test
	void testTicketsAndReceipts() {
		Garage garage = new Garage("first", "last", "password");
		garage.getSpaceBag().generateSpaces(10, 10, 10);
		Attendant attendant = new Attendant("first", "last", "password");
		garage.getUserBag().addAttendant(attendant);

		Manager manager = garage.getManager();
		Vehicle vehicle = new Motorcycle("abc");
		Vehicle vehicle2 = new Car("123");

		Document ticket = manager.park(vehicle, garage);
		Document ticket2 = attendant.park(vehicle, garage);

		assertNotNull(ticket);
		assertNull(ticket2);

		Document ticket3 = attendant.park(vehicle2, garage);
		assertNotNull(ticket3);

		assertNotNull(attendant.retrieve("abc", garage, 20));
		assertNotNull(manager.retrieve("123", garage, 20));
	}

}
