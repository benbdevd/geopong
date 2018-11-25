package edu.threegees.geopong;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class DummyActivity extends Activity
{
    private int mGameMode;
    private int mDifficulty;
    private int mScoreLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        Intent intent = getIntent();
        mGameMode = intent.getIntExtra("game_mode", JConstants.SINGLEPLAYER);
        mDifficulty = intent.getIntExtra("difficulty", 1);
        mScoreLimit = intent.getIntExtra("score_limit", JConstants.DEFAULT_POINT_LIMIT);

        TextView textView = findViewById(R.id.dummy_config);
        textView.setText("Game mode: " + mGameMode + "\nDifficulty: " + mDifficulty + "\nScore Limit: " + mScoreLimit);
    }

}
