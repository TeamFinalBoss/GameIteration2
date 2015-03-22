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
public class GoUpEffect extends HealEffect {
    
    
    public GoUpEffect(int amountToHeal){
        super(amountToHeal);
    }
    
    /**
     *
     * @param entityToAffect
     */
    @Override
    public void applyEffect(Entity entityToAffect){
        entityToAffect.modifyCurrentHP(getHealAmount());
    }
}
