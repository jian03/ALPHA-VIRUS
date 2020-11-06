package com.example.virusgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class GameActivity extends AppCompatActivity {
    TextView count;
    private int btncount = 0;
    private int btncount2 = 0;
    private int media_pos;
    private static MediaPlayer mp;
    ImageButton btnpause, btnsoundon;
    Thread thread;

    boolean loopFlag = true;
    boolean isFirst = true;
    boolean isRun = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        count = findViewById(R.id.timer);
        btnpause = findViewById(R.id.btn_pause);
        btnsoundon = findViewById(R.id.btn_soundon);

        btnpause.setOnClickListener(btnListener);
        btnsoundon.setOnClickListener(btnListener);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //상단바 숨기기

        mp = MediaPlayer.create(this, R.raw.backmusic);
        mp.setLooping(true);
        mp.start();

        thread = new MyThread();
        thread.start();
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
            }else if (msg.what ==2){
                count.setText(String.valueOf(msg.obj));
            }
        }
    };


    class MyThread extends Thread {
        public void run() {
            try{
                int countDown=100;
                while (loopFlag){ // true
                    Thread.sleep(1000);
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

                }
            } catch (Exception e){
            }
        }
    }

    public void onBackPressed() { //뒤로 가기 버튼 불가능
    }
}