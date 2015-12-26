# leJOS-Ev3-API

important note
==============
in order to use this libary you have to symlink my src to yours.
Maybe its just me but i cant add the JAR to the build path

what i have done
================
- made sensors more easy to use (only the get value; the modes are still the same)
 * UltrasonicSensor
 * ColorSensor
 * TouchSensor
 * GyroSensor
- add some dualMotorControler it isn't available now

what i won't do
===============
- edit motors (they are really great)
- recode LCD (use System.out; currently there are some lines Insider, they are mainly for dev)

bugs:
- Motorcontrol rotation end isn't sync.
- Motorcontrol rotate in circle controversal wheel direction

