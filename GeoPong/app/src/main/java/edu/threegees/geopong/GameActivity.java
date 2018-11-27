package edu.threegees.geopong;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static edu.threegees.geopong.JConstants.*;


public class GameActivity extends AppCompatActivity
{

    MediaPlayer mMediaPlayer;

    protected int mGameMode;
    protected int mDifficulty;
    protected int mScoreLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //force app to run in portrait for consistent experience on phone
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Intent intent = getIntent();
        mGameMode = intent.getIntExtra(game_mode, 0);
        mDifficulty = intent.getIntExtra(difficulty, 0);
        mScoreLimit = intent.getIntExtra(score_limit, 0);

        final GameView gameView = new GameView(getApplicationContext());

        gameView.pGameMode = mGameMode;
        gameView.pDifficulty = mDifficulty;
        gameView.pScoreLimit = mScoreLimit;

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
}
