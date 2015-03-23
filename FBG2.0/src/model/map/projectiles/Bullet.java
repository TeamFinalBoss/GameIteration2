/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.map.projectiles;


import model.effect.DealDamageEffect;
import model.effect.Effect;
import model.entity.Entity;
import model.map.pair.PreciseCoordinatePair;
import model.map.projectiles.Projectile;
import model.entity.Entity;
import model.map.Vector;

/**
 *
 * @author ashishag
 */
public class Bullet extends Projectile {
    
    public Bullet(Entity e1){
         super((long)5000, new Vector(e1.getDirection()) ,new PreciseCoordinatePair((double)e1.getLocation().getX(), (double)e1.getLocation().getY()), (Effect)(new DealDamageEffect(10)), e1);
         
    }
    @Override
           public boolean canSee(int observationLevel){
             return true;
         }

    @Override
    public void applyEffect(Entity entToEffect) {
        DealDamageEffect e = (DealDamageEffect) getEffect();
       e.applyEffect(entToEffect, 10);//TODO make this actually do something
       this.setInactive();
    }
    

    public String toString(){
        return "bullet";
    }
    
}
