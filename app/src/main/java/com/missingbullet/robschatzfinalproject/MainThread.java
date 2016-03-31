package com.missingbullet.robschatzfinalproject;

import android.graphics.Canvas;
import android.view.SurfaceHolder;



//This class will contain the main game loop


public class MainThread extends Thread {
//we want to cap the FPS at 30
private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    //MainThread constructor
    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        //calls the constructor of the super class
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {

        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        long frameCount = 0;

        //This will return the frame rate in milliseconds
        long targetTime = 1000 / FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {

                    //this is the heart and soul of the game. Every time we run through the game we are going to update it once
                    //and draw on the canvas
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
            }
            finally{
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }



            //the time it takes for each game loop above to run through once
            timeMillis = (System.nanoTime() - startTime) / 1000000;

            //how long we wait until the game loop starts again
            waitTime = targetTime-timeMillis;

            try{
                this.sleep(waitTime);
            }catch(Exception e){}

            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == FPS)
            {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount =0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }
    public void setRunning(boolean b)
    {
        running=b;
    }
}



