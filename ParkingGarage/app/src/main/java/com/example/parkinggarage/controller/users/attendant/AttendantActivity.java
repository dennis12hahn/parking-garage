package com.example.parkinggarage.controller.users.attendant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.tickets_and_receipts.TicketActivity;
import com.example.parkinggarage.controller.users.attendant.actions.ParkActivity;
import com.example.parkinggarage.controller.users.attendant.actions.RetrieveActivity;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.garage.SingletonGarage;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Attendant;

public class AttendantActivity extends AppCompatActivity {

	private static final int PARK_REQUEST_CODE = 1;
	private static final int RETRIEVE_REQUEST_CODE = 2;
	private Attendant attendant;
	private Garage garage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attendant);

		String username = (String) getIntent().getSerializableExtra("attendant_username");
		garage = SingletonGarage.getGarage();
		attendant = (Attendant) garage.getUserBag().getUser(username);

		setTitle(attendant.getUsername());

		Button signOutBtn = findViewById(R.id.activity_attendant_signOutBtn);
		Button parkBtn = findViewById(R.id.activity_attendant_parkBtn);
		Button exitBtn = findViewById(R.id.activity_attendant_retrieveBtn);

		signOutBtn.setOnClickListener(v -> {
			finish();
		});

		parkBtn.setOnClickListener(v -> {
			openParkActivity();
		});

		exitBtn.setOnClickListener(v -> {
			openRetrieveActivity();
		});

	}

	private void openParkActivity() {
		Intent intent = new Intent(this, ParkActivity.class);
		intent.putExtra("username", attendant.getUsername());
		startActivityForResult(intent, PARK_REQUEST_CODE);
	}

	private void openRetrieveActivity() {
		Intent intent = new Intent(this, RetrieveActivity.class);
		intent.putExtra("username", attendant.getUsername());
		startActivityForResult(intent, RETRIEVE_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PARK_REQUEST_CODE) {
			if (resultCode == RESULT_OK && data != null) {
				Document doc = (Document) data.getSerializableExtra("document");
				Intent intent = new Intent(this, TicketActivity.class);
				intent.putExtra("doc_type", "Ticket");
				intent.putExtra("document", doc);
				startActivity(intent);
			}
		}

		if (requestCode == RETRIEVE_REQUEST_CODE) {
			if (resultCode == RESULT_OK && data != null) {
				Document doc = (Document) data.getSerializableExtra("document");
				Intent intent = new Intent(this, TicketActivity.class);
				intent.putExtra("doc_type", "Receipt");
				intent.putExtra("document", doc);
				startActivity(intent);
			}
		}
	}
}
