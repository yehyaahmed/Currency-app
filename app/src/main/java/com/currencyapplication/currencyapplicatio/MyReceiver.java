package com.currencyapplication.currencyapplicatio;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import static android.content.Context.MODE_PRIVATE;

public class MyReceiver extends BroadcastReceiver {

    int def, value, goldValue, gold_history_def;
    boolean sw1, sw2;
    int idN, idN1;

    @Override
    public void onReceive(Context context, Intent intent) {

        RequestQueue referenceQueue = Volley.newRequestQueue(context);

        Intent i = new Intent(context, SplachActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);
        gold_history_def = MainActivity.gold_value;
        def = MainActivity.def;

        SharedPreferences sharedPreferences = context.getSharedPreferences("def", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        value = sharedPreferences.getInt("value", 0);
        goldValue = sharedPreferences.getInt("goldValue", 0);

        SharedPreferences sharedPreferencesSwitch = context.getSharedPreferences("Switch", MODE_PRIVATE);
        sw1 = sharedPreferencesSwitch.getBoolean(MainActivity.sw1, true);
        sw2 = sharedPreferencesSwitch.getBoolean(MainActivity.sw2, true);

        if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)
                || intent.getAction().equals(Intent.ACTION_SCREEN_ON)
                || intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

            if (sw1) {
                if (gold_history_def != 0) {
                    if (def != value) {
                        Log.d("nnnn", def + " def");
                        Log.d("nnnn", value + " value");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel channel = new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);
                            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
                            assert notificationManager != null;
                            notificationManager.createNotificationChannel(channel);

                        }

                        @SuppressLint("SdCardPath") NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "n")
                                .setContentTitle("تم تحديث قيمة العملات")
                                .setSmallIcon(R.drawable.notification)
                                .setAutoCancel(true)
                                .setContentIntent(pendingIntent)
                                .setSound(RingtoneManager.getDefaultUri(R.raw.sound));

                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                        managerCompat.notify(idN, builder.build());
                        idN++;

                        editor.putInt("value", def);
                        editor.apply();
                    }
                }
            }
            if (sw2) {
                if (gold_history_def != 0) {
                    if (goldValue != gold_history_def) {

                        Log.d("nnnn", goldValue + " goldValue");
                        Log.d("nnnn", gold_history_def + " gold_history_def");
                        @SuppressLint("SdCardPath") NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "n")
                                .setContentTitle("تم تحديث قيمة الذهب")
                                .setSmallIcon(R.drawable.notification)
                                .setAutoCancel(true)
                                .setContentIntent(pendingIntent)
                                .setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/" + R.raw.sound));

                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
                        managerCompat.notify(idN1, builder.build());
                        idN1++;

                        editor.putInt("goldValue", gold_history_def);
                        editor.apply();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel channel = new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);
                            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
                            AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build();
                            Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName() + "/" + R.raw.sound);
                            channel.setSound(sound, attributes);
                            assert notificationManager != null;
                            notificationManager.createNotificationChannel(channel);

                        }

                    }
                }
            }
        }
    }
}
