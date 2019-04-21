package com.example.parkinggarage.model.spaces;

public class TruckSpace extends Space {

	private final static int SIZE = 3;
	private final static double DEFAULT_EARLY_BIRD_PRICE = 40.0;
	private final static double DEFAULT_RATE = 5.0;

	public TruckSpace(double rate, double earlyBird) {
		super(rate, earlyBird, SIZE);
	}
}
