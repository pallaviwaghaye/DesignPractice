package com.webakruti.designpractice;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class LocalNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_local);

        // send notification
        showLocalNotification();
    }

    private void showLocalNotification() {

        int defaults = 0;
        defaults = defaults | Notification.DEFAULT_LIGHTS;
        defaults = defaults | Notification.DEFAULT_VIBRATE;
        defaults = defaults | Notification.DEFAULT_SOUND;

        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                LocalNotificationActivity.this);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();


        Notification notification;
        notification = mBuilder.setSmallIcon(R.drawable.logo2).setTicker("Order Successful").setWhen(0)
                .setAutoCancel(true)
                .setContentTitle("Order Successful")
                //.setContentIntent(resultPendingIntent)
                .setDefaults(defaults)
                .setStyle(inboxStyle)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.logo2)
                .setSmallIcon(getNotificationIcon())
                .setContentText("Your Order is confirmed. Thanks")
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        notificationManager.notify(m, notification);
    }


    private int getNotificationIcon() {
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.logo2 : R.drawable.logo2;
    }
}
