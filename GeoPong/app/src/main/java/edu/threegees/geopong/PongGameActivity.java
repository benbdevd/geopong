package edu.threegees.geopong;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PongGameActivity extends AppCompatActivity
{
    MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //force app to run in portrait for consistent experience on phone
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        final PongGameView pongGameView = new PongGameView(getApplicationContext());

        setContentView(pongGameView);
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
}
