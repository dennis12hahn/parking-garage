package com.example.parkinggarage.controller;

import com.example.parkinggarage.model.garage.Garage;

public abstract class DataHolder {

    private static Garage garage;

    public static Garage getGarage() {
        return garage;
    }

    public static void setGarage(Garage garage) {
        DataHolder.garage = garage;
    }
}
