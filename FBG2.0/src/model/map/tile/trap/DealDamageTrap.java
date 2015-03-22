/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.map.tile.trap;

import model.effect.DealDamageEffect;
import model.effect.GoDownEffect;
import model.entity.Entity;

/**
 *
 * @author Owner
 */
public class DealDamageTrap extends Trap {

    @Override
    public void Activate(Entity caller) {
        DealDamageEffect effect = new GoDownEffect(40);
        effect.applyEffect(caller);
    }
    
}
