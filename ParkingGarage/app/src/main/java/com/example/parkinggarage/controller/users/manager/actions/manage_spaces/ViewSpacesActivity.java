package com.example.parkinggarage.controller.users.manager.actions.manage_spaces;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.parkinggarage.R;
import com.example.parkinggarage.model.garage.SingletonGarage;
import com.example.parkinggarage.model.spaces.SpaceBag;

public class ViewSpacesActivity extends AppCompatActivity {

	private SpaceBag spaceBag;
	private RecyclerView recyclerView;
	private SpacesAdapter spacesAdapter;

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
			= item -> {
		switch (item.getItemId()) {
			case R.id.navigation_motorcycle_spaces:
				recyclerView.setAdapter(new SpacesAdapter(spaceBag.getMotorcycleSpaces(), this));
				return true;
			case R.id.navigation_car_spaces:
				recyclerView.setAdapter(new SpacesAdapter(spaceBag.getCarSpaces(), this));
				return true;
			case R.id.navigation_truck_spaces:
				recyclerView.setAdapter(new SpacesAdapter(spaceBag.getTruckSpaces(), this));
				return true;
		}
		return false;
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_view_spaces);
		BottomNavigationView navView = findViewById(R.id.attendant_nav_view);
		navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

		spaceBag = SingletonGarage.getGarage().getSpaceBag();
		recyclerView = findViewById(R.id.activity_manage_spaces_recyclerView);

		spacesAdapter = new SpacesAdapter(spaceBag.getMotorcycleSpaces(), this);
		recyclerView.setAdapter(spacesAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		Toolbar toolbar = findViewById(R.id.manage_spaces_toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle("Manage Spaces");
	}

}
