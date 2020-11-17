package com.currencyapplication.currencyapplicatio;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

 import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class SplachActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    private static int SPLASH_SCREEN = 3000;

    public static String url = "http://syria-ex.com/mzMvc/mzApi/mzSyriaExpoApi/mzCurrenciesData.php?license_key=app_key_136544876";
    InterstitialAd interstitialAd1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);


        ImageView imageView = findViewById(R.id.imageView3);
        TextView textView12 = findViewById(R.id.textView12);
        TextView textView11 = findViewById(R.id.textView11);
        TextView textView13 = findViewById(R.id.textView13);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
/*
        Intent intent = new Intent(SplachActivity.this, MyService.class);
        startService(intent);

        Calendar cal = Calendar.getInstance();
        PendingIntent pintent = PendingIntent.getService(SplachActivity.this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 3000, pintent);
*/
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animtion);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animtion);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        interstitialAd1 = new InterstitialAd(this);
        interstitialAd1.setAdUnitId("ca-app-pub-7449694416039010/7104236925");
        interstitialAd1.loadAd(new AdRequest.Builder().build());
        interstitialAd1.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Intent intent = new Intent(SplachActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        imageView.setAnimation(topAnim);
        textView11.setAnimation(bottomAnim);
        textView12.setAnimation(bottomAnim);
        textView13.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (interstitialAd1.isLoaded()) {
                    interstitialAd1.show();
                } else {
                    Intent intent = new Intent(SplachActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    interstitialAd1.show();
                    finish();
                }
            }
        },SPLASH_SCREEN);
    }
}