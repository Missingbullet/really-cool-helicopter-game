package com.missingbullet.robschatzfinalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;


/**
 * Created by robschatz on 3/6/16.
 */
public class Player extends GameObject{
    private Bitmap spritesheet;
    private int score;

    //this variable will help in calculating acceleration. It's dy + a (a for acceleration) - only for the up direction

    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public Player (Bitmap res, int w, int h, int numFrames)
    {

        x = 100;
        y = GamePanel.HEIGHT/2;
        dy = 0;
        height = h;
        width = w;

        //This is an array which will store the three images(frames) of the player from the helicopter drawable
        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        //this constrcutor will have the array be three elements - the first element is the first image, secomd element the second
        //image, and the third element the third image
        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i * width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();
    }

    //this will make the helicopter go up when the user presses down
    public void setUp (boolean b)
    {
        up = b;
    };

    //this will update the score based on how much time has passed - every 100ms

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        if(up){
            dy -=1;

        }
        else{
            dy +=1;
        }

        if(dy>14)dy = 10;
        if(dy<-14)dy = -2;

        y += dy*2;

    }



    /*public void update()
    {
        long elapsed = (System.nanoTime() - startTime)/1000000;

        if (elapsed>100){

            score ++;

            startTime = System.nanoTime();

    }
        animation.update();

        if (up){

            //acceleration rate of the player object
            dy -= 1;

        }
        else {
            dy += 1;
        }

        if (dy > 14) dy = 1;
        if (dy < 14) dy = -1;

        y += dy * 2;


    }*/

    public void draw (Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(), x, y, null);

    }

    public int getScore()
    {
        return score;

    }

    public boolean getPlaying()
    {
        return playing;
    }

    public void setPlaying(boolean b)
    {
        playing = b;

    }

    public void resetDYA()
    {
        dy = 0;
    }

    public void resetScore()
    {
        score = 0;
    }



}
