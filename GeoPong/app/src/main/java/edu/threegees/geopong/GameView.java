package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import static edu.threegees.geopong.JConstants.*;


public class GameView extends View
{
    private android.os.Handler mHandler;
    private Paint mPaint;

    private GameBall gameBall;

    private GamePaddle gamePaddleH = new GamePaddle(PADDLE_TYPE_HOME);
    private GamePaddle gamePaddleA = new GamePaddle(PADDLE_TYPE_AWAY);
    private GamePaddle gamePaddleS = new GamePaddle(PADDLE_TYPE_SP);

    public static int pGameHeight;
    public static int pGameWidth;

    public static int pGameMode;
    public static int pDifficulty;
    public static int pScoreLimit;

    public GameView(Context context)
    {
        super(context);
        setWillNotDraw(false);
        startGameThread();

        gameBall = new GameBall();

        mPaint = new Paint();
        //SET PAINT COLOR TO WHITE
        mPaint.setARGB(255, 255, 255, 255);
    }

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void startGameThread()
    {
        mHandler = new android.os.Handler();
        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                gameBall.update();
                invalidate();

                mHandler.post(this);
            }
        });
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //DRAW BLACK BACKGROUND
        canvas.drawARGB(255, 0, 0, 0);
        gameBall.draw(canvas, mPaint);
        //gamePaddleA.draw(canvas, mPaint);
        gamePaddleS.draw(canvas, mPaint);
        //gamePaddleH.draw(canvas, mPaint);
    }

    public static int getDifficulty()
    {
        return pDifficulty;
    }

    public static int getScoreLimit()
    {
        return pScoreLimit;
    }

    public static int getGameMode()
    {
        return pGameMode;
    }
}
