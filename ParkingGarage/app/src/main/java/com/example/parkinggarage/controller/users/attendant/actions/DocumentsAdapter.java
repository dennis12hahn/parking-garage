package com.example.parkinggarage.controller.users.attendant.actions;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.parkinggarage.R;
import com.example.parkinggarage.controller.tickets_and_receipts.TicketActivity;
import com.example.parkinggarage.model.tickets_and_receipts.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class DocumentsAdapter extends RecyclerView.Adapter<DocumentsAdapter.DocumentViewHolder> {

	private List<Document> documents;
	private Context context;

	public DocumentsAdapter(Stack<Document> documentStack, Context context) {
		this.context = context;
		this.documents = new ArrayList<>(documentStack);
	}

	@NonNull
	@Override
	public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		Context context = viewGroup.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);

		View docView = inflater.inflate(R.layout.layout_list_item, viewGroup, false);

		return new DocumentViewHolder(docView);
	}

	@Override
	public void onBindViewHolder(@NonNull DocumentViewHolder documentViewHolder, int i) {
		Document doc = documents.get(i);

		ArrayList<String> docInfo = doc.getReceiptInfo();
		String dateParked = docInfo.get(2);
		String timeParked = docInfo.get(3);
		String dateRetrieved = docInfo.get(5);
		String timeRetrieved = docInfo.get(6);

		boolean isReceipt = dateRetrieved != null;

		documentViewHolder.getLeftText().setText(dateParked + '\n' + timeParked);
		documentViewHolder.getRightText().setText(isReceipt ? dateRetrieved + '\n' + timeRetrieved : "Vehicle still parked");

		documentViewHolder.itemView.setOnClickListener(v -> {
			Intent intent = new Intent(context, TicketActivity.class);
			intent.putExtra("document", doc);
			intent.putExtra("doc_type", isReceipt ? "Receipt" : "Ticket");
			context.startActivity(intent);
		});
	}

	@Override
	public int getItemCount() {
		return documents.size();
	}

	class DocumentViewHolder extends RecyclerView.ViewHolder {

		private TextView leftText, rightText;

		public DocumentViewHolder(@NonNull View itemView) {
			super(itemView);

			leftText = itemView.findViewById(R.id.details_left);
			rightText = itemView.findViewById(R.id.details_right);
		}

		public TextView getLeftText() {
			return leftText;
		}

		public TextView getRightText() {
			return rightText;
		}
	}
}
