package com.example.parkinggarage.controller.users.manager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.users.attendant.actions.ParkFragment;
import com.example.parkinggarage.controller.users.attendant.actions.RetrieveFragment;
import com.example.parkinggarage.controller.users.manager.actions.CreateAttendantFragment;
import com.example.parkinggarage.controller.users.manager.actions.manage_spaces.ViewSpacesActivity;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.garage.SingletonGarage;
import com.example.parkinggarage.model.utils.SingletonIncrementalDataContainer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ManagerActivityBottomNav extends AppCompatActivity {

	private static final int WRITE_REQUEST_CODE = 43;
	private String username;
	private Garage garage;
	private Toolbar toolbar;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= item -> {
		switch (item.getItemId()) {
			case R.id.manager_nav_park:
				return loadFragment(ParkFragment.newInstance(username));
			case R.id.manager_nav_retrieve:
				return loadFragment(RetrieveFragment.newInstance(username));
			case R.id.manager_nav_create_attendant:
				return loadFragment(CreateAttendantFragment.newInstance());
		}
		return false;
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_manager_activity_bottom_nav);
		BottomNavigationView navView = findViewById(R.id.manager_nav_view);
		navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
		toolbar = findViewById(R.id.blank_toolbar);
		setSupportActionBar(toolbar);

		garage = SingletonGarage.getGarage();
		username = garage.getManager().getUsername();

		Toolbar toolbar = findViewById(R.id.manager_bottom_nav_toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle(username);

		loadFragment(ParkFragment.newInstance(username));
	}

	private boolean loadFragment(Fragment fragment) {
		if (fragment != null) {
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_manager_fragmentContainer, fragment).commit();
			return true;
		}

		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_manager_toolbar_settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case R.id.manager_sign_out:
				finish();
				return true;
			case R.id.manager_manage_spaces:
				startActivity(new Intent(this, ViewSpacesActivity.class));
				return true;
			case R.id.manager_save_garage:
				Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
				intent.addCategory(Intent.CATEGORY_OPENABLE);
				intent.setType("*/garage");
				intent.putExtra(Intent.EXTRA_TITLE, "garage.dat");
				startActivityForResult(intent, WRITE_REQUEST_CODE);
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

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
			oos.writeObject(garage);
			oos.writeObject(SingletonIncrementalDataContainer.getDataContainer());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
