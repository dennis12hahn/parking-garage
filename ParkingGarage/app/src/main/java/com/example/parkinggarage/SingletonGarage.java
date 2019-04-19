package com.example.parkinggarage;

import com.example.parkinggarage.model.garage.Garage;

public abstract class SingletonGarage {

    private static Garage garage;

    public static Garage getGarage() {
        return garage;
    }

    public static void setGarage(Garage garage) {
        SingletonGarage.garage = garage;
    }

}
