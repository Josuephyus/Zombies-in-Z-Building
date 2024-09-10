# Universal Values

Initialize should have all the universal options. As of now, those are

- scrW : Half of the width of the entire monitor (or the entire window width)
- scrH : Half of the height of the resolution (or the entire window height)
- uKL : Universal Key Listener
- uML : Universal Mouse Listener

## ANSWER MY QUESTIONS

- Top down or angled?
    Top down
- How much of Zombies should we copy?
    Almost all of it
- Should you drop guns or?
    No
- How much wood would a wood chuck chuck if a wood chuck could chuck wood?
    Huh?
- New Map?
    Kinda
- Can we use the mouse to aim and shoot with a controls overhaul?
    Half and Half

## Technical Patches

### 0.0.0.1

- Initialize.java starts and holds the window
- Listener.java holds the mouse and key listenners as well as keybinds
- Logic.java is where all the updates are held
- Artist.java is where all the art is drawn
- util/Point.java is a more accurate java.util.Point
- util/Line.java uses util/Point.java to create line detections
| Removed | 3:55pm 8/25/2024
- util/Shape.java uses util/Line.java to create area detections
Created Initialize.startMenu() which makes it easier to create the starting menu for the game.
| Finished Documentation 3:02am 5/27/2024

### 0.0.0.2

The camera trick is done by translating the graphics used by a third of the mouse movement.
Artist draws a circle at (0, 0) to check aboslute positioning and relative positioning
Frame rate increased but I will separate tickrate and frame rate eventually

### 0.0.0.3

Made new file in util for projectiles.
Added keybinds for Q and E to swap from the list in behavior/Weapons.java.
| Finished Documentation 5:17pm 5/29/2024

### 0.0.0.4

Added Keybind for shift and space.
New tick system and space keybind for testing slowdown.
| Finished Documentation 3:00am 5/30/2024

### 0.0.0.5

Window formatting?
Keybind class made.
Logic now checks damages against players and enemies

--- Addition 1

Added Laser class.
Need to:
 Make them scale with screen size
 Make them properly detect hits
 Make them easier to change
| Finished Documentation 12:28am 6/1/2024

--- Addition 2

Lasers have been adjusted.
Artist now draws using negative Y values instead of base Y values.
Zombie list changed from Zombie to Entity
| Finished Documentation 12:30am 6/1/2024

--- Addition 3

Lasers now scale with screen size
Artist has new functions to make drawing rectangles and circles quicker.
Fix: Laser hitboxes

### 0.0.0.6

Lasers added with fixed hitboxes.
| Finished Documentation 8:40pm 6/12/2024

--- Addition 1

HUD shakes randomly. Fix soon.
JSON interpretations added for:
 Projectiles
 Lasers
 Areas

--- Addition 2

Added a ".weapon" file type to change weapons quicker.

### 0.0.0.7

Released .weapon format for custom weapons
Added a static FPS value
Added a static millisecondsPerFrame
Added totalTime to logic for improved Tick Rate functions

TODO:
Fix HUD Shake
Add Area Weapons
Add ammo counter and rapid fire for projectiles
Fix Panels
Implement Damage, Healing, and Movement Instances
 Instead of each update happening based on spawn order, they happen based on strength and type
 Basically get all instances and order them before implementing them

REPLAN:
This is not a complete overhaul, just a change in game plan. No instances, just spawn order. Removing mod support to finish quicker. Everything is for testing, nothing is permanent yet.
Rewriting Patch notes too.
| Finished Documentation 4:25pm 8/25/2024

--- Addition 1

Added area weapons. Dont fuck with them, I don't understand how or why they work.
FORGOT TO MENTION: Adjusted some value types. Most were Doubles, but capital letter types are not numerical types but number types instead.
AKA a Double cannot convert to a Float but a double can convert to a float. This is the same with Integer and int. Most Doubles were changed to float or double depending on how bothersome they were to change.
Rule of thumb. If it's good enough for minecraft, its good enough for us. They use float for position and rotation. Unity also does. So does Unreal. doubles are just unnecessary.

--- Addition 2

Working on Interactions. Got them all. Kind of scuffed, but very easy to fix in big updates.

--- Addition 3

Working on walls. Got Walls and Obstacles working.

### 0.0.0.8

All the 0.0.0.7 additions.

--- Addition 1

+ Zombies have micro steps
+ Entity class has public static Map
+ Logic updates projectile position after collision
+ Logic gameLoop updated to not be dumb as hell

Note to self: Move Interactables to the map and have the Player.update check interactables with a toggle instead of Logic

--- Addition 2 ---

+ Control Rework
| Players now swap with 1, 2, 3, and 4
| Players now use E to interact (temporary)
| Players now use F to fire (temporary)
| Players Keybinds for [ Dash, Ability ] added but not used (yet)
+ Entity class now has getBuff and addBuff
+ Logic now checks for Lifesteal buff while dealing damage
+ Player has static class for quicker inputs (a little more complicated)
+ Map has gained Interactables
+ Player update is now simplier
+ Adjusted Artist to draw Logic.map.interactables instead of Logic.interactables

- Logic has lost Interactables

--- Addition 3 ---

+ Map now displays a map
+ Fixed all weapons (now have reloads fixed too!)
TODO:
    Add walls and locked rooms
    Add Dash