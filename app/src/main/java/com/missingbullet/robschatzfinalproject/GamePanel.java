package com.missingbullet.robschatzfinalproject;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by robschatz on 11/29/15.
 */

//Context context is getting it from (this) in the Game.java activity
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    //These are the dimensions of the game
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -5;

    private MainThread thread;

    //the background variable which is instantiated in the surfaceCreated class below
    private Background bg;

    //the player variable which is instantiated in the surfaceCreated class below
    private Player player;

    // //the smoke puff variable which is instantiated in the surfaceCreated class below
    private ArrayList<Smokepuff> smoke;

    private long smokeStartTimer;


    //This is the constructor, which is called when the GamePanel is called
    public GamePanel(Context context) {
        super(context);


        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override

    //This is SurFaceView constructor
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        int counter = 0;

        while (retry && counter > 1000) {
            counter++;
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //the instance of bg is instantiated, and
        //this is where the background of the game will be passed in via the drawable resource
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));

        //instantiates an instance of the Player from the Player class
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 65, 25, 3);

        //this will make the background image move slowly off the screen. But what happens when it's less than zero? See the update method in
        //Background.java, which will prevent this from happening by making any value less than zero equal to zero
        /*bg.setVector(-5); replaced by MOVESPEED*/

        //instantiates the smokepuff
        smoke = new ArrayList<Smokepuff>();

        smokeStartTimer = System.nanoTime();

        //we can safely start the game loop
        thread.setRunning(true);
        if(!thread.isAlive())
        {
            thread.start();
        }


    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //we want the screen to NOT be moving until the player presses down on the screen



        if (event.getAction() == MotionEvent.ACTION_DOWN)

        {
            if (!player.getPlaying()) {
                player.setPlaying(true);
            }

            player.setUp(true);
            Log.d("ontouch", "down");
            return true;
        }

        if (event.getAction() == MotionEvent.ACTION_UP)
        {
            player.setUp(false);
            Log.d("ontouch", "up");
            return true;

        }

        return super.onTouchEvent(event);
    }

    public void update() {
        //only update the background and player objects if the player is playing
        if (player.getPlaying()) {
            //updates bg
            bg.update();
            //updates player
            player.update();
            //the timer for the so that the smoke puffs don't all come out all at once
            long elapsed  = (System.nanoTime() - smokeStartTimer)/1000000;

            if (elapsed > 120)
            {
                smoke.add(new Smokepuff(player.getX(), player.getY() + 10));//+10 so that the smoke comes out from the tail

                //resets the smoke puff timer after the smoke comes out
                smokeStartTimer = System.nanoTime();
            }

            //This for loop will remove the smoke puffs from the GamePanel once they are off the screen/reach position -10
            for (int i = 0; i < smoke.size(); i++)
            {
                smoke.get(i).update();

                if (smoke.get(i).getX() < - 10)
                {
                    smoke.remove(i);
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        //getWidth gives us the entire surfaceView of the device, not just the game so we need to divide it by the size
        //of our game screen WIDTH. Same goes for Height

        final float scaleFactorX = getWidth() / WIDTH * 1.f;
        final float scaleFactorY = (float) getHeight() / HEIGHT * 1.f;

        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);

            bg.draw(canvas);
            player.draw(canvas);


            //sp will equal each value in the smoke array and when the loop iterates through each one, it will draw the smoke puff
            for (Smokepuff sp : smoke)
            {
                sp.draw(canvas);
            }

            //After scaling and drawing, we return back to the saved state. If we didn't call this, it would just
            //keep scaling and never stop
            canvas.restoreToCount(savedState);
        }

    }
}
