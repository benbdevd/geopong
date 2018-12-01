package edu.threegees.geopong;

public class JConstants
{
    static final int SP_GPS = 0;
    static final int SP_TOUCH = 1;
    static final int MULTI_LOCAL = 2;

    static final int[] INITIAL_SPEEDS = {10,12,14,16};
    static final int[] SPEED_INCREMENTS = {1,2,3,4};
    static final int[] SPEED_CAPS = {15,20,25,30};

    static final int PONG_BALL_RADIUS = 25;

    static final int PADDLE_TYPE_HOME = 0;
    static final int PADDLE_TYPE_AWAY = 1;
    static final int PADDLE_TYPE_SP = 2;

    static final int PONG_PADDLE_WIDTH = 270;
    static final int PONG_PADDLE_HEIGHT = 50;
    static final int SINGLE_PADDLE_HEIGHT = 86;

    static final int HEART_SIZE_DENOMINATOR = 31;
    static final int HEART_HEIGHT_DENOMINATOR = 19;
    static final int HEART_HEIGHT_ADJUSTMENT = 36;

    static final int PADDLE_EDGE_PADDING = 15;
    static final int MINISCULE_BALL_PADDING = 1;

    static final int[] GPS_SCALE_FACTORS = {10000, 5000, 2500, 1000};

    static final int[] DIFFICULTY_SCORE_FACTORS = {100, 250, 500, 1000};
    static final int NANOTIME_TO_SECONDS_FACTOR = 1000000000; //aka 1 BILLION

    /**
     *  UPDATE strings.xml score_limit_hint upon changing DEFAULT_POINT_LIMIT !
     */
    static final int DEFAULT_POINT_LIMIT = 15;
    static final int SINGLEPLAYER_INF_POINTS = -1;

    /**
     * INTENT PUT_EXTRA TAGS
     */
    static final String TAG_GAME_DIFFICULTY = "game_difficulty";
    static final String TAG_GAME_DURATION = "game_duration";
}
