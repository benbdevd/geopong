package edu.threegees.geopong;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

        mGameView = new GameView(getApplicationContext());

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = createLocationListener(mGameView);
        marryLocationListenerToManager();

        setContentView(mGameView);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.geopong);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

    }


    @Override
    protected void onPause()
    {
        super.onPause();
        mMediaPlayer.pause();

        //kill the old activity or something
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mMediaPlayer.start();

        //Add something here to start a new one
    }
    
    public LocationListener createLocationListener(final GameView gameView)
    {
        LocationListener locationListener = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                Log.d("LOC_TEST", "CHANGED: " + location.getLatitude());
                if(location != null && mCurLocation != null)
                {
                    gameView.homePaddle.setX((float) location.getLongitude());




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

    public void marryLocationListenerToManager()
    {
        try
        {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        }catch (SecurityException e)
        {
        }
    }


}
