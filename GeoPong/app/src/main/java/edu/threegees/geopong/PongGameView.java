package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PongGameView extends SurfaceView implements Runnable, SurfaceHolder.Callback
{
    private PongGameThread mPongGameThread;

    private PongBall mPongBall;
    private PongPaddle mLocalPaddle;
    private PongPaddle mAwayPaddle;

    public PongGameView(Context context)
    {
        super(context);

        setFocusable(true);
        getHolder().addCallback(this);
    }

    public void update()
    {
        mAwayPaddle.update();
        mLocalPaddle.update();
        mPongBall.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);

        mPongBall.draw(canvas);
        mLocalPaddle.draw(canvas);
        mAwayPaddle.draw(canvas);
    }

    @Override
    public void run()
    {

    }

    /*
        Required methods for implementing SurfaceHolder
     */

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPongGameThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mPongGameThread.interrupt();
    }
}
