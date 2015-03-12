/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Ability;

import java.util.ArrayList;

/**
 * The purpose of this class is to serve as a container for Spells, passing commands downward from [TBD, Entity?]
 * @author Jason Owens
 */
public class AbilityLibrary {
    private ArrayList<Ability> knownAbilitys;
    
    /*-----------Constructors-----------*/
    public AbilityLibrary(){
        knownAbilitys = new ArrayList<>();
    }
    
    /*-----------Mutators-----------*/
    public void addAbility(Ability ability){
        knownAbilitys.add(ability);        
    }
    public boolean forgetAbility(String abilityName){
         for(Ability s : knownAbilitys) {
            if (s.getName().equals(abilityName)) {
                return knownAbilitys.remove(s); 
            }
        }
        return false; //ability isn't known
    }
    
     /*-----------Accessors-----------*/
    public boolean hasAbility(String abilityName){
        for(Ability s : knownAbilitys) {
            if (s.getName().equals(abilityName)) {
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
        for(Ability s : knownAbilitys) {
            if (s.getName().equals(abilityName)) {
                s.performAbility(callingEntity);
            }
        }
    }
}
