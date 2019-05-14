package com.example.parkinggarage.model.spaces;

/**
 * A CarSpace is a subclass of a Space with a size of 1 and prices designated by the
 * manager.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public class CarSpace extends Space {

	private final static int SIZE = 2;
	private final static double DEFAULT_EARLY_BIRD_PRICE = 20.0;
	private final static double DEFAULT_RATE = 2.5;

	public CarSpace(double rate, double earlyBird) {
		super(rate, earlyBird, SIZE);
	}
}
