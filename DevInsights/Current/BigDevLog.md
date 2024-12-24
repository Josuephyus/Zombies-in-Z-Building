# Uhhhh

Sorry. I don't like the old code. It was ugly, clunky, and unnessesary.
Theres a term for java programming. "Great for writing Instant Legacy Code" and I couldn't agree more

## Big TODO

For next #.0.0

- [ ] 4 Guns
- [ ] 1 Melee weapon
- [ ] Rounds
- [ ] 3 Enemy types
- [ ] Game End Screens

## Small TODO

For next ?.#.0

- [ ] 1 Melee weapon
- [ ] Rounds

### 0.1.0

This is a little late but I'm going to summarize the basics.

- Main.java - Sets up the JFrame and "Starts()" Listener and Artist

- util/Menus.java - Handles the JPanels in the folder "menus/".
- util/Logic.java - ...the logic. All calculations should be done in here
- util/Artist.java - Draws onto the JPanel _2 (as of now) in "menus/".
- util/Listener.java - Holds the Key/Mouse listeners for the window. Also holds the keybinds and whether they are active or not.

- util/menus/_#.java - A JPanel that is applied to the window. This is for GUIs.

- util/math/MathF.java - The default Math package for java uses doubles as the return values. As of now, MathF just casts the double as a float and returns it. Might be changed for manual optimizations (if it becomes and issue).
- util/math/Vec2.java - Essentially java.awt.Point but with floats. {x} and {y} are public, but has some additional functions such as distance, directionTo, add, multiply, etc.

- maps/_Map.java - The default map, and will be made to be extendable for more maps, but as of now, basic and no collision checks

- entities/_Entity.java - the default entity. To be noted, it stores a subclass called Resource that acts as the HP and (later) the player Stamina. Use it for... resources and maybe ammunition.
- entities/Player.java - Player has some additional functions to entity for manual control rather than AI but pretty basic.
- entities/Zombie.java - A custom "entity" that automatically follows the player, winds up an attack, and hits the player (if in range).