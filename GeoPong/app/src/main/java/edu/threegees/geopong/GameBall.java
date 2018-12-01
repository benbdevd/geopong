package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;


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
    private boolean isGoingRight;

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
         * HANDLE PADDLE COLLISIONS (SPEED CHANGES)
         */
        checkPaddleCollisions();

        /**
         * HANDLE COLLISION WITH WALLS (SPEED DOES NOT CHANGE)
         */
        checkGameBounds();

        changeXBy(mXVelocity);
        changeYBy(mYVelocity);
    }

    //Reset ball position to center
    //Change if you don't care for it but I'm adding a random element
    public void respawnBall()
    {
        mGameView.mLives--;

        if (mGameView.mLives < 1)
        {
            mGameView.stopGame();
        }

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

        /**
         *  IF BALL GOES PAST A PADDLE, RESPAWN BALL
         */
        if (mYPosition > GameView.pGameHeight || mYPosition < 0)
        {
            respawnBall();

            /**
             * CHECK FOR WHICH PADDLE HIT THE BALL TO ASSIGN POINTS/TAKE LIVES
             */

            /*
            switch (mGameView.pLastPlayerToHit)
            {
                case PADDLE_TYPE_SP:
                    mGameView.mLives--;
                    break;

                case PADDLE_TYPE_AWAY:
                    //AWAY POINTS++
                    break;

                case PADDLE_TYPE_HOME:
                    //HOME POINTS++
                    break;

                default:
                    mGameView.mLives--;
                    break;
            }
            */
        }
    }

    public void collideWithPaddle(int paddleType)
    {
        mGameView.pLastPlayerToHit = paddleType;
        isGoingRight = mXVelocity > 0;

        /**
         *  IF THE BALL IS UNDER THE SPEED CAP, INCREMENT THE SPEED (X AND Y), BY THE AMOUNT SPECIFIED IN DIFFICULTY CONSTANTS
         */

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

            if(isGoingRight)
            {
                mXVelocity += SPEED_INCREMENTS[GameView.pDifficulty];
            }
            else
            {
                mXVelocity -= SPEED_INCREMENTS[GameView.pDifficulty];
            }

        }
        /**
         * IF THE BALL IS SET TO GO ABOVE THE SPEED CAP, SET IT TO THE SPEED CAP
         */

        else if (Math.abs(mYVelocity) > SPEED_CAPS[GameView.pDifficulty])
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

            if(isGoingRight)
            {
                mXVelocity = SPEED_CAPS[GameView.pDifficulty];
            }
            else
            {
                mXVelocity = - SPEED_CAPS[GameView.pDifficulty];
            }
        }
        setYVelocity(-mYVelocity);
    }

    public void collideWithWall()
    {
        setXVelocity(-mXVelocity);
    }
}
