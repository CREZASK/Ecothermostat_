package de.fh_zwickau.heatsc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class bathroom_activity extends AppCompatActivity {

    TextView bathroom_temp;
    TextView currenttemp;

    Button plus;
    Button minus;
    SwitchCompat timer_switch;
    TextView auto_on;
    TextView set_time;
    EditText auto_off_time;
    EditText set_timinhrs;

    Integer bathroomTemperature;
    String currenttempText;
    int currentTemperature;
    boolean switchState;

    private PreferencesHelper preferencesHelper;
    private SharedPreferences sharedPreferences;

    private static final int TEMP_THRESHOLD = 24;
    private static final int UPDATE_INTERVAL = 5000;

    private Handler handler;
    private Runnable temperatureUpdater;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bathroom);

        bathroom_temp = findViewById(R.id.bathroom_temp);
        currenttemp = findViewById(R.id.current_temp);
        timer_switch = findViewById(R.id.timer_switch);
        auto_on = findViewById(R.id.auto_on);
        auto_off_time = findViewById(R.id.auto_on_time);
        set_time = findViewById(R.id.set_time);
        set_timinhrs = findViewById(R.id.set_timeinhrs);

        preferencesHelper = new PreferencesHelper(this);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        bathroomTemperature = preferencesHelper.getTemperatureSetting("bathroom");
        currentTemperature = preferencesHelper.getTemperatureSetting("bathroom_curr_temp");
        switchState = sharedPreferences.getBoolean("switchState", false);

                updateDesiredDisplay();
        updateTemperatureDisplay();
        timer_switch.setChecked(switchState);

        if (switchState) {
            fadeInViews(auto_on, auto_off_time, set_time, set_timinhrs);
        } else {
            fadeOutViews(auto_on, auto_off_time, set_time, set_timinhrs);
        }


        Button btnsave = findViewById(R.id.save);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("switchState", timer_switch.isChecked());
                editor.apply();
                if (timer_switch.isChecked()) {
                    fadeInViews(auto_on, auto_off_time, set_time, set_timinhrs);
                } else {
                    fadeOutViews(auto_on, auto_off_time, set_time, set_timinhrs);
                }



                handler = new Handler();
                temperatureUpdater = new Runnable() {
                    @Override
                    public void run() {
                         currenttempText= currenttemp.getText().toString();
                         currentTemperature = Integer.parseInt(currenttempText.replaceAll("\\D", ""));
                        if (currentTemperature < bathroomTemperature) {
                                incrementTemperature();
                                updateTemperatureDisplay();
                                handler.postDelayed(this, UPDATE_INTERVAL);
                            preferencesHelper.saveTemperatureSetting("bathroom_curr_temp", currentTemperature);

                    }else if (currentTemperature == bathroomTemperature) {
                            updateTemperatureDisplay();
                            preferencesHelper.saveTemperatureSetting("bathroom_curr_temp", currentTemperature);

                        } else {
                            decrementTemperature();
                            updateTemperatureDisplay();
                            handler.postDelayed(this, UPDATE_INTERVAL);
                            preferencesHelper.saveTemperatureSetting("bathroom_curr_temp", currentTemperature);
                        }
                    }
                };


                handler.postDelayed(temperatureUpdater, UPDATE_INTERVAL);
            }
        });

        plus = findViewById(R.id.plus_symbol);
        plus.setOnClickListener(v -> {
            bathroomTemperature = Integer.parseInt(bathroom_temp.getText().toString().replace("°C", "").trim());
            bathroomTemperature++;
            updateDesiredDisplay();
            preferencesHelper.saveTemperatureSetting("bathroom", bathroomTemperature);
        });

        minus = findViewById(R.id.minus_symbol);
        minus.setOnClickListener(v -> {
            bathroomTemperature = Integer.parseInt(bathroom_temp.getText().toString().replace("°C", "").trim());
            bathroomTemperature--;
            updateDesiredDisplay();
            preferencesHelper.saveTemperatureSetting("bathroom", bathroomTemperature);
        });







        timer_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                fadeInViews(auto_on, auto_off_time, set_time, set_timinhrs);
            } else {
                fadeOutViews(auto_on, auto_off_time, set_time, set_timinhrs);
            }
        });

    }


    private void incrementTemperature() {
        currentTemperature++;
    }

    private void decrementTemperature() {
        currentTemperature--;
    }

    private void updateDesiredDisplay() {
        bathroom_temp.setText(String.valueOf(bathroomTemperature));
        if (bathroomTemperature > TEMP_THRESHOLD) {
            if (plus != null) {
                plus.setBackgroundResource(R.drawable.plus_red);
            }
            if (minus != null) {
                minus.setBackgroundResource(R.drawable.minus_red);
            }
        } else if (bathroomTemperature == TEMP_THRESHOLD) {
            if (plus != null) {
                plus.setBackgroundResource(R.drawable.plus_red);
            }
            if (minus != null) {
                minus.setBackgroundResource(R.drawable.minus_blue);
            }
        } else {
            if (plus != null) {
                plus.setBackgroundResource(R.drawable.plus_blue);
            }
            if (minus != null) {
                minus.setBackgroundResource(R.drawable.minus_blue);
            }
        }
    }

    private void updateTemperatureDisplay() {
        String temperatureFormat = getString(R.string.temperature_format, currentTemperature);
        currenttemp.setText(temperatureFormat);

        if (bathroomTemperature > TEMP_THRESHOLD) {
            currenttemp.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            currenttemp.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
        }
    }

    public static class PreferencesHelper {

        private static final String PREF_NAME = "MyAppPreferences";
        private final SharedPreferences sharedPreferences;

        public PreferencesHelper(Context context) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }

        public void saveTemperatureSetting(String room, int temperature) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(room, temperature);
            editor.apply();
        }

        public int getTemperatureSetting(String room) {
            return sharedPreferences.getInt(room, 22);
        }

    }

    private void fadeOutViews(View... views) {
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        for (View view : views) {
            view.startAnimation(fadeOut);
            view.setVisibility(View.INVISIBLE);
        }
    }

    private void fadeInViews(View... views) {
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        for (View view : views) {
            view.startAnimation(fadeIn);
            view.setVisibility(View.VISIBLE);
        }
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