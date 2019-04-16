package com.example.parkinggarage.model.vehicles;

import java.io.Serializable;

public abstract class Vehicle implements Serializable {

    private String license;
    private int size;

    public Vehicle(String license, int size) {
        this.license = license;
        this.size = size;
    }

    public String getLicense() {
        return license;
    }

    public int getSize() {
        return size;
    }

}
