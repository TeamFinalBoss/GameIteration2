package model.ability;

/*
 * TODO: finish this (pushed just so everyone can see it)
 */

import java.util.ArrayList;
import java.util.List;

import model.entity.Entity;

/**
 * The purpose of this class is to serve as a container for Spells, passing commands downward from [TBD, Entity?]
 * @author Jason Owens
 */
public class AbilityLibrary {
    private List<Ability> knownAbilities;
    
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
    /*
     * @author Jason Owens
     *
     * Checks to see if library contains ability.
     * @returns whether or not the ability is found 
     * @param abilityName the name of the ability 
     */
    public boolean hasAbility(String abilityName){
        for(Ability s : knownAbilities) {
            if (abilityName.equals(s.getName())) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * @author Jason Owens
     *
     * Activates ability. 
     * TODO Maybe store abilities in a Map<Name, Ability>? - Kyle
     *@returns whether or not the ability is found    
     */
    public boolean performActiveAbility(String abilityName, Entity callingEntity){
        for(Ability a : knownAbilities) {
            if (a.getName().equals(abilityName)) {
                a.performAbility(callingEntity.getDirection());
                return true;
            }
        }
        return false;
    }
    
    public List<Ability >getAbilities() {
    	return this.knownAbilities;
    }
}
