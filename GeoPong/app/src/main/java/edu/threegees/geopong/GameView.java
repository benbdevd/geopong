package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

import static edu.threegees.geopong.JConstants.*;


public class GameView extends View
{
    private android.os.Handler mHandler;
    private Paint mWhitePaint;

    private GameBall mGameBall;
    public GamePaddle pHomePaddle;
    public GamePaddle pAwayPaddle;

    private Drawable[] mHearts = new Drawable[3];

    public int mLives;

    public int pLastPlayerToHit;
    public static int pHomeScore = 0;
    public static int pAwayScore = 0;

    public ArrayList<GameObject> pAllGameObjects = new ArrayList<>();

    public static int pGameHeight;
    public static int pGameWidth;

    public static int pGameMode;
    public static int pDifficulty;
    public static int pScoreLimit;

    public GameView(Context context)
    {
        super(context);
        setWillNotDraw(false);
        startGame();

        mWhitePaint = new Paint();
        //SET PAINT COLOR TO WHITE
        mWhitePaint.setARGB(255, 255, 255, 255);
    }

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void startGame()
    {
        mGameBall = new GameBall(this);
        pHomePaddle = new GamePaddle(this, PADDLE_TYPE_HOME);

        switch (pGameMode)
        {
            case SINGLEPLAYER:
                pAwayPaddle = new GamePaddle(this, PADDLE_TYPE_SP);
                mLives = 3;
                break;

            case MULTI_LOCAL:
            case MULTI_ONLINE:
                pAwayPaddle = new GamePaddle(this, PADDLE_TYPE_AWAY);
                break;
        }

        mHandler = new android.os.Handler();
        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                for(GameObject obj : pAllGameObjects)
                {
                    obj.update();
                }
                invalidate();

                mHandler.post(this);
            }
        });
    }

    public void setHomePaddlePos(int xPos)
    {
        pHomePaddle.setXPos(xPos);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //DRAW BLACK BACKGROUND
        canvas.drawARGB(255, 0, 0, 0);

        for (GameObject obj : pAllGameObjects) {
            obj.draw(canvas, mWhitePaint);
        }

        //TODO make this a single player only question
        //Draws the lives for single player
        updateHearts();

        mHearts[0].draw(canvas);
        mHearts[1].draw(canvas);
        mHearts[2].draw(canvas);
    }

    /**
     * Once the newest version is up have an if statement for single player that decreases pLives by
     * 1 each time.
     */
    public void updateHearts()
    {
        mLives = mLives - mGameBall.getTimesResetCalled();

        //for left and top -, for right and top +
        int heartSize = (int) (pGameWidth/(double)HEART_SIZE_DENOMINATOR);

        //hearts height;
        int heartsHeight = (int) (pGameHeight/(double)HEART_HEIGHT_DENOMINATOR) - HEART_HEIGHT_ADJUSTMENT;

        //heart[0] location
        int firstHeartX = pGameWidth/3;
        //heart[1] location
        int secondHeartX = pGameWidth/2;
        //heart[2] location
        int thirdHeartX = (pGameWidth/3) * 2;
        switch (mLives)
        {
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
                /*
            default:
                mHearts[0] = getResources().getDrawable(R.drawable.emptyheart, null);
                mHearts[1] = getResources().getDrawable(R.drawable.emptyheart, null);
                mHearts[2] = getResources().getDrawable(R.drawable.emptyheart, null);
                break;
                */
        }

        mHearts[0].setBounds(firstHeartX -heartSize, heartsHeight - heartSize, firstHeartX + heartSize, heartsHeight + heartSize);
        mHearts[1].setBounds(secondHeartX -heartSize, heartsHeight - heartSize, secondHeartX + heartSize, heartsHeight + heartSize);
        mHearts[2].setBounds(thirdHeartX -heartSize, heartsHeight - heartSize, thirdHeartX + heartSize, heartsHeight + heartSize);
    }

    public ArrayList<GameObject> getAllGameObjects()
    {
        return pAllGameObjects;
    }
}
