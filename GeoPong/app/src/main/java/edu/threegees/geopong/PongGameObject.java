package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class PongGameObject
{
    protected int mXPos;
    protected int mYPos;
    protected int mHeight;
    protected int mLength;


    protected long mLastDrawNanoTime;


    public void update()
    {

    }

    public void draw(Canvas canvas)
    {

    }
    //the draw method
    public void draw(Canvas canvas, Paint paint) {

        //set the colour
        paint.setARGB(200, 0, 200, 0);

        canvas.drawRect(new Rect(mXPos, mYPos, mXPos + mLength,mYPos + mHeight), paint);

        this.mLastDrawNanoTime = System.nanoTime();
    }

    public void setMovingVectior()
    {

    }

    public int getY()
    {
        return mYPos;
    }

    public int getX()
    {
        return mXPos;
    }
}
