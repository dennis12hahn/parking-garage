package com.example.parkinggarage.model.spaces;

import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.io.Serializable;
import java.util.PriorityQueue;

public class SpaceBag implements Serializable {

	private static double motorcycleRate, motorcycleEarlyBird, carRate, carEarlyBird, truckRate, truckEarlyBird;
	private PriorityQueue<Space> motorcycleSpaces;
	private PriorityQueue<Space> carSpaces;
	private PriorityQueue<Space> truckSpaces;

	public SpaceBag() {
		this.motorcycleSpaces = new PriorityQueue<>();
		this.carSpaces = new PriorityQueue<>();
		this.truckSpaces = new PriorityQueue<>();
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

	public void generateSpaces(int totalMotorcycleSpaces, int totalCarSpaces, int totalTruckSpaces) {
		for (int i = totalMotorcycleSpaces; i > 0; i--) {
			addSpace(new MotorcycleSpace(motorcycleRate, motorcycleEarlyBird));
		}

		for (int i = totalCarSpaces; i > 0; i--) {
			addSpace(new CarSpace(carRate, carEarlyBird));
		}

		for (int i = totalTruckSpaces; i > 0; i--) {
			addSpace(new TruckSpace(truckRate, truckEarlyBird));
		}
	}

	public boolean addSpace(Space space) {
		if (space == null) {
			return false;
		}

		if (space instanceof MotorcycleSpace) {
			return motorcycleSpaces.add(space);
		}

		if (space instanceof CarSpace) {
			return carSpaces.add(space);
		}

		return truckSpaces.add(space);
	}

	public MotorcycleSpace pollMotorcycleSpaces() {
		if (motorcycleSpaces.isEmpty()) {
			return null;
		}

		return (MotorcycleSpace) motorcycleSpaces.poll();
	}

	public CarSpace pollCarSpaces() {
		if (carSpaces.isEmpty()) {
			return null;
		}

		return (CarSpace) carSpaces.poll();
	}

	public TruckSpace pollTruckSpaces() {
		if (truckSpaces.isEmpty()) {
			return null;
		}

		return (TruckSpace) truckSpaces.poll();
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

	public static double getMotorcycleRate() {
		return motorcycleRate;
	}

	public static void setMotorcycleRate(double motorcycleRate) {
		SpaceBag.motorcycleRate = motorcycleRate;
	}

	public static double getMotorcycleEarlyBird() {
		return motorcycleEarlyBird;
	}

	public static void setMotorcycleEarlyBird(double motorcycleEarlyBird) {
		SpaceBag.motorcycleEarlyBird = motorcycleEarlyBird;
	}

	public static double getCarRate() {
		return carRate;
	}

	public static void setCarRate(double carRate) {
		SpaceBag.carRate = carRate;
	}

	public static double getCarEarlyBird() {
		return carEarlyBird;
	}

	public static void setCarEarlyBird(double carEarlyBird) {
		SpaceBag.carEarlyBird = carEarlyBird;
	}

	public static double getTruckRate() {
		return truckRate;
	}

	public static void setTruckRate(double truckRate) {
		SpaceBag.truckRate = truckRate;
	}

	public static double getTruckEarlyBird() {
		return truckEarlyBird;
	}

	public static void setTruckEarlyBird(double truckEarlyBird) {
		SpaceBag.truckEarlyBird = truckEarlyBird;
	}

	public PriorityQueue<Space> getMotorcycleSpaces() {
		return motorcycleSpaces;
	}

	public PriorityQueue<Space> getCarSpaces() {
		return carSpaces;
	}

	public PriorityQueue<Space> getTruckSpaces() {
		return truckSpaces;
	}
}
