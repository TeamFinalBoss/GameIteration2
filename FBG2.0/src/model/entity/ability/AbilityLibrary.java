package model.entity.ability;

import java.util.ArrayList;
import java.util.List;

import model.entity.Entity;


//TODO: add the following operation: useAbility(int position)

/**
 * The purpose of this class is to serve as a container for Spells, passing commands downward from [TBD, Entity?]
 * @author Jason Owens, Matthew Kroeze
 */
public abstract class AbilityLibrary {
    private ArrayList<Ability> learnedAbilities;
    private ArrayList<Ability> unlearnedAbilities;
    
    private Entity owner;
    
    /* -------------------- CONSTRUCTORS -------------------- */
    public AbilityLibrary(Entity myEntity){
        learnedAbilities = new ArrayList<Ability>();
        unlearnedAbilities = new ArrayList<Ability>();
        owner = myEntity;
    }
    
    /*-----------Mutators-----------*/
    public void addAbility(Ability ability){
        learnedAbilities.add(ability); 
        unlearnedAbilities.remove(ability);
    }
    public boolean forgetAbility(String abilityName){
         for(Ability s : learnedAbilities) {
            if (s.getName().equals(abilityName)) {
                return learnedAbilities.remove(s); 
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
        for(Ability s : learnedAbilities) {
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
     *@params abilityName the String form of the ability to perform
     *@params callingEntity the Entity using the ability
     */
    public boolean performActiveAbility(String abilityName, Entity callingEntity){
        for(Ability a : learnedAbilities) {
            if (a.getName().equals(abilityName)) {
                a.performAbility(callingEntity);
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Checks to see if a skill can be moved from unlearnedSkills to LearnedSkill based 
     * on new Stats.
     * @author Jason Owens
     * 
     */
    public abstract void update();
    
    
    public boolean performActiveAbility(int position, Entity callingEntity){
    	if(position >= learnedAbilities.size()) return false;
    	learnedAbilities.get(position).performAbility(callingEntity);
    	return true;
    }
    
    public List<Ability> getAbilities() {
    	return this.learnedAbilities;
    }
}
