package com.example.parkinggarage.controller.users.attendant.actions;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.tickets_and_receipts.TicketActivity;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.garage.SingletonGarage;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Attendant;

public class RetrieveFragment extends Fragment {

	private EditText licenseField, paymentField;
	private Garage garage;

	public static RetrieveFragment newInstance(String username) {
		RetrieveFragment fragment = new RetrieveFragment();
		Bundle args = new Bundle();
		args.putString("username", username);
		fragment.setArguments(args);
		return fragment;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_retrieve, null);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		garage = SingletonGarage.getGarage();
		String username = getArguments().getString("username");
		Attendant attendant = (Attendant) garage.getUserBag().getUser(username);

		licenseField = getView().findViewById(R.id.activity_retrieve_licenseField);
		paymentField = getView().findViewById(R.id.activity_retrieve_paymentField);
		Button retrieveBtn = getView().findViewById(R.id.activity_retrieve_retrieveBtn);

		retrieveBtn.setOnClickListener(v -> {
			if (checkFields()) {
				String license = licenseField.getText().toString();
				double payment = Double.parseDouble(paymentField.getText().toString());

				if (garage.getTicketsAndReceipts().containsKey(license)) {
					if (garage.getTicketsAndReceipts().get(license).peek().getVehicle().isParked()) {
						Document doc = attendant.retrieve(license, garage, payment);

						Intent intent = new Intent(getView().getContext(), TicketActivity.class);
						intent.putExtra("document", doc);
						intent.putExtra("doc_type", "Receipt");
						startActivity(intent);
					} else {
						Toast.makeText(getView().getContext(), "Vehicle is not currently parked", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getView().getContext(), "Vehicle not found", Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	private boolean checkFields() {
		boolean result = true;

		if (isEmpty(licenseField)) {
			licenseField.setError("Enter a license plate");
			result = false;
		}

		if (isEmpty(paymentField)) {
			paymentField.setError("Enter a payment");
			result = false;
		}

		return result;
	}

	private boolean isEmpty(EditText field) {
		return field.getText().toString().equals("");
	}
}
