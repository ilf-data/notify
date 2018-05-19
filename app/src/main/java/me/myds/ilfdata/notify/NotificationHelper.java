package me.myds.ilfdata.notify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;

public class NotificationHelper extends ContextWrapper {

    private static final String Channel_ID = "me.myds.ilfdata.notify";
    private static final String Channel_NAME = "notify";
    private NotificationManager manager;
    public NotificationHelper(Context base){
        super(base);
        createChannels();
    }

    private void createChannels() {
        NotificationChannel notifyChannel = new NotificationChannel(Channel_ID,Channel_NAME,NotificationManager.IMPORTANCE_DEFAULT);
        notifyChannel.enableLights(true);
        notifyChannel.enableVibration(true);
        notifyChannel.setLightColor(Color.YELLOW);
        notifyChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(notifyChannel);
        
    }

    public NotificationManager getManager() {
        if(manager == null)
            manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        return manager;
    }

    public Notification.Builder getChannelNotification(String title,String body)
    {
        return new Notification.Builder(getApplicationContext(), Channel_ID)
                .setContentText(body)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setAutoCancel(true);
    }
}
