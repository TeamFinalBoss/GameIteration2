/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.effect;

import model.entity.Entity;

/**
 *
 * @author ashish,  Owner
 */
public abstract class HealEffect implements Effect{
    private int amountToHeal;
    public HealEffect(int amountToHeal){
        this.amountToHeal =amountToHeal;
    }
    
    /**
     *
     * @param entityToAffect
     */
    @Override
    public void applyEffect(Entity entityToAffect){
        entityToAffect.modifyCurrentHP(amountToHeal);
    }
    
    public int getHealAmount (){
        return amountToHeal;
    }
}
