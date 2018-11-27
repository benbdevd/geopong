package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PongGameView extends View
{
    private PongGameThread mPongGameThread;
    private Paint mPaint;

    private PongGameBall pongGameBall = new PongGameBall();
    private PongGamePaddle pongGamePaddleH = new PongGamePaddle(JConstants.PADDLE_TYPE_HOME);
    private PongGamePaddle pongGamePaddleA = new PongGamePaddle(JConstants.PADDLE_TYPE_AWAY);

    public static int pPongGameHeight;
    public static int pPongGameWidth;

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

            pPongGameHeight = this.getHeight();
            pPongGameWidth = this.getWidth();
        }

        //DRAW BLACK BACKGROUND
        canvas.drawARGB(255, 0, 0, 0);
        pongGameBall.draw(canvas, mPaint);
        pongGamePaddleH.draw(canvas, mPaint);
        pongGamePaddleA.draw(canvas,mPaint);
    }
}
