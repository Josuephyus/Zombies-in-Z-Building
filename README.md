# Zombies-in-Z-Building
 Top down Shooter against hordes of all types of zombies.
# Universal Values
 Initialize should have all the universal options. As of now, those are
- scrW : Half of the width of the entire monitor (or the entire window width)
- scrH : Half of the height of the resolution (or the entire window height)
- uKL : Universal Key Listener
- uML : Universal Mouse Listener
# ANSWER MY QUESTIONS
- Top down or angled?
- How much of Zombies should we copy?
- Should you drop guns or?
- How much wood would a wood chuck chuck if a wood chuck could chuck wood?
- New Map?
- Can we use the mouse to aim and shoot with a controls overhaul?
# Patch History : Cause I can?
### 0.0.0.1
Officially started documenting Patches.
Created Structure of the game. 
- Initialize.java starts and holds the window
- Listener.java holds the mouse and key listeners as well as keybinds
- Logic.java is where all the updates are held
- Artist.java is where all the art is created and drawn
- util/Point.java is a more accurate java.util.Point
- util/Line.java uses util/Point.java to create proper area detections
- util/Shape.java uses util/Line.java to create greater areas of detection
Created Initialize.startMenu() which makes it easier to create the starting menu for the game
Finished Documentation 05/27/24 . 3:02 AM