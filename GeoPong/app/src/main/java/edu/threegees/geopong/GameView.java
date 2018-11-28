package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import static edu.threegees.geopong.JConstants.*;


public class GameView extends View
{
    private android.os.Handler mHandler;
    private Paint mPaint;

    public static ArrayList<GameObject> allGameObj = new ArrayList<>();

    private GameBall gameBall;

    public GamePaddle homePaddle = new GamePaddle(PADDLE_TYPE_HOME);
    //public GamePaddle awayPaddle = new GamePaddle(PADDLE_TYPE_AWAY);
    public GamePaddle singlePaddle = new GamePaddle(PADDLE_TYPE_SP);

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
                for(GameObject obj : allGameObj)
                {
                    obj.update();
                }
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

        for(GameObject obj : allGameObj)
        {
            obj.draw(canvas, mPaint);
        }
    }
}
