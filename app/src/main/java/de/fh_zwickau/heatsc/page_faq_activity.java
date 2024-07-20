package de.fh_zwickau.heatsc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class page_faq_activity extends Activity {

	private ImageView homeNav;
	private ImageView statusNav;
	private ImageView statisticsNav;
	private ImageView settingsNav;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_faq);



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
