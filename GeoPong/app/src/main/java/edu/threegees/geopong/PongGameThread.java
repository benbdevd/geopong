package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

public class PongGameThread extends Thread
{
    /** Handle to the surface manager object we interact with */
    private SurfaceHolder surfaceHolder;
    private Paint paint;

    public PongGameThread(SurfaceHolder surfaceHolder, Context context, Handler handler)
    {
        this.surfaceHolder = surfaceHolder;
        paint = new Paint();
    }

    @Override
    public void run() {
        while(true)
        {
            Canvas canvas = surfaceHolder.lockCanvas();
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}