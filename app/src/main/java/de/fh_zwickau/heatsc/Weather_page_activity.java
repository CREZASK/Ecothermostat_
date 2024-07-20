
	 
	/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		page_6_re
	 *	@date 		Tuesday 11th of June 2024 03:31:07 PM
	 *	@title 		Page 1
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.figma
	 *
	 */
	

package de.fh_zwickau.heatsc;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

	public class Weather_page_activity extends Activity {


		TextView temp;
		TextView temp_min;
		TextView temp_max;
		TextView name;
		Button search;
		String default_loc;
		String location;
		TextView weather_condition;
		TextView humidity;
		TextView fl_temp;
		TextView wind_spd;
		EditText weather_loc;
		String sunriseTime;
		String sunsetTime;
		String weather_location;

		private PreferencesWeather preferencesWeather;

		@Override
		public void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.weather_page);

			preferencesWeather = new PreferencesWeather(this);

			search = findViewById(R.id.search);
			default_loc = getString(R.string.default_loc);

			weather_location = preferencesWeather.getWeatherLocation("city");

            location = weather_location;
			new GetWeatherTask().execute();
            search.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					weather_loc = findViewById(R.id.weather_loc);
					location = weather_loc.getText().toString().trim();
					if (!location.isEmpty()) {
						new GetWeatherTask().execute();
						preferencesWeather.saveWeatherLocation("city", location);
					} else {
						Log.e(TAG, "Location input is empty.");
					}
				}
			});

			TextView timeTextView = findViewById(R.id.weather_time);


			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				String currentTime = now.format(formatter);
				timeTextView.setText(currentTime);
			}

			//custom code goes here

		}

		public void onHomeClick(View view) {
			// Handle the click event here
			Intent intent = new Intent(getApplicationContext(), Home_page_activity.class);
			startActivity(intent);
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

		private class GetWeatherTask extends AsyncTask<Void, Void, HashMap<String, String>> {

			@Override
			protected HashMap<String, String> doInBackground(Void... voids) {
				HttpHandler sh = new HttpHandler();
				// Making a request to url and getting response
				String url = "https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=52272f64493a4c03447ca4be226228e0&units=metric";
				String jsonStr = sh.makeServiceCall(url);
				Log.d(TAG, "Weather data: " + jsonStr);

				HashMap<String, String> weather = new HashMap<>();

				if (jsonStr != null) {
					try {
						JSONObject jsonObj = new JSONObject(jsonStr);

						JSONArray weatherArray = jsonObj.getJSONArray("weather");

						JSONObject weatherObj = weatherArray.getJSONObject(0);
						// Getting JSON Array node

						Log.d(TAG, "Weather data: " + weatherObj);

						String weather_condition = weatherObj.getString("description");
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
						String humidity = weatherObj1.getString("humidity");
						String fl_temp = weatherObj1.getString("feels_like");

						weather.put("temp", temp + "°C");
						weather.put("temp_min", temp_min + "°C / ");
						weather.put("temp_max", temp_max + "°C");
						weather.put("humidity", humidity + "%");
						weather.put("feels_like", fl_temp + "°C");

						// adding weather to weather list

					} catch (JSONException e) {
						Log.e(TAG, "JSON parsing error: " + e.getMessage());
					}
					try {
						JSONObject jsonObj1 = new JSONObject(jsonStr);

						JSONObject weatherObj1 = jsonObj1.getJSONObject("wind");
						// Getting JSON Array node

						Log.d(TAG, "Weather data: " + weatherObj1);
						// looping through all WeatherArray entries

						String temp = weatherObj1.getString("speed");

						weather.put("speed", temp + "m/s");

						// adding weather to weather list

					} catch (JSONException e) {
						Log.e(TAG, "JSON parsing error: " + e.getMessage());
					}
					try {
						JSONObject jsonObj2 = new JSONObject(jsonStr);

						String name = jsonObj2.getString("name");
						weather.put("name", name);


						// adding weather to weather list

					} catch (JSONException e) {
						Log.e(TAG, "JSON parsing error: " + e.getMessage());
					}
					try {
						JSONObject jsonObject = new JSONObject(jsonStr);
						JSONObject sysObject = jsonObject.getJSONObject("sys");

						long sunriseTimestamp = sysObject.getLong("sunrise");
						long sunsetTimestamp = sysObject.getLong("sunset");

						sunriseTime = convertUnixToHumanReadable(sunriseTimestamp);
						sunsetTime = convertUnixToHumanReadable(sunsetTimestamp);

						weather.put("sunrisetime", sunriseTime);
						weather.put("sunsettime", sunsetTime);

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
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
					name = findViewById(R.id.weather_location);
					weather_condition = findViewById(R.id.weather_status);
					humidity = findViewById(R.id.humidity);
					fl_temp = findViewById(R.id.fl_temperature);
					wind_spd = findViewById(R.id.wind_speed);
					TextView sunriseTextView = findViewById(R.id.sunrise_time);
					TextView sunsetTextView = findViewById(R.id.sunset_time);
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
						temp.setText(weather.getOrDefault("temp", "Default"));
						name.setText(weather.getOrDefault("name", "Default"));
						weather_condition.setText(weather.getOrDefault("weather_condition", "Default"));
						temp_min.setText(weather.getOrDefault("temp_min", "Default"));
						temp_max.setText(weather.getOrDefault("temp_max", "Default"));
						humidity.setText(weather.getOrDefault("humidity", "Default"));
						fl_temp.setText(weather.getOrDefault("feels_like", "Default"));
						wind_spd.setText(weather.getOrDefault("speed", "Default"));
						sunriseTextView.setText(weather.getOrDefault("sunrisetime", "Default"));
						sunsetTextView.setText(weather.getOrDefault("sunsettime", "Default"));
					}
				}
			}

		}

		private String convertUnixToHumanReadable(long unixSeconds) {
			Date date = new Date(unixSeconds * 1000L); // Convert seconds to milliseconds
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			return sdf.format(date);
		}

		public static class PreferencesWeather {

			private static final String PREF_NAME = "MyAppPreferences";
			private final SharedPreferences sharedPreferences;

			public PreferencesWeather(Context context) {
				sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
			}

			public void saveWeatherLocation(String key, String location) {
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString(key, location);
				editor.apply();
			}

			public String getWeatherLocation(String loc) {
				return sharedPreferences.getString(loc, "Zwickau");

			}
		}

	}
	
	