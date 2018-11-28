package edu.threegees.geopong;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import static edu.threegees.geopong.JConstants.*;

/**
 * SIMPLE ACTIVITY SHOWN AT END OF GAME
 *      DISPLAYS MATCH RESULTS
 */
public class EndGameActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /*
    Caitlin will work on this tomorrow
     */

}
