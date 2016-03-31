package com.missingbullet.robschatzfinalproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by robschatz on 3/8/16.
 */
public class Smokepuff extends GameObject {

    //radius of the smokepuff
    public int r;

    public Smokepuff(int x, int y)
    {

        super.x = x;
        super.y = y;
        r = 5;
    }

    //rate at which the smokepuffs will move across the screen
    public void update()

    {
       x -= 10;

    }

    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(x - r, y - r, r, paint);
        canvas.drawCircle(x - r + 2, y - r - 2, r, paint);
        canvas.drawCircle(x - r + 4, y - r + 1, r, paint);
    }
}
