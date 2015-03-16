package model.ability;

/* Test commit
 * TODO: finish this (pushed just so everyone can see it)
 */

import model.map.Direction;
import model.director.CombatCoordinator;
import model.map.pair.CoordinatePair;
import java.util.ArrayList;
import model.entity.Entity;

/**
 *  This is the parent class of all usable abilities
 * @author Jason Owens
 */
public class Ability {
    private String name;
    private Effect myEffect;
    private CombatCoordinator myCC;
    private Effect cost; //cost of cast, dont cast if cost can't be met
    
    /*-----------Constructors-----------*/
    public Ability(){
        name = null;
    }
    public Ability(String name){
        this.name = name;
    }
    
     /*-----------Accessors-----------*/
    public String getName(){
        return name;
    }

    public void performAbility(Entity callingEntity) {
        ArrayList<CoordinatePair> affectedTiles = getAffectedTiles(callingEntity);
        myCC.attemptAffectEntities(affectedTiles, myEffect);
    }
    
    /**
    *
    * @author Jason Owens
    * To be overridden by subclasses
    * @param dir
    * 
    * @return ArrayList of Coordinates affected by ability
    */
    protected ArrayList<CoordinatePair> getAffectedTiles(Entity callingEntity){
        throw new UnsupportedOperationException("Not supported yet.");
    } //returns CoordinatePairs relative to (0,0) as entity location
}
