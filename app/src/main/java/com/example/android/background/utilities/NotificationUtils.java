package com.example.android.background.utilities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.NotificationCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {

    private static final int ID_WATER_NOTIFICATION = 123;

    // COMPLETED (11) Create a helper method called remindUserBecauseCharging, which takes a context, and
    // then calls remindUser. You should pass in the charging_reminder_notification_title String for
    // the title and the charging_reminder_notification_body String for the text.
    public static void remindUserBecauseCharging(Context context){
        remindUser(
                context,
                context.getString(R.string.charging_reminder_notification_title),
                context.getString(R.string.charging_reminder_notification_body)
        );
    }


    // COMPLETED (7) Create a method called remindUser which takes a Context and a String title and text.
    // This method will create notifications with the title and text supplied. It might be helpful
    // to take a look at this guide to see an example of what the code in this method will look like:
    // https://developer.android.com/training/notify-user/build-notification.html
    public static void remindUser(Context context, String title, String text) {
        // COMPLETED (8) In the remindUser method use NotificationCompat.Builder to create a notification
        // that:
        // - has a color of R.colorPrimary - use ContextCompat.getColor to get a compatible color
        // - has ic_launcher as the small icon
        // - uses icon returned by the largeIcon helper method as the large icon
        // - sets the title and text using the parameters of this method
        // - sets the style to NotificationCompat.BigTextStyle().bigText(text)
        // - uses the content intent returned by the contentIntent helper method for the contentIntent
        // - automatically cancels the notification when the notification is clicked
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context);
        nBuilder.setColor(context.getResources().getColor(R.color.colorPrimary));
        nBuilder.setSmallIcon(R.mipmap.ic_launcher);
        nBuilder.setLargeIcon(largeIcon(context));
        nBuilder.setContentTitle(title);
        nBuilder.setContentText(text);
        nBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(text));
        nBuilder.setContentIntent(contentIntent(context));
        nBuilder.setAutoCancel(true);
        // COMPLETED (9) Get a NotificationManager, using context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager nm = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        // COMPLETED (10) Trigger the notification by calling notify on the NotificationManager.
        // Pass in a unique ID of your choosing for the notification and notificationBuilder.build()
        nm.notify(ID_WATER_NOTIFICATION,nBuilder.build());

    }
    // COMPLETED (1) Create a helper method called contentIntent with a single parameter for a Context. It
    // should return a PendingIntent. This method will create the pending intent which will trigger when
    // the notification is pressed. This pending intent should open up the MainActivity.
    private static PendingIntent contentIntent(Context context) {
        // COMPLETED (2) Create an intent that opens up the MainActivity
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        // COMPLETED (3) Create a PendingIntent using getActivity that:
        // - Take the context passed in as a parameter
        // - Takes an unique integer ID for the pending intent (you can create a constant for
        //   this integer above
        // - Takes the intent to open the MainActivity you just created; this is what is triggered
        //   when the notification is triggered
        // - Has the flag FLAG_UPDATE_CURRENT, so that if the intent is created again, keep the
        // intent but update the data
        return PendingIntent.getActivity(context,ID_WATER_NOTIFICATION,mainActivityIntent,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    // COMPLETED (4) Create a helper method called largeIcon which takes in a Context as a parameter and
    // returns a Bitmap. This method is necessary to decode a bitmap needed for the notification.
    private static Bitmap largeIcon(Context context) {
        // COMPLETED (5) Get a Resources object from the context
        // COMPLETED (6) Create and return a bitmap using BitmapFactory.decodeResource, passing in the
        // resources object and
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_local_drink_black_24px);

    }
}
