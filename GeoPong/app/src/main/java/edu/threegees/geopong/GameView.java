package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static edu.threegees.geopong.JConstants.*;


public class GameView extends View
{
    private android.os.Handler handler;
    private Paint mPaint;

    private GameBall gameBall = new GameBall();

    private GamePaddle gamePaddleH = new GamePaddle(PADDLE_TYPE_HOME);
    private GamePaddle gamePaddleA = new GamePaddle(PADDLE_TYPE_AWAY);
    private GamePaddle gamePaddleS = new GamePaddle(PADDLE_TYPE_SP);

    public static int pGameHeight;
    public static int pGameWidth;

    public GameView(Context context)
    {
        super(context);
        setWillNotDraw(false);
        startGameThread();
    }

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setWillNotDraw(false);
        startGameThread();
    }

    public void startGameThread()
    {
        handler = new android.os.Handler();
        handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                gameBall.setXVelocity((float) 1);
                gameBall.setYVelocity((float) 1);
                gameBall.update();
                invalidate();

                handler.post(this);
            }
        });
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
        gameBall.draw(canvas, mPaint);
        gamePaddleA.draw(canvas, mPaint);
        //gamePaddleS.draw(canvas, mPaint);
        //gamePaddleH.draw(canvas, mPaint);
    }
}
