package com.example.parkinggarage.model.spaces;

public class TruckSpace extends Space {

    private final static int SIZE = 3;
    private final static double EARLY_BIRD_PRICE = 40.0;
    private final static double RATE = 5.0;

    public TruckSpace() {
        super(RATE, EARLY_BIRD_PRICE, SIZE);
    }
}
