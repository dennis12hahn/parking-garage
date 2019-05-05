package com.example.parkinggarage.controller.users.manager;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.users.attendant.actions.ParkFragment;
import com.example.parkinggarage.controller.users.attendant.actions.RetrieveFragment;
import com.example.parkinggarage.controller.users.manager.actions.CreateAttendantFragment;
import com.example.parkinggarage.model.garage.Garage;
import com.example.parkinggarage.model.garage.SingletonGarage;

public class ManagerActivityBottomNav extends AppCompatActivity {

	private String username;
	private Garage garage;

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
		setContentView(R.layout.activity_manager_bottom_nav);
		BottomNavigationView navView = findViewById(R.id.manager_nav_view);
		navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

		garage = SingletonGarage.getGarage();
		username = garage.getManager().getUsername();
		setTitle(username);
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
