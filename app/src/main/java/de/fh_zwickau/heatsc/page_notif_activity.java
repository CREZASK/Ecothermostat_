package de.fh_zwickau.heatsc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class page_notif_activity extends Activity {

	private ImageView homeNav, statusNav, statisticsNav, settingsNav;
	private TextView notifications, alertText, tipText, errorText, maintenanceText, networkIssueText;
	private ImageView notificationIcon, alertIcon, tipIcon, errorIcon, maintenanceIcon, networkIssueIcon;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_notif);

		notifications = findViewById(R.id.notifications);
		notificationIcon = findViewById(R.id.vector_ek11);

		alertIcon = findViewById(R.id.vector_ek10);
		alertText = findViewById(R.id.notif_1);

		tipIcon = findViewById(R.id.vector_ek12);
		tipText = findViewById(R.id.textView2);

		errorIcon = findViewById(R.id.vector_ek13);
		errorText = findViewById(R.id.textView3);

		maintenanceIcon = findViewById(R.id.vector_ek14);
		maintenanceText = findViewById(R.id.textView4);

		networkIssueIcon = findViewById(R.id.vector_ek15);
		networkIssueText = findViewById(R.id.textView5);


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
