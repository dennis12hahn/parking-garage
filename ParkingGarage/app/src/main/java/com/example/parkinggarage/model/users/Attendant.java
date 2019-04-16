package com.example.parkinggarage.model.users;

import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.util.HashMap;

public class Attendant extends User {

    private HashMap<String, Document> docs;

    public Attendant(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        this.docs = new HashMap<>();
    }

    public Document park(Vehicle vehicle, Garage garage) {
        Space space = garage.getClosestSpace(vehicle, "poll");
        space.setOccupied(true);
        garage.addSpace(space);
        return docs.put(vehicle.getLicense(), new Document(vehicle, space));
    }

    public Document retrieve(String license, Garage garage, double payment) {
        if (docs.containsKey(license)) {
            Document doc = docs.remove(license);
            Space space = doc.getSpace();
            doc.setTimeRetrieved();
            doc.setPaid(payment);
            garage.removeSpace(space);
            space.setOccupied(false);
            garage.addSpace(space);
            return doc;
        }

        return null;
    }

}