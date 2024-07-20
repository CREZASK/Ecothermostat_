package de.fh_zwickau.heatsc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class page_learn__activity extends Activity {

	private TextView learnWithUsTextView;
	private ImageView globeIconImageView;
	private TextView informationTextView;
	private ImageView homeNav;
	private ImageView statusNav;
	private ImageView statisticsNav;
	private ImageView settingsNav;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_learn_);


		learnWithUsTextView = findViewById(R.id.learn_with_us);
		globeIconImageView = findViewById(R.id.globe_icon);
		informationTextView = findViewById(R.id.information_text);

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
