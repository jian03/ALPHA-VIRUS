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
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    TextView count, finalscore, finalscore2;
    TextView startcountdown, hinttext;
    private int btncount2 = 0, score = 0, hintcount = 0;
    private int media_pos;
    private static MediaPlayer mp;
    ImageButton btnpause, btnsoundon, btnclose, btngo, btnrefresh, btnhome;
    LinearLayout q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20, lineargameover, lineargameclear;
    EditText ae1, ae2, ae3, ae4, ae5, ae6, ae7, ae8, ae9, ae10, ae11, ae12, ae13, ae14, ae15, ae16, ae17, ae18, ae19, ae20;
    Button ab1, ab2, ab3, ab4, ab5, ab6, ab7, ab8, ab9, ab10, ab11, ab12, ab13, ab14, ab15, ab16, ab17, ab18, ab19, ab20, overbtnhome, overbtnrefresh, btnhint, clearbtnhome, clearbtnrefresh;
    ImageView v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, imgq1;
    TimerThread thread;
    MyThread thread2;
    InputMethodManager mInputMethodManager;
    Button ab[] = new Button[20];
    Integer[] Rid_button = {
            R.id.ab1, R.id.ab2, R.id.ab3, R.id.ab4, R.id.ab5,
            R.id.ab6, R.id.ab7, R.id.ab8, R.id.ab9, R.id.ab10,
            R.id.ab11, R.id.ab12, R.id.ab13, R.id.ab14, R.id.ab15,
            R.id.ab16, R.id.ab17, R.id.ab18, R.id.ab19, R.id.ab20
    };
    EditText ae[] = new EditText[20];
    Integer[] Rid_edittext = {
            R.id.ae1, R.id.ae2, R.id.ae3, R.id.ae4, R.id.ae5,
            R.id.ae6, R.id.ae7, R.id.ae8, R.id.ae9, R.id.ae10,
            R.id.ae11, R.id.ae12, R.id.ae13, R.id.ae14, R.id.ae15,
            R.id.ae16, R.id.ae17, R.id.ae18, R.id.ae19, R.id.ae20
    };
    String[] answer = {
            "object", "earth", "ocean", "center", "hunter", "sheep", "piece", "french",
            "voice", "code", "mars", "fruit", "mouse", "plane", "weather", "phone", "puzzle",
            "camera", "program", "pencil"
    };
    String a;
    boolean loopFlag = true;
    boolean isFirst = true;
    boolean isRun = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout linear = (LinearLayout)inflater.inflate(R.layout.activity_countdown, null);
        lineargameover = (LinearLayout)inflater.inflate(R.layout.activity_gameover, null);
        lineargameclear = (LinearLayout)inflater.inflate(R.layout.activity_gameclear, null);
        final LinearLayout.LayoutParams paramlinear = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        final FrameLayout.LayoutParams paramframe = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        linear.setBackgroundColor(Color.parseColor("#99000000")); // 배경 불투명도 설정
        lineargameover.setBackgroundColor(Color.parseColor("#99000000"));
        lineargameclear.setBackgroundColor(Color.parseColor("#99000000"));
        addContentView(linear, paramlinear);
        View view = (View)getLayoutInflater().inflate(R.layout.activity_pause, null);
        final FrameLayout frame = view.findViewById(R.id.frame);
        frame.setBackgroundColor(Color.parseColor("#99000000"));

        count = findViewById(R.id.timer);
        btnpause = findViewById(R.id.btn_pause);
        btnclose = view.findViewById(R.id.btn_close);
        btngo = view.findViewById(R.id.btn_go);
        btnhome = view.findViewById(R.id.btn_home);
        btnrefresh = view.findViewById(R.id.btn_refresh);
        btnsoundon = findViewById(R.id.btn_soundon);
        btnhint = findViewById(R.id.btn_hint);
        startcountdown = findViewById(R.id.count3);
        overbtnhome = lineargameover.findViewById(R.id.overbtnhome);
        overbtnrefresh = lineargameover.findViewById(R.id.overbtnrefresh);
        clearbtnhome = lineargameclear.findViewById(R.id.clearbtnhome);
        clearbtnrefresh = lineargameclear.findViewById(R.id.clearbtnrefresh);
        finalscore = lineargameover.findViewById(R.id.finalscore);
        finalscore2 = lineargameclear.findViewById(R.id.finalscore2);
        hinttext = findViewById(R.id.hint_text);

        ae1 = findViewById(R.id.ae1); // answer editText
        ae2 = findViewById(R.id.ae2);ae3 = findViewById(R.id.ae3);ae4 = findViewById(R.id.ae4);ae5 = findViewById(R.id.ae5);ae6 = findViewById(R.id.ae6);ae7 = findViewById(R.id.ae7);ae8 = findViewById(R.id.ae8);ae9 = findViewById(R.id.ae9);ae10 = findViewById(R.id.ae10);ae11 = findViewById(R.id.ae11);ae12 = findViewById(R.id.ae12);ae13 = findViewById(R.id.ae13);ae14 = findViewById(R.id.ae14);ae15 = findViewById(R.id.ae15);ae16 = findViewById(R.id.ae16);ae17 = findViewById(R.id.ae17);ae18 = findViewById(R.id.ae18);ae19 = findViewById(R.id.ae19);ae20 = findViewById(R.id.ae20);
        ab1 = findViewById(R.id.ab1); // answer button
        ab2 = findViewById(R.id.ab2);ab3 = findViewById(R.id.ab3);ab4 = findViewById(R.id.ab4);ab5 = findViewById(R.id.ab5);ab6 = findViewById(R.id.ab6);ab7 = findViewById(R.id.ab7);ab8 = findViewById(R.id.ab8);ab9 = findViewById(R.id.ab9);ab10 = findViewById(R.id.ab10);ab11 = findViewById(R.id.ab11);ab12 = findViewById(R.id.ab12);ab13 = findViewById(R.id.ab13);ab14 = findViewById(R.id.ab14);ab15 = findViewById(R.id.ab15);ab16 = findViewById(R.id.ab16);ab17 = findViewById(R.id.ab17);ab18 = findViewById(R.id.ab18);ab19 = findViewById(R.id.ab19);ab20 = findViewById(R.id.ab20);
        q1 = findViewById(R.id.q1); // question layout
        q2 = findViewById(R.id.q2);q3 = findViewById(R.id.q3);q4 = findViewById(R.id.q4);q5 = findViewById(R.id.q5);q6 = findViewById(R.id.q6);q7 = findViewById(R.id.q7);q8 = findViewById(R.id.q8);q9 = findViewById(R.id.q9);q10 = findViewById(R.id.q10);q11 = findViewById(R.id.q11);q12 = findViewById(R.id.q12);q13 = findViewById(R.id.q13);q14 = findViewById(R.id.q14);q15 = findViewById(R.id.q15);q16 = findViewById(R.id.q16);q17 = findViewById(R.id.q17);q18 = findViewById(R.id.q18);q19 = findViewById(R.id.q19);q20 = findViewById(R.id.q20);
        v1 = findViewById(R.id.v1); // virus imageView
        v2 = findViewById(R.id.v2);v3 = findViewById(R.id.v3);v4 = findViewById(R.id.v4);v5 = findViewById(R.id.v5);v6 = findViewById(R.id.v6);v7 = findViewById(R.id.v7);v8 = findViewById(R.id.v8);v9 = findViewById(R.id.v9);v10 = findViewById(R.id.v10);
        imgq1 = findViewById(R.id.imgq1); // question imageView

        addContentView(frame, paramframe);
        frame.setVisibility(View.INVISIBLE);
        addContentView(lineargameover, paramlinear);
        lineargameover.setVisibility(View.INVISIBLE);
        addContentView(lineargameclear, paramlinear);
        lineargameclear.setVisibility(View.INVISIBLE);

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frame.setVisibility(View.VISIBLE);
                isRun = false;
                isFirst = false;
                mp.pause();
            }
        });
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frame.setVisibility(View.GONE);
                isRun = true;
                isFirst = true;
                mp.start();
            }
        });
        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frame.setVisibility(View.GONE);
                isRun = true;
                isFirst = true;
                mp.start();
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
        overbtnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.release();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        overbtnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
                finish(); //현재 Acticity 종료
            }
        });
        clearbtnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.release();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        clearbtnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
                finish(); //현재 Acticity 종료
            }
        });
        btnhint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hintcount++;
                btnhint.setEnabled(false);
                if(hintcount > 2) {
                    btnhint.setEnabled(false);
                } else if(q1.getVisibility() == View.VISIBLE) {
                    hinttext.setText("사물");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q2.getVisibility() == View.VISIBLE) {
                    hinttext.setText("지구");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q3.getVisibility() == View.VISIBLE) {
                    hinttext.setText("대양");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q4.getVisibility() == View.VISIBLE) {
                    hinttext.setText("중심");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q5.getVisibility() == View.VISIBLE) {
                    hinttext.setText("사냥꾼");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q6.getVisibility() == View.VISIBLE) {
                    hinttext.setText("양");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q7.getVisibility() == View.VISIBLE) {
                    hinttext.setText("조각");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q8.getVisibility() == View.VISIBLE) {
                    hinttext.setText("과일");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q9.getVisibility() == View.VISIBLE) {
                    hinttext.setText("목소리");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q10.getVisibility() == View.VISIBLE) {
                    hinttext.setText("코드");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q11.getVisibility() == View.VISIBLE) {
                    hinttext.setText("화성");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q12.getVisibility() == View.VISIBLE) {
                    hinttext.setText("프랑스어");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q13.getVisibility() == View.VISIBLE) {
                    hinttext.setText("쥐");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q14.getVisibility() == View.VISIBLE) {
                    hinttext.setText("비행기");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q15.getVisibility() == View.VISIBLE) {
                    hinttext.setText("날씨");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q16.getVisibility() == View.VISIBLE) {
                    hinttext.setText("휴대전화");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q17.getVisibility() == View.VISIBLE) {
                    hinttext.setText("퍼즐");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q18.getVisibility() == View.VISIBLE) {
                    hinttext.setText("카메라");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q19.getVisibility() == View.VISIBLE) {
                    hinttext.setText("프로그램");
                    hinttext.setVisibility(View.VISIBLE);
                } else if(q20.getVisibility() == View.VISIBLE) {
                    hinttext.setText("연필");
                    hinttext.setVisibility(View.VISIBLE);
                }
            }
        });

        final ArrayList<Integer> layoutList = new ArrayList<>();
        layoutList.add(R.drawable.q1);layoutList.add(R.drawable.q2);layoutList.add(R.drawable.q3);layoutList.add(R.drawable.q4);layoutList.add(R.drawable.q5);
        layoutList.add(R.drawable.q6);layoutList.add(R.drawable.q7);layoutList.add(R.drawable.q8);layoutList.add(R.drawable.q9);layoutList.add(R.drawable.q10);
        layoutList.add(R.drawable.q11);layoutList.add(R.drawable.q12);layoutList.add(R.drawable.q13);layoutList.add(R.drawable.q14);layoutList.add(R.drawable.q15);
        layoutList.add(R.drawable.q16);layoutList.add(R.drawable.q17);layoutList.add(R.drawable.q18);layoutList.add(R.drawable.q19);layoutList.add(R.drawable.q20);
        Collections.shuffle(layoutList); // 리스트 섞기



        imgq1.setImageResource(layoutList.get(0)); // 섞은 리스트 중 첫번째 사진으로 설정
        for(int i=1; i<=layoutList.size(); i++) {
            if(layoutList.get(i-1).equals(getResources().getIdentifier("q"+i, "drawable", getPackageName()))) {
                ab[i] = (Button) findViewById(Rid_button[i]);
                ab[i].setVisibility(View.VISIBLE);
                ae[i] = (EditText) findViewById(Rid_edittext[i]);
                ae[i].setVisibility(View.VISIBLE);
                answer[i] = (String)answer[i];
                ae[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i=1; i<=layoutList.size(); i++) {
                            mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            mInputMethodManager.hideSoftInputFromWindow(ae[i].getWindowToken(), 0); // 키보드 내리기
                            hinttext.setVisibility(View.INVISIBLE);
                            if(ae[i].getText().toString().equals(answer[i])) {
                                score += 10;
                                if(v9.getVisibility() == View.VISIBLE) {
                                    v9.setVisibility(View.INVISIBLE);
                                } else if(v8.getVisibility() == View.VISIBLE) {
                                    v8.setVisibility(View.INVISIBLE);
                                } else if(v7.getVisibility() == View.VISIBLE) {
                                    v7.setVisibility(View.INVISIBLE);
                                } else if(v6.getVisibility() == View.VISIBLE) {
                                    v6.setVisibility(View.INVISIBLE);
                                } else if(v5.getVisibility() == View.VISIBLE) {
                                    v5.setVisibility(View.INVISIBLE);
                                } else if(v4.getVisibility() == View.VISIBLE) {
                                    v4.setVisibility(View.INVISIBLE);
                                } else if(v3.getVisibility() == View.VISIBLE) {
                                    v3.setVisibility(View.INVISIBLE);
                                } else if(v2.getVisibility() == View.VISIBLE) {
                                    v2.setVisibility(View.INVISIBLE);
                                } else if(v1.getVisibility() == View.VISIBLE) {
                                    v1.setVisibility(View.INVISIBLE);
                                }
                            } else {
                                for(int j=0; j<=1; j++) {
                                    if (v1.getVisibility() == View.INVISIBLE) {
                                        v1.setVisibility(View.VISIBLE);
                                        break;
                                    } else if (v2.getVisibility() == View.INVISIBLE) {
                                        v2.setVisibility(View.VISIBLE);
                                        break;
                                    } else if (v3.getVisibility() == View.INVISIBLE) {
                                        v3.setVisibility(View.VISIBLE);
                                        break;
                                    } else if (v4.getVisibility() == View.INVISIBLE) {
                                        v4.setVisibility(View.VISIBLE);
                                        break;
                                    } else if (v5.getVisibility() == View.INVISIBLE) {
                                        v5.setVisibility(View.VISIBLE);
                                        break;
                                    } else if (v6.getVisibility() == View.INVISIBLE) {
                                        v6.setVisibility(View.VISIBLE);
                                        break;
                                    } else if (v7.getVisibility() == View.INVISIBLE) {
                                        v7.setVisibility(View.VISIBLE);
                                        break;
                                    } else if (v8.getVisibility() == View.INVISIBLE) {
                                        v8.setVisibility(View.VISIBLE);
                                        break;
                                    } else if (v9.getVisibility() == View.INVISIBLE) {
                                        v9.setVisibility(View.VISIBLE);
                                        break;
                                    } else {
                                        v10.setVisibility(View.VISIBLE);
                                        isRun = false;
                                        isFirst = false;
                                        mp.stop();
                                        finalscore.setText("SCORE : " + String.valueOf(score));
                                        lineargameover.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                            imgq1.setImageResource(layoutList.get(i));
                            if(hintcount <= 2) {
                                btnhint.setEnabled(true);
                            }
                            if(v10.getVisibility() == View.VISIBLE) {
                                isRun = false;
                                isFirst = false;
                                mp.stop();
                                finalscore.setText("SCORE : " + String.valueOf(score));
                                lineargameover.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }
        }


        btnsoundon.setOnClickListener(btnListener);
        frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true; // 터치 막기
            }
        });
        lineargameover.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //상단바 숨기기
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 화면 안꺼지게

        mp = MediaPlayer.create(this, R.raw.backmusic);
        mp.setLooping(true);


        new Handler().post(new Runnable() { // 321
            @Override
            public void run() {
                thread = new TimerThread();
                thread.start();
            }
        });

        new Handler().postDelayed(new Runnable() { // 100초
            @Override
            public void run() {
                linear.setVisibility(View.GONE);
                thread2 = new MyThread();
                thread2.start();
                mp.start();

                q1.setVisibility(View.VISIBLE);
                btnhint.setVisibility(View.VISIBLE);
                btnsoundon.setVisibility(View.VISIBLE);
                btnpause.setVisibility(View.VISIBLE);

            }
        }, 4000); // 4초 뒤에 카운트다운 시작
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
                // 게임 오버
                mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae1.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae2.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae3.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae4.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae5.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae6.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae7.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae8.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae10.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae11.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae12.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae13.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae14.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae15.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae16.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae17.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae18.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae19.getWindowToken(), 0); // 키보드 내리기
                mInputMethodManager.hideSoftInputFromWindow(ae20.getWindowToken(), 0); // 키보드 내리기
                finalscore.setText("SCORE : " + String.valueOf(score));
                lineargameover.setVisibility(View.VISIBLE);
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
                int countDown=101; // 100+1초 동안
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