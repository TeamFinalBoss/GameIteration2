/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity.ability;

import model.director.CombatCoordinator;
import model.effect.DealDamageEffect;
import model.effect.Effect;
import model.effect.GoDownEffect;
import model.entity.Entity;

/**
 *
 * @author Owner
 */
public class FireBall extends Ability {

    public FireBall(){
        super("FireBall", new GoDownEffect(), CombatCoordinator.getInstance(), new GoDownEffect());
    }
    
    @Override
    public boolean meetsStatRequirements(Entity entityToLearn) {
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
