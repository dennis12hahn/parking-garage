package com.example.parkinggarage.controller.users.attendant.actions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.garage.SingletonGarage;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Attendant;

public class RetrieveActivity extends AppCompatActivity {

	private EditText licenseField, paymentField;
	private Garage garage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retrieve);

		setTitle("Retrieve");

		String username = (String) getIntent().getSerializableExtra("username");
		Attendant attendant = (Attendant) SingletonGarage.getGarage().getUserBag().getUser(username);
		garage = SingletonGarage.getGarage();

		licenseField = findViewById(R.id.activity_retrieve_licenseField);
		paymentField = findViewById(R.id.activity_retrieve_paymentField);
		Button retrieveBtn = findViewById(R.id.activity_retrieve_retrieveBtn);

		retrieveBtn.setOnClickListener(v -> {
			if (checkFields()) {
				String license = licenseField.getText().toString();
				double payment = Double.parseDouble(paymentField.getText().toString());

				if (garage.getTicketsAndReceipts().containsKey(license)) {
					if (garage.getTicketsAndReceipts().get(license).peek().getVehicle().isParked()) {
						Document doc = attendant.retrieve(license, garage, payment);

						Intent resultIntent = new Intent();
						resultIntent.putExtra("document", doc);
						setResult(RESULT_OK, resultIntent);
						finish();
					} else {
						Toast.makeText(this, "Vehicle is not currently parked", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(this, "Vehicle not found", Toast.LENGTH_SHORT).show();
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
