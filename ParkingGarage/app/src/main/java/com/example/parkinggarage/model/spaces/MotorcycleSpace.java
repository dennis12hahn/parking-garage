package com.example.parkinggarage.model.spaces;

public class MotorcycleSpace extends Space {

    private final static int SIZE = 1;
    private final static double EARLY_BIRD_PRICE = 10.0;
    private final static double RATE = 1.0;

    public MotorcycleSpace() {
        super(RATE, EARLY_BIRD_PRICE, SIZE);
    }
}