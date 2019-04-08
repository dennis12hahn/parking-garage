package com.example.parkinggarage.model.spaces;

public abstract class Space implements Comparable<Space> {

    private static int distanceCounter = 0;
    private double rate, earlyBirdPrice;
    private boolean occupied;
    private int distanceToExit, size;

    public Space(double rate, double earlyBirdPrice, int size) {
        this.distanceToExit = ++distanceCounter;
        this.rate = rate;
        this.earlyBirdPrice = earlyBirdPrice;
        this.occupied = false;
        this.size = size;
    }

    @Override
    public int compareTo(Space space) {
        if (this.isOccupied() && !space.isOccupied()) {
            return 1;
        } else if (!this.isOccupied() && space.isOccupied()) {
            return -1;
        } else {
            return Integer.compare(this.getDistanceToExit(), space.getDistanceToExit());
        }
    }

    public int getDistanceToExit() {
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

    @Override
    public String toString() {
        return "Space{" +
                "distanceToExit=" + distanceToExit +
                ", rate=" + rate +
                ", earlyBirdPrice=" + earlyBirdPrice +
                ", occupied=" + occupied +
                ", size=" + size +
                ", class=" + getClass() +
                '}';
    }
}
