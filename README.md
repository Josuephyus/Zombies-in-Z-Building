<style>
    tab {
        margin-left: 20px;
    }
    h1 {
        color: lightblue;
    }
</style>


<h1>Zombies in Z Building </h1><p>
Top down Shooter against hordes of all types of zombies.</p>
<h1>Universal Values</h1><p>
Initialize should have all the universal options. As of now, those are</p><p>
<tab></tab>- scrW : Half of the width of the entire monitor (or the entire window width)<br>
<tab></tab>- scrH : Half of the height of the resolution (or the entire window height)<br>
<tab></tab>- uKL : Universal Key Listener<br>
<tab></tab>- uML : Universal Mouse Listener</p>
<h1>ANSWER MY QUESTIONS</h1><p>
<tab></tab>- Top down or angled?<br>
<tab></tab>- How much of Zombies should we copy?<br>
<tab></tab>- Should you drop guns or?<br>
<tab></tab>- How much wood would a wood chuck chuck if a wood chuck could chuck wood?<br>
<tab></tab>- New Map?<br>
<tab></tab>- Can we use the mouse to aim and shoot with a controls overhaul?<br></p>


<h1>Patch History : Cause I can?</h1>
<h3>0.0.0.1</h3><p>
Officially started documenting Patches. Created Structure of the game.</p><p>
<tab></tab>- Initialize.java starts and holds the window<br>
<tab></tab>- Listener.java holds the mouse and key listeners as well as keybinds.<br>
<tab></tab>- Logic.java is where all the updates are held.<br>
<tab></tab>- Artist.java is where all the art is created and drawn.<br>
<tab></tab>- util/Point.java is a more accurate java.util.Point.<br>
<tab></tab>- util/Line.java uses util/Point.java to create proper area detections.<br>
<tab></tab>- util/Shape.java uses util/Line.java to create greater areas of detection.<br><br>
Created Initialize.startMenu() which makes it easier to create the starting menu for the game<br>
Finished Documentation 05/27/24 . 3:02 AM
</p>


<h3>0.0.0.2</h3><p>
Added a Camera Mechanic!<br>
<tab></tab>- Camera now moves in relation to the mouse as well<br>
Map Changes!<br>
<tab></tab>- A Circle with a radius of 10 units at 0,0 (centered)<br>
 Player Movement Buff!<br>
<tab></tab>- Player movement increased from 150 to 280!<br>
<tab></tab>- Ontop of a followup System change, the speed is actually 560 in comparison to previous<br>
 System Change!<br>
<tab></tab>- Frame Rate increased from 30 to 60!<br>
</p>


<h3>0.0.0.3</h3><p>
 Added Projectiles + Weapon Swapping<br>
 Players can now use Q and E to swap weapons (unlimited)<br>
 Projectiles are summoned from the players body!<br>
 Finished Documentatin 05/29/2024 . 5:17 PM
</p>

<h3>0.0.0.4</h3><p>
Added An Enemy! (It can move!!!)<br>
Holding space now slows time by 80%!<br>
Holding shift uses sprint and increases movement speed!!!!<br>
Finished Documentation 0/30/2024 . 3:00 AM
</p>

<h3>0.0.0.5</h3><p>
Added damage Instances to Player<br>
Added damage Instances to Enemies<br>
Fixed window formating<br>
Added easy changing Keybinds<br>
</p>

<h4>0.0.0.5.1</h4><p>
Added VERY BASIC lasers...<br>
Need a lot more fixing<br>
<tab></tab>- Scale with screen size<br>
<tab></tab>- Proper Hitbox detection<br>
<tab></tab>- Make easy to change (its not rn)<br>
Lot more to do, so I'm gonna do it in the morning<br>
Finished Documentation 06/01/2024 . 12:28 AM<br>
</p>

<h4>0.0.0.5.2</h4><p>
Lasers have been adjusted (i think).<br>
Artist have been changed to display with negative Y-Coordinates instead of a mix bag of negative. and non-negatives in the code.<br>
New files for Laser and Projectile.<br>
List for zombies now properly holds the ArrayList&lt;Entity> instead of &lt;Zombie>.<br>
Finished Documentation 06/01/2024 . 12:30 AM
</p>

<h4>0.0.0.5.3</h4><p>
Lasers now scale with screen size.<br>
Artist now has new functions for Relative and Absolute, both of which scale with screen size
(widths widen the wider the screen and heights increases the taller the screen)<br>
<tab></tab>- Needs to be adjusted to look right
FIX: Laser hitboxes are bugged... fix soon
</p>

<h3>0.0.0.6</h3><p>
Lasers are now implemented with fixed hitboxes.<br>
Ofiicially...<br>
Finished Documentation 06/12/2024 . 8:40 PM
</p>

<h4>0.0.0.6.1</h4><p>
Cannot for the life of me replicate the HUD shaking and I don't know why.<br>
New JSON interpretations! Every value is self explanatory.<br></p>
<h5>Projectiles</h5><p>
pierce - 0 and 1 mean the same thing...<br>
secondary - die, bounce, boomerang<br>
values:<br>
<tab></tab>if pierce > 1, reduceType: percent, flat<br>
<tab></tab>if bounce, range:<br>
<tab></tab>if boomerang, returnSpeed:<br>
</p>
<h5> Areas</h5><p>
Self Explanitory
</p>
<h5>Lasers</h5><p>
values:<br>
<tab></tab>width: max laser width and the one used for hit detection<br>
<tab></tab>1/2/3/4/5: width at n/5 of lifespan<br>
Extra Information... This is not finalized. I think the weapons should have damage modifiers and attack speed, max ammo, etc, but this is a good start.
</p>

<h4>0.0.0.6.2</h4><p>
Added a ".weapon" file type to easily add and change weapons as well as added a Weapons.java to behaviors to interpret ".weapon" files.<br>
The idea of having ".something" is super appealing and I'll have it like that for ".enemy", ".map", and ".powerup" if i can.
</p>

<h3>0.0.0.7</h3><p>
Fixed the ".weapon" file format for projectiles and added a player inventory (one weapon as of now)<br>
Added a static FPS value to Initialize.RunnablePanel<br>
Added a static millisecondsPerFrame value to Initialize.RunnablePanel<br>
Added totalTime to Logic to better make better use of TickRate<br>
</p>

<h2>TODO</h2><p>
Fix HUD shake: (Still exists)<br>
Add .weapon integration<br>
Add Area Weapons (shotguns)<br>
Add ammo counter and rapid fire for projectiles<br>
Fix Panels<br>
Implement Damage, Healing, and Movement Instances<br>
<tab></tab>Instead of each individual updating all in order, grab all of them and update them in order<br>
<tab></tab>Basically add the command to a list and do the list in order instead of doing them based on entity list<br>
</p>