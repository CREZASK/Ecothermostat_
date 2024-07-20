package de.fh_zwickau.heatsc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class statistics_pagedaily_Activity extends AppCompatActivity {

	Button W_button;
	Button M_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistics_pagedaily);

		W_button = findViewById(R.id._1W);
		M_button = findViewById(R.id._1M);

		W_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), statistics_pageweekly_Activity.class);
				startActivity(intent);
			}
		});

		M_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), statistics_pagemonthly_Activity.class);
				startActivity(intent);
			}
		});

	}

	public void onHomeClick(View view) {

		Intent intent = new Intent(getApplicationContext(), Home_page_activity.class);
		startActivity(intent);
	}

	public void onStatusClick(View view) {

		Intent intent = new Intent(getApplicationContext(), status_page_activity.class);
		startActivity(intent);
	}

	public void onStatisticsClick(View view) {

		Intent intent = new Intent(getApplicationContext(), statistics_pagedaily_Activity.class);
		startActivity(intent);
	}

	public void onSettingsClick(View view) {

		Intent intent = new Intent(getApplicationContext(), page_settings_activity.class);
		startActivity(intent);
	}
}