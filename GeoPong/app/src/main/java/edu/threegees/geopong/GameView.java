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
    private GameActivity mGameActivity;

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
    public static long pGameStartTime;
    public static long pGameEndTime;
    public boolean isGameOn = true;

    /**
     * HEART DISPLAY CONSTANTS
     */
    //for left and top -, for right and top +
    private final int HEART_SIZE = (int) (pGameWidth / (double) HEART_SIZE_DENOMINATOR);
    //hearts height;
    private final int HEART_HEIGHT = (int) (pGameHeight / (double) HEART_HEIGHT_DENOMINATOR) - HEART_HEIGHT_ADJUSTMENT;
    //heart[0] location
    private final int FIRST_HEART_X = pGameWidth / 3;
    //heart[1] location
    private final int SECOND_HEART_X = pGameWidth / 2;
    //heart[2] location
    private final int THIRD_HEAR_X = (pGameWidth / 3) * 2;


    public GameView(Context context, GameActivity gameActivity)
    {
        super(context);
        setWillNotDraw(false);
        startGame();

        mGameActivity = gameActivity;

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
        isGameOn = true;
        pGameStartTime = System.nanoTime();

        mGameBall = new GameBall(this);
        pHomePaddle = new GamePaddle(this, PADDLE_TYPE_HOME);

        switch (pGameMode)
        {
            case SP_GPS:
            case SP_TOUCH:
                pAwayPaddle = new GamePaddle(this, PADDLE_TYPE_SP);
                mLives = 3;
                break;

            case MP_LOCAL:
                pAwayPaddle = new GamePaddle(this, PADDLE_TYPE_AWAY);
                break;
        }

        /**
         * THREAD THAT UPDATES GAME STATE \/
         */

        mHandler = new android.os.Handler();
        mHandler.post(new Runnable()
        {
            @Override
            public void run()
            {
                if(isGameOn)
                {
                    for (GameObject obj : pAllGameObjects)
                    {
                        obj.update();
                    }
                    invalidate();

                    mHandler.post(this);
                }
            }
        });
    }

    public void stopGame()
    {
        isGameOn = false;

        pGameEndTime = System.nanoTime();
        long gameDuration = pGameEndTime - pGameStartTime;

        mGameActivity.onGameEnd(gameDuration);
    }

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //DRAW BLACK BACKGROUND
        canvas.drawARGB(255, 0, 0, 0);

        for (GameObject obj : pAllGameObjects)
        {
            obj.draw(canvas, mWhitePaint);
        }

        //Draws the lives for single player only
        if (pGameMode == SP_TOUCH)
        {
            updateHearts();

            mHearts[0].draw(canvas);
            mHearts[1].draw(canvas);
            mHearts[2].draw(canvas);
        }
    }

    /**
     * SETS / UPDATES HEART DISPLAY (SP_TOUCH ONLY)
     */
    public void updateHearts()
    {
            switch (mLives) {
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

                default:
                    mHearts[0] = getResources().getDrawable(R.drawable.emptyheart, null);
                    mHearts[1] = getResources().getDrawable(R.drawable.emptyheart, null);
                    mHearts[2] = getResources().getDrawable(R.drawable.emptyheart, null);
                    break;
            }

            mHearts[0].setBounds(FIRST_HEART_X - HEART_SIZE, HEART_HEIGHT - HEART_SIZE, FIRST_HEART_X + HEART_SIZE, HEART_HEIGHT + HEART_SIZE);
            mHearts[1].setBounds(SECOND_HEART_X - HEART_SIZE, HEART_HEIGHT - HEART_SIZE, SECOND_HEART_X + HEART_SIZE, HEART_HEIGHT + HEART_SIZE);
            mHearts[2].setBounds(THIRD_HEAR_X - HEART_SIZE, HEART_HEIGHT - HEART_SIZE, THIRD_HEAR_X + HEART_SIZE, HEART_HEIGHT + HEART_SIZE);

    }

    /**
     * One player set paddle method home
     * takes in a position input in order to update the paddle for painting
     * @param xPos
     */
    public void setHomePaddlePos(int xPos)
    {
        pHomePaddle.setXPos(xPos);
    }

    /**
     * player one set home paddle method
     * takes in a position input in order to update the paddle for painting
     * @param xPos
     * @param yPos
     */
    public void setHomePaddlePos(int xPos, int yPos)
    {
        if(yPos > GameView.pGameHeight/2)
        {
            pHomePaddle.setXPos(xPos);
        }
    }

    /**
     * Player two set away paddle method
     * takes in a position input in order to update the paddle for painting
     * @param xPos
     * @param yPos
     */
    public void setAwayPaddlePos(int xPos, int yPos)
    {
        if(yPos < GameView.pGameHeight/2)
        {
            pAwayPaddle.setXPos(xPos);
        }
    }
}
