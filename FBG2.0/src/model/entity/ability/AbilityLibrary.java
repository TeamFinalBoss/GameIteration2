package model.entity.ability;

import java.util.ArrayList;
import java.util.List;

import model.entity.Entity;


//TODO: add the following operation: useAbility(int position)

/**
 * The purpose of this class is to serve as a container for Spells, passing commands downward from [TBD, Entity?]
 * @author Jason Owens, Matthew Kroeze
 */
public class AbilityLibrary {
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
    protected void addAbility(Ability ability){
        learnedAbilities.add(ability); 
        unlearnedAbilities.remove(ability);
    }
    protected boolean forgetAbility(String abilityName){
         for(Ability s : learnedAbilities) {
            if (s.getName().equals(abilityName)) {
            	unlearnedAbilities.add(s);
                return learnedAbilities.remove(s); 
            }
        }
        return false; //ability isn't known
    }
    
    protected void addToLibrary(Ability ability){
    	unlearnedAbilities.add(ability);
    }
    
    protected boolean removeFromLibrary(Ability ability){
    	return (learnedAbilities.remove(ability) || unlearnedAbilities.remove(ability));
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
    
    /*-----------Accessors-----------*/
    
    
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
    public void update(){
    	for (Ability ability : unlearnedAbilities){
    		if (ability.meetsStatRequirements(entityToLearn)(owner)){
    			addAbility(ability);
    		}
    	}
    }
    
    
    public boolean performActiveAbility(int position, Entity callingEntity){
    	if(position >= learnedAbilities.size()) return false;
    	learnedAbilities.get(position).performAbility(callingEntity);
    	return true;
    }
    
    public List<Ability> getAbilities() {
    	return this.learnedAbilities;
    }
}
