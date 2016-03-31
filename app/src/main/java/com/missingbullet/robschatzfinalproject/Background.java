package com.missingbullet.robschatzfinalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by robschatz on 3/5/16.
 */
public class Background {

    private Bitmap image;
    private int x, y, dx;

    //The constructor - Bitmap is being passed into the class, which is going to equal image
    public Background (Bitmap res)
    {
        image = res;
        dx = GamePanel.MOVESPEED;
    }

    public void update()
    {
        x+=dx;
        if(x<-GamePanel.WIDTH)
        {
            x = 0;

        }

    }

    public void draw(Canvas canvas)
    {

        canvas.drawBitmap(image, x, y,null);

        if(x<0)
        {
            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
        }
    }

}
