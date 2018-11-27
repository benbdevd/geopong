package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import static edu.threegees.geopong.JConstants.*;


public class GameBall extends GameObject
{

    //level will be the currentSpeed of the ball (how places per frame it move)
    public GameBall()
    {
        super();
        mXPosition = GameView.pGameWidth/2;
        mYPosition = GameView.pGameHeight/2;

        mXVelocity = (float) INITIAL_SPEEDS[GameView.pDifficulty];
        mYVelocity = (float) INITIAL_SPEEDS[GameView.pDifficulty];
    }

    @Override
    public void update()
    {
        changeXBy(mXVelocity);
        changeYBy(mYVelocity);

        collideLeft = (int) mXPosition - PONG_BALL_RADIUS;
        collideTop = (int) mYPosition - PONG_BALL_RADIUS;
        collideRight = (int) mXPosition + PONG_BALL_RADIUS;
        collideBottom = (int) mYPosition + PONG_BALL_RADIUS;

        /**
         * HANDLE COLLISION WITH WALLS (SPEED DOES NOT CHANGE)
         */

        if(collideRight >= GameView.pGameWidth || collideLeft <= 0)
        {
            setXVelocity(-mXVelocity);
        }
        if(collideBottom >= GameView.pGameHeight || collideTop <= 0)
        {
            setYVelocity(-mYVelocity);
        }

        mLastUpdateTime = System.nanoTime();
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



    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawCircle(mXPosition, mYPosition, PONG_BALL_RADIUS, paint);
    }

}
