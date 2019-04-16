package com.example.parkinggarage.view;

import android.content.Intent;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.GarageController;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.users.Attendant;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ManagerActivity extends AppCompatActivity {

    private Garage garage;
    private static final int WRITE_REQUEST_CODE = 43;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        garage = GarageController.getGarage();

        Button signOutBtn = findViewById(R.id.activity_manager_signOutBtn);
        Button createAttendantBtn = findViewById(R.id.activity_manager_createAttendantBtn);
        Button saveGarageBtn = findViewById(R.id.activity_manager_saveBtn);

        setTitle(garage.getManager().getUsername());

        signOutBtn.setOnClickListener(v -> {
            finish();
        });

        createAttendantBtn.setOnClickListener(v -> {
            openCreateAttendantView();
        });

        saveGarageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/garage");
            intent.putExtra(Intent.EXTRA_TITLE, "garage.dat");
            startActivityForResult(intent, WRITE_REQUEST_CODE);
        });

    }

    private void openCreateAttendantView() {
        Intent intent = new Intent(this, CreateAttendantActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
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
    }

    private void saveGarage(Uri uri) {
        try {
            ParcelFileDescriptor fileDescriptor = this.getContentResolver().openFileDescriptor(uri, "w");
            FileOutputStream fos = new FileOutputStream(fileDescriptor.getFileDescriptor());
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(GarageController.getGarage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
