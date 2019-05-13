package com.example.parkinggarage.controller.users.attendant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.users.attendant.actions.ParkFragment;
import com.example.parkinggarage.controller.users.attendant.actions.RetrieveFragment;
import com.example.parkinggarage.controller.users.attendant.actions.view_documents.ViewDocumentsActivity;
import com.example.parkinggarage.model.garage.SingletonGarage;

public class AttendantActivityBottomNav extends AppCompatActivity {

	private String username;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= item -> {
		switch (item.getItemId()) {
			case R.id.attendant_nav_park:
				return loadFragment(ParkFragment.newInstance(username));
			case R.id.attendant_nav_retrieve:
				return loadFragment(RetrieveFragment.newInstance(username));
		}
		return false;
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_attendant_toolbar_settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case R.id.attendant_sign_out:
				finish();
				return true;
			case R.id.attendant_view_documents:
				showDialog();
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void showDialog() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle("Enter License");
		builder.setMessage("Enter the license plate to view the vehicle's tickets and receipts.");

		EditText licenseField = new EditText(this);

		licenseField.setInputType(InputType.TYPE_CLASS_TEXT);
		licenseField.setHint("License");

		builder.setView(licenseField);

		builder.setPositiveButton("Confirm", (dialog, which) -> {

			if (TextUtils.isEmpty(licenseField.getText().toString())) {
				licenseField.setError("Enter a license plate");
			} else {
				String license = licenseField.getText().toString();

				if (SingletonGarage.getGarage().getTicketsAndReceipts().containsKey(license)) {
					dialog.dismiss();
					Intent viewDoc = new Intent(this, ViewDocumentsActivity.class);
					viewDoc.putExtra("license", license);
					startActivity(viewDoc);
				} else {
					Toast.makeText(this, "License plate not found", Toast.LENGTH_SHORT).show();
				}
			}

		});

		builder.setNegativeButton("Cancel", (dialog, which) -> {
			dialog.dismiss();
		});

		builder.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_attendant_activity_bottom_nav);
		BottomNavigationView navView = findViewById(R.id.view_spaces_nav_view);
		navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

		username = (String) getIntent().getSerializableExtra("attendant_username");

		Toolbar toolbar = findViewById(R.id.attendant_bottom_nav_toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle(username);

		loadFragment(ParkFragment.newInstance(username));
	}

	private boolean loadFragment(Fragment fragment) {
		if (fragment != null) {
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_attendant_fragmentContainer, fragment).commit();
			return true;
		}

		return false;
	}
}
