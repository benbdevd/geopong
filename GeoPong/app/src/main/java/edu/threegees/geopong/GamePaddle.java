package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import static edu.threegees.geopong.JConstants.*;

public class GamePaddle extends GameObject
{
    private Rect dimensions;
    private int mWidth;
    private int mHeight;
    //Rect rect, boolean isHomePaddle

    public GamePaddle(int paddleType)
    {
        switch (paddleType)
        {
            case PADDLE_TYPE_HOME:
                mXPosition = GameView.pGameWidth - PADDLE_PADX;
                mYPosition = GameView.pGameHeight - PADDLE_PADY;

                mWidth = PONG_PADDLE_WIDTH;
                mHeight = PONG_PADDLE_HEIGHT;
                break;

            case PADDLE_TYPE_AWAY:
                mXPosition = PADDLE_PADX;
                mYPosition = PADDLE_PADY;

                mWidth = (int) mXPosition + PONG_PADDLE_WIDTH;
                mHeight = (int) mYPosition + PONG_PADDLE_HEIGHT;
                break;

            case PADDLE_TYPE_SP:
                mXPosition = 0;
                mYPosition = 10;

                mWidth = GameView.pGameWidth;
                mHeight = (int) mYPosition + PONG_PADDLE_HEIGHT;
                break;
        }

        dimensions = new Rect((int) mXPosition, (int) mYPosition, mWidth, mHeight);

    }

    @Override
    public void update()
    {
        changeXBy(mXVelocity);
        changeYBy(mYVelocity);

        mCollideLeft = (int) mXPosition;
        mCollideTop = (int) mYPosition;
        mCollideRight = (int) mXPosition + mWidth;
        mCollideBottom = (int) mYPosition + mHeight;

        mLastUpdateTime = System.nanoTime();
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawRect(dimensions, paint);
    }

    public void setDimensions(int left, int top, int right, int bottom)
    {
        dimensions.set(left, top, right, bottom);
    }
}
