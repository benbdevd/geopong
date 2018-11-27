package edu.threegees.geopong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

public class PongGameThread implements Runnable
{
    private PongGameObject[] mPongGameObjects;
    private PongGameState mPongGameState;

    public PongGameThread(SurfaceHolder surfaceHolder, Context context, Handler handler, PongGameObject[] gameObjects)
    {
        mPongGameObjects = gameObjects;
        mPongGameState = new PongGameState();
    }

    @Override
    public void run() {
        while(true)
        {

        }
    }
}