package com.appintech.litelessons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.appintech.litelessons.App.CHANNEL_1_ID;

public class DashBoard extends AppCompatActivity implements View.OnClickListener {

    Button assBut,showImageBut;
    private NotificationManagerCompat notificationManager;
    String title = "notifiation title";
    String message = "notification";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        assBut = findViewById(R.id.assignment_but);
        showImageBut = findViewById(R.id.user_images_but);
        showImageBut.setOnClickListener(this);
        assBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("i click", "clicked");
                    // throwing error
//                Notification notification = new NotificationCompat.Builder(DashBoard.this, CHANNEL_1_ID)
//                        .setSmallIcon(R.drawable.ic_android)
//                        .setContentTitle(title)
//                        .setContentText(message)
//                        .setPriority(NotificationCompat.PRIORITY_HIGH)
//                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                        .build();
//                notificationManager.notify(1, notification);
            }
        });
    }






    @Override
    public void onClick(View v){
        startActivity(new Intent(DashBoard.this,ShowImages.class));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
