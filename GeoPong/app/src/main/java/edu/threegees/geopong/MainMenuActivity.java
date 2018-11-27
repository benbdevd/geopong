package edu.threegees.geopong;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Service;
import static edu.threegees.geopong.JConstants.*;

public class MainMenuActivity extends AppCompatActivity
{
    private int mGameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /**
         * MAKE SURE THAT ACTIVITY DOESN'T CRASH WHEN PRESSING BACK BUTTON FROM OPTIONS_ACTIVITY
         */
        setContentView(R.layout.activity_main_menu);
        //force app to run in portrait for consistent experience on phone
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        if (savedInstanceState != null)
        {

        }
    }


    public void onClickMultiOnline(View v)
    {
        mGameMode = MULTI_ONLINE;
        onGameModeSelect();
    }

    public void onClickMultiLocal(View v)
    {
        mGameMode = MULTI_LOCAL;
        onGameModeSelect();
    }

    public void onClickSinglePlayer(View v)
    {
        mGameMode = SINGLEPLAYER;
        onGameModeSelect();
    }

    private void onGameModeSelect()
    {
        Intent intent = new Intent(this, OptionsMenuActivity.class);
        intent.putExtra(game_mode, mGameMode);

        View thisLayout = findViewById(R.id.main_menu_layout);
        GameView.pGameHeight = thisLayout.getHeight();
        GameView.pGameWidth = thisLayout.getWidth();

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
