package srcOG.behavior;

import srcOG.data.weapons.*;


public class WeaponManager {
    
    public static Weapon newWeapon(int index, Entity e){
        switch(index){
            case 0: return new Pistol();
            case 1: return new Sniper();
            case 2: return new Shotgun();
            default: break;
        }
        return null;
    }
}
