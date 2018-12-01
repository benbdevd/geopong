package edu.threegees.geopong;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static edu.threegees.geopong.JConstants.*;
/**
 *  ACTIVITY AT THE TOP OF THE HIERARCHY FOR THE 'PONG GAME'
 *      CREATES A GameView, LocationManager, LocationListner, and a MediaPlayer
 *      SETS CONTENT VIEW TO THE mGameView
 */

public class GameActivity extends AppCompatActivity
{
    GameView mGameView;

    Location mCurLocation;
    LocationManager mLocationManager;
    LocationListener mLocationListener;

    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //force app to run in portrait for consistent experience on phone
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mGameView = new GameView(getApplicationContext(), this);

        //startLocationControl();

        setContentView(mGameView);
    }

    /**
     * At the moment this only deals with the music part of the game
     * Maybe move some stuff from onCreate here?
     */
    @Override
    protected void onStart()
    {
        super.onStart();
        mMediaPlayer = new MediaPlayer();

        if(GameView.pDifficulty == 0)
        {
            //tempo 120
            mMediaPlayer = MediaPlayer.create(this, R.raw.geopongtemposlowest);
        }
        else if(GameView.pDifficulty == 1)
        {
            //tempo 140
            mMediaPlayer = MediaPlayer.create(this, R.raw.geopong);
        }
        else if(GameView.pDifficulty == 2)
        {
            //tempo 180
            mMediaPlayer = MediaPlayer.create(this, R.raw.geopongtempofaster);
        }
        else
        {
            //tempo 200
            mMediaPlayer = MediaPlayer.create(this, R.raw.geopongtempofastest);
        }
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mMediaPlayer.pause();

        //KILL THE OLD GameView
        mGameView.isGameOn = false;
        mGameView = new GameView(getApplicationContext(), this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        //CHANGE TO NEW GameView
        setContentView(mGameView);

        mMediaPlayer.start();
    }

    public void startTouchControl()
    {

    }

    public void startLocationControl()
    {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = createLocationListener(mGameView);

        try
        {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        }catch (SecurityException e)
        {
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float xPos = event.getX();

        switch (event.getAction())
        {
            case MotionEvent.ACTION_MOVE:
                mGameView.setHomePaddlePos((int) (xPos - (PONG_PADDLE_WIDTH / 2.0)));
                //TODO add multiplayer logic here
                break;
        }

        return true;
    }

    
    public LocationListener createLocationListener(final GameView gameView)
    {
        LocationListener locationListener = new LocationListener()
        {

            /**
             * VERY TEMPORARY TESTING IMPLEMENTATION
             *
             * JUST SETS THE PLAYER PADDLE X POSITION TO THE DEVICE LONGITUDE
             *
             * MUST CHANGE
             * @param location
             */

            @Override
            public void onLocationChanged(Location location)
            {
                Log.d("LOC_TEST", "CHANGED: " + location.getLatitude());
                if(location != null && mCurLocation != null)
                {
                    gameView.pHomePaddle.setX((float) location.getLongitude());

                   ///(mGameView.getCirX() + (float) (mCurLocation.getLatitude() - location.getLatitude()) * GPS_SCALE_FACTORS[GameView.pDifficulty]);
                   // mGameView.setCirY(mGameView.getCirY() + (float) (mCurLocation.getLongitude() - location.getLongitude()) * GPS_SCALE_FACTORS[GameView.pDifficulty]);
                }

                mCurLocation = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras)
            {
            }

            @Override
            public void onProviderEnabled(String provider)
            {
                try
                {
                    mCurLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }catch (SecurityException e)
                {
                }
            }

            @Override
            public void onProviderDisabled(String provider)
            {
            }
        };
        
        return locationListener;
    }

    /**
     * If certain conditions are met the game activity will end and the player will be sent to the
     * EndGameActivity
     */
    public void onGameEnd(long gameDuration)
    {
        Intent intent = new Intent(this, EndGameActivity.class);
        intent.putExtra(TAG_GAME_DIFFICULTY, mGameView.pDifficulty);
        intent.putExtra(TAG_GAME_DURATION, gameDuration);

        Log.d("TAG11", ": dur" + gameDuration +", dif: " + mGameView.pDifficulty);

        startActivity(intent);
    }
}
