package com.example.parkinggarage.model.garage;

import com.example.parkinggarage.model.spaces.Space;

import java.util.PriorityQueue;

public class Garage {

    private PriorityQueue<Space> spaces;

    public Garage() {
        this.spaces = new PriorityQueue<>();
    }

    public boolean addSpace(Space space) {
        return spaces.add(space);
    }

    public Space pollSpaces() {
        return spaces.poll();
    }

    public PriorityQueue<Space> getSpaces() {
        return spaces;
    }
}
