/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.map;

import model.effect.GoDownEffect;
import model.entity.Entity;
import model.map.pair.PreciseCoordinatePair;

/**
 *
 * @author ashishag
 */
public class Bullet extends Projectile {
    
    public Bullet(Entity e1){
         super(5, new Vector(e1.getDirection()),new PreciseCoordinatePair((double)e1.getLocation().getX(), (double)e1.getLocation().getY()), new GoDownEffect(10));
         //projectiles refresh every 10 milliseconds (20 times a second)
    }
    @Override
           public boolean canSee(int observationLevel){
             return true;
         }
    

    
}
