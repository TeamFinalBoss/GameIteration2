package model.ability;

/*
 * TODO: finish this (pushed just so everyone can see it)
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.entity.Entity;

/**
 * The purpose of this class is to serve as a container for Spells, passing commands downward from [TBD, Entity?]
 * @author Jason Owens
 */
public class AbilityLibrary {
    private Map<String,Ability> knownAbilities;
    
    /*-----------Constructors-----------*/
    public AbilityLibrary(){
        knownAbilities = new HashMap<>();
    }
    
    /*-----------Mutators-----------*/
    public void addAbility(Ability ability){
        knownAbilities.put(ability.getName(), ability);        
    }
    public boolean forgetAbility(String abilityName){
    	if(this.knownAbilities.containsKey(abilityName)) {
    		knownAbilities.remove(abilityName);
    		return true;
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
    	return this.knownAbilities.containsKey(abilityName);
    }
    
    
    /**
     * @author Jason Owens
     *
     * Activates ability. 
     * TODO Maybe store abilities in a Map<Name, Ability>? - Kyle
     *@returns whether or not the ability is found  
     *@params abilityName the String form of the ability to perform
     *@params callingEntity the Entity using the ability
     */
    public boolean performActiveAbility(String abilityName, Entity callingEntity){
    	if(this.knownAbilities.containsKey(abilityName)) {
    		knownAbilities.get(abilityName).performAbility(callingEntity);
    		return true;
    	}
    	return false;
    }
    
    public List<Ability>getAbilities() {
    	List<Ability> abilities = new ArrayList<>();
    	for(Map.Entry<String, Ability> entry : this.knownAbilities.entrySet()) {
    		abilities.add(entry.getValue());
    	}
    	return abilities;
    }
}
