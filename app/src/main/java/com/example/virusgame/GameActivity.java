package com.example.virusgame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    TextView count;
    static TimerTask tt;
    final Timer timer = new Timer();
    private int btncount = 0;
    private int btncount2 = 0;
    private int media_pos;
    private static MediaPlayer mp;
    ImageButton btnsoundon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        count = findViewById(R.id.timer);
        ImageButton btnpause = findViewById(R.id.btn_pause);
        btnsoundon = findViewById(R.id.btn_soundon);

        btnpause.setOnClickListener(btnListener);
        btnsoundon.setOnClickListener(btnListener);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //상단바 숨기기

        tt = startTimerTask();
        timer.schedule(tt, 0, 10);
        startTimerTask();

        mp = MediaPlayer.create(this, R.raw.backmusic);
        mp.setLooping(true);
        mp.start();
    }
    public TimerTask startTimerTask() {
        TimerTask timet = new TimerTask() {
            int minute = 1;
            int seconds = minute *60;
            int milliseconds  = seconds*100;

            @Override
            public void run()
            {
                milliseconds--;
                final int seconds = (int) (milliseconds / 100) % 60 ;
                final int minutes = (int) ((milliseconds / (100*60)) % 60);
                count.post(new Runnable() {
                    @Override
                    public void run() {
                        if (String.valueOf(minutes).length() == 1 && String.valueOf(seconds).length() == 1) {
                            count.setText("0"+minutes+":0"+seconds);
                        } else if (String.valueOf(minutes).length() == 1 && String.valueOf(seconds).length() == 2) {
                            count.setText("0"+minutes+":"+seconds);
                        }
                        if(milliseconds==0)
                        {
                            count.setText("TimeOver!");
                            mp.stop();
                            mp.release();
                            tt.cancel();
                            tt = null;
                        }
                    }
                });
            }
        };
        return timet;
    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_pause:
                    if(btncount % 2 == 0) {
                        tt.cancel();
                    } else if(btncount % 2 == 1) {
                        tt = startTimerTask();
                        timer.schedule(tt, 0, 10);
                    }
                    btncount++;
                    break;
                case R.id.btn_soundon:
                    if(btncount2 % 2 == 0) {
                        btnsoundon.setImageResource(R.drawable.soundoff);
                        mp.pause();
                        media_pos = mp.getCurrentPosition();
                    } else if(btncount2 % 2 == 1) {
                        btnsoundon.setImageResource(R.drawable.soundon);
                        mp.seekTo(media_pos);
                        mp.start();
                    }
                    btncount2++;
                    break;
            }
        }
    };

}