/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.map.projectiles;

import model.effect.DealDamageEffect;
import model.effect.Effect;
import model.entity.Entity;
import model.map.Vector;
import model.map.pair.PreciseCoordinatePair;

/**
 *
 * @author Owner
 */
public class NinjaStar extends Projectile{

    public NinjaStar(long initialLifetime, Vector velocity, PreciseCoordinatePair initialLocation, Effect effects, Entity castingEntity){
        super(initialLifetime, velocity, initialLocation, effects, castingEntity);
        
    }
    
    @Override
    public void applyEffect(Entity entToEffect) {
        DealDamageEffect e = (DealDamageEffect) getEffect();
       e.applyEffect(entToEffect, 10);//TODO make this actually do something
       this.setInactive();
    }

    @Override
    public boolean canSee(int observationLevel) {
        return true;
    }
    
    
    public String toString(){
        return "ninjastar";
    }
}
