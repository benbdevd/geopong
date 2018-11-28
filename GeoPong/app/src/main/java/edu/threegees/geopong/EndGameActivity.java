package edu.threegees.geopong;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

    /**
     * This should send the user back to MainMenuActivity when the user presses the button
     * @param view
     */
    public void onClickMainMenu(View view)
    {
        Intent intent = new Intent(this, MainMenuActivity.class);

        startActivity(intent);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

}
