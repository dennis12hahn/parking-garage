package com.example.parkinggarage.controller;

import com.example.parkinggarage.model.garage.Garage;

public abstract class GarageController {

    private static Garage garage;

    public static Garage getGarage() {
        return garage;
    }

    public static void setGarage(Garage garage) {
        GarageController.garage = garage;
    }

}
