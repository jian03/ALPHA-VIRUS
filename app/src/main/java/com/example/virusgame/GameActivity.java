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

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    TextView count, finalscore;
    TextView startcountdown;
    private int btncount2 = 0, score = 0;
    private int media_pos;
    private static MediaPlayer mp;
    ImageButton btnpause, btnsoundon, btnclose, btngo, btnrefresh, btnhome;
    LinearLayout q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20, lineargameover;
    EditText ae1, ae2, ae3, ae4, ae5, ae6, ae7, ae8, ae9, ae10, ae11, ae12, ae13, ae14, ae15, ae16, ae17, ae18, ae19, ae20;
    Button ab1, ab2, ab3, ab4, ab5, ab6, ab7, ab8, ab9, ab10, ab11, ab12, ab13, ab14, ab15, ab16, ab17, ab18, ab19, ab20, overbtnhome, overbtnrefresh;
    ImageView v1, v2, v3, v4, v5, v6, v7, v8, v9, v10;
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
        lineargameover = (LinearLayout)inflater.inflate(R.layout.activity_gameover, null);
        /*final LinearLayout linearnotanswer = (LinearLayout)inflater.inflate(R.layout.activity_notanswer, null);
        final LinearLayout linearrightanswer = (LinearLayout)inflater.inflate(R.layout.activity_rightanswer, null);*/
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
        //linearrightanswer.setBackgroundColor(Color.parseColor("#99000000"));
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
        startcountdown = findViewById(R.id.count3);
        overbtnhome = lineargameover.findViewById(R.id.overbtnhome);
        overbtnrefresh = lineargameover.findViewById(R.id.overbtnrefresh);
        finalscore = lineargameover.findViewById(R.id.finalscore);

        ae1 = findViewById(R.id.ae1); // answer editText
        ae2 = findViewById(R.id.ae2);ae3 = findViewById(R.id.ae3);ae4 = findViewById(R.id.ae4);ae5 = findViewById(R.id.ae5);ae6 = findViewById(R.id.ae6);ae7 = findViewById(R.id.ae7);ae8 = findViewById(R.id.ae8);ae9 = findViewById(R.id.ae9);ae10 = findViewById(R.id.ae10);ae11 = findViewById(R.id.ae11);ae12 = findViewById(R.id.ae12);ae13 = findViewById(R.id.ae13);ae14 = findViewById(R.id.ae14);ae15 = findViewById(R.id.ae15);ae16 = findViewById(R.id.ae16);ae17 = findViewById(R.id.ae17);ae18 = findViewById(R.id.ae18);ae19 = findViewById(R.id.ae19);ae20 = findViewById(R.id.ae20);
        ab1 = findViewById(R.id.ab1); // answer button
        ab2 = findViewById(R.id.ab2);ab3 = findViewById(R.id.ab3);ab4 = findViewById(R.id.ab4);ab5 = findViewById(R.id.ab5);ab6 = findViewById(R.id.ab6);ab7 = findViewById(R.id.ab7);ab8 = findViewById(R.id.ab8);ab9 = findViewById(R.id.ab9);ab10 = findViewById(R.id.ab10);ab11 = findViewById(R.id.ab11);ab12 = findViewById(R.id.ab12);ab13 = findViewById(R.id.ab13);ab14 = findViewById(R.id.ab14);ab15 = findViewById(R.id.ab15);ab16 = findViewById(R.id.ab16);ab17 = findViewById(R.id.ab17);ab18 = findViewById(R.id.ab18);ab19 = findViewById(R.id.ab19);ab20 = findViewById(R.id.ab20);
        q1 = findViewById(R.id.q1); // question layout
        q2 = findViewById(R.id.q2);q3 = findViewById(R.id.q3);q4 = findViewById(R.id.q4);q5 = findViewById(R.id.q5);q6 = findViewById(R.id.q6);q7 = findViewById(R.id.q7);q8 = findViewById(R.id.q8);q9 = findViewById(R.id.q9);q10 = findViewById(R.id.q10);q11 = findViewById(R.id.q11);q12 = findViewById(R.id.q12);q13 = findViewById(R.id.q13);q14 = findViewById(R.id.q14);q15 = findViewById(R.id.q15);q16 = findViewById(R.id.q16);q17 = findViewById(R.id.q17);q18 = findViewById(R.id.q18);q19 = findViewById(R.id.q19);q20 = findViewById(R.id.q20);
        v1 = findViewById(R.id.v1); // virus imageView
        v2 = findViewById(R.id.v2);v3 = findViewById(R.id.v3);v4 = findViewById(R.id.v4);v5 = findViewById(R.id.v5);v6 = findViewById(R.id.v6);v7 = findViewById(R.id.v7);v8 = findViewById(R.id.v8);v9 = findViewById(R.id.v9);v10 = findViewById(R.id.v10);

        addContentView(frame, paramframe);
        frame.setVisibility(View.INVISIBLE);
        addContentView(lineargameover, paramlinear);
        lineargameover.setVisibility(View.INVISIBLE);
        /*addContentView(linearnotanswer, paramlinear);
        linearnotanswer.setVisibility(View.INVISIBLE);
        addContentView(linearrightanswer, paramlinear);
        linearrightanswer.setVisibility(View.INVISIBLE);*/

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
        ab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae1.getWindowToken(), 0); // 키보드 내리기
                if(ae1.getText().toString().equals("object")) {
                    score += 10;
                } else {
                    v1.setVisibility(View.VISIBLE);
                }
                q1.setVisibility(View.INVISIBLE);
                q2.setVisibility(View.VISIBLE);
            }
        });
        ab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae2.getWindowToken(), 0); // 키보드 내리기
                if(ae2.getText().toString().equals("earth")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
                        if (v1.getVisibility() == View.INVISIBLE) {
                            v1.setVisibility(View.VISIBLE);
                            break;
                        } else {
                            v2.setVisibility(View.VISIBLE);
                        }
                    }
                }
                q2.setVisibility(View.INVISIBLE);
                q3.setVisibility(View.VISIBLE);
            }
        });
        ab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae3.getWindowToken(), 0); // 키보드 내리기
                if(ae3.getText().toString().equals("ocean")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
                        if (v1.getVisibility() == View.INVISIBLE) {
                            v1.setVisibility(View.VISIBLE);
                            break;
                        } else if (v2.getVisibility() == View.INVISIBLE) {
                            v2.setVisibility(View.VISIBLE);
                            break;
                        } else {
                            v3.setVisibility(View.VISIBLE);
                        }
                    }
                }
                q3.setVisibility(View.INVISIBLE);
                q4.setVisibility(View.VISIBLE);
            }
        });
        ab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae4.getWindowToken(), 0); // 키보드 내리기
                if(ae4.getText().toString().equals("center") || ae4.getText().toString().equals("recent")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
                        if (v1.getVisibility() == View.INVISIBLE) {
                            v1.setVisibility(View.VISIBLE);
                            break;
                        } else if (v2.getVisibility() == View.INVISIBLE) {
                            v2.setVisibility(View.VISIBLE);
                            break;
                        } else if (v3.getVisibility() == View.INVISIBLE) {
                            v3.setVisibility(View.VISIBLE);
                            break;
                        } else {
                            v4.setVisibility(View.VISIBLE);
                        }
                    }
                }
                q4.setVisibility(View.INVISIBLE);
                q5.setVisibility(View.VISIBLE);
            }
        });
        ab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae5.getWindowToken(), 0); // 키보드 내리기
                if(ae5.getText().toString().equals("hunter")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                        } else {
                            v5.setVisibility(View.VISIBLE);
                        }
                    }
                }
                q5.setVisibility(View.INVISIBLE);
                q6.setVisibility(View.VISIBLE);
            }
        });
        ab6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae6.getWindowToken(), 0); // 키보드 내리기
                if(ae6.getText().toString().equals("sheep")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                        } else {
                            v6.setVisibility(View.VISIBLE);
                        }
                    }
                }
                q6.setVisibility(View.INVISIBLE);
                q7.setVisibility(View.VISIBLE);
            }
        });
        ab7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae7.getWindowToken(), 0); // 키보드 내리기
                if(ae7.getText().toString().equals("piece")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                        } else {
                            v7.setVisibility(View.VISIBLE);
                        }
                    }
                }
                q7.setVisibility(View.INVISIBLE);
                q8.setVisibility(View.VISIBLE);
            }
        });
        ab8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae8.getWindowToken(), 0); // 키보드 내리기
                if(ae8.getText().toString().equals("french")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                        } else {
                            v8.setVisibility(View.VISIBLE);
                        }
                    }
                }
                q8.setVisibility(View.INVISIBLE);
                q9.setVisibility(View.VISIBLE);
            }
        });
        ab9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae9.getWindowToken(), 0); // 키보드 내리기
                if(ae9.getText().toString().equals("voice")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                        } else {
                            v9.setVisibility(View.VISIBLE);
                        }
                    }
                }
                q9.setVisibility(View.INVISIBLE);
                q10.setVisibility(View.VISIBLE);
            }
        });
        ab10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae10.getWindowToken(), 0); // 키보드 내리기
                if(ae10.getText().toString().equals("code")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                q10.setVisibility(View.INVISIBLE);
                if(v10.getVisibility() == View.VISIBLE) {
                    isRun = false;
                    isFirst = false;
                    mp.stop();
                    finalscore.setText("SCORE : " + String.valueOf(score));
                    lineargameover.setVisibility(View.VISIBLE);
                } else {
                    q11.setVisibility(View.VISIBLE);
                }
            }
        });
        ab11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae11.getWindowToken(), 0); // 키보드 내리기
                if(ae11.getText().toString().equals("mars")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                q11.setVisibility(View.INVISIBLE);
                if(v10.getVisibility() == View.VISIBLE) {
                    isRun = false;
                    isFirst = false;
                    mp.stop();
                    finalscore.setText("SCORE : " + String.valueOf(score));
                    lineargameover.setVisibility(View.VISIBLE);
                } else {
                    q12.setVisibility(View.VISIBLE);
                }
            }
        });
        ab12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae12.getWindowToken(), 0); // 키보드 내리기
                if(ae12.getText().toString().equals("fruit")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                q12.setVisibility(View.INVISIBLE);
                if(v10.getVisibility() == View.VISIBLE) {
                    isRun = false;
                    isFirst = false;
                    mp.stop();
                    finalscore.setText("SCORE : " + String.valueOf(score));
                    lineargameover.setVisibility(View.VISIBLE);
                } else {
                    q13.setVisibility(View.VISIBLE);
                }
            }
        });
        ab13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae13.getWindowToken(), 0); // 키보드 내리기
                if(ae13.getText().toString().equals("mouse")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                q13.setVisibility(View.INVISIBLE);
                if(v10.getVisibility() == View.VISIBLE) {
                    isRun = false;
                    isFirst = false;
                    mp.stop();
                    finalscore.setText("SCORE : " + String.valueOf(score));
                    lineargameover.setVisibility(View.VISIBLE);
                } else {
                    q14.setVisibility(View.VISIBLE);
                }
            }
        });
        ab14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae14.getWindowToken(), 0); // 키보드 내리기
                if(ae14.getText().toString().equals("plane")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                q14.setVisibility(View.INVISIBLE);
                if(v10.getVisibility() == View.VISIBLE) {
                    isRun = false;
                    isFirst = false;
                    mp.stop();
                    finalscore.setText("SCORE : " + String.valueOf(score));
                    lineargameover.setVisibility(View.VISIBLE);
                } else {
                    q15.setVisibility(View.VISIBLE);
                }
            }
        });
        ab15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae15.getWindowToken(), 0); // 키보드 내리기
                if(ae15.getText().toString().equals("weather")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                q15.setVisibility(View.INVISIBLE);
                if(v10.getVisibility() == View.VISIBLE) {
                    isRun = false;
                    isFirst = false;
                    mp.stop();
                    finalscore.setText("SCORE : " + String.valueOf(score));
                    lineargameover.setVisibility(View.VISIBLE);
                } else {
                    q16.setVisibility(View.VISIBLE);
                }
            }
        });
        ab16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae16.getWindowToken(), 0); // 키보드 내리기
                if(ae16.getText().toString().equals("phone")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                q16.setVisibility(View.INVISIBLE);
                if(v10.getVisibility() == View.VISIBLE) {
                    isRun = false;
                    isFirst = false;
                    mp.stop();
                    finalscore.setText("SCORE : " + String.valueOf(score));
                    lineargameover.setVisibility(View.VISIBLE);
                } else {
                    q17.setVisibility(View.VISIBLE);
                }
            }
        });
        ab17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae17.getWindowToken(), 0); // 키보드 내리기
                if(ae17.getText().toString().equals("puzzle")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                q17.setVisibility(View.INVISIBLE);
                if(v10.getVisibility() == View.VISIBLE) {
                    isRun = false;
                    isFirst = false;
                    mp.stop();
                    finalscore.setText("SCORE : " + String.valueOf(score));
                    lineargameover.setVisibility(View.VISIBLE);
                } else {
                    q18.setVisibility(View.VISIBLE);
                }
            }
        });
        ab18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae18.getWindowToken(), 0); // 키보드 내리기
                if(ae18.getText().toString().equals("camera")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                q18.setVisibility(View.INVISIBLE);
                if(v10.getVisibility() == View.VISIBLE) {
                    isRun = false;
                    isFirst = false;
                    mp.stop();
                    finalscore.setText("SCORE : " + String.valueOf(score));
                    lineargameover.setVisibility(View.VISIBLE);
                } else {
                    q19.setVisibility(View.VISIBLE);
                }
            }
        });
        ab19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae19.getWindowToken(), 0); // 키보드 내리기
                if(ae19.getText().toString().equals("program")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                q19.setVisibility(View.INVISIBLE);
                if(v10.getVisibility() == View.VISIBLE) {
                    isRun = false;
                    isFirst = false;
                    mp.stop();
                    finalscore.setText("SCORE : " + String.valueOf(score));
                    lineargameover.setVisibility(View.VISIBLE);
                } else {
                    q20.setVisibility(View.VISIBLE);
                }
            }
        });
        ab20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(ae20.getWindowToken(), 0); // 키보드 내리기
                if(ae20.getText().toString().equals("pencil")) {
                    score += 10;
                } else {
                    for(int i=0; i<=1; i++) {
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
                q20.setVisibility(View.INVISIBLE);
                isRun = false;
                isFirst = false;
                mp.stop();

                finalscore.setText("SCORE : " + String.valueOf(score));
                lineargameover.setVisibility(View.VISIBLE);
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
                finalscore.setText("SCORE : " + String.valueOf(score));
                lineargameover.setVisibility(View.VISIBLE);
                /*timer = new Timer();
                timer.schedule(gameovertt, 0, 3000);*/
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