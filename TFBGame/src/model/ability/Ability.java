/*
 * TODO: finish this (pushed just so everyone can see it)
 */
package model.ability;

import java.util.ArrayList;
import model.map.CoordinatePair;

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

    public void performAbility(Direction dir) {
        ArrayList<CoordinatePair> affectedTiles = getAffectedTiles(dir);
        myCC.attemptAffectEntities(affectedTiles, myEffect);
    }
    
    /**
    *
    * @author Jason Owens
    * To be overridden by subclasses
    * 
    * @return ArrayList of Coordinates affected by ability
    */
    protected ArrayList<CoordinatePair> getAffectedTiles(Direction dir); //returns CoordinatePairs relative to (0,0) as entity location
}
