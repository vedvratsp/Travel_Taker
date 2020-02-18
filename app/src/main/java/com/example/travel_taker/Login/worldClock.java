package com.example.travel_taker.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_taker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class worldClock extends AppCompatActivity {


    Calendar current;
    Spinner spinner;
    TextView tvTimeZone, tvCurrentTime, tvtimeZonelabel;
    long miliseconds;
    ArrayAdapter<String> idAdapter;
    SimpleDateFormat dateFormat;
    Date resultDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_clock);



        spinner = findViewById(R.id.spinner);
        tvCurrentTime = findViewById(R.id.Currenttime);


        tvtimeZonelabel = findViewById(R.id.timezone);
        tvTimeZone = findViewById(R.id.time);

        String[] idArray = TimeZone.getAvailableIDs();
        dateFormat = new SimpleDateFormat("EEEE, dd MM yyyy HH:mm:ss" );
        idAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idArray);
        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(idAdapter);

        getGMTtime();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getGMTtime();
                String selectedID = (String) (parent.getItemAtPosition(position));

                TimeZone timeZone = TimeZone.getTimeZone(selectedID);
                String TimezoneArea = timeZone.getDisplayName();

                int TimezoneOffSet = timeZone.getRawOffset() / (60 * 1000);

                int hours = TimezoneOffSet/60;
                int minutes = TimezoneOffSet%60;

                miliseconds = miliseconds + timeZone.getRawOffset();

                resultDate = new Date(miliseconds);
                System.out.println(dateFormat.format(resultDate));

                tvtimeZonelabel.setText(TimezoneArea + " : GMT " +hours+ "." +minutes);

                tvTimeZone.setText("" + dateFormat.format(resultDate));
                miliseconds = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getGMTtime(){
        current = Calendar.getInstance();

        tvCurrentTime.setText("" +current.getTime());

        miliseconds = current.getTimeInMillis();
        TimeZone currentTimezone = current.getTimeZone();
        int offset = currentTimezone.getRawOffset();
        if (currentTimezone.inDaylightTime(new Date())){
            offset = offset+currentTimezone.getDSTSavings();
        }
        miliseconds = miliseconds - offset;
        resultDate = new Date(miliseconds);
        System.out.println(dateFormat.format(resultDate));

    }
}
