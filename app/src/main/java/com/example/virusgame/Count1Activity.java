package com.example.virusgame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Count1Activity extends AppCompatActivity {
    SoundPool soundPool;
    int soundID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //상단바 숨기기

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundID = soundPool.load(this,R.raw.count_sound, 1);

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                startActivity(new Intent(Count1Activity.this, CountStartActivity.class));
                soundPool.play(soundID,1f, 1f, 0, 0, 1f);
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 1000);
    }
}