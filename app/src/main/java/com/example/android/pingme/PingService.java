package com.example.android.pingme;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * PingService creates a notification that includes 2 buttons: one to snooze the
 * notification, and one to dismiss it.
 */
public class PingService extends IntentService {

    private NotificationManager mNotificationManager;
    private String mMessage;
    private int mMillis;
    NotificationCompat.Builder builder;

    public PingService() {

        // The super call is required. The background thread that IntentService
        // starts is labeled with the string argument you pass.
        super("com.example.android.pingme");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // The reminder message the user set.
        mMessage = intent.getStringExtra(CommonConstants.EXTRA_MESSAGE);
        // The timer duration the user set. The default is 10 seconds.
        mMillis = intent.getIntExtra(CommonConstants.EXTRA_TIMER,
                CommonConstants.DEFAULT_TIMER_DURATION);
        NotificationManager nm = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        String action = intent.getAction();
        issueNotification(intent, mMessage);
    }

    private void issueNotification(Intent intent, String msg) {
        mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        // Constructs the Builder object.
        builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_notification)
                        .setContentTitle(getString(R.string.notification))
                        .setContentText(getString(R.string.ping))
                        .setDefaults(Notification.DEFAULT_ALL); // requires VIBRATE permission

        /*
         * Clicking the notification itself displays ResultActivity, which provides
         * UI for snoozing or dismissing the notification.
         * This is available through either the normal view or big view.
         */
        Intent resultIntent = new Intent(this, ResultActivity.class);
        resultIntent.putExtra(CommonConstants.EXTRA_MESSAGE, msg);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        builder.setContentIntent(resultPendingIntent);
        startTimer(mMillis);
    }

    private void issueNotification(NotificationCompat.Builder builder) {
        mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        // Including the notification ID allows you to update the notification later on.
        mNotificationManager.notify(CommonConstants.NOTIFICATION_ID, builder.build());
    }

    // Starts the timer according to the number of seconds the user specified.
    private void startTimer(int millis) {
        Log.d(CommonConstants.DEBUG_TAG, getString(R.string.timer_start));
        try {
            Thread.sleep(millis);

        } catch (InterruptedException e) {
            Log.d(CommonConstants.DEBUG_TAG, getString(R.string.sleep_error));
        }
        Log.d(CommonConstants.DEBUG_TAG, getString(R.string.timer_finished));
        issueNotification(builder);
    }
}