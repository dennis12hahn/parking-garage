package com.example.parkinggarage.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.GarageController;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Attendant;

import java.util.Arrays;

public class AttendantActivity extends AppCompatActivity {

    private Attendant attendant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendant);

        String username = (String) getIntent().getSerializableExtra("attendant_username");
        attendant = (Attendant) GarageController.getGarage().getUserBag().getUser(username);

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
            openExitActivity();
        });

    }

    private void openParkActivity() {
        Intent intent = new Intent(this, ParkActivity.class);
        intent.putExtra("attendant_username", attendant.getUsername());
        startActivityForResult(intent, 1);
    }

    private void openExitActivity() {
        Intent intent = new Intent(this, RetrieveActivity.class);
        intent.putExtra("attendant_username", attendant.getUsername());
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                Document doc = (Document) data.getSerializableExtra("document");
                Log.v("DOCUMENT", Arrays.toString(doc.getTicketInfo()));
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK && data != null) {
                Document doc = (Document) data.getSerializableExtra("document");
                Log.v("DOCUMENT", Arrays.toString(doc.getReceiptInfo()));
            }
        }
    }
}
