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
    private Paint mWhitePaint;

    private GameBall mGameBall;
    public GamePaddle pHomePaddle;
    public GamePaddle pAwayPaddle;


<<<<<<< HEAD
    private Drawable[] mHearts = new Drawable[3];


    public GamePaddle homePaddle = new GamePaddle(PADDLE_TYPE_HOME);
    //public GamePaddle awayPaddle = new GamePaddle(PADDLE_TYPE_AWAY);
    public GamePaddle singlePaddle = new GamePaddle(PADDLE_TYPE_SP);
=======
    public int pLastPlayerToHit;
    public int pHomeScore = 0;
    public int pAwayScore = 0;

    public ArrayList<GameObject> pAllGameObjects = new ArrayList<>();
>>>>>>> WARNING: COLLISION BROKE BUT USEABLE FOR TESTING-ISH

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

    public void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //DRAW BLACK BACKGROUND
        canvas.drawARGB(255, 0, 0, 0);

        for(GameObject obj : pAllGameObjects)
        {
            obj.draw(canvas, mWhitePaint);
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

    public ArrayList<GameObject> getAllGameObjects()
    {
        return pAllGameObjects;
    }
}
