package com.example.parkinggarage.model.garage;

public abstract class SingletonGarage {

	private static Garage garage;

	public static Garage getGarage() {
		return garage;
	}

	public static void setGarage(Garage garage) {
		SingletonGarage.garage = garage;
	}

}
