package edu.threegees.geopong;

public class JConstants
{
    static final String game_mode = "game_mode";
    static final String difficulty = "difficulty";
    static final String score_limit = "score_limit";

    static final int MULTI_ONLINE = 2;
    static final int MULTI_LOCAL = 1;
    static final int SINGLEPLAYER = 0;

    static final int[] INITIAL_SPEEDS = {1,2,3,4};
    static final int[] SPEED_INCREMENTS = {1,2,3,4};
    static final int[] SPEED_CAPS = {10,20,30,40};

    static final int PONG_BALL_RADIUS = 30;

    static final int PADDLE_TYPE_HOME = 0;
    static final int PADDLE_TYPE_AWAY = 1;
    static final int PADDLE_TYPE_SP = 2;


    static final int PONG_PADDLE_WIDTH = 256;
    static final int PONG_PADDLE_HEIGHT = 50;

    static final int PADDLE_PADX = 25;
    static final int PADDLE_PADY = 25;


    static final int[] GPS_SCALE_FACTORS = {10000, 5000, 2500, 1000};

    //UPDATE strings.xml score_limit_hint upon changing
    static final int DEFAULT_POINT_LIMIT = 15;
    static final int SINGLEPLAYER_INF_POINTS = -1;
}
