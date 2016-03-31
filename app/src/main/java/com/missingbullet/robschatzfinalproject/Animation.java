package com.missingbullet.robschatzfinalproject;

import android.graphics.Bitmap;

/**
 * Created by robschatz on 3/6/16.
 */
public class Animation {

    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;

    //this variable is used so that we can play through some animations only once - like the explosion drawable
    private boolean playedOnce;

    public void setFrames (Bitmap[] frames)
    {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void setDelay (long d)
    {
        delay = d;
    }

    private void setFrame (int i)
    {
        currentFrame = i;
    }

    //another update timer - works like the others inside of the app
    public void update()
    {
        long elapsed  = (System.nanoTime() - startTime)/1000000;

        if (elapsed>delay)
        {

            currentFrame ++;
            startTime = System.nanoTime();
        }

        if (currentFrame == frames.length)
        {
            currentFrame = 0;
            playedOnce = true;
        }

    }

    public Bitmap getImage()
    {
        return frames[currentFrame];
    }

    public int getFrame()
    {
        return currentFrame;

    }

    public boolean playedOnce()
    {
        return playedOnce;
    }
}
