package com.example.android.pingme;

/**
 *
 * A set of constants used by all of the components in this application. To use these constants
 * the components implement the interface.
 */

public final class CommonConstants {

    public CommonConstants() {

        // don't allow the class to be instantiated
    }

    // Milliseconds in the snooze duration, which translates
    // to 20 seconds.
    public static final int SNOOZE_DURATION = 20000;
    public static final int DEFAULT_TIMER_DURATION = 10000;
    public static final String ACTION_SNOOZE = "com.example.android.pingme.ACTION_SNOOZE";
    public static final String ACTION_DISMISS = "com.example.android.pingme.ACTION_DISMISS";
    public static final String ACTION_PING = "com.example.android.pingme.ACTION_PING";
    public static final String EXTRA_MESSAGE= "com.example.android.pingme.EXTRA_MESSAGE";
    public static final String EXTRA_TIMER = "com.example.android.pingme.EXTRA_TIMER";
    public static final int NOTIFICATION_ID = 001;
    public static final String DEBUG_TAG = "PingMe";
}