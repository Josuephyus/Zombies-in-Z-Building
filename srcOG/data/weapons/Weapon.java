package srcOG.data.weapons;

import behavior.Entity;
import util.*;

public class Weapon {
    
    public String image;
    public int width, height;

    public Damage create;

    public int _MaxClipSize = 16;
    public int _MaxAmmo = 80;
    public int _CurrentClip = 16;
    public int _CurrentAmmo = 80;

    public boolean reloading = false;
    public float reloadSpeed = 2f;
    public float fireRate = 2f;
    
    public Damage Fire(Entity e){return new Damage();}
    public void update(float time){}
    public void Reload(Entity e){}

    public int getMaxAmmo(){return _MaxAmmo;}
    public int getCurAmmo(){return _CurrentAmmo;}

    public int getMaxClip(){return _MaxClipSize;}
    public int getCurClip(){return _CurrentClip;}
}
