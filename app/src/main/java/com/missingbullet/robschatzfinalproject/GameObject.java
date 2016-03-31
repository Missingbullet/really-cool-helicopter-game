package com.missingbullet.robschatzfinalproject;

import android.graphics.Rect;

/**
 * Created by robschatz on 3/5/16.
 */

//All objects in the game will have the attributes below
public abstract class GameObject {
    protected int x;
    protected int y;

    //the x and y vectors
    protected int dx;
    protected int dy;

    protected int width;
    protected int height;

    public void setX(int x)
    {
        this.x=x;
    }

    public void setY(int y)
    {
        this.y=y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getDx()
    {
        return dx;
    }

    public int getDy()
    {
        return dy;
    }

    //This method will allow for collision detection, as each obkect on the screen will
    //take up the space of a rectangle
    public Rect getRectangle()
    {
        return new Rect(x, y, x+width, y+height);

    }

}
