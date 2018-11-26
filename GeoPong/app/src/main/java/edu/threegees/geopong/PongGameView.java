package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class PongGameView extends SurfaceView implements SurfaceHolder.Callback
{
    private PongGameThread mPongGameThread;

    public PongGameView(Context context, Integer difficulty)
    {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        setFocusable(true);
        surfaceHolder.addCallback(this);

        PongGameObject[] gameObjects = {new PongGameBall(difficulty), new PongGamePaddle(), new PongGamePaddle()};

        mPongGameThread = new PongGameThread(surfaceHolder, context, new Handler(), gameObjects);
    }

    /*
        Required methods for implementing SurfaceHolder
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        mPongGameThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        mPongGameThread.interrupt();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }
}
