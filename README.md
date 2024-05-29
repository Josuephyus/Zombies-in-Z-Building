<h1>Zombies in Z Building </h1><p>
Top down Shooter against hordes of all types of zombies.</p>
<h1>Universal Values</h1><p>
Initialize should have all the universal options. As of now, those are</p><p>
- scrW : Half of the width of the entire monitor (or the entire window width)<br>
- scrH : Half of the height of the resolution (or the entire window height)<br>
- uKL : Universal Key Listener<br>
- uML : Universal Mouse Listener</p>
<h1>ANSWER MY QUESTIONS</h1><p>
- Top down or angled?<br>
- How much of Zombies should we copy?<br>
- Should you drop guns or?<br>
- How much wood would a wood chuck chuck if a wood chuck could chuck wood?<br>
- New Map?<br>
- Can we use the mouse to aim and shoot with a controls overhaul?<br></p>
<h1>Patch History : Cause I can?</h1>
<h3>0.0.0.1</h3><p>
Officially started documenting Patches.
Created Structure of the game.</p><p>
 - Initialize.java starts and holds the window<br>
 - Listener.java holds the mouse and key listeners as well as keybinds.<br>
 - Logic.java is where all the updates are held.<br>
 - Artist.java is where all the art is created and drawn.<br>
 - util/Point.java is a more accurate java.util.Point.<br>
 - util/Line.java uses util/Point.java to create proper area detections.<br>
 - util/Shape.java uses util/Line.java to create greater areas of detection.<br>
Created Initialize.startMenu() which makes it easier to create the starting menu for the game<br>
Finished Documentation 05/27/24 . 3:02 AM</p>
<h3>0.0.0.2</h3><p>
 Added a Camera Mechanic!
 - Camera now moves in relation to the mouse as well<br>
Map Changes!<br>
 - A Circle with a radius of 10 units at 0,0 (centered)<br>
 Player Movement Buff!<br>
 - Player movement increased from 150 to 280!<br>
 - Ontop of a followup System change, the speed is actually 560 in comparison to previous<br>
 System Change!<br>
 - Frame Rate increased from 30 to 60!<br>
</p>