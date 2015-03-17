/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ability.effects;

import model.entity.Entity;

/**
 * Deals damage to an entity over time. Effect applied at construction.
 * @author Jason Owens
 */
public class DealDamageOverTimeEffect extends OverTimeEffect {
    private int damagePerSecond;
    private double leftoverDamage; // very low DPS effects need this or else they'll never do damage
    double leftoverPerClick;
    
    public DealDamageOverTimeEffect(Entity entityToEffect, int lifetimeInMilliSeconds, int damagePerSecond) {
        super(entityToEffect, lifetimeInMilliSeconds);
        this.damagePerSecond = damagePerSecond;
        this.leftoverPerClick = (((double)damagePerSecond * (double)refreshRate/ (double)1000) % 1);
    }

    @Override
    public void run(){
        leftoverDamage += leftoverPerClick;
        int damageToDeal = damagePerSecond * refreshRate / 1000; 
        if(leftoverDamage>1){
            ++damageToDeal;
            --leftoverDamage;
        }
        myEntity.dealDamage(damageToDeal);
    }
    
}
