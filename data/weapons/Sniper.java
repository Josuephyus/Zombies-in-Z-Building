package data.weapons;

import behavior.*;
import util.*;

public class Sniper extends Weapon{
    public Sniper(){
        _MaxClipSize = 8;
        _MaxAmmo = 60;
        _CurrentClip = 8;
        _CurrentAmmo = 60;

        image = "Blue";
        create = new Laser(0, 0, 0);

        width = 120;
        height = 30;

        create.damage = 100;
        create.range = 400;

        create.width = 7;
        create.image = "N/A";
        create.duration = 0.4f;
    }

    public Damage Fire(Entity e){
        Damage a = new Laser(create, e.position, e.rotation);
        a.tied = e;
        return a;
    }
}
