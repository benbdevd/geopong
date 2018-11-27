package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class PongGameView extends View
{
    private PongGameThread mPongGameThread;
    private Paint mPaint;

    private PongGameBall pongGameBall = new PongGameBall(0);

    public PongGameView(Context context)
    {
        super(context);
        setWillNotDraw(false);
    }

    public PongGameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if (mPaint == null)
        {
            mPaint = new Paint();
            //SET PAINT COLOR TO WHITE
            mPaint.setARGB(255, 255, 255, 255);
        }

        //DRAW BLACK BACKGROUND
        canvas.drawARGB(255, 0, 0, 0);
        pongGameBall.draw(canvas, mPaint);

    }
}
