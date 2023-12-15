package com.example.alarmclock;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;

import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Boolean connected = false;
    private Button setAlarm;
    private TimePicker timePicker;
    private EditText blueToothStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Iconify.with(new FontAwesomeModule());
        setContentView(R.layout.activity_main);
        setAlarm = findViewById(R.id.set_alarm);
        timePicker = findViewById(R.id.time);
        blueToothStatus = findViewById(R.id.blueToothStatus);
        setAlarm.setOnClickListener((View.OnClickListener) this);
        setConnectionStatus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        //create the icons
        Drawable settingIcon = new IconDrawable(this, FontAwesomeIcons.fa_gear)
                .colorRes(R.color.white).sizeDp(20);
        //get the menuitems
        MenuItem setting = menu.findItem(R.id.setting);
        setting.setIcon(settingIcon);
        setting.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Toast.makeText(MainActivity.this, "To The Setting Activity!!!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), BlueToothActivity.class));
                return true;
            }
        });
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view)
    {
        //current date and time
        Calendar cal = Calendar.getInstance();
        //set the time for the alarm
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                timePicker.getHour(),
                timePicker.getMinute(),0);
        //send the alarm time
        Alarm_set(cal.getTimeInMillis());
    }
    private void Alarm_set(long timeInMillis)
    {
        AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP
                ,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this,"Your Alarm is Set", Toast.LENGTH_LONG).show();

    }
    private void setConnectionStatus()
    {
        if(!connected)
        {
            blueToothStatus.setText("Not Connected");
        }
        else
        {
            blueToothStatus.setText("Connected");
        }
        blueToothStatus.setEnabled(false);
        blueToothStatus.setFocusable(false);
        blueToothStatus.setFocusableInTouchMode(false);
    }
}