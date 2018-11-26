package edu.threegees.geopong;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity
{

    private int mGameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //force app to run in portrait for consistent experience on phone
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (savedInstanceState != null)
        {

        }
    }

    public void onClickMultiOnline(View v)
    {
        mGameMode = JConstants.MULTI_ONLINE;
        onGameModeSelect();
    }

    public void onClickMultiLocal(View v)
    {
        mGameMode = JConstants.MULTI_LOCAL;
        onGameModeSelect();
    }

    public void onClickSinglePlayer(View v)
    {
        mGameMode = JConstants.SINGLEPLAYER;
        onGameModeSelect();
    }

    private void onGameModeSelect()
    {
        Intent intent = new Intent(this, OptionsMenuActivity.class);
        intent.putExtra("game_mode", mGameMode);

        startActivity(intent);
    }
}
