package com.example.parkinggarage.model.vehicles;

/**
 * A Car is a {@link com.example.parkinggarage.model.vehicles.Vehicle} with a size of 2
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public class Car extends Vehicle {

	private final static int SIZE = 2;

	public Car(String license) {
		super(license, SIZE);
	}
}
