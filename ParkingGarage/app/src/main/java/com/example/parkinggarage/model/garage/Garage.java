package com.example.parkinggarage.model.garage;

import com.example.parkinggarage.model.spaces.CarSpace;
import com.example.parkinggarage.model.spaces.MotorcycleSpace;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.spaces.TruckSpace;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.util.Iterator;
import java.util.PriorityQueue;

public class Garage {

    private PriorityQueue<Space> spaces;

    public Garage() {
        this.spaces = new PriorityQueue<>();
    }

    public boolean addSpace(Space space) {
        return spaces.add(space);
    }

    public Space peekSpaces() {
        return spaces.peek();
    }

    private Space getClosestSmallestSpace(Vehicle vehicle) {
        Iterator<Space> iterator = spaces.iterator();
        Space space = iterator.next();

        while (iterator.hasNext()) {
            if (space.isOccupied()) {
                return space;
            }

            space = iterator.next();

            if (vehicle instanceof Motorcycle && space instanceof MotorcycleSpace) {
                return space;
            }

            if (vehicle instanceof Car && space instanceof CarSpace) {
                return space;
            }

            if (vehicle instanceof Truck && space instanceof TruckSpace) {
                return space;
            }
        }

        return space;
    }

    private Space getClosestLargerSpace(Vehicle vehicle) {
        Iterator<Space> iterator = spaces.iterator();
        Space space = iterator.next();

        while (iterator.hasNext()) {
            if (space.isOccupied()) {
                return space;
            }

            space = iterator.next();

            if (vehicle instanceof Motorcycle && space instanceof CarSpace) {
                return space;
            }

            if (vehicle instanceof Car && space instanceof TruckSpace) {
                return space;
            }
        }

        return space;
    }

    private Space getClosestLargestSpace(Vehicle vehicle) {
        Iterator<Space> iterator = spaces.iterator();
        Space space = iterator.next();

        while (iterator.hasNext()) {
            if (space.isOccupied()) {
                return space;
            }

            space = iterator.next();

            if (vehicle instanceof Motorcycle && space instanceof TruckSpace) {
                return space;
            }
        }

        return space;
    }

    public Space getClosestSpace(Vehicle vehicle) {

        Space space = getClosestSmallestSpace(vehicle);

        if (space.isOccupied() && !(vehicle instanceof Truck)) {
            space = getClosestLargerSpace(vehicle);
        }

        if (space.isOccupied() && !(vehicle instanceof Car)) {
            space = getClosestLargestSpace(vehicle);
        }

        return space;

    }

    public PriorityQueue<Space> getSpaces() {
        return spaces;
    }

    public void generateSpaces(int totalMotorcycleSpaces, int totalCarSpaces, int totalTruckSpaces) {
        for (int i = totalMotorcycleSpaces; i > 0; i--) {
            addSpace(new MotorcycleSpace());
        }

        for (int i = totalCarSpaces; i > 0; i--) {
            addSpace(new CarSpace());
        }

        for (int i = totalTruckSpaces; i > 0; i--) {
            addSpace(new TruckSpace());
        }
    }

    public Space pollSpaces() {
        return spaces.poll();
    }
}
