package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;

import static edu.threegees.geopong.JConstants.*;


public class GameView extends View
{
    private android.os.Handler mHandler;
    private Paint mPaint;

    public static ArrayList<GameObject> allGameObj = new ArrayList<>();

    private GameBall gameBall;

    private Drawable[] mHearts = new Drawable[3];


    public GamePaddle homePaddle = new GamePaddle(PADDLE_TYPE_HOME);
    //public GamePaddle awayPaddle = new GamePaddle(PADDLE_TYPE_AWAY);
    public GamePaddle singlePaddle = new GamePaddle(PADDLE_TYPE_SP);

    public static int pGameHeight;
    public static int pGameWidth;

    public static int pGameMode;
    public static int pDifficulty;
    public static int pScoreLimit;
    public static int pLives;

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

    public void drawHearts()
    {
        switch (GameView.pLives)
        {
            default:
                
                break;
            case 3:

                break;
            case 2:

                break;
            case 1:

                break;
        }
    }
}
