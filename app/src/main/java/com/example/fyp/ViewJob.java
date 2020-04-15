package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.GregorianCalendar;

import static com.example.fyp.MyJobs.DATE;
import static com.example.fyp.MyJobs.DESCRIPTION;
import static com.example.fyp.MyJobs.TEMP;
import static com.example.fyp.MyJobs.TIME;
import static com.example.fyp.MyJobs.TITLE;
import static com.example.fyp.MyJobs.WEATHER;

public class ViewJob extends AppCompatActivity {

    TextView timeBox, weatherType, tempBox, descriptionBox, titleBox, dateBox;
    Button cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_job);

            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));

        timeBox = findViewById(R.id.view_job_time);
        weatherType = findViewById(R.id.view_job_weather);
        tempBox = findViewById(R.id.view_job_temp);
        descriptionBox = findViewById(R.id.view_job_description);
        titleBox = findViewById(R.id.view_job_title);
        dateBox = findViewById(R.id.view_job_date);

        cal = findViewById(R.id.addJobToCalendar);

        Intent intent = getIntent();

        final String weather = intent.getStringExtra(WEATHER);
        final String time = intent.getStringExtra(TIME);
        final String date = intent.getStringExtra(DATE);
        final double temp = intent.getDoubleExtra(TEMP, 0);
        final String title = intent.getStringExtra(TITLE);
        final String description = intent.getStringExtra(DESCRIPTION);

        weatherType.setText(weather);
        timeBox.setText(time);
        dateBox.setText(date);
        tempBox.setText("" + temp + "°C");
        titleBox.setText(title);
        descriptionBox.setText(description);


        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] parts = date.split(" ");
                String d = parts[1];

                String[] dateParts = d.split("/");
                String day = dateParts[0];
                String month = dateParts[1];
                String year = dateParts[2];


                Toast.makeText(ViewJob.this, "Day: " + day + " Month: " + month + " Year: " + year, Toast.LENGTH_LONG).show();

                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE, title);
                calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Garden");
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, description);

                GregorianCalendar calDate = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, time);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calDate.getTimeInMillis() + 60 * 60 * 1000);

                startActivity(calIntent);
            }
        });


    }
}
