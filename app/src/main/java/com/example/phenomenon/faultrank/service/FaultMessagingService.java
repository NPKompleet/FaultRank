package com.example.phenomenon.faultrank.service;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.example.phenomenon.faultrank.MainActivity;
import com.example.phenomenon.faultrank.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by PHENOMENON on 9/20/2017.
 */

public class FaultMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            showNotification(remoteMessage.getData().get("faultType"), remoteMessage.getData().get("undertaking"));
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {

        }
    }

    private void showNotification(String faultType, String undertaking) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Fault: " + faultType)
                .setSmallIcon(R.drawable.ic_flash)
                .setContentText("at " + undertaking)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 , notificationBuilder.build());
    }
}
