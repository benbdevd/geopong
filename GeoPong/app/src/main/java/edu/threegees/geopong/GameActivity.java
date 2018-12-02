package edu.threegees.geopong;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import static edu.threegees.geopong.JConstants.*;

/**
 * ACTIVITY AT THE TOP OF THE HIERARCHY FOR THE 'PONG GAME'
 * CREATES A GameView, LocationManager, LocationListner, and a MediaPlayer
 * SETS CONTENT VIEW TO THE mGameView
 */

public class GameActivity extends AppCompatActivity
{
    GameView mGameView;

    Location mCurLocation;
    LocationManager mLocationManager;
    LocationListener mLocationListener;
    float[] distanceInMeters;

    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //force app to run in portrait for consistent experience on phone
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mGameView = new GameView(getApplicationContext(), this);

        if(mGameView.pGameMode == SP_GPS)
        {
            startLocationControl();
        }

        setContentView(mGameView);
    }

    /**
     * At the moment this only deals with the music part of the game
     */
    @Override
    protected void onStart()
    {
        super.onStart();
        mMediaPlayer = new MediaPlayer();

        if (GameView.pDifficulty == 0)
        {
            //tempo 120
            mMediaPlayer = MediaPlayer.create(this, R.raw.geopongtemposlowest);
        }
        else if (GameView.pDifficulty == 1)
        {
            //tempo 140
            mMediaPlayer = MediaPlayer.create(this, R.raw.geopong);
        }
        else if (GameView.pDifficulty == 2)
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

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float xPos;
        float yPos;
        //TODO check if this is accurate
        //Player one on the bottom half
        float yPosP1;
        //Player two is on the top half
        float yPosP2;

        switch (mGameView.pGameMode)
        {
            case SP_TOUCH:
                xPos = event.getX();

                switch (event.getAction())
                {
                    case MotionEvent.ACTION_MOVE:
                        mGameView.setHomePaddlePos((int) (xPos - (PONG_PADDLE_WIDTH / 2.0)));
                        break;
                }
                return true;

            case MP_LOCAL:
                xPos = event.getX();
                yPos = event.getY();


                switch (event.getAction())
                {
                    case MotionEvent.ACTION_MOVE:

                        //Player one
                        if(yPos > GameView.pGameHeight/2)
                        {
                            mGameView.setHomePaddlePos((int) (xPos - (PONG_PADDLE_WIDTH / 2.0)));
                        }
                        //Player two
                        else if(yPos < GameView.pGameHeight/2)
                        {
                            mGameView.setAwayPaddlePos((int) (xPos - (PONG_PADDLE_WIDTH / 2.0)));
                        }

                        break;
                }
                return true;

            default:
                return false;
        }
    }

    public void startLocationControl()
    {
        distanceInMeters = new float[1];

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = createLocationListener(mGameView);

        try
        {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        } catch (SecurityException e)
        {
        }
    }

    public LocationListener createLocationListener(final GameView gameView)
    {
        LocationListener locationListener = new LocationListener()
        {

            /**
             * VERY TEMPORARY TESTING IMPLEMENTATION
             *
             * Uses a rudimentary way of changing player paddle based on GPS
             *
             * MUST CHANGE
             * @param location
             */

            @Override
            public void onLocationChanged(Location location)
            {
                Log.d("LOC_TEST", "CHANGED: " + location.getLatitude());
                if (location != null && mCurLocation != null)
                {
                    double currentLat = mCurLocation.getLatitude();
                    double currentLong = mCurLocation.getLongitude();
                    double newLat = location.getLatitude();
                    double newLong = location.getLongitude();

                    Location.distanceBetween(currentLat, currentLong, newLat, newLong, distanceInMeters);

                    distanceInMeters[0] *= GPS_SCALE_FACTORS[mGameView.pDifficulty];

                    if(currentLong > newLong)
                    {
                        mGameView.pHomePaddle.changeXVelocityBy(distanceInMeters[0]);
                    }
                    else
                    {
                        mGameView.pHomePaddle.changeXVelocityBy(- distanceInMeters[0]);
                    }
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
                } catch (SecurityException e)
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

        Log.d("TAG11", ": dur" + gameDuration + ", dif: " + mGameView.pDifficulty);

        startActivity(intent);
    }
}
