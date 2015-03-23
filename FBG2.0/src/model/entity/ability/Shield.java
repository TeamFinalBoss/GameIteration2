/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.ability;

import model.effect.DealDamageEffect;
import model.effect.Effect;
import model.effect.ShieldEffect;
import model.entity.Entity;

/**
 *
 * @author Jason Owens
 */
public class Shield extends Ability{
    
    public Shield(Entity caster){
      super("Shield", new ShieldEffect(caster), new DealDamageEffect(1));
    }
    
    @Override
    public boolean performAbility(Entity caster) {
        if(caster.getCurrentMP()<1)
            return false;
        caster.modifyCurrentMP(-1);
        
       ShieldEffect se = new ShieldEffect(caster);
       se.applyEffect(caster);
       
       
       return true;
    }

    @Override
    public boolean meetsStatRequirements(Entity caster) {
        return caster.getIntellect() > 15;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
