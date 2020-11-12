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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    TextView count;
    TextView startcountdown;
    private int btncount = 0, btncount2 = 0;
    private int media_pos;
    private static MediaPlayer mp;
    ImageButton btnpause, btnsoundon, btnclose, btngo, btnrefresh, btnhome;
    TimerThread thread;
    MyThread thread2;
    Timer timer;
    TimerTask gameovertt;

    boolean loopFlag = true;
    boolean isFirst = true;
    boolean isRun = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout linear = (LinearLayout)inflater.inflate(R.layout.activity_countdown, null);
        final LinearLayout lineargameover = (LinearLayout)inflater.inflate(R.layout.activity_gameover, null);
        final LinearLayout.LayoutParams paramlinear = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        final FrameLayout.LayoutParams paramframe = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        linear.setBackgroundColor(Color.parseColor("#99000000")); // 배경 불투명도 설정
        addContentView(linear, paramlinear);
        View view = (View)getLayoutInflater().inflate(R.layout.activity_pause, null);
        final FrameLayout frame = view.findViewById(R.id.frame);
        frame.setBackgroundColor(Color.parseColor("#99000000"));
        lineargameover.setBackgroundColor(Color.parseColor("#99000000"));

        count = findViewById(R.id.timer);
        btnpause = findViewById(R.id.btn_pause);
        btnclose = view.findViewById(R.id.btn_close);
        btngo = view.findViewById(R.id.btn_go);
        btnhome = view.findViewById(R.id.btn_home);
        btnrefresh = view.findViewById(R.id.btn_refresh);
        btnsoundon = findViewById(R.id.btn_soundon);
        startcountdown = findViewById(R.id.count3);

        addContentView(frame, paramframe);
        frame.setVisibility(View.INVISIBLE);
        addContentView(lineargameover, paramlinear);
        lineargameover.setVisibility(View.INVISIBLE);

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frame.setVisibility(View.VISIBLE);
                isRun = false;
                isFirst = false;
            }
        });
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frame.setVisibility(View.GONE);
                isRun = true;
                isFirst = true;
                mp.stop();
            }
        });
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frame.setVisibility(View.GONE);
                isRun = true;
                isFirst = true;
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                mp.release();
            }
        });
        btnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
                finish(); //현재 Acticity 종료
                mp.stop();
            }
        });
        btnsoundon.setOnClickListener(btnListener);
        frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true; // 터치 막기
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //상단바 숨기기
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mp = MediaPlayer.create(this, R.raw.backmusic);
        mp.setLooping(true);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                thread = new TimerThread();
                thread.start();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                linear.setVisibility(View.GONE);
                thread2 = new MyThread();
                thread2.start();
                mp.start();
            }
        }, 4000); // 4초 뒤에 카운트다운 시작
        gameovertt = new TimerTask() {
            @Override
            public void run() {
                lineargameover.setVisibility(View.VISIBLE);
            }
        };
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
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
        public void handleMessage(Message msg){ // 100초
            if(msg.what == 1){
                count.setText("Time : "+String.valueOf(msg.arg1));
            }else if (msg.what == 2){
                timer = new Timer();
                timer.schedule(gameovertt, 0, 3000);
            }
        }
    };
    Handler autoCountHandler = new Handler(){ // 321
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
                int countDown=11; // 100+1초 동안
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