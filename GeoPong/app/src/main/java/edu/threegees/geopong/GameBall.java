package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

import static edu.threegees.geopong.JConstants.*;

/**
 * GameBall
 * CAN DRAW ITSELF
 * CAN UPDATE ITSELF
 */

public class GameBall extends GameObject
{
    public GameBall(GameView gameView)
    {
        super(gameView);

        mXPosition = GameView.pGameWidth / 2;
        mYPosition = GameView.pGameHeight / 2;

        mXVelocity = INITIAL_SPEEDS[GameView.pDifficulty];
        mYVelocity = INITIAL_SPEEDS[GameView.pDifficulty];
    }

    @Override
    public void update()
    {
        boolean isGoingUp = mYVelocity < 0;
        boolean collideY;
        boolean collideX;

        checkGameBounds();

        /**
         * HANDLE PADDLE COLLISIONS FIRST
         * Should be moved to paddles checking so we can more easily get which paddle it collided with
         */

        for (GameObject paddle : mGameView.pAllGameObjects)
        {
            if(paddle instanceof GamePaddle)
            {
                int paddleType = ((GamePaddle) paddle).mPaddleType;

                collideY = mYPosition  > paddle.getY() && mYPosition < paddle.getY() + PONG_PADDLE_HEIGHT;

                if(collideY)
                {
                    collideWithPaddle(paddleType);
                }
            }
        }

        if (mYPosition > GameView.pGameHeight || mYPosition < 0)
        {
            switch (mGameView.pLastPlayerToHit)
            {
                case PADDLE_TYPE_SP:
                    break;

                case PADDLE_TYPE_AWAY:
                    break;

                case PADDLE_TYPE_HOME:
                    break;

                default:
                    break;
            }
            respawnBall();
        }

        /**
         * HANDLE COLLISION WITH WALLS (SPEED DOES NOT CHANGE)
         */
        /*
        if (mXPosition + PONG_BALL_RADIUS >= GameView.pGameWidth || mXPosition - PONG_BALL_RADIUS <= 0)
        {
            collideWithWall();
        }
        */

        changeXBy(mXVelocity);
        changeYBy(mYVelocity);
    }

    //Reset ball position to center
    //Change if you don't care for it but I'm adding a random element
    public void respawnBall()
    {
        Random ran = new Random();
        //The +- 30 is to prevent the ball from spawning inside the wall
        setX(ran.nextInt(GameView.pGameWidth - 30) + 30);
        //setX(GameView.pGameWidth/2);
        setY(GameView.pGameHeight / 2);
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawCircle(mXPosition, mYPosition, PONG_BALL_RADIUS, paint);
    }

    public void checkPaddleCollision()
    {

    }

    public void collideWithPaddle(int paddleType)
    {
        mYVelocity = (-mYVelocity + SPEED_INCREMENTS[GameView.pDifficulty]);
    }

    public void checkGameBounds()
    {
        if (mXPosition >= GameView.pGameWidth)
        {
            setX(GameView.pGameWidth - PONG_BALL_RADIUS - MINISCULE_BALL_PADDING);
            collideWithWall();
        }
        if (mXPosition <= 0)
        {
            setX(0 + PONG_BALL_RADIUS + MINISCULE_BALL_PADDING);
            collideWithWall();
        }
    }

    public void collideWithWall()
    {
        setXVelocity(-mXVelocity);
    }
}
