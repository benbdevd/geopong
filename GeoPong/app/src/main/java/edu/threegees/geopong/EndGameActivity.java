package edu.threegees.geopong;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static edu.threegees.geopong.JConstants.*;

/**
 * SIMPLE ACTIVITY SHOWN AT END OF GAME
 *      DISPLAYS MATCH RESULTS
 */
public class EndGameActivity extends AppCompatActivity
{
    MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //LOCK TO PORTRAIT
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_end_game);

        Intent intent = getIntent();
        int difficulty = intent.getIntExtra(TAG_GAME_DIFFICULTY, 0);
        int timeLasted = intent.getIntExtra(TAG_GAME_DURATION, 10);

        long finalScore = (long) (timeLasted / (float) NANOTIME_TO_SECONDS_FACTOR) * DIFFICULTY_SCORE_FACTORS[difficulty];
        TextView scoreDisplay = findViewById(R.id.score_display);

        //TODO calculate game score
        /*
        I was thinking that we could calculate the score like:
        nanosecs/1000 * 5
        or something
         */
        scoreDisplay.setText("430968203498");

        }

    /**
     * This should send the user back to MainMenuActivity when the user presses the button
     * @param view
     */
    public void onClickMainMenu(View view)
    {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void onClickReplay(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mMediaPlayer = new MediaPlayer();

        //TODO find what music we want to use
        //mMediaPlayer = MediaPlayer.create(this, R.raw.);


        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

    }

    @Override
    public void onPause()
    {
        super.onPause();
        mMediaPlayer.stop();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mMediaPlayer.start();
    }

}
