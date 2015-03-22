/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.map;

import model.effect.Effect;
import model.map.pair.PreciseCoordinatePair;
import model.util.GameTimer;

/**
 *
 * @author ashishag
 */
public class Bullet extends Projectile {
    
    public void Bullet(){
         super(long initialLifetime, Vector velocity, PreciseCoordinatePair initialLocation, Effect effects){
        lifetime = initialLifetime;
        this.velocity = velocity;
        location = initialLocation;
        this.effects = effects;
        myTimer = GameTimer.getInstance();
        myTimer.addEvent(this, 0); //immediately calls run
        refreshRate = 10; //projectiles refresh every 10 milliseconds (20 times a second)
    }
    }
    
}
