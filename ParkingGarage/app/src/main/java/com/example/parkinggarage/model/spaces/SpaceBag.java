package com.example.parkinggarage.model.spaces;

import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.io.Serializable;
import java.util.PriorityQueue;

/**
 * A SpaceBag contains the priority queues of the different types of spaces in the garage as well as
 * the prices designated by the manager.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public class SpaceBag implements Serializable {

	private double motorcycleRate, motorcycleEarlyBird, carRate, carEarlyBird, truckRate, truckEarlyBird;
	private PriorityQueue<Space> motorcycleSpaces;
	private PriorityQueue<Space> carSpaces;
	private PriorityQueue<Space> truckSpaces;

	public SpaceBag() {
		this.motorcycleSpaces = new PriorityQueue<>();
		this.carSpaces = new PriorityQueue<>();
		this.truckSpaces = new PriorityQueue<>();
	}

	/**
	 * Returns the closest space in the garage for the given vehicle.
	 *
	 * @param vehicle the vehicle that we're trying to find a space for
	 * @param option  gives an option for peek or poll
	 * @return the smallest and closest space in the garge, null if there aren't any
	 */
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

	/**
	 * Returns the closest space for a motorcycle with the desired option.
	 * Checks space at the head of the motorcycle space queue first.
	 * If that space is occupied, check the space at the head of the car space queue.
	 * If the space is occupied, check the space at the head of the truck space queue.
	 *
	 * @param option gives an option for peek or poll
	 * @return the smallest and closest space for a motorcycle in the garage, null if all are occupied
	 */
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

	/**
	 * Returns the closest space for a car with the desired option.
	 * Checks space at the head of the car space queue first.
	 * If that space is occupied, check the space at the head of the truck space queue.
	 *
	 * @param option gives an option for peek or poll
	 * @return the smallest and closest space for a motorcycle in the garage, null if all are occupied
	 */
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

	/**
	 * Returns the closest space for a truck with the desired option.
	 * Checks space at the head of the truck space queue.
	 *
	 * @param option gives an option for peek or poll
	 * @return the closest space for a truck in the garage, null if all are occupied
	 */
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

	/**
	 * Fills the queues with the desired amount of each space.
	 * Motorcycle spaces are placed in the front,
	 * car spaces are placed after, and truck spaces at the end.
	 *
	 * @param totalMotorcycleSpaces the desired amount of motorcycle spaces
	 * @param totalCarSpaces        the desired amount of car spaces
	 * @param totalTruckSpaces      the desired amount of truck spaces
	 */
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

	/**
	 * Will add the given space to the correct queue.
	 *
	 * @param space the desired space to be added to the garage
	 * @return true if the space has been added, false if not
	 */
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

	/**
	 * Removes the designated space from the correct queue.
	 *
	 * @param space the desired space to be removed
	 */
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

	public double getMotorcycleRate() {
		return motorcycleRate;
	}

	public void setMotorcycleRate(double motorcycleRate) {
		this.motorcycleRate = motorcycleRate;
	}

	public double getMotorcycleEarlyBird() {
		return motorcycleEarlyBird;
	}

	public void setMotorcycleEarlyBird(double motorcycleEarlyBird) {
		this.motorcycleEarlyBird = motorcycleEarlyBird;
	}

	public double getCarRate() {
		return carRate;
	}

	public void setCarRate(double carRate) {
		this.carRate = carRate;
	}

	public double getCarEarlyBird() {
		return carEarlyBird;
	}

	public void setCarEarlyBird(double carEarlyBird) {
		this.carEarlyBird = carEarlyBird;
	}

	public double getTruckRate() {
		return truckRate;
	}

	public void setTruckRate(double truckRate) {
		this.truckRate = truckRate;
	}

	public double getTruckEarlyBird() {
		return truckEarlyBird;
	}

	public void setTruckEarlyBird(double truckEarlyBird) {
		this.truckEarlyBird = truckEarlyBird;
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
