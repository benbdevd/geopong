# GeoPong
A multiplayer, gps position controlled game of pong.
The game will be played with two phones and the inputs controlling the pong paddles will be the difference in GPS positions taken at server update intervals.

## Timeline
The grand timeline for developing this app will follow:

1. **Develop pong app that can be played on a single device, against AI or multiplayer, controlled by touch inputs**
2. **Add inter-phone multiplayer using a server process on the “host” phone, or possibly Google Play Game Services**
3. **Change input from touch slider to GPS location differences at server update interval**

## Challenging Tasks
This game app has numerous challenging components to be implemented in order to get the intended function. The areas of challenge, accompanied by additional details and possible solutions are:

### Graphics and control of basic pong game:
* Rectangular paddles, a middle line, scoreboard, and a ball whose appearance may gradually change depending on some factor like distance to player to make it more visually interesting

### Multiplayer across two phones over an internet connection (or LAN):
* Match and authenticate games by creating a 4 letter code on the host phone,  and entered on invitee phone
* May consider having the user create a Wi-Fi hotspot for the two phones to connect via LAN if WAN poses too much of a challenge

### Accessing location for input updates:
* Regular pinging of GPS location and sending it to the server process
* May look into using other sensors to improve quality of life like adding an arrow around the paddle to indicate what direction you are facing relative to the game board

### Applying game design principles:
* Decide on ideal size of real-world play space depending on location accuracy and playtesting
* Multiple difficulty settings and a physical endurance mode for playing against a perfect AI or “wall” with high ball velocity
* Each difficulty should have a constant that determines the increments the ball’s speed will increase with each successful hit and also each should have a respective cap on the max ball speed
