package com.example.travel_taker.Login;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travel_taker.R;

import java.util.Timer;
import java.util.TimerTask;

public class SetAlarmActivity extends AppCompatActivity {
TextClock currentTime;
TimePicker alarmTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        currentTime = findViewById(R.id.Currenttime);
        alarmTime = findViewById(R.id.alarmtime);

      //  currentTime.setVisibility(View.GONE);

        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(currentTime.getText().toString().equals(AlarmTime()))
                {
                    r.play();
                }else {
                    r.stop();
                }
            }
        },0,1000);

    }

    public String AlarmTime(){
        Integer alarmHours = alarmTime.getCurrentHour();
        Integer alarmMinutes = alarmTime.getCurrentMinute();
        String stringAlarmMinutes;

        if(alarmMinutes<10)
        {
            stringAlarmMinutes = "0";
            stringAlarmMinutes = stringAlarmMinutes.concat(alarmMinutes.toString());
        }else {
            stringAlarmMinutes = alarmMinutes.toString();
        }
        String stringAlarmTime;
        if(alarmHours > 12){
            alarmHours = alarmHours - 12;
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes).concat(" PM");
        }else {
            stringAlarmTime = alarmHours.toString().concat(":").concat(stringAlarmMinutes).concat(" AM");
        }
        return stringAlarmTime;
    }
}
