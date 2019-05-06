package com.example.parkinggarage.model.utils;

import java.io.Serializable;

public class IncrementalDataContainer implements Serializable {

	private int spaceDistanceCounter, userIdCounter;

	public int getSpaceDistanceCounter(boolean increment) {
		if (increment) {
			return ++spaceDistanceCounter;
		}

		return spaceDistanceCounter;
	}

	public int getUserIdCounter(boolean increment) {
		if (increment) {
			return ++userIdCounter;
		}

		return userIdCounter;
	}
}
