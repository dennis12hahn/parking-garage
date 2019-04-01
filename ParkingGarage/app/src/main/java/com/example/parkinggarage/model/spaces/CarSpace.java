package com.example.parkinggarage.model.spaces;

public class CarSpace extends Space {

    private final static int SIZE = 2;
    private final static double EARLY_BIRD_PRICE = 20.0;
    private final static double RATE = 2.5;

    public CarSpace() {
        super(RATE, EARLY_BIRD_PRICE, SIZE);
    }
}
