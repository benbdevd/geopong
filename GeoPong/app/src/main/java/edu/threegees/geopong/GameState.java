package edu.threegees.geopong;

import static edu.threegees.geopong.JConstants.*;


public class GameState
{
    //private GameObject[] pongPaddles = {new GamePaddle(), new GamePaddle()};

    private boolean gameOver;

    public void update()
    {

    }

    /*
    public void checkForCollision(GameObject obj1, GameObject obj2)
    {
        int mCollideLeft = Math.max(obj1.mCollideLeft, obj2.mCollideLeft);
        int mCollideRight = Math.min(obj1.mCollideRight, obj2.mCollideRight);
        int mCollideTop = Math.min(obj1.mCollideTop, obj2.mCollideTop);
        int mCollideBottom = Math.max(obj1.mCollideBottom, obj2.mCollideBottom);

        for (int x = mCollideLeft; x < mCollideRight; x++)
        {
            for (int y = mCollideTop; y < mCollideBottom; y++)
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
