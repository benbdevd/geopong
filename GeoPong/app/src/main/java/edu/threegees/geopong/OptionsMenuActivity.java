package edu.threegees.geopong;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OptionsMenuActivity extends AppCompatActivity implements View.OnClickListener
{
    private int mGameMode;
    private int mDifficulty;
    private int mScoreLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mGameMode = intent.getIntExtra("game_mode", JConstants.SINGLEPLAYER);

        //if statement sets layout based on whether user chose Singleplayer or a Multiplayer mode
        if(mGameMode == JConstants.SINGLEPLAYER)
        {
            setContentView(R.layout.activity_options_menu_sp);
            mScoreLimit = JConstants.SINGLEPLAYER_INF_POINTS;
        }
        else
        {
            setContentView(R.layout.activity_options_menu_mp);
        }

        //force app to run in portrait for consistent experience on phone
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initializeToolbar();
        initializeButtons();
    }

    private void initializeButtons()
    {
        Button difficulty1Button = (Button) findViewById(R.id.difficulty_1_button);
        Button difficulty2Button = (Button) findViewById(R.id.difficulty_2_button);
        Button difficulty3Button = (Button) findViewById(R.id.difficulty_3_button);
        Button difficulty4Button = (Button) findViewById(R.id.difficulty_4_button);

        difficulty1Button.setOnClickListener(this);
        difficulty2Button.setOnClickListener(this);
        difficulty3Button.setOnClickListener(this);
        difficulty4Button.setOnClickListener(this);
    }

    private void initializeToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_options);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.difficulty_1_button:
                mDifficulty = 0;
                break;

            case R.id.difficulty_2_button:
                mDifficulty = 1;
                break;

            case R.id.difficulty_3_button:
                mDifficulty = 2;
                break;

            case R.id.difficulty_4_button:
                mDifficulty = 3;
                break;
        }

        if(mGameMode != JConstants.SINGLEPLAYER)
        {
            EditText editText = findViewById(R.id.score_limit_field);
            String scoreFieldText = editText.getText().toString();

            mScoreLimit = Integer.valueOf(scoreFieldText);
        }

        Intent intent = new Intent(this, DEPRECATED_DummyActivity.class);
        intent.putExtra("game_mode", mGameMode);
        intent.putExtra("difficulty", mDifficulty);
        intent.putExtra("score_limit", mScoreLimit);

        startActivity(intent);
    }
}
