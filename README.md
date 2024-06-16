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
New JSON interpretations!<br></p>
<h5>Projectiles</h5><p>
type: "classifying its a projectile"<br>
id: which image index to use<br>
width / height: the size of the weapon in hand<br>
values: a list of all projectiles shot and their values<br>
<tab></tab>id: what image index to use for the projectile<br>
<tab></tab>speed: speed of the projectile<br>
<tab></tab>range: maximum range of the projectile<br>
<tab></tab>direction: degrees in which it is offset from forward<br><br>
<tab></tab>width/height: draw size of the projectile<br>
<tab></tab>radius: the detection range used to detect hits<br>
<tab></tab>main: die, pierce, penetrate<br>
<tab></tab>secondary: blank, bounce, boomerang, return<br>
<tab></tab>values: holds main and secondary information<br>
<tab></tab><tab></tab>lifespan: for pierce (and bounce), how many enemies<br>
<tab></tab><tab></tab>radius: for boomerange<br>
<tab></tab><tab></tab>mainType: how to reduce<br>
<tab></tab><tab></tab>mainValue: amount reduced
Extra Information, two main types. Pierce either deals damage past the first enemy reduced by main value per enemy hit or after the first one. Penetration pierces on kill and either does full damage, is reduced by the amount of damage that killed, or is modified by a percentage.</p>
<h5> Areas</h5><p>
type: "classifying its an area"<br>
id: which image index to use<br>
width / height: the size of weapon in hand<br>
values: a list of areas<br>
<tab></tab>type: additive or subtractive<br>
<tab></tab>shape: triangle, square or circle<br>
<tab></tab>width / height: (base height for triange)<br>
<tab></tab>x / y: offset from center<br>
<tab></tab>rotation: rotated around offset position (degrees)<br>
Extra information, yes its complicated but easier to add newer area weapons.
</p>
<h5>Lasers</h5><p>
type, id, width height you alr get it.<br>
values: a list of lasers and their info<br>
<tab></tab>width: max laser width and the one used for hit detection<br>
<tab></tab>direction: degrees in which it is offset from forward<br><br>
<tab></tab>lifspan: how long the laser persists<br>
<tab></tab>1/2/3/4/5: width at n/5 of lifespan<br>
Extra Information... This is not finalized. I think the weapons should have damage modifiers and attack speed, max ammo, etc, but this is a good start.
</p>


<h2>TODO</h2><p>
Add JSON integration<br>
Add Area Weapons (shotguns)<br>
Add an inventory of weapons<br>
</p>