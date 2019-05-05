package com.example.parkinggarage.controller.users.manager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.tickets_and_receipts.TicketActivity;
import com.example.parkinggarage.controller.users.attendant.actions.ParkActivity;
import com.example.parkinggarage.controller.users.attendant.actions.RetrieveActivity;
import com.example.parkinggarage.controller.users.manager.actions.CreateAttendantActivity;
import com.example.parkinggarage.controller.users.manager.actions.manage_spaces.ManageSpacesActivity;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.garage.SingletonGarage;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Attendant;
import com.example.parkinggarage.model.users.Manager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ManagerActivity extends AppCompatActivity {

	private static final int PARK_REQUEST_CODE = 1;
	private static final int RETRIEVE_REQUEST_CODE = 2;
	private static final int WRITE_REQUEST_CODE = 43;
	private static final int CREATE_ATTENDANT_REQUEST_CODE = 44;
	private Garage garage;
	private Manager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager);

		garage = SingletonGarage.getGarage();
		manager = garage.getManager();

		Button signOutBtn = findViewById(R.id.activity_manager_signOutBtn);
		Button createAttendantBtn = findViewById(R.id.activity_manager_createAttendantBtn);
		Button saveGarageBtn = findViewById(R.id.activity_manager_saveBtn);
		Button parkBtn = findViewById(R.id.activity_manager_parkBtn);
		Button retrieveBtn = findViewById(R.id.activity_manager_retrieveBtn);
		Button manageSpacesBtn = findViewById(R.id.activity_manager_manageSpacesBtn);

		setTitle(garage.getManager().getUsername());

		signOutBtn.setOnClickListener(v -> finish());

		parkBtn.setOnClickListener(v -> openParkActivity());

		retrieveBtn.setOnClickListener(v -> openRetrieveActivity());

		createAttendantBtn.setOnClickListener(v -> openCreateAttendantView());

		manageSpacesBtn.setOnClickListener(v -> openManageSpacesActivity());

		saveGarageBtn.setOnClickListener(v -> {
			Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			intent.setType("*/garage");
			intent.putExtra(Intent.EXTRA_TITLE, "garage.dat");
			startActivityForResult(intent, WRITE_REQUEST_CODE);
		});

	}

	private void openManageSpacesActivity() {
		Intent intent = new Intent(this, ManageSpacesActivity.class);
		startActivity(intent);
	}

	private void openParkActivity() {
		Intent intent = new Intent(this, ParkActivity.class);
		intent.putExtra("username", manager.getUsername());
		startActivityForResult(intent, PARK_REQUEST_CODE);
	}

	private void openRetrieveActivity() {
		Intent intent = new Intent(this, RetrieveActivity.class);
		intent.putExtra("username", manager.getUsername());
		startActivityForResult(intent, RETRIEVE_REQUEST_CODE);
	}

	private void openCreateAttendantView() {
		Intent intent = new Intent(this, CreateAttendantActivity.class);
		startActivityForResult(intent, CREATE_ATTENDANT_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CREATE_ATTENDANT_REQUEST_CODE) {
			if (resultCode == RESULT_OK) {
				Attendant attendant = (Attendant) data.getSerializableExtra("attendant");
				String message = "Attendant " + attendant.getUsername() + " created";
				Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
				garage.getUserBag().addAttendant(attendant);
			}

			if (resultCode == RESULT_CANCELED) {
				Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
			}
		}

		if (requestCode == WRITE_REQUEST_CODE) {
			if (resultCode == RESULT_OK && data != null) {
				Uri uri = data.getData();
				if (uri != null) {
					saveGarage(uri);
				} else {
					Toast.makeText(this, "Could not save file", Toast.LENGTH_SHORT).show();
				}
			}
		}

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

	private void saveGarage(Uri uri) {
		try {
			ParcelFileDescriptor fileDescriptor = this.getContentResolver().openFileDescriptor(uri, "w");
			FileOutputStream fos = new FileOutputStream(fileDescriptor.getFileDescriptor());
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(garage);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
