package com.example.parkinggarage.model.utils;

import java.io.Serializable;

/**
 * The IncrementalDataContainer class is used to store the counters for the userId and
 * the distance from the exit for the spaces.
 * It replaces static fields in the classes themselves as they are not saved when the garage is
 * saved.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public class IncrementalDataContainer implements Serializable {

	private int spaceDistanceCounter, userIdCounter;

	/**
	 * Returns the current state of the space distance counter. Will increment if specified.
	 *
	 * @param increment used to specify between incrementing the returning or just returning
	 * @return the state of the counter
	 */
	public int getSpaceDistanceCounter(boolean increment) {
		if (increment) {
			return ++spaceDistanceCounter;
		}

		return spaceDistanceCounter;
	}

	/**
	 * Returns the current state of the userId counter. Will increment if specified.
	 *
	 * @param increment used to specify between incrementing the returning or just returning
	 * @return the state of the counter
	 */
	public int getUserIdCounter(boolean increment) {
		if (increment) {
			return ++userIdCounter;
		}

		return userIdCounter;
	}
}
