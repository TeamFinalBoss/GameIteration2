/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ability.effects;

import model.entity.Entity;

/**
 * Deals damage to an entity over time. Effect applied at applyEffect.
 * @author Jason Owens
 */
public class DealDamageOverTimeEffect extends OverTimeEffect {
    private int damagePerSecond;
    private double leftoverDamage; // very low DPS effects need this or else they'll never do damage
    double leftoverPerClick;
    
    public DealDamageOverTimeEffect(int lifetimeInMilliSeconds, int damagePerSecond) {
        super(lifetimeInMilliSeconds);
        this.damagePerSecond = damagePerSecond;
        this.leftoverPerClick = (((double)damagePerSecond * (double)refreshRate/ (double)1000) % 1);
    }
    
    /**
     * makes a copy of this Effect and attaches it to the given Entity
     * @author Jason Owens
     * @param entityToAffect 
     */
    @Override
    public void applyEffect(Entity entityToAffect){
        DealDamageOverTimeEffect newEffect = new DealDamageOverTimeEffect(lifetime, damagePerSecond);
        myTimer.addEvent(newEffect, lifetime);
    }
    
    /**
    * Run is called every [refreshRate] in milliseconds. leftoverDamage variable is used
    * to solve problem of small integer damage effects not dividing evenly.
    * @author Jason Owens
    */
    @Override
    public void run(){
        leftoverDamage += leftoverPerClick;
        int damageToDeal = damagePerSecond * refreshRate / 1000; 
        if(leftoverDamage>1){
            ++damageToDeal;
            --leftoverDamage;
        }
        myEntity.dealDamage(damageToDeal);
        
        lifetime -= refreshRate;
        if(lifetime>0)
            myTimer.addEvent(this, refreshRate);
    }
    
}
