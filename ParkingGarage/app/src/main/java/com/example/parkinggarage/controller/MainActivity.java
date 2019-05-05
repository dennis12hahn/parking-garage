package com.example.parkinggarage.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.create_garage.CreateGarageActivity;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.garage.SingletonGarage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {

	private static final int READ_REQUEST_CODE = 44;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setTitle("Parking Garage");

		Button createGarageBtn = findViewById(R.id.activity_main_createGarageBtn);
		Button loadGarageBtn = findViewById(R.id.activity_main_loadGarageBtn);

		createGarageBtn.setOnClickListener(v -> openCreateGarageActivity());

		loadGarageBtn.setOnClickListener(v -> {
			Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
			chooseFile.setType("*/garage");
			chooseFile = Intent.createChooser(chooseFile, "Load file");
			startActivityForResult(chooseFile, READ_REQUEST_CODE);
		});
	}

	private void openCreateGarageActivity() {
		Intent intent = new Intent(this, CreateGarageActivity.class);
		finish();
		startActivity(intent);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == READ_REQUEST_CODE) {
			if (resultCode == RESULT_OK && data != null) {
				Uri uri = data.getData();
				if (uri != null) {
					loadGarage(uri);
					Intent intent = new Intent(this, SignInActivity.class);
					finish();
					startActivity(intent);
				} else {
					Toast.makeText(this, "Could not load file", Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	private void loadGarage(Uri uri) {
		try {
			ParcelFileDescriptor fileDescriptor = this.getContentResolver().openFileDescriptor(uri, "r");
			FileInputStream fis = new FileInputStream(fileDescriptor.getFileDescriptor());
			ObjectInputStream ois = new ObjectInputStream(fis);
			SingletonGarage.setGarage((Garage) ois.readObject());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
