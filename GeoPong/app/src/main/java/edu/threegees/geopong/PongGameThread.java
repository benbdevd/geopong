package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

public class PongGameThread extends Thread
{
    private PongGameObject[] mPongGameObjects;

    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;

    public PongGameThread(SurfaceHolder surfaceHolder, Context context, Handler handler, PongGameObject[] gameObjects)
    {
        mPongGameObjects = gameObjects;

        this.mSurfaceHolder = surfaceHolder;
        mPaint = new Paint();
    }

    @Override
    public void run() {
        while(true)
        {
            Canvas canvas = mSurfaceHolder.lockCanvas();

            canvas.drawRGB(0, 0, 0);

            for(PongGameObject object : mPongGameObjects)
            {
                object.update();
                object.draw(canvas, mPaint);
            }

            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}