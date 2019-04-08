package com.example.parkinggarage.model.garage;

import com.example.parkinggarage.model.spaces.CarSpace;
import com.example.parkinggarage.model.spaces.MotorcycleSpace;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.spaces.TruckSpace;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.util.PriorityQueue;

public class Garage {

    private PriorityQueue<MotorcycleSpace> motorcycleSpaces;
    private PriorityQueue<CarSpace> carSpaces;
    private PriorityQueue<TruckSpace> truckSpaces;

    public Garage() {
        this.motorcycleSpaces = new PriorityQueue<>();
        this.carSpaces = new PriorityQueue<>();
        this.truckSpaces = new PriorityQueue<>();
    }

    public boolean addSpace(Space space) {
        if (space instanceof MotorcycleSpace) {
            return motorcycleSpaces.add((MotorcycleSpace) space);
        }

        if (space instanceof CarSpace) {
            return carSpaces.add((CarSpace) space);
        }

        return truckSpaces.add((TruckSpace) space);
    }

    private Space getClosestSpaceForMotorcycle() {
        if (!motorcycleSpaces.peek().isOccupied()) {
            return motorcycleSpaces.peek();
        }

        if (!carSpaces.peek().isOccupied()) {
            return carSpaces.peek();
        }

        if (!truckSpaces.peek().isOccupied()) {
            return truckSpaces.peek();
        }

        return null;
    }

    private Space getClosestSpaceForCar() {
        if (!carSpaces.peek().isOccupied()) {
            return carSpaces.peek();
        }

        if (!truckSpaces.peek().isOccupied()) {
            return truckSpaces.peek();
        }

        return null;
    }

    private Space getClosestSpaceForTruck() {
        if (!truckSpaces.peek().isOccupied()) {
            return truckSpaces.peek();
        }

        return null;
    }

    public Space getClosestSpace(Vehicle vehicle) {
        if (vehicle instanceof Motorcycle) {
            return getClosestSpaceForMotorcycle();
        }

        if (vehicle instanceof Car) {
            return getClosestSpaceForCar();
        }

        if (vehicle instanceof Truck) {
            return getClosestSpaceForTruck();
        }

        return null;
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

    public MotorcycleSpace peekMotorcycleSpace() {
        return motorcycleSpaces.peek();
    }

    public CarSpace peekCarSpaces() {
        return carSpaces.peek();
    }

    public TruckSpace peekTruckSpace() {
        return truckSpaces.peek();
    }

    public MotorcycleSpace pollMotorcycleSpaces() {
        return motorcycleSpaces.poll();
    }

    public CarSpace pollCarSpaces() {
        return carSpaces.poll();
    }

    public TruckSpace pollTruckSpaces() {
        return truckSpaces.poll();
    }
}
