package com.example.parkinggarage.model.spaces;

public class CarSpace extends Space {

	private final static int SIZE = 2;
	private final static double DEFAULT_EARLY_BIRD_PRICE = 20.0;
	private final static double DEFAULT_RATE = 2.5;

	public CarSpace(double rate, double earlyBird) {
		super(rate, earlyBird, SIZE);
	}
}
