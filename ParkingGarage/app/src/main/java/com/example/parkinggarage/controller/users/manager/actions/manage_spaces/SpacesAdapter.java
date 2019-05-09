package com.example.parkinggarage.controller.users.manager.actions.manage_spaces;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.example.parkinggarage.model.spaces.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SpacesAdapter extends RecyclerView.Adapter<SpacesAdapter.SpaceViewHolder> {

	private Queue<Space> spacesQueue;
	private List<Space> spacesList;
	private Context context;

	public SpacesAdapter(Queue<Space> spaces, Context context) {
		this.spacesQueue = spaces;
		this.spacesList = new ArrayList<>(spaces);
		this.context = context;
	}

	public List<Space> getSpacesList() {
		return spacesList;
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
	}

	private void displayDialog(Space space) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		builder.setTitle("Edit Space");
		builder.setMessage("Leave fields empty if you don't want to change them");

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
		});

		builder.setNegativeButton("Cancel", (dialog, which) -> {
		});

		builder.show();
	}

	@Override
	public int getItemCount() {
		return spacesList.size();
	}

	class SpaceViewHolder extends RecyclerView.ViewHolder {

		private TextView leftText, rightText;

		public SpaceViewHolder(@NonNull View itemView) {
			super(itemView);

			leftText = itemView.findViewById(R.id.space_details_left);
			rightText = itemView.findViewById(R.id.space_details_right);
		}

		public TextView getLeftText() {
			return leftText;
		}

		public TextView getRightText() {
			return rightText;
		}
	}
}
