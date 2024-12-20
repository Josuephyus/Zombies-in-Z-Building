package srcOG.data.weapons;

import behavior.*;
import util.*;

public class Shotgun extends Weapon{

    private float _cooldown;
    private float _currentReloadSpeed;
    private float fireToggle= 0;

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

        reloadSpeed = 2;
        fireRate = 8;
    }

    public void update(float time){
        _cooldown -= time;
        if (reloading){
            float ratio = _MaxClipSize / _currentReloadSpeed;
            int ammoAmount = (int)((_currentReloadSpeed - _cooldown) * ratio);
            for (int i = 0; i < ammoAmount - _CurrentClip; i++){
                _CurrentClip++;
                _CurrentAmmo--;
                if (_CurrentAmmo == 0){
                    reloading = false;
                    _cooldown = 0;
                }
            } if (_CurrentClip >= _MaxClipSize){
                _cooldown = 0;
                reloading = false;
            }
        }

        if (fireToggle == 1){
            fireToggle = 2;
        } else if (fireToggle == 2){
            fireToggle = 0;
        }
    }

    public void Reload(Entity e){
        if (!reloading && _CurrentClip < _MaxClipSize){
            reloading = true;
            _currentReloadSpeed = reloadSpeed;
            _cooldown = _currentReloadSpeed;
        }
    }

    public Damage Fire(Entity e){
        Damage a = null;
        if (!reloading && fireToggle == 0){
            System.out.println(_CurrentClip);
            _CurrentClip--;
            if (_CurrentClip < 1){
                Reload(e);
            }
            a = new Area(e, create, e.position, e.rotation);
        }
        fireToggle = 1;
        return a;
    }
}
