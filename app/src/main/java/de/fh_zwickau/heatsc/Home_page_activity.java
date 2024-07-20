
package de.fh_zwickau.heatsc;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Home_page_activity extends AppCompatActivity {

	 TextView temp;
	 TextView temp_min;
	 TextView temp_max;
	 TextView name;
	TextView weather_condition;
	String location;

	SwitchCompat bed1_switch;
	SwitchCompat bed2_switch;
	SwitchCompat bath_switch;
	SwitchCompat office_switch;
	SwitchCompat liv_switch;
	SwitchCompat kitchen_switch;

	boolean switchState_bed1;
	boolean switchState_bed2;
	boolean switchState_bath;
	boolean switchState_office;
	boolean switchState_liv;
	boolean switchState_kitchen;


	public Weather_page_activity.PreferencesWeather preferencesWeather;
	private PreferencesHelper preferenceHelper;

		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.home_page);

			preferencesWeather = new Weather_page_activity.PreferencesWeather(this);
			preferenceHelper = new PreferencesHelper(this);

			location = preferencesWeather.getWeatherLocation("city");

			//custom code goes here
			new GetWeatherTask().execute();

			switchState_bed1 = preferenceHelper.getSwitchState("switchState_bed1");

			bed1_switch = findViewById(R.id.bed1_switch);

			bed1_switch.setChecked(switchState_bed1);

			bed1_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
				if (isChecked) {
					preferenceHelper.saveSwitchState("switchState_bed1", bed1_switch.isChecked());
				} else {
					preferenceHelper.saveSwitchState("switchState_bed1", false);
				}
			});

			switchState_bed2 = preferenceHelper.getSwitchState("switchState_bed2");

			bed2_switch = findViewById(R.id.bed2_switch);

			bed2_switch.setChecked(switchState_bed2);

			bed2_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
				if (isChecked) {
					preferenceHelper.saveSwitchState("switchState_bed2", bed2_switch.isChecked());
				} else {
					preferenceHelper.saveSwitchState("switchState_bed2", false);
				}
			});

			switchState_bath = preferenceHelper.getSwitchState("switchState_bath");

			bath_switch = findViewById(R.id.bath_switch);

			bath_switch.setChecked(switchState_bath);

			bath_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
				if (isChecked) {
					preferenceHelper.saveSwitchState("switchState_bath", bath_switch.isChecked());
				} else {
					preferenceHelper.saveSwitchState("switchState_bath", false);
				}
			});

			switchState_office = preferenceHelper.getSwitchState("switchState_office");

			office_switch = findViewById(R.id.office_switch);

			office_switch.setChecked(switchState_office);

			office_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
				if (isChecked) {
					preferenceHelper.saveSwitchState("switchState_office", office_switch.isChecked());
				} else {
					preferenceHelper.saveSwitchState("switchState_office", false);
				}
			});

			switchState_liv = preferenceHelper.getSwitchState("switchState_liv");

			liv_switch = findViewById(R.id.liv_switch);

			liv_switch.setChecked(switchState_liv);

			liv_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
				if (isChecked) {
					preferenceHelper.saveSwitchState("switchState_liv", liv_switch.isChecked());
				} else {
					preferenceHelper.saveSwitchState("switchState_liv", false);
				}
			});

			switchState_kitchen = preferenceHelper.getSwitchState("switchState_kitchen");

			kitchen_switch = findViewById(R.id.kitchen_switch);

			kitchen_switch.setChecked(switchState_kitchen);

			kitchen_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
				if (isChecked) {
					preferenceHelper.saveSwitchState("switchState_kitchen", kitchen_switch.isChecked());
				} else {
					preferenceHelper.saveSwitchState("switchState_kitchen", false);
				}
			});

		}

		public void onStatusClick(View view) {
			// Handle the click event here
			Intent intent = new Intent(getApplicationContext(), status_page_activity.class);
			startActivity(intent);
		}

		public void onStatisticsClick(View view) {
			// Handle the click event here
			Intent intent = new Intent(getApplicationContext(), statistics_pagedaily_Activity.class);
			startActivity(intent);
		}

		public void onSettingsClick(View view) {
		// Handle the click event here
			Intent intent = new Intent(getApplicationContext(), page_settings_activity.class);
			startActivity(intent);
		}

		// For weather button or frame click
		public void onFrameClick(View view) {
			// Handle the click event here
			Intent intent = new Intent(getApplicationContext(), Weather_page_activity.class);
			startActivity(intent);
		}

		public void onbed_1_Click(View view) {
			if (bed1_switch.isChecked()) {
				preferenceHelper.saveSwitchState("switchState_bed1", bed1_switch.isChecked());
				Intent intent = new Intent(getApplicationContext(), bedroom_1_activity.class);
				startActivity(intent);
			} else {
				Toast.makeText(this, "The room is turned off", Toast.LENGTH_SHORT).show();
							}
			// Handle the click event here

		}

		public void onbed_2_Click(View view) {
			if (bed2_switch.isChecked()) {
				preferenceHelper.saveSwitchState("switchState_bed2", bed2_switch.isChecked());
				Intent intent = new Intent(getApplicationContext(), bedroom_2_activity.class);
				startActivity(intent);
		} else {
				Toast.makeText(this, "The room is turned off", Toast.LENGTH_SHORT).show();
	}
		}

		public void onbathroom_Click(View view) {
			if (bath_switch.isChecked()) {
				preferenceHelper.saveSwitchState("switchState_bath", bath_switch.isChecked());
			Intent intent = new Intent(getApplicationContext(), bathroom_activity.class);
			startActivity(intent);
		} else {
				Toast.makeText(this, "The room is turned off", Toast.LENGTH_SHORT).show();
	}
		}

		public void onoffice_Click(View view) {
			if (office_switch.isChecked()) {
				preferenceHelper.saveSwitchState("switchState_office", office_switch.isChecked());
			Intent intent = new Intent(getApplicationContext(), office_activity.class);
			startActivity(intent);
		} else {
				Toast.makeText(this, "The room is turned off", Toast.LENGTH_SHORT).show();
	}
		}

		public void onliv_room_Click(View view) {
			if (liv_switch.isChecked()) {
				preferenceHelper.saveSwitchState("switchState_liv", liv_switch.isChecked());
			Intent intent = new Intent(getApplicationContext(), living_room_activity.class);
			startActivity(intent);
		} else {
				Toast.makeText(this, "The room is turned off", Toast.LENGTH_SHORT).show();
	}
		}

		public void onkitchen_Click(View view) {
			if (kitchen_switch.isChecked()) {
				preferenceHelper.saveSwitchState("switchState_kitchen", kitchen_switch.isChecked());
			Intent intent = new Intent(getApplicationContext(), kitchen_activity.class);
			startActivity(intent);
		} else {
				Toast.makeText(this, "The room is turned off", Toast.LENGTH_SHORT).show();
	}
		}

		public void on_notification_Click(View view) {
			// Handle the click event here
			Intent intent = new Intent(getApplicationContext(), page_notif_activity.class);
			startActivity(intent);
		}


