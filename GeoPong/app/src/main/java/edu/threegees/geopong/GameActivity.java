package edu.threegees.geopong;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static edu.threegees.geopong.JConstants.*;


public class GameActivity extends AppCompatActivity
{
    GameView gameView;

    Location mCurLocation;
    LocationManager locationManager;
    LocationListener locationListener;
    Handler mLocHandler;

    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //force app to run in portrait for consistent experience on phone
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        gameView = new GameView(getApplicationContext());

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = createLocListener(gameView);
        setLocationManagerRelation();

        mLocHandler = new Handler();

        setContentView(gameView);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.geopong);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }
    
    public LocationListener createLocListener(final GameView gameView)
    {
        LocationListener locationListener = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                Log.d("LOC_TEST", "CHANGED" + location.getLatitude());
                if(location != null && mCurLocation != null)
                {
                    gameView.homePaddle.setX((float) location.getLongitude());




                            ///(gameView.getCirX() + (float) (mCurLocation.getLatitude() - location.getLatitude()) * GPS_SCALE_FACTORS[GameView.pDifficulty]);
                   // gameView.setCirY(gameView.getCirY() + (float) (mCurLocation.getLongitude() - location.getLongitude()) * GPS_SCALE_FACTORS[GameView.pDifficulty]);
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
                    mCurLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
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

    public void setLocationManagerRelation()
    {
        try
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }catch (SecurityException e)
        {
            Log.d("Security", e.getStackTrace().toString());
        }
    }


}
