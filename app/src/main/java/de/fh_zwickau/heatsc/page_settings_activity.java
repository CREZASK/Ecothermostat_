package de.fh_zwickau.heatsc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class page_settings_activity extends Activity {

	private View _bg__page_settings;
	private ImageView vector_7;
	private ImageView vector;
	private ImageView vector_ek1;
	private ImageView vector_ek2;
	private ImageView vector_ek3;
	private ImageView vector_6;
	private ImageView vector_ek4;
	private ImageView vector_ek5;
	private ImageView vector_ek6;
	private ImageView vector_ek7;
	private ImageView vector_ek8;
	private ImageView vector_ek9;
	private View rectangle_19;
	private View rectangle_20;
	private View rectangle_21;
	private View rectangle_23;
	private TextView settings;
	private TextView arnold_schwarzenegger;
	private TextView faq__frequently_asked_questions_;
	private TextView notifications;
	private TextView arnold_schwar_gmail_com;
	private TextView learn_with_us;
	private TextView logout;
	private ImageView ellipse_9;
	private ImageView vector_ek10;
	private ImageView background;
	private ImageView knob;
	private ImageView _124666_200_1;
	private ImageView union;
	private ImageView vector_ek11;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_settings);
	}

	public void onFAQClick(View view) {
		Intent intent = new Intent(getApplicationContext(), page_faq_activity.class);
		startActivity(intent);
	}

	public void onLearnClick(View view) {
		Intent intent = new Intent(getApplicationContext(), page_learn__activity.class);
		startActivity(intent);
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

	public void onNotificationsClick(View view) {
		Intent intent = new Intent(getApplicationContext(), page_notif_activity.class);
		startActivity(intent);
	}
}
