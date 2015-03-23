
package model.entity.ability;

import static java.lang.Math.pow;
import model.director.CombatCoordinator;
import model.effect.Effect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

import static java.lang.Math.pow;
import model.director.CombatCoordinator;
import model.effect.Effect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

import static java.lang.Math.pow;
import model.director.CombatCoordinator;
import model.effect.Effect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

/**
 *
 * @author ashishag
 */
public class BasicAttack extends Ability{
    
     public BasicAttack(){
       super("BasicAttack", null, null);
    }

    /**
    * @author Aaron Iglesias, Jason Owens
    * constructor for Ability
    * @param name, effect, myCC, cost
    */
    public BasicAttack(String name, Effect effect, Effect cost){
       super(name, effect, cost);
    }
    

    /**
    * @author Aaron Iglesias, Jason Owens
    * passes affectedTiles and effect to CombatCoordinator to deal damage
    * @param callingEntity
    */
    
    public boolean performAbility(Entity caster){
        caster.useWeapon();
        return true;
    }
    public boolean meetsStatRequirements(Entity caster){
        return true;
    }
    
    
    
    
}
