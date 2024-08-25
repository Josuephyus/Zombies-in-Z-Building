# Player.weapons as a three item array

I want the player inventory to be a three item array.

To do this, they all have to extends or implement the same
class/interface. The problem is that laser weapons, projectile
weapons, and area weapons all interact differently.

The easiest way is to make them all secretly projectiles, but
that isn't very neat. Another option is to make a damage class
that is the area damage.

Oh, fuck me. The absolute best way is to make them all extend
a weapon class. The weapon class has two things, Fire() and
reload(). Each class can override Fire() and Reload() so it
shoudl be good.

So...
A day or two later.
I made Projectile, Laser, and Area extend Damage so they can all be accessed by weapon.
Because of that, I made it kinda easier to use.

I haven't used the Permweapons yet because I'm importing the regular ones and I don't want to change it just yet.
