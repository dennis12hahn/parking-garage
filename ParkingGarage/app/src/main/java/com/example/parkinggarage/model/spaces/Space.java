package com.example.parkinggarage.model.spaces;

import com.example.parkinggarage.model.utils.SingletonIncrementalDataContainer;

import java.io.Serializable;

/**
 * The Space class is used to hold data relevant to each space associated with the garage.
 * Will contain an hourly rate for the space and an early bird price which is the price for the entire day.
 * Will contain the occupied status of the space and its size and the distance it is away from the exit.
 *
 * @author Dennis Hahn <A href="mailto:hahnd62@mail.sunysuffolk.edu">hahnd62@mail.sunysuffolk.edu</A>
 * @version 05/2019
 */
public abstract class Space implements Comparable<Space>, Serializable {

	private double rate, earlyBirdPrice;
	private boolean occupied;
	private int distanceToExit, size;

	public Space(double rate, double earlyBirdPrice, int size) {
		this.distanceToExit = SingletonIncrementalDataContainer.getDataContainer().getSpaceDistanceCounter(true);
		this.rate = rate;
		this.earlyBirdPrice = earlyBirdPrice;
		this.occupied = false;
		this.size = size;
	}

	public int getDistanceToExit() {
		return distanceToExit;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getEarlyBirdPrice() {
		return earlyBirdPrice;
	}

	public void setEarlyBirdPrice(double earlyBirdPrice) {
		this.earlyBirdPrice = earlyBirdPrice;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Used to order the priority queues of spaces in the garage.
	 * If both spaces are occupied, the closer space has priority.
	 * The unoccupied space always has priority over occupied spaces.
	 *
	 * @param space the space being compared to this space
	 * @return an integer designating which space has priority
	 */
	@Override
	public int compareTo(Space space) {
		if (!this.occupied && space.occupied) {
			return -1;
		}

		if (this.occupied && !space.occupied) {
			return 1;
		}

		return Integer.compare(this.distanceToExit, space.distanceToExit);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "{" +
				"distanceToExit=" + distanceToExit +
				", rate=" + rate +
				", earlyBirdPrice=" + earlyBirdPrice +
				", occupied=" + occupied +
				", size=" + size +
				'}';
	}
}
