package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class PongGameBall extends PongGameObject
{

    //level will be the currentSpeed of the ball (how places per frame it move)
    public PongGameBall()
    {
        super();
    }

    @Override
    public void update()
    {

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
        mXVelocity = INITIAL_SPEED;
        mYVelocity = INITIAL_SPEED;

    }
    */



    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, JConstants.PONG_BALL_RADIUS, paint);
    }

}
