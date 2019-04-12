package com.example.parkinggarage.model.garage;

import com.example.parkinggarage.model.spaces.CarSpace;
import com.example.parkinggarage.model.spaces.MotorcycleSpace;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.spaces.TruckSpace;
import com.example.parkinggarage.model.users.Manager;
import com.example.parkinggarage.model.users.UserBag;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.io.Serializable;
import java.util.PriorityQueue;

public class Garage implements Serializable {

    private PriorityQueue<MotorcycleSpace> motorcycleSpaces;
    private PriorityQueue<CarSpace> carSpaces;
    private PriorityQueue<TruckSpace> truckSpaces;
    private Manager manager;
    private UserBag userBag;

    public Garage(String managerFirstName, String managerLastName, String managerPassword, int motorcycleSpaces, int carSpaces, int truckSpaces) {
        this.userBag = new UserBag();
        this.motorcycleSpaces = new PriorityQueue<>();
        this.carSpaces = new PriorityQueue<>();
        this.truckSpaces = new PriorityQueue<>();
        this.manager = createManager(managerFirstName, managerLastName, managerPassword);
        generateSpaces(motorcycleSpaces, carSpaces, truckSpaces);
    }

    private Manager createManager(String managerFirstName, String managerLastName, String managerPassword) {
        Manager manager = new Manager(managerFirstName, managerLastName, managerPassword);
        userBag.addManager(manager);
        return manager;
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

    private void generateSpaces(int totalMotorcycleSpaces, int totalCarSpaces, int totalTruckSpaces) {
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

    public MotorcycleSpace pollMotorcycleSpaces() {
        if (motorcycleSpaces.isEmpty()) {
            return null;
        }

        return motorcycleSpaces.poll();
    }

    public CarSpace pollCarSpaces() {
        if (carSpaces.isEmpty()) {
            return null;
        }

        return carSpaces.poll();
    }

    public TruckSpace pollTruckSpaces() {
        if (truckSpaces.isEmpty()) {
            return null;
        }

        return truckSpaces.poll();
    }

    public void displayAllSpaces() {
        PriorityQueue<Space> motorcycles = new PriorityQueue<>(motorcycleSpaces);
        PriorityQueue<Space> cars = new PriorityQueue<>(carSpaces);
        PriorityQueue<Space> trucks = new PriorityQueue<>(truckSpaces);

        System.out.print("\n\n\nMotorcycles: ");
        while (!motorcycles.isEmpty()) {
            System.out.print(motorcycles.poll());
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

    public PriorityQueue<MotorcycleSpace> getMotorcycleSpaces() {
        return motorcycleSpaces;
    }

    public PriorityQueue<CarSpace> getCarSpaces() {
        return carSpaces;
    }

    public PriorityQueue<TruckSpace> getTruckSpaces() {
        return truckSpaces;
    }

    public Manager getManager() {
        return manager;
    }

    public UserBag getUserBag() {
        return userBag;
    }

}
