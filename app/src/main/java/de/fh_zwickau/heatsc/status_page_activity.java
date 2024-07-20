
 package de.fh_zwickau.heatsc;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.TextView;


    import androidx.appcompat.app.AppCompatActivity;

    public class status_page_activity extends AppCompatActivity {

        public bathroom_activity.PreferencesHelper preferenceHelperbath;
        public bedroom_1_activity.PreferencesHelperBed1 preferencesHelperBed1;
        public bedroom_2_activity.PreferencesHelperBed2 preferencesHelperBed2;
        public kitchen_activity.PreferencesHelperkitchen preferencesHelperkitchen;
        public living_room_activity.PreferencesHelperliv preferencesHelperliv;
        public office_activity.PreferencesHelperO preferencesHelperO;
        public Home_page_activity.PreferencesHelper preferencesHelper;

        TextView bath_temp;
        TextView bath_status;
        TextView bath_targ_temp;
        TextView bed1_temp;
        TextView bed1_status;
        TextView bed1_targ_temp;
        TextView bed2_temp;
        TextView bed2_status;
        TextView bed2_targ_temp;
        TextView kitchen_temp;
        TextView kitchen_status;
        TextView kitchen_targ_temp;
        TextView liv_temp;
        TextView liv_status;
        TextView liv_targ_temp;
        TextView o_temp;
        TextView o_status;
        TextView o_targ_temp;

        int bathroomTemperature;
        int currentBathTemperature;
        int bedroom1Temperature;
        int currentBed1Temperature;
        int bedroom2Temperature;
        int currentBed2Temperature;
        int kitchenTemperature;
        int currentKitchenTemperature;
        int livTemperature;
        int currentLivTemperature;
        int oTemperature;
        int currentOTemperature;
        String cooling;
        String heating;
        String Off;
        boolean switchState_bed1;
        boolean switchState_bed2;
        boolean switchState_bath;
        boolean switchState_office;
        boolean switchState_liv;
        boolean switchState_kitchen;

        private static final int TEMP_THRESHOLD = 24;

        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.status_page);

            preferenceHelperbath = new bathroom_activity.PreferencesHelper(this);
            preferencesHelperBed1 = new bedroom_1_activity.PreferencesHelperBed1(this);
            preferencesHelperBed2 = new bedroom_2_activity.PreferencesHelperBed2(this);
            preferencesHelperkitchen = new kitchen_activity.PreferencesHelperkitchen(this);
            preferencesHelperliv = new living_room_activity.PreferencesHelperliv(this);
            preferencesHelperO = new office_activity.PreferencesHelperO(this);
            preferencesHelper = new Home_page_activity.PreferencesHelper(this);


            cooling = getString(R.string.cooling);
            heating = getString(R.string.heating);
            Off = "Off";
            bath_temp = findViewById(R.id.bathroom_temp);
            bath_targ_temp = findViewById(R.id.target_bath_temp);
            bath_status = findViewById(R.id.bathroom_status);
            bed1_temp = findViewById(R.id.bedroom1_temp);
            bed1_targ_temp = findViewById(R.id.targ_bed1_temp);
            bed1_status = findViewById(R.id.bedroom1_status);
            bed2_temp = findViewById(R.id.bedroom2_temp);
            bed2_targ_temp = findViewById(R.id.bedroom2_target_temp);
            bed2_status = findViewById(R.id.bedroom2_status);
            kitchen_temp = findViewById(R.id.kitchen_temp);
            kitchen_targ_temp = findViewById(R.id.k_target_temp);
            kitchen_status = findViewById(R.id.k_status);
            liv_temp = findViewById(R.id.liv_temp);
            liv_targ_temp = findViewById(R.id.liv_targ_temp);
            liv_status = findViewById(R.id.liv_status);
            o_temp = findViewById(R.id.office_temp);
            o_targ_temp = findViewById(R.id.o_targ_temp);
            o_status = findViewById(R.id.office_status);
            bathroomTemperature = preferenceHelperbath.getTemperatureSetting("bathroom");
            currentBathTemperature = preferenceHelperbath.getTemperatureSetting("bathroom_curr_temp");
            bedroom1Temperature = preferencesHelperBed1.getTemperatureSetting("bedroom1");
            currentBed1Temperature = preferencesHelperBed1.getTemperatureSetting("bedroom1_curr_temp");
            bedroom2Temperature = preferencesHelperBed2.getTemperatureSetting("bedroom2");
            currentBed2Temperature = preferencesHelperBed2.getTemperatureSetting("bedroom2_curr_temp");
            kitchenTemperature = preferencesHelperkitchen.getTemperatureSetting("kitchen");
            currentKitchenTemperature = preferencesHelperkitchen.getTemperatureSetting("kitchen_curr_temp");
            livTemperature = preferencesHelperliv.getTemperatureSetting("livingroom");
            currentLivTemperature = preferencesHelperliv.getTemperatureSetting("liv_curr_temp");
            oTemperature = preferencesHelperO.getTemperatureSetting("office");
            currentOTemperature = preferencesHelperO.getTemperatureSetting("office_curr_temp");


            switchState_bed1 = preferencesHelper.getSwitchState("switchState_bed1");
            switchState_bed2 = preferencesHelper.getSwitchState("switchState_bed2");
            switchState_bath = preferencesHelper.getSwitchState("switchState_bath");
            switchState_liv = preferencesHelper.getSwitchState("switchState_liv");
            switchState_office = preferencesHelper.getSwitchState("switchState_office");
            switchState_kitchen = preferencesHelper.getSwitchState("switchState_kitchen");
            updateTemperatureDisplay();

        }

        private void updateTemperatureDisplay() {
            String temperatureFormatbath = getString(R.string.temperature_format, bathroomTemperature);
            String curr_bath_temperatureFormat = getString(R.string.temperature_format, currentBathTemperature);
            bath_temp.setText(curr_bath_temperatureFormat);
            bath_targ_temp.setText(temperatureFormatbath);

            if (switchState_bath){
                if (bathroomTemperature > TEMP_THRESHOLD) {
                    bath_status.setText(String.valueOf(heating));
                } else {
                    bath_status.setText(String.valueOf(cooling));
                }
            }else{
                bath_status.setText(String.valueOf(Off));
                bath_temp.setAlpha(0.3f);
                bath_targ_temp.setAlpha(0.3f);
            }

            String temperatureFormatbed1 = getString(R.string.temperature_format, bedroom1Temperature);
            String curr_bed1_temperatureFormat = getString(R.string.temperature_format, currentBed1Temperature);
            bed1_temp.setText(curr_bed1_temperatureFormat);
            bed1_targ_temp.setText(temperatureFormatbed1);

            if (switchState_bed1){
                if (bedroom1Temperature > TEMP_THRESHOLD) {
                    bed1_status.setText(String.valueOf(heating));
                } else {
                    bed1_status.setText(String.valueOf(cooling));
                }
            }else{
                bed1_status.setText(String.valueOf(Off));
                bed1_temp.setAlpha(0.3f);
                bed1_targ_temp.setAlpha(0.3f);
            }

            String temperatureFormatbed2 = getString(R.string.temperature_format, bedroom2Temperature);
            String curr_bed2_temperatureFormat = getString(R.string.temperature_format, currentBed2Temperature);
            bed2_temp.setText(curr_bed2_temperatureFormat);
            bed2_targ_temp.setText(temperatureFormatbed2);

            if (switchState_bed2){
                if (bedroom2Temperature > TEMP_THRESHOLD) {
                    bed2_status.setText(String.valueOf(heating));
                } else {
                    bed2_status.setText(String.valueOf(cooling));
                }
            }else{
                bed2_status.setText(String.valueOf(Off));
                bed2_temp.setAlpha(0.3f);
                bed2_targ_temp.setAlpha(0.3f);
            }

            String temperatureFormatk = getString(R.string.temperature_format, kitchenTemperature);
            String curr_k_temperatureFormat = getString(R.string.temperature_format, currentKitchenTemperature);
            kitchen_temp.setText(curr_k_temperatureFormat);
            kitchen_targ_temp.setText(temperatureFormatk);

            if (switchState_kitchen){
                if (kitchenTemperature > TEMP_THRESHOLD) {
                    kitchen_status.setText(String.valueOf(heating));
                } else {
                    kitchen_status.setText(String.valueOf(cooling));
                }
            }else{
                kitchen_status.setText(String.valueOf(Off));
                kitchen_temp.setAlpha(0.3f);
                kitchen_targ_temp.setAlpha(0.3f);
            }

            String temperatureFormatl = getString(R.string.temperature_format, livTemperature);
            String curr_l_temperatureFormat = getString(R.string.temperature_format, currentLivTemperature);
            liv_temp.setText(curr_l_temperatureFormat);
            liv_targ_temp.setText(temperatureFormatl);

            if (switchState_liv){
                if (livTemperature > TEMP_THRESHOLD) {
                    liv_status.setText(String.valueOf(heating));
                } else {
                    liv_status.setText(String.valueOf(cooling));
                }
            }else{
                liv_status.setText(String.valueOf(Off));
                liv_temp.setAlpha(0.3f);
                liv_targ_temp.setAlpha(0.3f);
            }

            String temperatureFormato = getString(R.string.temperature_format, oTemperature);
            String curr_o_temperatureFormat = getString(R.string.temperature_format, currentOTemperature);
            o_temp.setText(curr_o_temperatureFormat);
            o_targ_temp.setText(temperatureFormato);

            if (switchState_office){
                if (oTemperature > TEMP_THRESHOLD) {
                    o_status.setText(String.valueOf(heating));
                } else {
                    o_status.setText(String.valueOf(cooling));
                }
            }else{
                o_status.setText(String.valueOf(Off));
                o_temp.setAlpha(0.3f);
                o_targ_temp.setAlpha(0.3f);
            }
        }

        public void onHomeClick(View view) {

            Intent intent = new Intent(getApplicationContext(), Home_page_activity.class);
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
	
	