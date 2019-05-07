package com.example.parkinggarage.controller.users.manager.actions;

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
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.garage.SingletonGarage;
import com.example.parkinggarage.model.users.Attendant;

public class CreateAttendantFragment extends Fragment {

	private Garage garage;
	private EditText firstNameField, lastNameField, passwordField, confirmPasswordField;

	public static CreateAttendantFragment newInstance() {
		return new CreateAttendantFragment();
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.layout_create_attendant, null);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		garage = SingletonGarage.getGarage();

		firstNameField = getView().findViewById(R.id.activity_create_attendant_firstNameField);
		lastNameField = getView().findViewById(R.id.activity_create_attendant_lastNameField);
		passwordField = getView().findViewById(R.id.activity_create_attendant_passwordField);
		confirmPasswordField = getView().findViewById(R.id.activity_create_attendant_confirmPasswordField);
		Button createAttendantBtn = getView().findViewById(R.id.activity_create_attendant_createAttendantBtn);

		createAttendantBtn.setOnClickListener(v -> {
			if (checkFields()) {
				String firstName = firstNameField.getText().toString();
				String lastName = lastNameField.getText().toString();
				String password = passwordField.getText().toString();

				Attendant attendant = new Attendant(firstName, lastName, password);
				garage.getUserBag().addAttendant(attendant);
				Toast.makeText(getView().getContext(), "Attendant " + attendant.getUsername() + " added", Toast.LENGTH_SHORT).show();

			}
		});
	}

	private boolean checkFields() {
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

		if (!confirmPasswordField.getText().toString().equals(passwordField.getText().toString())) {
			Toast.makeText(getView().getContext(), "Make sure your passwords match", Toast.LENGTH_SHORT).show();
			result = false;
		}

		return result;
	}

	private boolean isEmpty(EditText field) {
		return field.getText().toString().equals("");
	}
}
