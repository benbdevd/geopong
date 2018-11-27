package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class PongGameObject
{
    protected float mXPosition;
    protected float mYPosition;

    protected int mXVelocity;
    protected int mYVelocity;

    protected long mLastUpdateTime;

    public void update()
    {

    }

    public void draw(Canvas canvas, Paint paint)
    {

    }

    public void setMovingVectior()
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
}
