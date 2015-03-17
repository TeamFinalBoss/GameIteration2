/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.map;

import java.util.ArrayList;
import model.ability.effects.Effect;
import model.map.pair.CoordinatePair;

/**
 * Facilitates projectiles and abilities
 * @author Jason Owens
 */
public class CombatCoordinator {
    
    CombatCoordinator(){
        
    }
    
    public void AttemptAffectEntities(ArrayList<CoordinatePair> listOfCPs, Effect effect){
        for(CoordinatePair CP: listOfCPs){
            
        }
    }
    public boolean AttemptAffectEntity(CoordinatePair CP){
        Entity entityToAffect = entityInCoordinatePair(CP)
        if(entityToAffect != null){
            
        }
    }
    
}
