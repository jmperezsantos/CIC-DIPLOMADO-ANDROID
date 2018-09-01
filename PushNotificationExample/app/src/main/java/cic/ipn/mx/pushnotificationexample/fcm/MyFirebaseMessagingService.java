package cic.ipn.mx.pushnotificationexample.fcm;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onNewToken(String newToken) {

        Log.d("TAG", "Refreshed token: " + newToken);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("TAG", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            Log.d("TAG", "Message data payload: " + remoteMessage.getData());
            //Procesar información ej. POS

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {

            Log.d("TAG",
                    "Message Notification Title: " +
                            remoteMessage.getNotification().getTitle());

            Log.d("TAG",
                    "Message Notification Body: " +
                            remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

    }
}
