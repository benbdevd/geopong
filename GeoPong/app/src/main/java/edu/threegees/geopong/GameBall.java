package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;

import static edu.threegees.geopong.JConstants.*;


public class GameBall extends GameObject
{
    public GameBall()
    {
        super();

        mXPosition = GameView.pGameWidth/2;
        mYPosition = GameView.pGameHeight/2;

        mXVelocity = INITIAL_SPEEDS[GameView.pDifficulty];
        mYVelocity = INITIAL_SPEEDS[GameView.pDifficulty];
    }

    @Override
    public void update()
    {
        /**
         * HANDLE PADDLE COLLISIONS FIRST
         */
        for(GameObject paddle : GameView.allGameObj)
        {
            if (paddle instanceof GamePaddle)
            {
                {
                    if (mYPosition > paddle.getY() && mYPosition < paddle.getY() + PONG_PADDLE_HEIGHT)
                    {
                        if (mXPosition > paddle.getX() && mXPosition < paddle.getX() + PONG_PADDLE_WIDTH)
                        {
                            collideWithPaddle();
                        }
                    }
                }
            }
            /**
             *
             */
            if (mYPosition > GameView.pGameHeight || mYPosition < 0)
            {
                reset();
            }
        }

        changeXBy(mXVelocity);
        changeYBy(mYVelocity);

        mCollideLeft = (int) mXPosition - PONG_BALL_RADIUS;
        mCollideTop = (int) mYPosition - PONG_BALL_RADIUS;
        mCollideRight = (int) mXPosition + PONG_BALL_RADIUS;
        mCollideBottom = (int) mYPosition + PONG_BALL_RADIUS;

        /**
         * HANDLE COLLISION WITH WALLS (SPEED DOES NOT CHANGE)
         */
        if(mCollideRight >= GameView.pGameWidth || mCollideLeft <= 0)
        {
            setXVelocity(-mXVelocity);
        }

    }

    /*
    @Override
    public void draw(Canvas canvas, Paint paint)
    {

        Random ran = new Random();
        //ball Starting position randomly launches ball in a certain direction
        //Think about adding bounds so as to not launch at max or min height
        mYPosition = GAME_LENGTH/2;
        mXPosition = (GAME_HEIGHT * ran.nextInt());

    }
    */

    public void reset()
    {
        setX(GameView.pGameWidth/2);
        setY(GameView.pGameHeight/2);
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawCircle(mXPosition, mYPosition, PONG_BALL_RADIUS, paint);
    }

    public void collideWithPaddle()
    {
        setYVelocity(-mYVelocity * SPEED_INCREMENTS[GameView.pDifficulty]);
    }

}
