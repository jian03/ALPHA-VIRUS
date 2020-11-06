/*
package com.example.virusgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Locale;

public class CountActivity extends AppCompatActivity {
    TextView countdown;
    private int count = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //상단바 숨기기

        countdown = (TextView) findViewById(R.id.count3);
        countdown.setText(String.valueOf(count));


        autoCountHandler.postDelayed(autoCountRunnable, 1000);

    }
    private Handler autoCountHandler = new Handler();
    private Runnable autoCountRunnable = new Runnable() {
        public void run() {
            count--;
            countdown.setText(String.valueOf(count));
            if(count > 0) {
                autoCountHandler.postDelayed(autoCountRunnable, 1000);
            } else if(count == 0) {
                countdown.setText("START!");
                Intent intent = new Intent(getApplication(), GameActivity.class);
                startActivity(intent);
                CountActivity.this.finish();
            }
        }
    };
}*/
