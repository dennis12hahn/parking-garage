package com.example.parkinggarage.model.spaces;

public class MotorcycleSpace extends Space {

	private final static int SIZE = 1;
	private final static double DEFAULT_EARLY_BIRD_PRICE = 10.0;
	private final static double DEFAULT_RATE = 1.0;

	public MotorcycleSpace(double rate, double earlyBird) {
		super(rate, earlyBird, SIZE);
	}
}
