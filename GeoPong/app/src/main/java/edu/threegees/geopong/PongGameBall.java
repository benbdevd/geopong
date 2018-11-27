package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class PongGameBall extends PongGameObject
{
    //update game height and lengths
    private final int GAME_HEIGHT = 0;
    private final int GAME_LENGTH = 0;

    //subject to change

    private int mDifficulty;

    //level will be the currentSpeed of the ball (how places per frame it move)
    public PongGameBall(int difficulty)
    {
        super();
        //pongBall = new Image("pongball.png");
        mDifficulty = difficulty;

        //ball Starting positon
        launchBall();
    }

    private final int INITIAL_SPEED = JConstants.INITIAL_SPEEDS[mDifficulty];
    private final int MAX_SPEED = JConstants.SPEED_CAPS[mDifficulty];
    private final int SPEED_INCREMENT = JConstants.SPEED_INCREMENTS[mDifficulty];

    @Override
    public void update()
    {
        mXPosition += mXVelocity;
        mYPosition += mYVelocity;

        if(hasCollidedEdges())
        {
            mXVelocity = -mYVelocity;
        }
        if(hasCollidedPaddle())
        {
            mXVelocity += SPEED_INCREMENT;
            mYVelocity += SPEED_INCREMENT;

            mYVelocity = -mYVelocity;
        }
        if(hasWonPoint() != 0)
        {
            switch (hasWonPoint())
            {
                case 1:
                    //player 1 points ++
                    break;

                case 2:
                    //player 2 points ++
                    break;
            }

            mXVelocity += SPEED_INCREMENT;
            mYVelocity += SPEED_INCREMENT;

            launchBall();
        }
    }

    private void launchBall()
    {
        Random ran = new Random();
        //ball Starting position randomly launches ball in a certain direction
        //Think about adding bounds so as to not launch at max or min height
        mYPosition = GAME_LENGTH/2;
        mXPosition = (GAME_HEIGHT * ran.nextInt());

        mXVelocity = INITIAL_SPEED;
        mYVelocity = INITIAL_SPEED;
    }

    private boolean hasCollidedEdges()
    {
        return mXPosition < 0 || mXPosition > GAME_LENGTH;
    }

    //update with paddle placements
    private boolean hasCollidedPaddle()
    {
        return mYPosition < 0 || mYPosition > GAME_HEIGHT;
    }

    private int hasWonPoint()
    {
        if(mYPosition < 0)
        {
            return 1;
        }
        else if(mYPosition > GAME_HEIGHT)
        {
            return 2;
        }
        else
            return 0;
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 50, paint);
    }

}
