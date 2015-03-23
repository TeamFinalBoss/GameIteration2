/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.effect;

import model.entity.Entity;

/**
 *
 * @author Jason Owens
 */
public class ShieldEffect extends TemporaryEffect{

    public ShieldEffect(Entity entityToAffect) {
        super(entityToAffect);
    }

    @Override
    protected void applyEffect() {
        myEntity.modifyEquipArmor(1000);
        myTimer.addEvent(this, lifetime);
    }

    @Override
    protected void applyInverseEffect() {
        myEntity.modifyEquipArmor(-1000);
    }

    @Override
    public void applyEffect(Entity entityToAffect) {
        this.myEntity = entityToAffect;
        applyEffect();
        //myTimer.addEvent(this, lifetime);
    }
    
}
