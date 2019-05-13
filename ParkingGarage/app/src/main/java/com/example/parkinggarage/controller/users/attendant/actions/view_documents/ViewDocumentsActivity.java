package com.example.parkinggarage.controller.users.attendant.actions.view_documents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.parkinggarage.R;
import com.example.parkinggarage.model.garage.SingletonGarage;
import com.example.parkinggarage.model.tickets_and_receipts.Document;

import java.util.Stack;

public class ViewDocumentsActivity extends AppCompatActivity {

	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_documents);

		String license = getIntent().getStringExtra("license");

		Toolbar toolbar = findViewById(R.id.view_documents_toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle("Tickets & Receipts for " + license);

		recyclerView = findViewById(R.id.view_documents_recyclerView);

		Stack<Document> documentStack = SingletonGarage.getGarage().getTicketsAndReceipts().get(license);
		DocumentsAdapter documentsAdapter = new DocumentsAdapter(documentStack, this);
		recyclerView.setAdapter(documentsAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

	}
}
