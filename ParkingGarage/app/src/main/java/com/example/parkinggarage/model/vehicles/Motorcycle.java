package com.example.parkinggarage.model.vehicles;

/**
 * A Motorcycle is a {@link com.example.parkinggarage.model.vehicles.Vehicle} with a size of 1
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 04/19/2019
 */
public class Motorcycle extends Vehicle {

	private final static int SIZE = 1;

	public Motorcycle(String license) {
		super(license, SIZE);
	}
}
