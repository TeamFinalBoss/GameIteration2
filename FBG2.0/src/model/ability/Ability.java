package model.ability;

/* Test commit
 * TODO: finish this (pushed just so everyone can see it)
 */

import model.ability.effects.Effect;
import model.map.Direction;
import model.director.CombatCoordinator;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;
import model.entity.Entity;

/**
 *  This is the parent class of all usable abilities
 * @author Aaron Iglesias, Jason Owens
 */
public class Ability {
    private String name;
    private Effect effect;
    private CombatCoordinator myCC;
    private Effect cost; //cost of cast, dont cast if cost can't be met
    
    /*-----------Constructors-----------*/

    /**
    * @author Aaron Iglesias, Jason Owens
    * default constructor for Ability
    */
    public Ability(){
        cost = null;
        myCC = null;
        effect = null;
        name = null;
    }

    /**
    * @author Aaron Iglesias, Jason Owens
    * constructor for Ability
    * @param name
    */
    public Ability(String name){
        this.name = name;
    }
    
     /*-----------Accessors-----------*/

    /**
    * @author Aaron Iglesias, Jason Owens
    * gets name of Ability
    */
    public String getName(){
        return name;
    }

    /**
    * @author Aaron Iglesias, Jason Owens
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
    public void setCost(int cost)
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
    *
    * @author Aaron Iglesias, Jason Owens
    * To be overridden by subclasses
    * @param callingEntity 
    * 
    * @return ArrayList of Coordinates affected by ability
    */
    protected ArrayList<CoordinatePair> getAffectedTiles(Entity callingEntity){
        throw new UnsupportedOperationException("Not supported yet.");
    } //returns CoordinatePairs relative to (0,0) as entity location
}
