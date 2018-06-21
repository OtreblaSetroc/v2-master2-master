package net.gshp.app1;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmMessaginService extends FirebaseMessagingService {
    private final String messaage="message";
    public FcmMessaginService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size()>0 && remoteMessage.getNotification()!=null){
            sendNotification(remoteMessage);
        }
    }
    private  void sendNotification(RemoteMessage remoteMessage){
        String desc = (remoteMessage.getData().get(messaage)).toString();
        Intent intent= new Intent(this,MapsActivity.class);
        intent.putExtra(messaage,desc);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Uri defaulSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification.Builder noBuilder = new  Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(defaulSoundUri)
                .setContentIntent(pendingIntent);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            noBuilder.setColor(!desc.isEmpty()?
                    ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary):
                    ContextCompat.getColor(getApplicationContext(),R.color.colorAccent)

            );

        }

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            String channelId = getString(R.string.normal_channel_id);
            String channelName =  getString(R.string.normal_channel_name);
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName,NotificationManager
                    .IMPORTANCE_DEFAULT);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100,200,200,50});
            if (notificationManager!=null){
                notificationManager.createNotificationChannel(notificationChannel);
            }
            noBuilder.setChannelId(channelId);


        }
        if (noBuilder!=null){
            notificationManager.notify(0,noBuilder.build());
        }


    }
}
