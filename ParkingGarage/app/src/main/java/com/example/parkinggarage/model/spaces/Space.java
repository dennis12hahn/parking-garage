package com.example.parkinggarage.model.spaces;

public abstract class Space {

    private static int distanceCounter = 0;
    private double distanceToExit, rate, earlyBirdPrice;
    private boolean occupied;
    private int size;

    public Space(double rate, double earlyBirdPrice, int size) {
        this.distanceToExit = ++distanceCounter;
        this.rate = rate;
        this.earlyBirdPrice = earlyBirdPrice;
        this.occupied = false;
        this.size = size;
    }

    public double getDistanceToExit() {
        return distanceToExit;
    }

    public double getRate() {
        return rate;
    }

    public double getEarlyBirdPrice() {
        return earlyBirdPrice;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
