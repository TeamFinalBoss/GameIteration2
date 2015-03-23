/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.map.projectiles;

import model.effect.Effect;
import model.entity.Entity;
import model.map.Vector;
import model.map.pair.PreciseCoordinatePair;

/**
 *
 * @author Jason Owens
 */
public class Fireball extends Projectile {

    public Fireball(long initialLifetime, Vector velocity, PreciseCoordinatePair initialLocation, Effect effects, Entity castingEntity){
        super(initialLifetime,  velocity,  initialLocation,  effects,  castingEntity);
    }
    
    @Override
    public boolean canSee(int observationLevel) {
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   

    @Override
    public void applyEffect(Entity entToEffect){
       Effect e = getEffect();
       e.applyEffect(entToEffect);
    }
   
}
