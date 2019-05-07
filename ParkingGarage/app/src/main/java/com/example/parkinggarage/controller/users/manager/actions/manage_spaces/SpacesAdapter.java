package com.example.parkinggarage.controller.users.manager.actions.manage_spaces;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parkinggarage.R;
import com.example.parkinggarage.model.spaces.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SpacesAdapter extends RecyclerView.Adapter<SpacesAdapter.ViewHolder> {

	private Queue<Space> spacesQueue;
	private List<Space> spacesList;

	public SpacesAdapter(Queue<Space> spaces) {
		this.spacesQueue = spaces;
		this.spacesList = new ArrayList<>(spaces);
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		Context context = viewGroup.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);

		View spaceView = inflater.inflate(R.layout.layout_space_item, viewGroup, false);

		return new ViewHolder(spaceView);
	}

	@Override
	public void onBindViewHolder(@NonNull SpacesAdapter.ViewHolder viewHolder, int i) {
		Space space = spacesList.get(i);

		String distance = "Distance to exit: " + space.getDistanceToExit();
		String occupied = space.isOccupied() ? "Occupied" : "Empty";
		String rate = "Rate: $" + space.getRate() + "/hr";
		String earlyBird = "Early Bird Price: $" + space.getEarlyBirdPrice();

		viewHolder.getLeftText().setText(distance + '\n' + rate);
		viewHolder.getRightText().setText(occupied + '\n' + earlyBird);
	}

	@Override
	public int getItemCount() {
		return spacesList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		private TextView leftText, rightText;

		public ViewHolder(@NonNull View itemView) {
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
