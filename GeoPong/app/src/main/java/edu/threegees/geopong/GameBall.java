package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;
import static edu.threegees.geopong.JConstants.*;


public class GameBall extends GameObject
{

    //level will be the currentSpeed of the ball (how places per frame it move)
    public GameBall()
    {
        super();
        mXPosition = GameView.pGameWidth/2;
        mYPosition = GameView.pGameHeight/2;
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
        canvas.drawCircle(mXPosition, mYPosition, PONG_BALL_RADIUS, paint);
    }

}
