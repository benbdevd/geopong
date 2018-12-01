package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import static edu.threegees.geopong.JConstants.*;

/**
 *  GamePaddle
 *      CAN DRAW ITSELF
 *      CAN UPDATE ITSELF
 */

public class GamePaddle extends GameObject
{
    private Rect mDimensions;
    private int mWidth;
    private int mHeight;

    public int mPaddleType;

    public GamePaddle(GameView gameView, int paddleType)
    {
        super(gameView);

        mPaddleType = paddleType;

        switch (paddleType)
        {
            case PADDLE_TYPE_HOME:
                mXPosition = GameView.pGameWidth/2 - PONG_PADDLE_WIDTH/2;
                mYPosition = GameView.pGameHeight - (PONG_PADDLE_HEIGHT + PADDLE_EDGE_PADDING);

                mWidth = PONG_PADDLE_WIDTH;
                mHeight = PONG_PADDLE_HEIGHT;
                break;

            case PADDLE_TYPE_AWAY:
                mXPosition = GameView.pGameWidth/2 - PONG_PADDLE_WIDTH/2;
                mYPosition = (PONG_PADDLE_HEIGHT - PADDLE_EDGE_PADDING);

                mWidth = PONG_PADDLE_WIDTH;
                mHeight = PONG_PADDLE_HEIGHT;
                break;

            case PADDLE_TYPE_SP:
                mXPosition = 0;
                mYPosition = PADDLE_EDGE_PADDING;

                mWidth = GameView.pGameWidth;
                mHeight = SINGLE_PADDLE_HEIGHT;
                break;
        }

        mDimensions = new Rect((int) mXPosition, (int) mYPosition, (int)mXPosition + mWidth, (int)mYPosition  + mHeight);
    }

    @Override
    public void update()
    {
        changeXBy( (float) (mXVelocity  * PADDLE_MOVE_X_PERCENT));
        mXVelocity *= PADDLE_TRIM_VEL_PERCENT;

        setDimensions((int) mXPosition, (int) mYPosition, (int)mXPosition + mWidth, (int)mYPosition  + mHeight);
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawRect(mDimensions, paint);
    }

    public void setDimensions(int left, int top, int right, int bottom)
    {
        mDimensions.set(left, top, right, bottom);
    }

    public void setXPos(int xPos)
    {
        mXPosition = xPos;
    }

    public int getWidth()
    {
        return mWidth;
    }

    public int getHeight()
    {
        return mHeight;
    }


    @Override
    public void changeXBy(float newX)
    {
        if(mXPosition + newX > 0)
        {
            if(mXPosition + PONG_PADDLE_WIDTH  + newX < mGameView.pGameWidth)
            {
                mXPosition += newX;
            }
            else
            {
                mXPosition = mGameView.pGameWidth - PONG_PADDLE_WIDTH;
            }
        }
        else
        {
            mXPosition = 0;
        }
    }
}
