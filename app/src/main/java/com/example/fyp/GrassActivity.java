package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp.Objects.OtherDetails;
import com.example.fyp.Objects.SoilType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.TimeZone;

public class GrassActivity extends AppCompatActivity {

    Button add;

    DatabaseReference reference, reference2;
    FirebaseUser user;
    FirebaseAuth firebaseAuth;
    TextView startGrass, stopGrass, cutEvery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grass);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.statusBar));

        add = findViewById(R.id.addToCalendar);

        startGrass = findViewById(R.id.startCuttingBox);
        stopGrass = findViewById(R.id.stopcuttingBox);
        cutEvery = findViewById(R.id.cutEveryBox);

        stopGrass.setText("31st of October");


        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();
        assert user != null;
        reference = FirebaseDatabase.getInstance().getReference().child("SoilType").child(user.getUid());
        reference2 = FirebaseDatabase.getInstance().getReference().child("OtherDetails").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot1: dataSnapshot.getChildren()) {

                    SoilType st = snapshot1.getValue(SoilType.class);
                    assert st != null;
                    String name = st.getName();

                    switch (name) {
                        case "Brown Soil":

                            cutEvery.setText("7 days");

                            break;
                        case "Podzol Soil":

                            reference2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot snapshot2 : dataSnapshot.getChildren()) {

                                        OtherDetails od = snapshot2.getValue(OtherDetails.class);
                                        assert od != null;
                                        String orientation = od.getOrientation();

                                        if (orientation.equals("South")) {

                                            cutEvery.setText("13 days");

                                        } else if (orientation.equals("East") || orientation.equals("West") || orientation.equals("North")) {

                                            cutEvery.setText("14 days");

                                        }

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                            break;
                        case "Gley Soil":

                            reference2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot snapshot2 : dataSnapshot.getChildren()) {

                                        OtherDetails od = snapshot2.getValue(OtherDetails.class);
                                        assert od != null;
                                        String orientation = od.getOrientation();

                                        if (orientation.equals("South")) {

                                            cutEvery.setText("5 days");

                                        } else if (orientation.equals("East") || orientation.equals("West") || orientation.equals("North")) {

                                            cutEvery.setText("6 days");

                                        }

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                            break;
                        case "Peaty Soil":

                            reference2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot snapshot2 : dataSnapshot.getChildren()) {

                                        OtherDetails od = snapshot2.getValue(OtherDetails.class);
                                        assert od != null;
                                        String orientation = od.getOrientation();

                                        if (orientation.equals("South")) {

                                            cutEvery.setText("6 days");

                                        } else if (orientation.equals("East") || orientation.equals("West") || orientation.equals("North")) {

                                            cutEvery.setText("7 days");
                                        }

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });


                            break;
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()) {

                    SoilType st = snapshot1.getValue(SoilType.class);
                    assert st != null;
                    String name = st.getName();

                    switch (name) {
                        case "Brown Soil":

                            startGrass.setText("15-18th March");

                            break;
                        case "Podzol Soil":

                            startGrass.setText("20-25th March");

                            break;
                        case "Gley Soil":

                            startGrass.setText("10-15th March");

                            break;
                        case "Peaty Soil":

                            startGrass.setText("15-20th March");

                            break;
                    }


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot1: dataSnapshot.getChildren()) {

                            SoilType st = snapshot1.getValue(SoilType.class);
                            assert st != null;
                            String name = st.getName();

                            switch (name) {
                                case "Brown Soil":

                                    Calendar cal = Calendar.getInstance();
                                    Intent calIntent = new Intent(Intent.ACTION_INSERT);
                                    calIntent.setType("vnd.android.cursor.item/event");
                                    calIntent.putExtra(CalendarContract.Events.TITLE, "Cut Lawns");
                                    calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Garden");
                                    calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Cut all the lawns");
                                    calIntent.putExtra(CalendarContract.Events.DTSTART, cal.getTimeInMillis());
                                    calIntent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;BYDAY=SA;INTERVAL=1;UNTIL=20201031T000000Z");
                                    calIntent.putExtra(CalendarContract.Events.ALL_DAY, 1); // 0 for false, 1 for true

                                    calIntent.putExtra(CalendarContract.Events.STATUS, 1);
                                    calIntent.putExtra(CalendarContract.Events.HAS_ALARM, 1); // 0 for false, 1 for true

                                    calIntent.putExtra(CalendarContract.Events.DURATION, "P3600S");
                                    startActivity(calIntent);

                                    break;
                                case "Podzol Soil":

                                    reference2.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for (DataSnapshot snapshot2 : dataSnapshot.getChildren()) {

                                                OtherDetails od = snapshot2.getValue(OtherDetails.class);
                                                assert od != null;
                                                String orientation = od.getOrientation();

                                                if (orientation.equals("South")) {

                                                    Calendar cal = Calendar.getInstance();
                                                    Intent calIntent = new Intent(Intent.ACTION_INSERT);
                                                    calIntent.setType("vnd.android.cursor.item/event");
                                                    calIntent.putExtra(CalendarContract.Events.TITLE, "Cut Lawns");
                                                    calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Garden");
                                                    calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Cut all the lawns");
                                                    calIntent.putExtra(CalendarContract.Events.DTSTART, cal.getTimeInMillis());
                                                    calIntent.putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY;INTERVAL=13;UNTIL=20201031T000000Z");
                                                    calIntent.putExtra(CalendarContract.Events.ALL_DAY, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.STATUS, 1);
                                                    calIntent.putExtra(CalendarContract.Events.HAS_ALARM, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.DURATION, "P3600S");
                                                    startActivity(calIntent);
                                                } else if (orientation.equals("East") || orientation.equals("West") || orientation.equals("North")) {

                                                    Calendar cal = Calendar.getInstance();
                                                    Intent calIntent = new Intent(Intent.ACTION_INSERT);
                                                    calIntent.setType("vnd.android.cursor.item/event");
                                                    calIntent.putExtra(CalendarContract.Events.TITLE, "Cut Lawns");
                                                    calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Garden");
                                                    calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Cut all the lawns");
                                                    calIntent.putExtra(CalendarContract.Events.DTSTART, cal.getTimeInMillis());
                                                    calIntent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;BYDAY=SA;INTERVAL=2;UNTIL=20201031T000000Z");
                                                    calIntent.putExtra(CalendarContract.Events.ALL_DAY, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.STATUS, 1);
                                                    calIntent.putExtra(CalendarContract.Events.HAS_ALARM, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.DURATION, "P3600S");
                                                    startActivity(calIntent);

                                                }

                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    break;
                                case "Gley Soil":

                                    reference2.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for (DataSnapshot snapshot2 : dataSnapshot.getChildren()) {

                                                OtherDetails od = snapshot2.getValue(OtherDetails.class);
                                                assert od != null;
                                                String orientation = od.getOrientation();

                                                if (orientation.equals("South")) {

                                                    Calendar cal = Calendar.getInstance();
                                                    Intent calIntent = new Intent(Intent.ACTION_INSERT);
                                                    calIntent.setType("vnd.android.cursor.item/event");
                                                    calIntent.putExtra(CalendarContract.Events.TITLE, "Cut Lawns");
                                                    calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Garden");
                                                    calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Cut all the lawns");
                                                    calIntent.putExtra(CalendarContract.Events.DTSTART, cal.getTimeInMillis());
                                                    calIntent.putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY;INTERVAL=5;UNTIL=20201031T000000Z");
                                                    calIntent.putExtra(CalendarContract.Events.ALL_DAY, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.STATUS, 1);
                                                    calIntent.putExtra(CalendarContract.Events.HAS_ALARM, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.DURATION, "P3600S");
                                                    startActivity(calIntent);
                                                } else if (orientation.equals("East") || orientation.equals("West") || orientation.equals("North")) {

                                                    Calendar cal = Calendar.getInstance();
                                                    Intent calIntent = new Intent(Intent.ACTION_INSERT);
                                                    calIntent.setType("vnd.android.cursor.item/event");
                                                    calIntent.putExtra(CalendarContract.Events.TITLE, "Cut Lawns");
                                                    calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Garden");
                                                    calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Cut all the lawns");
                                                    calIntent.putExtra(CalendarContract.Events.DTSTART, cal.getTimeInMillis());
                                                    calIntent.putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY;INTERVAL=6;UNTIL=20201031T000000Z");
                                                    calIntent.putExtra(CalendarContract.Events.ALL_DAY, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.STATUS, 1);
                                                    calIntent.putExtra(CalendarContract.Events.HAS_ALARM, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.DURATION, "P3600S");
                                                    startActivity(calIntent);

                                                }

                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    break;
                                case "Peaty Soil":

                                    reference2.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            for (DataSnapshot snapshot2 : dataSnapshot.getChildren()) {

                                                OtherDetails od = snapshot2.getValue(OtherDetails.class);
                                                assert od != null;
                                                String orientation = od.getOrientation();

                                                if (orientation.equals("South")) {

                                                    Calendar cal = Calendar.getInstance();
                                                    Intent calIntent = new Intent(Intent.ACTION_INSERT);
                                                    calIntent.setType("vnd.android.cursor.item/event");
                                                    calIntent.putExtra(CalendarContract.Events.TITLE, "Cut Lawns");
                                                    calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Garden");
                                                    calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Cut all the lawns");
                                                    calIntent.putExtra(CalendarContract.Events.DTSTART, cal.getTimeInMillis());
                                                    calIntent.putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY;INTERVAL=6;UNTIL=20201031T000000Z");
                                                    calIntent.putExtra(CalendarContract.Events.ALL_DAY, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.STATUS, 1);
                                                    calIntent.putExtra(CalendarContract.Events.HAS_ALARM, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.DURATION, "P3600S");
                                                    startActivity(calIntent);
                                                } else if (orientation.equals("East") || orientation.equals("West") || orientation.equals("North")) {

                                                    Calendar cal = Calendar.getInstance();
                                                    Intent calIntent = new Intent(Intent.ACTION_INSERT);
                                                    calIntent.setType("vnd.android.cursor.item/event");
                                                    calIntent.putExtra(CalendarContract.Events.TITLE, "Cut Lawns");
                                                    calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Garden");
                                                    calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Cut all the lawns");
                                                    calIntent.putExtra(CalendarContract.Events.DTSTART, cal.getTimeInMillis());
                                                    calIntent.putExtra(CalendarContract.Events.RRULE, "FREQ=WEEKLY;BYDAY=SA;INTERVAL=1;UNTIL=20201031T000000Z");
                                                    calIntent.putExtra(CalendarContract.Events.ALL_DAY, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.STATUS, 1);
                                                    calIntent.putExtra(CalendarContract.Events.HAS_ALARM, 1); // 0 for false, 1 for true
                                                    calIntent.putExtra(CalendarContract.Events.DURATION, "P3600S");
                                                    startActivity(calIntent);

                                                }

                                            }

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    break;
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });







            }
        });
    }
}
