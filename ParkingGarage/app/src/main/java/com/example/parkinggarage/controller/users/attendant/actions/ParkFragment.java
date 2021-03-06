package com.example.parkinggarage.controller.users.attendant.actions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.tickets_and_receipts.TicketActivity;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.garage.SingletonGarage;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.vehicles.Car;
import com.example.parkinggarage.model.vehicles.Motorcycle;
import com.example.parkinggarage.model.vehicles.Truck;
import com.example.parkinggarage.model.vehicles.Vehicle;

import java.time.LocalDateTime;

public class ParkFragment extends Fragment implements AdapterView.OnItemSelectedListener {

	private String vehicleType;
	private EditText licenseField;
	private Vehicle vehicle;
	private Space space;
	private Attendant attendant;
	private Garage garage;

	public static ParkFragment newInstance(String username) {
		ParkFragment fragment = new ParkFragment();
		Bundle args = new Bundle();
		args.putString("username", username);
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_park, null);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		garage = SingletonGarage.getGarage();
		String username = getArguments().getString("username");
		attendant = (Attendant) garage.getUserBag().getUser(username);

		licenseField = getView().findViewById(R.id.activity_park_licenseField);
		createVehicleSpinner();
		Button parkBtn = getView().findViewById(R.id.activity_park_parkBtn);

		parkBtn.setOnClickListener(v -> {
			if (checkFields()) {
				vehicle = createVehicle();
				space = garage.getSpaceBag().getClosestSpace(vehicle, "peek");

				if (space == null || space.isOccupied()) {
					Toast.makeText(getView().getContext(), "No spaces available", Toast.LENGTH_SHORT).show();
				} else {
					if (garage.getTicketsAndReceipts().containsKey(licenseField.getText().toString())) {
						Vehicle vehicle = garage.getTicketsAndReceipts().get(licenseField.getText().toString()).peek().getVehicle();

						if (vehicle.isParked()) {
							Toast.makeText(getView().getContext(), "Vehicle already parked", Toast.LENGTH_SHORT).show();
						} else {
							displayOption();
						}
					} else {
						displayOption();
					}
				}

			}
		});
	}

	private void createVehicleSpinner() {
		Spinner vehicleTypeSpinner = getView().findViewById(R.id.activity_park_vehicleTypeSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getView().getContext(), R.array.vehicle_types, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		vehicleTypeSpinner.setAdapter(adapter);
		vehicleTypeSpinner.setOnItemSelectedListener(this);
	}

	private boolean checkFields() {
		boolean result = true;

		if (isEmpty(licenseField)) {
			licenseField.setError("Enter a license plate number");
			result = false;
		}

		if (vehicleType.equals("")) {
			Toast.makeText(getView().getContext(), "Select a vehicle type", Toast.LENGTH_SHORT).show();
			result = false;
		}

		return result;
	}

	private Vehicle createVehicle() {
		switch (vehicleType) {
			case "Motorcycle":
				return new Motorcycle(licenseField.getText().toString());
			case "Car":
				return new Car(licenseField.getText().toString());
			case "Truck":
				return new Truck(licenseField.getText().toString());
		}
		return null;
	}

	private void displayOption() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getView().getContext());
		builder.setCancelable(true);

		StringBuilder title = new StringBuilder();
		StringBuilder message = new StringBuilder();

		setTitleAndMessage(title, message);

		builder.setTitle(title.toString());
		builder.setMessage(message.toString());

		builder.setPositiveButton("Confirm",
				((dialog, which) -> {
					Document doc = attendant.park(vehicle, garage);

					if (doc == null) {
						Toast.makeText(getView().getContext(), "Unable to park vehicle", Toast.LENGTH_SHORT).show();
					} else {
						Intent intent = new Intent(getView().getContext(), TicketActivity.class);
						intent.putExtra("document", doc);
						intent.putExtra("doc_type", "Ticket");
						startActivity(intent);
					}

				}));

		builder.setNegativeButton("Cancel",
				(dialog, which) -> {
				});

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	private boolean isEmpty(EditText field) {
		return field.getText().toString().equals("");
	}

	private void setTitleAndMessage(StringBuilder title, StringBuilder message) {
		if (vehicle.getSize() < space.getSize()) {
			title.append("Only Larger Space is Available");
			message.append("This space is larger than necessary and costs more but is the only one available. ");
		} else {
			title.append("Space Found");
		}

		if (LocalDateTime.now().getHour() < 8) {
			message.append("The price of this space is $").append(space.getEarlyBirdPrice()).append(" for the day. ");
		} else {
			message.append("The rate of this space is $").append(space.getRate()).append("/hr. ");
		}
		message.append("Park in this space?");
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		vehicleType = parent.getItemAtPosition(position).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
}
