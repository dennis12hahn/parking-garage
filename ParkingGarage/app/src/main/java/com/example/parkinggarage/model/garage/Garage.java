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
        if (space == null) {
            return false;
        }

        if (space instanceof MotorcycleSpace) {
            return motorcycleSpaces.add((MotorcycleSpace) space);
        }

        if (space instanceof CarSpace) {
            return carSpaces.add((CarSpace) space);
        }

        return truckSpaces.add((TruckSpace) space);
    }

    private Space getClosestSpaceForMotorcycle() {
        if (motorcycleSpaces.peek() != null && !motorcycleSpaces.peek().isOccupied()) {
            return motorcycleSpaces.poll();
        }

        if (carSpaces.peek() != null && !carSpaces.peek().isOccupied()) {
            return carSpaces.poll();
        }

        if (truckSpaces.peek() != null && !truckSpaces.peek().isOccupied()) {
            return truckSpaces.poll();
        }

        return null;
    }

    private Space getClosestSpaceForCar() {
        if (carSpaces.peek() != null && !carSpaces.peek().isOccupied()) {
            return carSpaces.poll();
        }

        if (truckSpaces.peek() != null && !truckSpaces.peek().isOccupied()) {
            return truckSpaces.poll();
        }

        return null;
    }

    private Space getClosestSpaceForTruck() {
        if (truckSpaces.peek() != null && !truckSpaces.peek().isOccupied()) {
            return truckSpaces.poll();
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

    public PriorityQueue<MotorcycleSpace> getMotorcycleSpaces() {
        return motorcycleSpaces;
    }

    public PriorityQueue<CarSpace> getCarSpaces() {
        return carSpaces;
    }

    public PriorityQueue<TruckSpace> getTruckSpaces() {
        return truckSpaces;
    }

    public void displayAllSpaces() {
        PriorityQueue<Space> motos = new PriorityQueue<>(motorcycleSpaces);
        PriorityQueue<Space> cars = new PriorityQueue<>(carSpaces);
        PriorityQueue<Space> trucks = new PriorityQueue<>(truckSpaces);

        System.out.print("\n\n\nMotorcycles: ");
        while (!motos.isEmpty()) {
            System.out.print(motos.poll());
        }

        System.out.print("\n\n\nCars: ");
        while (!cars.isEmpty()) {
            System.out.print(cars.poll());
        }

        System.out.print("\n\n\nTrucks: ");
        while (!trucks.isEmpty()) {
            System.out.print(trucks.poll());
        }


    }
}
