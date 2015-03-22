package model.entity.ability;

import model.map.Direction;
import model.director.CombatCoordinator;
import model.effect.Effect;
import model.entity.Entity;
import model.map.pair.CoordinatePair;

import java.util.ArrayList;

import static java.lang.Math.pow;

/**
 *  This is the parent class of all usable abilities
 *  @author Jason Owens
 */
public abstract class Ability {
    private String name;
    private Effect effect;
    private CombatCoordinator myCC;
    private Effect cost; //cost of cast, dont cast if cost can't be met
    
    
    /* -------------------- CONSTRUCTORS --------------------*/

    /**
    * @author Aaron Iglesias, Jason Owens
    * default constructor for Ability
    */
    public Ability(){
        name = null;
        effect = null;
        myCC = null;
        cost = null;
    }

    /**
    * @author Aaron Iglesias, Jason Owens
    * constructor for Ability
    * @param name, effect, myCC, cost
    */
    public Ability(String name, Effect effect, CombatCoordinator myCC, Effect cost){
        this.name = name;
        this.effect = effect;
        this.myCC = myCC;
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
    * @author Jason Owens
    * passes affectedTiles and effect to CombatCoordinator to deal damage
    * @param callingEntity
    */
    public void performAbility(Entity callingEntity) {
        ArrayList<CoordinatePair> affectedTiles = getAffectedTiles(callingEntity);
        myCC.attemptAffectEntities(affectedTiles, effect);
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
    
    /**
     * @author Jason Owens
     * @param entityToLearn
     * @return whether or not the entity given meets the stats requirements for this ability
     */
    public abstract boolean meetsStatRequirements(Entity entityToLearn);
    
}
