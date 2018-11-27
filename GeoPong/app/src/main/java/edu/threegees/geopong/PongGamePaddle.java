package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class PongGamePaddle extends PongGameObject
{
    private Rect dimensions;
    private int width;
    private int height;
    //Rect rect, boolean isHomePaddle

    public PongGamePaddle( int paddleType)
    {
        switch (paddleType)
        {
            case JConstants.PADDLE_TYPE_HOME:
                mXPosition = PongGameView.pPongGameWidth - JConstants.PADDLE_PADX;
                mYPosition = PongGameView.pPongGameHeight - JConstants.PADDLE_PADY;

                width = JConstants.PONG_PADDLE_WIDTH;
                height = JConstants.PONG_PADDLE_HEIGHT;
                break;

            case JConstants.PADDLE_TYPE_AWAY:
                mXPosition = JConstants.PADDLE_PADX;
                mYPosition = JConstants.PADDLE_PADY;

                width = JConstants.PONG_PADDLE_WIDTH;
                height = JConstants.PONG_PADDLE_HEIGHT;
                break;

            case JConstants.PADDLE_TYPE_SP:
                mXPosition = 0;
                mYPosition = 0;

                width = PongGameView.pPongGameWidth;
                height = PongGameView.pPongGameHeight;
                break;
        }

        dimensions = new Rect((int) mXPosition, (int) mXPosition, JConstants.PONG_PADDLE_WIDTH, JConstants.PONG_PADDLE_HEIGHT);
    }

    @Override
    public void update()
    {

    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawRect(dimensions, paint);
    }
}
