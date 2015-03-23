
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
    private String name;
    private Effect effect;
    private CombatCoordinator myCC;
    private Effect cost;
    
     public BasicAttack(){
        name = "BasicAttack";
        effect = null;
        myCC = null;
        cost = null;
    }

    /**
    * @author Aaron Iglesias, Jason Owens
    * constructor for Ability
    * @param name, effect, myCC, cost
    */
    public BasicAttack(String name, Effect effect, Effect cost){
        this.name = "BasicAttack";
        this.effect = effect;
        this.myCC = CombatCoordinator.getInstance();
        this.cost = cost;
    }
    
     /*-----------Accessors-----------*/

    /**
    * @author Aaron Iglesias, Jason Owens
    * gets name of Ability
    * @return name
    */
    public String getName(){
        return name;
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
    
    /**
    * @author Aaron Iglesias, Jason Owens
    * gets cost of Ability
    */
    public Effect getCost()
    {
        return cost;
    }
    
    /**
    * @author Aaron Iglesias, Jason Owens
    * sets cost of Ability
    * @param cost
    */
    public void setCost(Effect cost)
    {
        this.cost = cost;
    }

    /**
    * @author Aaron Iglesias, Jason Owens
    * sets effect of Ability
    * @param effect
    */
    public void setEffect(Effect effect)
    {
        this.effect = effect;
    }

    /**
    * @author Aaron Iglesias
    * calculate distance between two coordinate pairs
    * @param entity1, entity2
    * @return distance
    */
    protected double getDistance(CoordinatePair location1, CoordinatePair location2)
    {
        int x1 = location1.getX();
        int y2 = location2.getY();

        int y1 = location1.getY();
        int x2 = location2.getX();
        
        double distance = pow(pow(x1 - x2, 2) + pow(y1 - y2, 2), 0.5);
        
        return distance;
    }
    
    
    
    
}
