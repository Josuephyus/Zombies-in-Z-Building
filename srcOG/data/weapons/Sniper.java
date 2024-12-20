package srcOG.data.weapons;

import behavior.*;
import util.*;

public class Sniper extends Weapon{

    private float _cooldown;
    private float _currentReloadSpeed;
    private float _minReloadSpeed = 0.4f;

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
    }

    public Damage Fire(Entity e){
        if (_cooldown <= 0 && !reloading){
            _CurrentClip--;
            _cooldown = 1 / fireRate;
            if (e.hasBuff("Firerate")){
                _cooldown /= 2;
            }
            if (_CurrentClip < 1){
                Reload(e);
            }
            Damage a = new Laser(e, create, e.position, e.rotation);
            return a;
        }
        return null;
    }
    
    public void Reload(Entity e){
        if (!reloading && _CurrentClip < _MaxClipSize){
            reloading = true;
            _currentReloadSpeed = reloadSpeed * (1 - ((float)_CurrentClip / (float)_MaxClipSize));
            if (_currentReloadSpeed < _minReloadSpeed) _currentReloadSpeed = _minReloadSpeed;
            _cooldown = _currentReloadSpeed;
        }
    }
}
