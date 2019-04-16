package com.example.parkinggarage.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.GarageController;
import com.example.parkinggarage.model.tickets_and_receipts.Document;
import com.example.parkinggarage.model.users.Attendant;

public class RetrieveActivity extends AppCompatActivity {

    private EditText licenseField, paymentField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        setTitle("Retrieve");

        String username = (String) getIntent().getSerializableExtra("attendant_username");
        Attendant attendant = (Attendant) GarageController.getGarage().getUserBag().getUser(username);

        licenseField = findViewById(R.id.activity_retrieve_licenseField);
        paymentField = findViewById(R.id.activity_retrieve_paymentField);
        Button retrieveBtn = findViewById(R.id.activity_retrieve_retrieveBtn);

        retrieveBtn.setOnClickListener(v -> {
            if (checkFields()) {
                String license = licenseField.getText().toString();
                double payment = Double.parseDouble(paymentField.getText().toString());
                Document doc = attendant.retrieve(license, GarageController.getGarage(), payment);

                if (doc == null) {
                    Toast.makeText(this, "Vehicle not found", Toast.LENGTH_SHORT).show();
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("document", doc);
                    setResult(RESULT_OK, resultIntent);
                    finish();
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
