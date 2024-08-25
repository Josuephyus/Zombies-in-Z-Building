package data.weapons;

import behavior.*;
import util.*;

public class Pistol extends Weapon {

    public Pistol(){
        _MaxClipSize = 16;
        _MaxAmmo = 80;
        _CurrentClip = 16;
        _CurrentAmmo = 80;
        
        image = "CPistol";
        create = new Projectile(0, 0, 0);

        width = 90;
        height = 45;
        create.damage = 100;
        create.range = 9999f;
        
        create.width = 6;
        create.height = 6;
        create.radius = 10;
        create.image = "N/A";
        create.speed = 750;
    }

    public Damage Fire(Entity e){
        Damage a = new Projectile(create, e.position, e.rotation);
        return a;
    }

    public void Reload(){

    }
}
