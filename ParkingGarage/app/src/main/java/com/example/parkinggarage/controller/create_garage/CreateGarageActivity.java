package com.example.parkinggarage.controller.create_garage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.SignInActivity;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.garage.SingletonGarage;

public class CreateGarageActivity extends AppCompatActivity {

	private EditText firstNameField, lastNameField, passwordField, confirmPasswordField,
			numMotoSpacesField, numCarSpacesField, numTruckSpacesField,
			motoRateField, carRateField, truckRateField,
			motoEarlyBirdField, carEarlyBirdField, truckEarlyBirdField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_garage);

		setTitle("Create Manager");

		initializeManagerFields();
		Button createManager = findViewById(R.id.activity_create_garage_createManagerBtn);

		createManager.setOnClickListener(v -> {
			if (checkManagerFields()) {
				createGarage();
				openSetPricesLayout();
			}
		});
	}

	private void createGarage() {
		String firstName = firstNameField.getText().toString();
		String lastName = lastNameField.getText().toString();
		String password = passwordField.getText().toString();
		SingletonGarage.setGarage(new Garage(firstName, lastName, password));

		String message = "The manager's username is " + SingletonGarage.getGarage().getManager().getUsername();
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

	}

	private void openSetPricesLayout() {
		setContentView(R.layout.activity_set_prices);

		setTitle("Set Prices");
		initializePriceFields();

		Button setPricesBtn = findViewById(R.id.activity_set_prices_setPricesBtn);

		setPricesBtn.setOnClickListener(v -> {
			if (checkPriceFields()) {
				double motoRate = Double.parseDouble(motoRateField.getText().toString());
				double carRate = Double.parseDouble(carRateField.getText().toString());
				double truckRate = Double.parseDouble(truckRateField.getText().toString());

				double motoEarlyBird = Double.parseDouble(motoEarlyBirdField.getText().toString());
				double carEarlyBird = Double.parseDouble(carEarlyBirdField.getText().toString());
				double truckEarlyBird = Double.parseDouble(truckEarlyBirdField.getText().toString());

				Garage.setMotorcycleRate(motoRate);
				Garage.setCarRate(carRate);
				Garage.setTruckRate(truckRate);

				Garage.setMotorcycleEarlyBird(motoEarlyBird);
				Garage.setCarEarlyBird(carEarlyBird);
				Garage.setTruckEarlyBird(truckEarlyBird);

				Toast.makeText(this, "Prices set", Toast.LENGTH_SHORT).show();
				openMakeSpacesLayout();
			}
		});

	}

	private void openMakeSpacesLayout() {
		setContentView(R.layout.activity_number_spaces);

		setTitle("Enter Spaces");
		initializeSpaceFields();

		Button createSpacesBtn = findViewById(R.id.activity_number_spaces_createSpacesBtn);

		createSpacesBtn.setOnClickListener(v -> {
			if (checkSpaceFields()) {
				int numMotoSpaces = Integer.parseInt(numMotoSpacesField.getText().toString());
				int numCarSpaces = Integer.parseInt(numCarSpacesField.getText().toString());
				int numTruckSpaces = Integer.parseInt(numTruckSpacesField.getText().toString());

				SingletonGarage.getGarage().generateSpaces(numMotoSpaces, numCarSpaces, numTruckSpaces);

				Toast.makeText(this, "Spaces generated", Toast.LENGTH_SHORT).show();
				openSignInActivity();
			}
		});
	}

	private void openSignInActivity() {
		Intent intent = new Intent(this, SignInActivity.class);
		finish();
		startActivity(intent);
	}

	private boolean checkManagerFields() {
		boolean result = true;

		if (isEmpty(firstNameField)) {
			firstNameField.setError("Enter a first name");
			result = false;
		}

		if (isEmpty(lastNameField)) {
			lastNameField.setError("Enter a last name");
			result = false;
		}

		if (isEmpty(passwordField)) {
			passwordField.setError("Enter a password");
			result = false;
		}

		if (isEmpty(confirmPasswordField)) {
			confirmPasswordField.setError("Confirm your password");
			result = false;
		}

		if (!passwordField.getText().toString().equals(confirmPasswordField.getText().toString())) {
			Toast.makeText(getApplicationContext(), "Make sure your passwords match", Toast.LENGTH_SHORT).show();
			result = false;
		}

		return result;
	}

	private boolean checkPriceFields() {
		boolean result = true;

		if (isEmpty(motoRateField)) {
			motoRateField.setError("Enter a rate");
			result = false;
		}

		if (isEmpty(motoEarlyBirdField)) {
			motoEarlyBirdField.setError("Enter a price");
			result = false;
		}
		if (isEmpty(carRateField)) {
			carRateField.setError("Enter a rate");
			result = false;
		}

		if (isEmpty(carEarlyBirdField)) {
			carEarlyBirdField.setError("Enter a price");
			result = false;
		}
		if (isEmpty(truckRateField)) {
			truckRateField.setError("Enter a rate");
			result = false;
		}

		if (isEmpty(truckEarlyBirdField)) {
			truckEarlyBirdField.setError("Enter a price");
			result = false;
		}

		return result;
	}

	private boolean checkSpaceFields() {
		boolean result = true;

		if (isEmpty(numMotoSpacesField)) {
			numMotoSpacesField.setError("Enter total motorcycle spaces");
			result = false;
		}

		if (isEmpty(numCarSpacesField)) {
			numCarSpacesField.setError("Enter total car spaces");
			result = false;
		}

		if (isEmpty(numTruckSpacesField)) {
			numTruckSpacesField.setError("Enter total truck spaces");
			result = false;
		}

		return result;
	}

	private boolean isEmpty(EditText field) {
		return field.getText().toString().equals("");
	}

	private void initializeManagerFields() {
		firstNameField = findViewById(R.id.activity_create_garage_firstNameField);
		lastNameField = findViewById(R.id.activity_create_garage_lastNameField);
		passwordField = findViewById(R.id.activity_create_garage_passwordField);
		confirmPasswordField = findViewById(R.id.activity_create_garage_confirmPasswordField);
	}

	private void initializePriceFields() {
		motoRateField = findViewById(R.id.activity_set_prices_motoRate);
		motoEarlyBirdField = findViewById(R.id.activity_set_prices_motoEarlyBird);
		carRateField = findViewById(R.id.activity_set_prices_carRate);
		carEarlyBirdField = findViewById(R.id.activity_set_prices_carEarlyBird);
		truckRateField = findViewById(R.id.activity_set_prices_truckRate);
		truckEarlyBirdField = findViewById(R.id.activity_set_prices_truckEarlyBird);
	}

	private void initializeSpaceFields() {
		numMotoSpacesField = findViewById(R.id.activity_number_spaces_numMotoSpacesField);
		numCarSpacesField = findViewById(R.id.activity_number_spaces_numCarSpacesField);
		numTruckSpacesField = findViewById(R.id.activity_number_spaces_numTruckSpacesField);
	}
}
