package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import static edu.threegees.geopong.JConstants.*;


public abstract class GameObject
{
    protected float mXPosition;
    protected float mYPosition;

    protected float mXVelocity;
    protected float mYVelocity;

    protected long mLastUpdateTime;

    public void update()
    {
        changeXBy(mXVelocity);
        changeYBy(mYVelocity);

        mLastUpdateTime = System.nanoTime();
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
}
