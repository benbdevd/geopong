package edu.threegees.geopong;

public class PongGameState
{
    //private PongGameObject[] pongPaddles = {new PongGamePaddle(), new PongGamePaddle()};

    private boolean gameOver;


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
