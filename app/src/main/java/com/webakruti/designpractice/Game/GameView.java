package com.webakruti.designpractice.Game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.webakruti.designpractice.R;

/**
 * Created by DELL on 4/16/2019.
 */

public class GameView extends View {

    //canvas
    private int canvasWidth;
    private int canvasHeight;
    private int score;

    //bird
    private Bitmap bird[] = new Bitmap[2];
    private int birdX = 10;
    private int birdY;
    private int birdSpeed;

    //blue ball
    private int blueX;
    private int blueY;
    private int blueSpeed = 15;
    private Paint bluePaint = new Paint();

    //black ball
    private int blackX;
    private int blackY;
    private int blackSpeed = 20;
    private Paint blackPaint = new Paint();

    //background
    private Bitmap bgImage;

    //score
    private Paint scorePaint = new Paint();

    //level
    private Paint levelPaint = new Paint();

    //life
    private Bitmap life[] = new Bitmap[2];
    private int lifeCount;

    //status check
    private boolean touch_flg = false;

    public GameView(Context context) {
        super(context);

        bird[0] = BitmapFactory.decodeResource(getResources(),R.drawable.birds_01);
        bird[1] = BitmapFactory.decodeResource(getResources(),R.drawable.birds_03);

        bgImage = BitmapFactory.decodeResource(getResources(),R.drawable.back2);

        bluePaint.setColor(Color.BLUE);
        bluePaint.setAntiAlias(false);

        blackPaint.setColor(Color.BLACK);
        blackPaint.setAntiAlias(false);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(32);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        levelPaint.setColor(Color.DKGRAY);
        levelPaint.setTextSize(32);
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setTextAlign(Paint.Align.RIGHT);
        levelPaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.heart);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heartgrey1);

        //first position
        birdY = 500;
        score = 0;
        lifeCount = 3;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        //bird
        //canvas.drawBitmap(bird,0,0,null);
        int minBirdY = bird[0].getHeight();
        int maxBirdY = canvasHeight - bird[0].getHeight() * 3;

        birdY += birdSpeed;
        if(birdY < minBirdY) birdY = minBirdY;
        if(birdY > maxBirdY) birdY = maxBirdY;
        birdSpeed += 2;

        if(touch_flg)
        {
            //flap wings
            canvas.drawBitmap(bird[1],birdX,birdY,null);
            touch_flg = false;

        }else{
            canvas.drawBitmap(bird[0],birdX,birdY,null);
        }

        canvas.drawBitmap(bgImage,0,0,null);

        //blue
        blueX -= blueSpeed;
        if(hitCheck(blueX,blueY))
        {
            score += 10;
            blueX = -100;
        }
        if(blueX < 0)
        {
            blueX = canvasWidth + 20;
            blueY = (int) Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;
        }

        canvas.drawCircle(blueX,blueY,10,bluePaint);

        //black
        blackX -= blackSpeed;
        if(hitCheck(blackX,blackY))
        {
            blackX = -100;
            lifeCount--;
            if(lifeCount == 0)
            {
                //gameover
                Toast.makeText(getContext(),"Game Over!!",Toast.LENGTH_SHORT).show();

            }
        }
        if(blackX < 0)
        {
            blackX = canvasWidth + 200;
            blackY = (int) Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;
        }

        canvas.drawCircle(blackX,blackY,20,blackPaint);

        //score
        canvas.drawText("Score : " + score,20,60,scorePaint);
        //level
        canvas.drawText("Lv.1", canvas.getWidth()/2,60,levelPaint);
        //life
        for(int i = 0;i<3;i++)
        {
            int x = (int) (560 + life[0].getWidth() * 1.5 * i);
            int y =30;

            if(i<lifeCount)
            {
                canvas.drawBitmap(life[0],x,y,null);
            }
            else
                canvas.drawBitmap(life[1],x,y,null);
        }


        /*canvas.drawBitmap(life[0],560,10,null);
        canvas.drawBitmap(life[0],650,10,null);
        canvas.drawBitmap(life[1],740,10,null);*/
    }

    private boolean hitCheck(int x, int y) {
        if(birdX < x && x < (birdX + bird[0].getWidth()) &&
                birdY < y && y < (birdY +bird[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch_flg = true;
            birdSpeed = -20;
        }
        return true;
    }
}
