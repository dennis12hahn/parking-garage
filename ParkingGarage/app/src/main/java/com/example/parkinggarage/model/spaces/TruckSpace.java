package com.example.parkinggarage.model.spaces;

/**
 * A TruckSpace is a subclass of a Space with a size of 1 and prices designated by the
 * manager.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public class TruckSpace extends Space {

	private final static int SIZE = 3;
	private final static double DEFAULT_EARLY_BIRD_PRICE = 40.0;
	private final static double DEFAULT_RATE = 5.0;

	public TruckSpace(double rate, double earlyBird) {
		super(rate, earlyBird, SIZE);
	}
}
