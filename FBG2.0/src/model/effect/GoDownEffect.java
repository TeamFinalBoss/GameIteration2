/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.effect;

import model.entity.Entity;

/**
 *
 * @author ashishag
 */
public class GoDownEffect extends DealDamageEffect{
    
    public GoDownEffect(int amountToDamage){
        super(amountToDamage);
    }
    
    
    /**
     *
     * @param entityToAffect
     */
    @Override
    public void applyEffect(Entity entityToAffect){
        entityToAffect.dealDamage(damageToDeal);
    }
    
}
