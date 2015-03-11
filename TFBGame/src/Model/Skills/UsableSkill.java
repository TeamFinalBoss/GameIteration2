/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Skills;

import java.util.ArrayList;
import map.CoordinatePair;

/**
 *  This is the parent class of all usable skills
 * @author Jason Owens
 */
public class UsableSkill {
    private String name;
    private PlayerStats myEffect;
    private CombatCoordinator myCC;
    private PlayerStats cost; //cost of cast, dont cast if cost can't be met
    
    /*-----------Constructors-----------*/
    public UsableSkill(){
        name = null;
    }
    public UsableSkill(String name){
        this.name = name;
    }
    
     /*-----------Accessors-----------*/
    public String getName(){
        return name;
    }

    public void performSkill(Direction dir) {
        ArrayList<CoordinatePair> affectedTiles = getAffectedTiles(dir);
        myCC.attemptAffectEntities(affectedTiles, myEffect);
    }
    
    /**
    *
    * @author Jason Owens
    * To be overridden by subclasses
    * 
    * @return ArrayList of Coordinates affected by skill
    */
    protected ArrayList<CoordinatePair> getAffectedTiles(Direction dir); //returns CoordinatePairs relative to (0,0) as entity location
}
