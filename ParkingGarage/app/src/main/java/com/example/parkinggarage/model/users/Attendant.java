package com.example.parkinggarage.model.users;

import com.example.parkinggarage.model.payment.Document;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.vehicles.Vehicle;
import com.example.parkinggarage.model.vehicles.VehicleBag;

import java.time.LocalDateTime;
import java.util.Stack;

/**
 * The Attendant class extends the User class and controls the following:
 * the amount currently owed by the attendant
 * charging the attendant
 * allowing the attendant to pay their debts
 * parking of the attendant's vehicle into a designated space
 * exiting the currently occupied space
 * the vehicles currently associated with the attendant
 *
 * @author Dennis Hahn
 * @version 1, 03/28/2019
 */

public class Attendant extends User {

    private double amountReceivable;
    private VehicleBag vehicles;
    private Stack<Document> docs;

    public Attendant(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        this.amountReceivable = 0;
        this.vehicles = new VehicleBag();
        this.docs = new Stack<>();
    }

    public double getAmountReceivable() {
        return amountReceivable;
    }

    /**
     * @param payment: the amount being paid by the attendant
     * @return the change from the payment, -1 if payment is less than or equal to 0
     * @author Dennis Hahn
     * @hahn.precondition payment must be greater than 0
     * @hahn.postcondition amountReceivable must be greater than or equal to 0
     */
    public double pay(double payment) {

        if (payment <= 0) {
            return -1;
        }

        double change;

        if (payment > amountReceivable) {
            change = payment - amountReceivable;
            amountReceivable = 0;
        } else {
            change = amountReceivable -= payment;
        }

        return change;
    }

    /**
     * @param chargeAmount: amount to charge on the account
     * @return the amountReceivable on the account, -1 if the chargeAmount is less than or equal to 0
     * @author Dennis Hahn
     * @hahn.precondition chargeAmount must be greater than 0
     */
    public double charge(double chargeAmount) {
        if (chargeAmount <= 0) {
            return -1;
        }
        return amountReceivable += chargeAmount;
    }


    public boolean addVehicle(Vehicle vehicle) {
        return vehicles.addVehicle(vehicle);
    }

    public boolean addMotorcycle(String license, String make, String model, int year) {
        return vehicles.addMotorcycle(license, make, model, year);
    }

    public boolean addCar(String license, String make, String model, int year) {
        return vehicles.addCar(license, make, model, year);
    }

    public boolean addTruck(String license, String make, String model, int year) {
        return vehicles.addTruck(license, make, model, year);
    }

    public String[] park(Vehicle vehicle, Space space) {
        if (vehicle.isParkable(space)) {
            vehicles.addVehicle(vehicle);
            vehicle.setParked(true);
            space.setOccupied(true);
            return docs.push(new Document(this, vehicle, space)).getTicketInfo();
        }
        return null;
    }

    public String[] exit(Vehicle vehicle, Space space, double payment) {
        if (vehicle.isParked() && space.isOccupied()) {
            docs.peek().setTimeRetrieved(LocalDateTime.now());
            docs.peek().setPaid(payment);
            charge(docs.peek().calculateCharge());
            pay(payment);
            vehicle.setParked(false);
            space.setOccupied(false);
            return docs.peek().getReceiptInfo();
        }
        return null;
    }
}