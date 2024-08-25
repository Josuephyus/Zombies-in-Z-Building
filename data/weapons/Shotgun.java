package data.weapons;

import behavior.*;
import util.*;

public class Shotgun extends Weapon{
    public Shotgun(){
        _MaxClipSize = 4;
        _MaxAmmo = 60;
        _CurrentClip = 4;
        _CurrentAmmo = 60;

        image = "CWA";
        create = new Area();

        width = 110;
        height = 50;
        create.damage = 100;
        create.range = 100;
        
        create.width = 200;
        create.height = 100;
        create.image = "N/A"; 
        create.duration = 0.3f;
    }

    public Damage Fire(Entity e){
        Damage a = new Area(create, e.position, e.rotation);
        return a;
    }
}
