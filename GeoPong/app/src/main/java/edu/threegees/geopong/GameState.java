package edu.threegees.geopong;

import static edu.threegees.geopong.JConstants.*;


public class GameState
{
    //private GameObject[] pongPaddles = {new GamePaddle(), new GamePaddle()};

    private boolean gameOver;

    /*
    public void checkForCollision(GameObject obj1, GameObject obj2)
    {
        int collideLeft = Math.max(obj1.collideLeft, obj2.collideLeft);
        int collideRight = Math.min(obj1.collideRight, obj2.collideRight);
        int collideTop = Math.min(obj1.collideTop, obj2.collideTop);
        int collideBottom = Math.max(obj1.collideBottom, obj2.collideBottom);

        for (int x = collideLeft; x < collideRight; x++)
        {
            for (int y = collideTop; y < collideBottom; y++)
            {
                if (obj1.isFilled(x, y) && obj2.isFilled(x, y))
                {
                    return true;
                }
            }
        }
    }
    */


    /*
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

            firstDraw();
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
     */
}
