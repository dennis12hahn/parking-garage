package com.example.parkinggarage.controller.users.attendant;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.users.attendant.actions.ParkFragment;
import com.example.parkinggarage.controller.users.attendant.actions.RetrieveFragment;

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
		getMenuInflater().inflate(R.menu.menu_attendant, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case R.id.attendant_sign_out:
				finish();
				return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attendant_bottom_nav);
		BottomNavigationView navView = findViewById(R.id.attendant_nav_view);
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
