package edu.threegees.geopong;

public class JConstants
{
    static final int MULTI_ONLINE = 2;
    static final int MULTI_LOCAL = 1;
    static final int SINGLEPLAYER = 0;

    //Should difficulties start at different Velocities?
    static final int[] INITIAL_VELOCITIES = {1,2,3,4};
    static final int INITIAL_VELOCITY = 1;

    static final int[] VELOCITY_INCREMENTS = {1,2,3,4};
    static final int[] VELOCITY_CAPS = {10,20,30,40};

    //UPDATE strings.xml score_limit_hint upon changing
    static final int DEFAULT_POINT_LIMIT = 15;
    static final int SINGLEPLAYER_INF_POINTS = -1;
}
