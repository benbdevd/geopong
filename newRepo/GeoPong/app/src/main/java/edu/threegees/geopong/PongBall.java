package edu.threegees.geopong;

import java.util.Random;

public class PongBall
{
    //update game height and lengths
    private final int GAME_HEIGHT = 0;
    private final int GAME_LENGTH = 0;
    private final int[] GAME_SPEED = {1,2,3,4};

    private int yAxis;
    private int xAxis;
    private int speed;

    //subject to change
    private int incrementX;
    private int incrementY;

    //level will be the speed of the ball (how places per frame it move)
    public PongBall(int difficulty)
    {
        speed = GAME_SPEED[difficulty];

        //ball Starting positon
        launchBall();
    }

    public void updateBall()
    {
        xAxis += incrementX;
        yAxis += incrementY;

        if(hasCollidedEdges())
        {
            incrementX = -incrementY;
        }
        if(hasCollidedPaddle())
        {
            incrementX += speed;
            incrementY += speed;

            incrementY = -incrementY;
        }
        if(hasWonPoint())
        {
            //Increment points
            if(xAxis < GAME_LENGTH/2) {/*Player 1 gets ++ points*/ }
            else if(xAxis > GAME_LENGTH/2) { /*Player 2 ++ points*/ }

            incrementX += speed;
            incrementY += speed;

            launchBall();
        }
    }

    private void launchBall()
    {
        Random ran = new Random();
        //ball Starting positon randomly launches ball in a certain direction
        //Think about adding bounds so as to not launch at max or min height
        xAxis = GAME_LENGTH/2;
        yAxis = (GAME_HEIGHT * ran.nextInt());

    }

    private boolean hasCollidedEdges()
    {
        return yAxis < 0 || yAxis > GAME_HEIGHT;
    }

    //update with paddle placements
    private boolean hasCollidedPaddle() { return xAxis < 0 || xAxis > GAME_LENGTH; }

    private boolean hasWonPoint() { return xAxis < 0 || xAxis > GAME_LENGTH; }
}
