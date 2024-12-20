package srcOG.data.weapons;

import behavior.*;
import util.*;

public class Pistol extends Weapon {

    private float _cooldown;
    private float _currentReloadSpeed;
    private float _minReloadSpeed = 0.6f;

    public Pistol(){
        _MaxClipSize = 16;
        _MaxAmmo = 80;
        _CurrentClip = 16;
        _CurrentAmmo = 80;
        
        image = "CPistol";
        create = new Projectile(0, 0, 0);

        width = 90;
        height = 45;
        create.damage = 60;
        create.range = 9999f;
        
        create.width = 6;
        create.height = 6;
        create.radius = 10;
        create.image = "N/A";
        create.speed = 750;

        fireRate = 2.25f;
        reloadSpeed = 2f;
        reloading = false;
        
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
            Damage a = new Projectile(e, create, e.position, e.rotation);
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