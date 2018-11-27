package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;

import static edu.threegees.geopong.JConstants.*;


public class GameBall extends GameObject
{
    private boolean isFirstUpdate = true;
    private int initVelocity;

    //level will be the currentSpeed of the ball (how places per frame it move)
    public GameBall()
    {
        super();
        mXPosition = GameView.pGameWidth/2;
        mYPosition = GameView.pGameHeight/2;

        mXVelocity = INITIAL_SPEEDS[GameView.getDifficulty()];
        mYVelocity = INITIAL_SPEEDS[GameView.getDifficulty()];
    }

    @Override
    public void update()
    {
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
        if(mCollideBottom >= GameView.pGameHeight || mCollideTop <= 0)
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
