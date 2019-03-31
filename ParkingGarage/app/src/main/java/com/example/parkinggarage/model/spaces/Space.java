package com.example.parkinggarage.model.spaces;

public abstract class Space {

    private double distanceToExit, rate, earlyBirdPrice;
    private boolean occupied;
    private int size;

    public Space(double distanceToExit, double rate, double earlyBirdPrice, boolean occupied, int size) {
        this.distanceToExit = distanceToExit;
        this.rate = rate;
        this.earlyBirdPrice = earlyBirdPrice;
        this.occupied = occupied;
        this.size = size;
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
