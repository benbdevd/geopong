package edu.threegees.geopong;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 *  Abstract class for the game objects
 */
public abstract class GameObject
{
    protected float mXPosition;
    protected float mYPosition;

    protected float mXVelocity;
    protected float mYVelocity;

    protected GameView mGameView;

    public GameObject(GameView gameView)
    {
        this.mGameView = gameView;
        gameView.pAllGameObjects.add(this);
    }


    /**
     * TO IMPLEMENT: DELTA_TIME if multiplayer
     */
    public void update()
    {
    }

    public void draw(Canvas canvas, Paint paint)
    {
    }

    public float getY()
    {
        return mYPosition;
    }

    public float getX()
    {
        return mXPosition;
    }

    public void setX(float newX)
    {
        mXPosition = newX;
    }

    public void setY(float newY)
    {
        mYPosition = newY;
    }

    public void setXVelocity(float velocity)
    {
        mXVelocity = velocity;
    }

    public void setYVelocity(float velocity)
    {
        mYVelocity = velocity;
    }

    public void changeXBy(float newX)
    {
        mXPosition += newX;
    }

    public void changeYBy(float newY)
    {
        mYPosition += newY;
    }
}