private class GetWeatherTask extends AsyncTask<Void, Void, HashMap<String, String>>{

		@Override
				protected HashMap<String, String> doInBackground(Void... voids) {
		HttpHandler sh = new HttpHandler();
		// Making a request to url and getting response
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=52272f64493a4c03447ca4be226228e0&units=metric";
		String jsonStr = sh.makeServiceCall(url);
			Log.d(TAG, "Weather data: " + jsonStr);

		HashMap<String, String> weather = new HashMap<>();

		if (jsonStr != null){
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);

				JSONArray weatherArray = jsonObj.getJSONArray("weather");

					JSONObject weatherObj = weatherArray.getJSONObject(0);
					// Getting JSON Array node

					Log.d(TAG, "Weather data: " + weatherObj);
					// looping through all WeatherArray entries

					String weather_condition = weatherObj.getString("description");
					// tmp hash map for single weather entry
					// adding each child node to HashMap key => value
					weather.put("weather_condition", weather_condition);
				// adding weather to weather list

			} catch (JSONException e) {
				Log.e(TAG, "JSON parsing error: " + e.getMessage());
			}
			try {
				JSONObject jsonObj1 = new JSONObject(jsonStr);

				JSONObject weatherObj1 = jsonObj1.getJSONObject("main");
				// Getting JSON Array node

				Log.d(TAG, "Weather data: " + weatherObj1);
				// looping through all WeatherArray entries

				String temp = weatherObj1.getString("temp");
				String temp_min = weatherObj1.getString("temp_min");
				String temp_max = weatherObj1.getString("temp_max");
				//Log.d(TAG, timestamp + " ." + condition + ". " + temperature + ". " + relativeHumidity + ".");
				// tmp hash map for single weather entry
				// adding each child node to HashMap key => value
				weather.put("temp", temp + "°C");
				weather.put("temp_min", temp_min + "°C");
				weather.put("temp_max", temp_max + "°C");

				// adding weather to weather list

			} catch (JSONException e) {
				Log.e(TAG, "JSON parsing error: " + e.getMessage());
			}
			try {
				JSONObject jsonObj2 = new JSONObject(jsonStr);

				// Getting JSON Array node
				// looping through all WeatherArray entries

				String name = jsonObj2.getString("name");
				// tmp hash map for single weather entry
				// adding each child node to HashMap key => value
				weather.put("name", name);


				// adding weather to weather list

			} catch (JSONException e) {
				Log.e(TAG, "JSON parsing error: " + e.getMessage());
			}
		}else {
			Log.e(TAG, "Couldn't get json from server");
		}

		return weather;

        }

	@Override
	protected void onPostExecute(HashMap<String, String> weather) {
		if (weather != null) {
			// Log or use the weather data
			Log.d(TAG, "Weather data: " + weather.toString());
			// For example, update UI here
			temp = findViewById(R.id.temperature);
			temp_min = findViewById(R.id.temp_min);
			temp_max = findViewById(R.id.temp_max);
			name = findViewById(R.id.name);
			weather_condition = findViewById(R.id.weather_condition);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				temp.setText(weather.getOrDefault("temp", "Default"));
				name.setText(weather.getOrDefault("name", "Default"));
				temp_min.setText(weather.getOrDefault("temp_min", "Default"));
				temp_max.setText(weather.getOrDefault("temp_max", "Default"));
				weather_condition.setText(weather.getOrDefault("weather_condition", "Default"));
			}
		}
	}
}

	public static class PreferencesHelper {

		private static final String PREF_NAME = "switch_prefs";
		private final SharedPreferences sharedPreferences;
		private final SharedPreferences.Editor editor;

		public PreferencesHelper(Context context) {
			sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
			editor = sharedPreferences.edit();
		}

		public void saveSwitchState(String room, boolean state) {
			editor.putBoolean(room, state);
			editor.apply();
		}

		public boolean getSwitchState(String room) {
			return sharedPreferences.getBoolean(room, false);
		}
	}

	}

