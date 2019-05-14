package com.example.parkinggarage.model.vehicles;

import java.io.Serializable;

/**
 * The Vehicle class contains data relevant to each vehicle in the garage.
 * A Vehicle will contain a license plate number and a size used in tandem with the size of a
 * {@link com.example.parkinggarage.model.spaces.Space}
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public abstract class Vehicle implements Serializable {

	private String license;
	private int size;
	private boolean parked;

	public Vehicle(String license, int size) {
		this.license = license;
		this.size = size;
	}

	public boolean isParked() {
		return parked;
	}

	public void setParked(boolean parked) {
		this.parked = parked;
	}

	public String getLicense() {
		return license;
	}

	public int getSize() {
		return size;
	}

}
