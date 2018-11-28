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
import android.widget.ImageView;

import java.util.ArrayList;

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

        //TODO make this a single player only question
        //Draws the lives for single player
        setHearts();

        mHearts[0].draw(canvas);
        mHearts[1].draw(canvas);
        mHearts[2].draw(canvas);
    }

    /**
     * Once the newest version is up have an if statement for single player that decreases pLives by
     * 1 each time.
     */
    public void setHearts()
    {
        //TODO Have a variable that resets the lives
        pLives = 1;

        //for left and top -, for right and top +
        int heartsize = pGameWidth/31;

        //hearts height;
        int heartsHeight = (pGameHeight/19) + 1;

        //heart[0] location
        int firstHeartX = pGameWidth/3;
        //heart[1] location
        int secondHeartX = pGameWidth/2;
        //heart[2] location
        int thirdHeartX = (pGameWidth/3) * 2;
        switch (GameView.pLives)
        {
            default:
                mHearts[0] = getResources().getDrawable(R.drawable.emptyheart, null);
                mHearts[1] = getResources().getDrawable(R.drawable.emptyheart, null);
                mHearts[2] = getResources().getDrawable(R.drawable.emptyheart, null);
                break;
            case 3:
                mHearts[0] = getResources().getDrawable(R.drawable.fullheart, null);
                mHearts[1] = getResources().getDrawable(R.drawable.fullheart, null);
                mHearts[2] = getResources().getDrawable(R.drawable.fullheart, null);
                break;
            case 2:
                mHearts[0] = getResources().getDrawable(R.drawable.fullheart, null);
                mHearts[1] = getResources().getDrawable(R.drawable.fullheart, null);
                mHearts[2] = getResources().getDrawable(R.drawable.emptyheart, null);
                break;
            case 1:
                mHearts[0] = getResources().getDrawable(R.drawable.fullheart, null);
                mHearts[1] = getResources().getDrawable(R.drawable.emptyheart, null);
                mHearts[2] = getResources().getDrawable(R.drawable.emptyheart, null);
                break;
        }

        mHearts[0].setBounds(firstHeartX -heartsize, heartsHeight - heartsize, firstHeartX + heartsize, heartsHeight + heartsize);
        mHearts[1].setBounds(secondHeartX -heartsize, heartsHeight - heartsize, secondHeartX + heartsize, heartsHeight + heartsize);
        mHearts[2].setBounds(thirdHeartX -heartsize, heartsHeight - heartsize, thirdHeartX + heartsize, heartsHeight + heartsize);
    }
}
