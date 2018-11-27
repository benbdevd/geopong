package edu.threegees.geopong;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class BackgroundSoundService extends Service
{
    private static final String TAG = null;
    MediaPlayer mMediaPlayer;


    public IBinder onBind(Intent arg0)
    {
        return null;
    }


    @Override
    public void onCreate()
    {
        super.onCreate();

        Log.d("service", "onCreate");
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.geopong);
        try
        {
            mMediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + "/your file.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setLooping(true); // Set looping
        mMediaPlayer.setVolume(100,100);
    }


    public int onStartCommand(Intent intent, int flags, int startId)
    {
        try {
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return START_STICKY_COMPATIBILITY;
    }


    public IBinder onUnBind(Intent arg0)
    {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop()
    {
        mMediaPlayer.stop();
    }

    public void onPause()
    {
        mMediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }

    @Override
    public void onLowMemory() {

    }
}

