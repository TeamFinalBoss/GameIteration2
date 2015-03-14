/*
 * TODO: finish this (pushed just so everyone can see it)
 */
package model.ability;

import java.util.ArrayList;

import model.entities.Entity;

/**
 * The purpose of this class is to serve as a container for Spells, passing commands downward from [TBD, Entity?]
 * @author Jason Owens
 */
public class AbilityLibrary {
    private ArrayList<Ability> knownAbilities;
    
    /*-----------Constructors-----------*/
    public AbilityLibrary(){
        knownAbilities = new ArrayList<>();
    }
    
    /*-----------Mutators-----------*/
    public void addAbility(Ability ability){
        knownAbilities.add(ability);        
    }
    public boolean forgetAbility(String abilityName){
         for(Ability s : knownAbilities) {
            if (s.getName().equals(abilityName)) {
                return knownAbilities.remove(s); 
            }
        }
        return false; //ability isn't known
    }
    
     /*-----------Accessors-----------*/
    public boolean hasAbility(String abilityName){
        for(Ability s : knownAbilities) {
            if (abilityName.equals(s.getName())) {
                return true;
            }
        }
        return false;
    }
    
    
    /*
     * @author Jason Owens
     *
     * Activates ability.
     *@returns whether or not the ability is found    
     */
    public boolean PerformActiveAbility(String abilityName, Entity callingEntity){
        for(Ability a : knownAbilities) {
            if (a.getName().equals(abilityName)) {
                a.performAbility(callingEntity.getDirection());
                break;
            }
        }
    }
}
