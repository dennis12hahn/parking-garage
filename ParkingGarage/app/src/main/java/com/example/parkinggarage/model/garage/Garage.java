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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.PriorityQueue;

public class Garage implements Serializable {

    private PriorityQueue<MotorcycleSpace> motorcycleSpaces;
    private PriorityQueue<CarSpace> carSpaces;
    private PriorityQueue<TruckSpace> truckSpaces;
    private Manager manager;
    private UserBag userBag;
    private static double motorcycleRate, motorcycleEarlyBird, carRate, carEarlyBird, truckRate, truckEarlybird;

    public Garage(String managerFirstName, String managerLastName, String managerPassword) {
        this.userBag = new UserBag();
        this.motorcycleSpaces = new PriorityQueue<>();
        this.carSpaces = new PriorityQueue<>();
        this.truckSpaces = new PriorityQueue<>();
        this.manager = createManager(managerFirstName, managerLastName, managerPassword);
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

    private Space getClosestSpaceForMotorcycle(String option) {
        if (motorcycleSpaces.peek() != null && !motorcycleSpaces.peek().isOccupied()) {
            if (option.equals("poll")) {
                return motorcycleSpaces.poll();
            }

            if (option.equals("peek")) {
                return motorcycleSpaces.peek();
            }
        }

        if (carSpaces.peek() != null && !carSpaces.peek().isOccupied()) {
            if (option.equals("poll")) {
                return carSpaces.poll();
            }

            if (option.equals("peek")) {
                return carSpaces.peek();
            }
        }

        if (truckSpaces.peek() != null && !truckSpaces.peek().isOccupied()) {
            if (option.equals("poll")) {
                return truckSpaces.poll();
            }

            if (option.equals("peek")) {
                return truckSpaces.peek();
            }
        }

        return null;
    }

    private Space getClosestSpaceForCar(String option) {
        if (carSpaces.peek() != null && !carSpaces.peek().isOccupied()) {
            if (option.equals("poll")) {
                return carSpaces.poll();
            }

            if (option.equals("peek")) {
                return carSpaces.peek();
            }
        }

        if (truckSpaces.peek() != null && !truckSpaces.peek().isOccupied()) {
            if (option.equals("poll")) {
                return truckSpaces.poll();
            }

            if (option.equals("peek")) {
                return truckSpaces.peek();
            }
        }

        return null;
    }

    private Space getClosestSpaceForTruck(String option) {
        if (truckSpaces.peek() != null && !truckSpaces.peek().isOccupied()) {
            if (option.equals("poll")) {
                return truckSpaces.poll();
            }

            if (option.equals("peek")) {
                return truckSpaces.peek();
            }
        }

        return null;
    }

    public Space getClosestSpace(Vehicle vehicle, String option) {
        if (vehicle instanceof Motorcycle) {
            return getClosestSpaceForMotorcycle(option);
        }

        if (vehicle instanceof Car) {
            return getClosestSpaceForCar(option);
        }

        if (vehicle instanceof Truck) {
            return getClosestSpaceForTruck(option);
        }

        return null;
    }

    public void generateSpaces(int totalMotorcycleSpaces, int totalCarSpaces, int totalTruckSpaces) {
        for (int i = totalMotorcycleSpaces; i > 0; i--) {
            addSpace(new MotorcycleSpace(motorcycleRate, motorcycleEarlyBird));
        }

        for (int i = totalCarSpaces; i > 0; i--) {
            addSpace(new CarSpace(carRate, carEarlyBird));
        }

        for (int i = totalTruckSpaces; i > 0; i--) {
            addSpace(new TruckSpace(truckRate, truckEarlybird));
        }
    }

    public static void setMotorcycleRate(double motorcycleRate) {
        Garage.motorcycleRate = motorcycleRate;
    }

    public static void setMotorcycleEarlyBird(double motorcycleEarlyBird) {
        Garage.motorcycleEarlyBird = motorcycleEarlyBird;
    }

    public static void setCarRate(double carRate) {
        Garage.carRate = carRate;
    }

    public static void setCarEarlyBird(double carEarlyBird) {
        Garage.carEarlyBird = carEarlyBird;
    }

    public static void setTruckRate(double truckRate) {
        Garage.truckRate = truckRate;
    }

    public static void setTruckEarlybird(double truckEarlybird) {
        Garage.truckEarlybird = truckEarlybird;
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

    public Manager getManager() {
        return manager;
    }

    public UserBag getUserBag() {
        return userBag;
    }

    public void removeSpace(Space space) {
        if (space instanceof MotorcycleSpace) {
            motorcycleSpaces.remove(space);
        }

        if (space instanceof CarSpace) {
            carSpaces.remove(space);
        }

        if (space instanceof TruckSpace) {
            truckSpaces.remove(space);
        }
    }

}
