package com.example.parkinggarage.model.vehicles;

import com.example.parkinggarage.model.spaces.Space;

interface Parkable {

    default boolean isParkable(Space space, Vehicle vehicle) {
        return vehicle.getSize() <= space.getSize() && !space.isOccupied();
    }
}
