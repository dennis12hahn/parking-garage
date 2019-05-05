package com.example.parkinggarage.controller.users.attendant;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.users.attendant.actions.ParkFragment;
import com.example.parkinggarage.controller.users.attendant.actions.RetrieveFragment;

public class AttendantActivityBottomNav extends AppCompatActivity {

	private String username;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= item -> {
		switch (item.getItemId()) {
			case R.id.navigation_park:
				return loadFragment(ParkFragment.newInstance(username));
			case R.id.navigation_retrieve:
				return loadFragment(RetrieveFragment.newInstance(username));
		}
		return false;
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attendant_bn);
		BottomNavigationView navView = findViewById(R.id.nav_view);
		navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

		username = (String) getIntent().getSerializableExtra("attendant_username");
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
