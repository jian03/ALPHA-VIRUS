package com.example.virusgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class GameActivity extends AppCompatActivity {
    TextView count;
    TextView startcountdown;
    private int btncount = 0;
    private int btncount2 = 0;
    private int media_pos;
    private static MediaPlayer mp;
    ImageButton btnpause, btnsoundon;
    TimerThread thread;
    MyThread thread2;

    boolean loopFlag = true;
    boolean isFirst = true;
    boolean isRun = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout linear = (LinearLayout)inflater.inflate(R.layout.activity_countdown, null);
        LinearLayout.LayoutParams paramlinear = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        linear.setBackgroundColor(Color.parseColor("#99000000")); // 배경 불투명도 설정
        addContentView(linear, paramlinear);

        count = findViewById(R.id.timer);
        btnpause = findViewById(R.id.btn_pause);
        btnsoundon = findViewById(R.id.btn_soundon);
        startcountdown = (TextView) findViewById(R.id.count3);

        btnpause.setOnClickListener(btnListener);
        btnsoundon.setOnClickListener(btnListener);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //상단바 숨기기

        mp = MediaPlayer.create(this, R.raw.backmusic);
        mp.setLooping(true);

        Thread mThread = new Thread(); // 3 2 1 카운트다운 스레드
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                thread = new TimerThread();
                thread.start();
            }
        });

        Thread mThread2 = new Thread(); // 100초 카운트 스레드
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                linear.setVisibility(View.GONE);
                thread2 = new MyThread();
                thread2.start();
            }
        }, 4000); // 4초 뒤에 카운트다운 시작
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_pause:
                    if(btncount % 2 == 0) { // 일시정지
                        isFirst = false;
                        isRun = false;
                    }else if((btncount % 2 == 1)){ // 재생
                        isRun = true;
                    }
                    btncount++;
                    break;
                case R.id.btn_soundon:
                    if(btncount2 % 2 == 0) { // 음소거
                        btnsoundon.setImageResource(R.drawable.soundoff);
                        mp.pause();
                        media_pos = mp.getCurrentPosition();
                    } else if(btncount2 % 2 == 1) { // 재생
                        btnsoundon.setImageResource(R.drawable.soundon);
                        mp.seekTo(media_pos);
                        mp.start();
                    }
                    btncount2++;
                    break;
            }
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 1){
                count.setText(String.valueOf(msg.arg1));
            }else if (msg.what == 2){
                count.setText(String.valueOf(msg.obj));
            }
        }
    };
    Handler autoCountHandler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 1){
                startcountdown.setText(String.valueOf(msg.arg1));
            }else if (msg.what == 2){
                startcountdown.setText(String.valueOf(msg.obj));
            }
        }
    };

    class TimerThread extends Thread {
        public void run() {
            try {
                int startcount = 4;
                while (loopFlag) { // true
                    if (isRun) {
                        startcount--;
                        Message message = new Message();
                        message.what = 1;
                        message.arg1 = startcount;
                        autoCountHandler.sendMessage(message);
                        if (startcount == 0) {
                            message = new Message();
                            message.what = 2;
                            message.obj = "Start!";
                            autoCountHandler.sendMessage(message);

                            //theread 종료되게 설정
                            loopFlag = false;
                            mp.start();
                        }
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException ex) {
            }
        }
    }

    class MyThread extends Thread {
        public void run() {
            try{
                int countDown=101;
                loopFlag = true;
                while (loopFlag){ // true
                    if (isRun) {
                        countDown--;
                        Message message = new Message();
                        message.what = 1;
                        message.arg1 = countDown;
                        handler.sendMessage(message);
                        if (countDown == 0) {
                            message = new Message();
                            message.what = 2;
                            message.obj = "Finish!!";
                            handler.sendMessage(message);

                            //theread 종료되게 설정
                            loopFlag = false;
                            mp.stop();
                        }
                    }
                    Thread.sleep(1000);

                }
            } catch (Exception e){
            }
        }
    }

    public void onBackPressed() { //뒤로 가기 버튼 불가능
    }
}