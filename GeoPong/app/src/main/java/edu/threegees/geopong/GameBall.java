package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;

import java.util.Random;

import static edu.threegees.geopong.JConstants.*;

/**
 *  GameBall
 *      CAN DRAW ITSELF
 *      CAN UPDATE ITSELF
 */

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
         * Should be moved to paddles checking so we can more easily get which paddle it collided with
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

        /**
         * HANDLE COLLISION WITH WALLS (SPEED DOES NOT CHANGE)
         */
        if(mXPosition + PONG_BALL_RADIUS >= GameView.pGameWidth || mXPosition - PONG_BALL_RADIUS <= 0)
        {
            setXVelocity(-mXVelocity);
        }

        changeXBy(mXVelocity);
        changeYBy(mYVelocity);
    }

    //Reset ball position to center
    //Change if you don't care for it but I'm adding a random element
    public void reset()
    {
        Random ran  = new Random();
        //The +- 30 is to prevent the ball from spawning inside the wall
        setX(ran.nextInt(GameView.pGameWidth - 30) + 30);
        //setX(GameView.pGameWidth/2);
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

        /*We'll want to put this stuff inside GamePaddle once we get everything working there
        NOTE: this will crash the program if we put it here. So don't
        mPongHit = new MediaPlayer();
        mPongHit = MediaPlayer.create(this, R.raw.pongpaddlehit);
        mPongHit.start();*/
    }

}
