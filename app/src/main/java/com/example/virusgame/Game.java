package com.example.virusgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

public class Game extends View {

    int scrw, scrh; // screen width, height
    Paint p = new Paint();
    private GameThread T;

    public Game(Context con, AttributeSet at) {
        super(con, at);
    }


    @Override
    protected void onSizeChanged(int sw, int sh, int esw, int esh) {
        super.onSizeChanged(sw, sh, esw, esh);
        this.scrw = sw;
        this.scrh = sh;

        if(T == null) {
            T = new GameThread();
            T.start();
        }
    }

    @Override
    // 뷰가 윈도우에서 분리될 때마다 발생
    protected void onDetachedFromWindow() {
        T.run = false;
        super.onDetachedFromWindow();
    }

    @Override
    // 화면에 그림
    protected void onDraw(Canvas canvas) {
        p.setColor(0xFF000000);
        p.setTextSize(scrw/16);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return true;
    }

    class GameThread extends Thread {
        public boolean run = true;

        @Override
        public void run() {
            while(run) {
                try {
                    postInvalidate(); //뷰에서 이미지를 분리시킨다.
                    sleep(10); //0.01초 지연
                } catch(Exception e) {

                }
            }
        }
    }



}
