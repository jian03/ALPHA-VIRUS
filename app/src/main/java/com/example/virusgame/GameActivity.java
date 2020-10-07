package com.example.virusgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    List<String> word = new ArrayList<String>() {{
        add("apple"); add("like"); add("yellow");
        add("blue"); add("pen"); add("orange");
        add("dance"); add("cup"); add("test");
    }};
    CountDownTimer countDownTimer;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //상단바 숨기기

        Collections.shuffle(word);

        new CountDownTimer(100*1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(""+ millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
            }
        }.start();
    }

    class GameView extends View {
        public GameView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) { // 그림을 그려주는 부분
            int w = getWidth();
            int h = getHeight();
            Paint paint = new Paint();
        }

    }
}