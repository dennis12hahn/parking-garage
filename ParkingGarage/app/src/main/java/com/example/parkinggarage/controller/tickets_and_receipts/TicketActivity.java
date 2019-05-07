package com.example.parkinggarage.controller.tickets_and_receipts;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.parkinggarage.R;
import com.example.parkinggarage.model.tickets_and_receipts.Document;

public class TicketActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_ticket);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show());

		Document doc = (Document) getIntent().getSerializableExtra("document");
		String docType = (String) getIntent().getSerializableExtra("doc_type");

		getSupportActionBar().setTitle(docType);

		TextView contentView = findViewById(R.id.ticket_activity_content_textView);
		StringBuilder content = new StringBuilder();

		if (docType.equals("Ticket")) {
			for (int i = 0; i < doc.getTicketInfo().size(); i++) {
				content.append(doc.getTicketInfo().get(i)).append("\n");
			}
		}

		if (docType.equals("Receipt")) {
			for (int i = 0; i < doc.getReceiptInfo().size(); i++) {
				content.append(doc.getReceiptInfo().get(i)).append("\n");
			}
		}

		contentView.setText(content.toString());

	}
}
