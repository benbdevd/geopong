package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;


public abstract class GameObject
{
    protected float mXPosition;
    protected float mYPosition;

    protected float mXVelocity;
    protected float mYVelocity;

    protected int mCollideLeft;
    protected int mCollideTop;
    protected int mCollideRight;
    protected int mCollideBottom;

    public GameObject()
    {
        GameView.allGameObj.add(this);
    }


    /**
     * TO IMPLEMENT: DELTA_TIME
     */
    public void update()
    {
    }

    public void draw(Canvas canvas, Paint paint)
    {
    }

    public float getY()
    {
        return mYPosition;
    }

    public float getX()
    {
        return mXPosition;
    }

    public void setX(float newX)
    {
        mXPosition = newX;
    }

    public void setY(float newY)
    {
        mYPosition = newY;
    }

    public void setXVelocity(float velocity)
    {
        mXVelocity = velocity;
    }

    public void setYVelocity(float velocity)
    {
        mYVelocity = velocity;
    }

    public void changeXBy(float newX)
    {
        mXPosition += newX;
    }

    public void changeYBy(float newY)
    {
        mYPosition += newY;
    }

    public int getCollideLeft()
    {
        return mCollideLeft;
    }

    public int getCollideTop()
    {
        return mCollideTop;
    }

    public int getCollideRight()
    {
        return mCollideRight;
    }

    public int getCollideBottom()
    {
        return mCollideBottom;
    }
}
