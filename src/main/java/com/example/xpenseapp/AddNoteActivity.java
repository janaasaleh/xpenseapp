package com.example.xpenseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.applandeo.materialcalendarview.CalendarView;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        final CalendarView datePicker = (CalendarView) findViewById(R.id.datePicker);
        Button button = (Button) findViewById(R.id.addNoteButton);
        final EditText noteEditText = (EditText) findViewById(R.id.noteEditText);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Xpense", "Xpense_Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();

                notifyUser();
                MyEventDay myEventDay = new MyEventDay(datePicker.getSelectedDate(),
                        R.drawable.colorgrad, noteEditText.getText().toString());

//                Log.i("Notify", String.valueOf(datePicker.getFirstSelectedDate()));
//                Log.i("Notify", String.valueOf(datePicker.getFirstSelectedDate().YEAR));
//                Log.i("Notify", String.valueOf(datePicker.getFirstSelectedDate().MONTH));
//                Log.i("Notify", String.valueOf(datePicker.getFirstSelectedDate().DAY_OF_YEAR));

                returnIntent.putExtra(CalenderActivity.RESULT, myEventDay);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }

        });
    }

    public void notifyUser() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(AddNoteActivity.this, "Xpense")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Xpense Calendar")
                .setContentText("You have successfully added an event to your calendar")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);
        NotificationManagerCompat manager = NotificationManagerCompat.from(AddNoteActivity.this);
        manager.notify(1, builder.build());
    }
}
