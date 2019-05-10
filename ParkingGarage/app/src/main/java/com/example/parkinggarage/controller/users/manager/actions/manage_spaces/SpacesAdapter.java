package com.example.parkinggarage.controller.users.manager.actions.manage_spaces;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.parkinggarage.R;
import com.example.parkinggarage.model.spaces.CarSpace;
import com.example.parkinggarage.model.spaces.MotorcycleSpace;
import com.example.parkinggarage.model.spaces.Space;
import com.example.parkinggarage.model.spaces.SpaceBag;
import com.example.parkinggarage.model.spaces.TruckSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SpacesAdapter extends RecyclerView.Adapter<SpacesAdapter.SpaceViewHolder> {

	private String spaceType;
	private Queue<Space> spacesQueue;
	private List<Space> spacesList;
	private Context context;
	private FloatingActionButton fab;
	private SpaceBag spaceBag;

	SpacesAdapter(Queue<Space> spaces, Context context, FloatingActionButton fab, String spaceType, SpaceBag spaceBag) {
		this.spacesQueue = spaces;
		this.spacesList = new ArrayList<>(spaces);
		this.context = context;
		this.fab = fab;
		this.spaceType = spaceType;
		this.spaceBag = spaceBag;
	}

	@NonNull
	@Override
	public SpaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		Context context = viewGroup.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);

		View spaceView = inflater.inflate(R.layout.layout_space_item, viewGroup, false);

		return new SpaceViewHolder(spaceView);
	}

	@Override
	public void onBindViewHolder(@NonNull SpaceViewHolder viewHolder, int i) {
		Space space = spacesList.get(i);

		String distance = "Distance to exit: " + space.getDistanceToExit();
		String occupied = space.isOccupied() ? "Occupied" : "Empty";
		String rate = "Rate: $" + space.getRate() + "/hr";
		String earlyBird = "Early Bird Price: $" + space.getEarlyBirdPrice();

		viewHolder.getLeftText().setText(distance + '\n' + rate);
		viewHolder.getRightText().setText(occupied + '\n' + earlyBird);

		viewHolder.itemView.setOnClickListener(v -> displayDialog(space));

		fab.setOnClickListener(v -> {
			Space newSpace = null;

			switch (spaceType) {
				case "motorcycle":
					newSpace = new MotorcycleSpace(spaceBag.getMotorcycleRate(), spaceBag.getMotorcycleEarlyBird());
					break;
				case "car":
					newSpace = new CarSpace(spaceBag.getCarRate(), spaceBag.getCarEarlyBird());
					break;
				case "truck":
					newSpace = new TruckSpace(spaceBag.getTruckRate(), spaceBag.getTruckEarlyBird());
					break;

			}

			spacesList.add(newSpace);
			spacesQueue.add(newSpace);
			notifyDataSetChanged();
		});
	}

	private void displayDialog(Space space) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		builder.setTitle("Edit Space");
		builder.setMessage("Leave fields empty if you wish to leave them unchanged. Click remove to remove the space from the garage. Click confirm to confirm changes.");

		LinearLayout view = new LinearLayout(context);
		EditText rateField = new EditText(context);
		EditText earlyBirdField = new EditText(context);

		rateField.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		rateField.setHint("Rate");

		earlyBirdField.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		earlyBirdField.setHint("Early Bird Price");

		view.setOrientation(LinearLayout.VERTICAL);
		view.setPadding(20, 20, 20, 20);
		view.addView(rateField);
		view.addView(earlyBirdField);

		builder.setView(view);

		builder.setPositiveButton("Confirm", (dialog, which) -> {
			double rate, earlyBirdPrice;

			if (TextUtils.isEmpty(rateField.getText().toString())) {
				rate = space.getRate();
			} else {
				rate = Double.parseDouble(rateField.getText().toString());
			}

			if (TextUtils.isEmpty(earlyBirdField.getText().toString())) {
				earlyBirdPrice = space.getEarlyBirdPrice();
			} else {
				earlyBirdPrice = Double.parseDouble(earlyBirdField.getText().toString());
			}

			space.setRate(rate);
			space.setEarlyBirdPrice(earlyBirdPrice);
			notifyDataSetChanged();
			dialog.dismiss();
		});

		builder.setNegativeButton("Remove", (dialog, which) -> {
			spacesList.remove(space);
			spacesQueue.remove(space);
			notifyDataSetChanged();
			dialog.dismiss();
		});

		builder.show();
	}

	@Override
	public int getItemCount() {
		return spacesList.size();
	}

	class SpaceViewHolder extends RecyclerView.ViewHolder {

		private TextView leftText, rightText;

		SpaceViewHolder(@NonNull View itemView) {
			super(itemView);

			leftText = itemView.findViewById(R.id.space_details_left);
			rightText = itemView.findViewById(R.id.space_details_right);
		}

		TextView getLeftText() {
			return leftText;
		}

		TextView getRightText() {
			return rightText;
		}
	}
}
