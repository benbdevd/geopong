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
    private boolean isGoingUp;

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
        /**
         * HANDLE COLLISION WITH WALLS (SPEED DOES NOT CHANGE)
         */
        checkGameBounds();

        /**
         * HANDLE PADDLE COLLISIONS (SPEED CHANGES)
         */
        checkPaddleCollisions();

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

        mXVelocity = INITIAL_SPEEDS[GameView.pDifficulty];
        mYVelocity = INITIAL_SPEEDS[GameView.pDifficulty];
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawCircle(mXPosition, mYPosition, PONG_BALL_RADIUS, paint);
    }

    public void checkPaddleCollisions()
    {
        isGoingUp = mYVelocity < 0;
        boolean collideY;
        boolean collideX1;
        boolean collideX2;

        for (GameObject paddle : mGameView.pAllGameObjects)
        {
            if (paddle instanceof GamePaddle)
            {
                int paddleType = ((GamePaddle) paddle).mPaddleType;

                collideX1 = mXPosition + PONG_BALL_RADIUS > paddle.getX() && mXPosition + PONG_BALL_RADIUS < paddle.getX() + ((GamePaddle) paddle).getWidth();
                collideX2 = mXPosition - PONG_BALL_RADIUS > paddle.getX() && mXPosition - PONG_BALL_RADIUS < paddle.getX() + ((GamePaddle) paddle).getWidth();

                if (isGoingUp)
                {
                    collideY = mYPosition - PONG_BALL_RADIUS > paddle.getY() && mYPosition - PONG_BALL_RADIUS < paddle.getY() + ((GamePaddle) paddle).getHeight();
                }
                else
                {
                    collideY = mYPosition + PONG_BALL_RADIUS > paddle.getY() && mYPosition + PONG_BALL_RADIUS < paddle.getY() + ((GamePaddle) paddle).getHeight();
                }

                if (collideY && (collideX1 || collideX2))
                {
                    collideWithPaddle(paddleType);
                }
            }
        }
    }

    public void collideWithPaddle(int paddleType)
    {
        if(Math.abs(mYVelocity) <= SPEED_CAPS[GameView.pDifficulty])
        {
            switch (paddleType)
            {
                case PADDLE_TYPE_HOME:
                    mYVelocity += SPEED_INCREMENTS[GameView.pDifficulty];
                    break;

                default:
                    mYVelocity -= SPEED_INCREMENTS[GameView.pDifficulty];
                    break;
            }
        }
        else
        {
            switch (paddleType)
            {
                case PADDLE_TYPE_HOME:
                    mYVelocity = SPEED_CAPS[GameView.pDifficulty];
                    break;

                default:
                    mYVelocity = - SPEED_CAPS[GameView.pDifficulty];
                    break;
            }
        }
        setYVelocity(-mYVelocity);
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
