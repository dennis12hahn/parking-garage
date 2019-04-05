package com.example.parkinggarage.model;

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
 * @version 1.0, 03/28/2019
 */

public class Attendant extends User {

    private double amountReceivable;
    // todo
    // add vehicles as a Vehicle Bag

    public Attendant(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        this.amountReceivable = 0;
    }

    public double getAmountReceivable() {
        return amountReceivable;
    }

    /**
     *
     * @param payment: the amount being paid by the attendant
     * @return the change from the payment, -1 if payment is less than or equal to 0
     *
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
     *
     * @param chargeAmount: amount to charge on the account
     * @return the amountReceivable on the account, -1 if the chargeAmount is less than or equal to 0
     *
     * @author Dennis Hahn
     * @hahn.precondition chargeAmount must be greater than 0
     */
    public double charge(double chargeAmount) {
        if (chargeAmount <= 0) {
            return -1;
        }
        return amountReceivable += chargeAmount;
    }


    // todo
    // add park, exit, and addVehicle methods
}
