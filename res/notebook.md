# Engineering Notebook

## Preface

Group includes Rohan Venkatapuram (Bumbing Bingus), Ashish Rao (Jean-Luc Picard), Amartya Bhattacharya (TigerStar), and 
Siddarth Viswanathan (Shadowclaw Epic Styl). 
Game being built is a 3D first-person shooter game which allows for players to use traditional weaponry 
and also a portal gun similar to the popular game 'Portal' to take out targets.

## 5/19/19

Bumbing Bingus
* Fixed movement so that its relative to the camera. Controls are currently W and S for forward and back with A & D
for turning. Plan to add mouse controls soon.

Jean-Luc Picard
* Added a simple model of a gun to the game
* Added code that keeps the gun in the same field of view even if the camera gets moved

## 5/20/19

Bumbing Bingus
* Mouse movement for camera added. Camera is done with mouse and movement is done with WASD.

## 5/21/19
First whole group meeting. Discussed plan for development, and distributed tasks.

Bumbing Bingus
* Added deltaTime() to world to help with movement calculations

Jean-Luc Picard
* Worked on creating 3D models of a tree and a house in Blender. 

Shadowclaw Epic Styl
* Added variables required for original jump, xVelocity and zVelocity
## 5/22/19

TigerStar
* Set up Level class
* Made Platforms

Shadowclaw Epic Styl
* Finished original jump, slowed down jump downwards linearly
## 5/24/19

TigerStar
* Made Targets

Jean-Luc Picard
* Begin work on collisions, come up with basic first iteration of code for collisions.

## 5/25/19

Bumbling Bingus
* Started bullets. Bullet velocity is correct, but starting point needs some work

Jean-Luc Picard
* Further refinement of collision code, and edits to enable jumping on platforms.

## 5/28/19

TigerStar
* Finished Level 

## 5/29/19

Bumbling Bingus
* Added explosion effect to bullet 
* Changed jumping to be based on gravity and velocity
* Added ground checker to allow for landing on things that aren't platforms

Bumbling Bingus and TigerStar
* Collaborated on combining on what already existed with TigerStar's
Level class. Not super successful, as collision is off, bullets don't spawn right
(even worse than before), and (insert crab emoji) gun is gone. As
the level architecture is crucial to this game, we will work on fixing this
ASAP.

## 6/2/19

Jean-Luc Picard
* Changed shooting to use the mouse
* Improved shooting spawn point
* Experimented with adding new entities like enemies

Bumbling Bingus
* Fixed the issues with level implementation. Turns out it wasn't working
since we never called level.addCameraGroupToWorld(). This really was a 
b r u h  moment. It works now.aaaaaaa
* Made mouse stay in frame. This is necessary for shooting with mouse.

## 6/5/19

Shadowclaw Epic Styl
* Added targets to the world, got rid of the walls


