package com.example.parkinggarage.controller.users.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.SignInActivity;
import com.example.parkinggarage.controller.users.attendant.actions.ParkFragment;
import com.example.parkinggarage.controller.users.attendant.actions.RetrieveFragment;
import com.example.parkinggarage.controller.users.manager.actions.CreateAttendantFragment;
import com.example.parkinggarage.controller.users.manager.actions.manage_spaces.ManageSpacesActivity;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.garage.SingletonGarage;

public class ManagerActivityBottomNav extends AppCompatActivity {

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
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_manager, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case R.id.manager_sign_out:
				finish();
				return true;
			case R.id.manager_manage_spaces:
				startActivity(new Intent(this, ManageSpacesActivity.class));
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manager_bottom_nav);
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
}
