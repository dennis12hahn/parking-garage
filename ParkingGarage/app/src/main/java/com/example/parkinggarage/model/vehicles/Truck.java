package com.example.parkinggarage.model.vehicles;

/**
 * A Truck is a {@link com.example.parkinggarage.model.vehicles.Vehicle} with a size of 3
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 04/19/2019
 */
public class Truck extends Vehicle {

	private final static int SIZE = 3;

	public Truck(String license) {
		super(license, SIZE);
	}
}
