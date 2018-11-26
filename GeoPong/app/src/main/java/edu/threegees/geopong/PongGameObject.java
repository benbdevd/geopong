package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class PongGameObject
{
    protected int mXPosition;
    protected int mYPosition;
    protected int mHeight;
    protected int mLength;

    protected int mXVelocity;
    protected int mYVelocity;

    protected long mLastUpdateTime;

    public void update()
    {

    }

    public void draw(Canvas canvas, Paint paint) {

        //set the colour to white
        paint.setARGB(255, 255, 255, 255);

        canvas.drawRect(new Rect(mXPosition, mYPosition, mXPosition + mLength, mYPosition + mHeight), paint);

        this.mLastUpdateTime = System.nanoTime();
    }

    public void setMovingVectior()
    {

    }

    public int getY()
    {
        return mYPosition;
    }

    public int getX()
    {
        return mXPosition;
    }
}
