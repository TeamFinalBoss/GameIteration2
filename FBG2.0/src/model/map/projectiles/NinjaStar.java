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
 * @author Owner
 */
public class NinjaStar extends Projectile{

    public NinjaStar(long initialLifetime, Vector velocity, PreciseCoordinatePair initialLocation, Effect effects, Entity castingEntity){
        super(initialLifetime, velocity, initialLocation, effects, castingEntity);
        
    }
    
    @Override
    public void applyEffect(Entity entToEffect) {
        
    }

    @Override
    public boolean canSee(int observationLevel) {
        return true;
    }
    
}
