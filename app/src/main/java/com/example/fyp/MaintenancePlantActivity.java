package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import static com.example.fyp.seasons.Autumn.CARE;
import static com.example.fyp.seasons.Autumn.IMAGE;
import static com.example.fyp.seasons.Autumn.LINK;
import static com.example.fyp.seasons.Autumn.NAME;

public class MaintenancePlantActivity extends AppCompatActivity {

    Button addToCalendar;
    TextView nameBox, careBox, linkBox;
    ImageView imageBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_plant);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));

        addToCalendar = findViewById(R.id.mpJobAddToCalendar);
        nameBox = findViewById(R.id.mp_view_name);
        careBox = findViewById(R.id.mp_view_care);
        linkBox = findViewById(R.id.mp_view_link);
        imageBox = findViewById(R.id.mp_view_image);

        Intent intent = getIntent();

        final String name = intent.getStringExtra(NAME);
        final String image = intent.getStringExtra(IMAGE);
        final String link= intent.getStringExtra(LINK);
        final String care = intent.getStringExtra(CARE);

        linkBox.setPaintFlags(linkBox.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        linkBox.setText("Click here");
        linkBox.setTextColor(Color.parseColor("#FF2233"));
        nameBox.setText(name);
        Picasso.get().load(image).fit().into(imageBox);
        assert care != null;
        careBox.setText(care.replace("Garden Care:", ""));


        linkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(link));
                startActivity(i);

            }
        });

        addToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE, name + " maintenance");
                calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Garden");
                startActivity(calIntent);
            }
        });




    }
}
