/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Skills;

import java.util.ArrayList;

/**
 * The purpose of this class is to serve as a container for Spells, passing commands downward from [TBD, Entity?]
 * @author Jason Owens
 */
public class SkillLibrary {
    private ArrayList<UsableSkill> knownSkills;
    
    /*-----------Constructors-----------*/
    public SkillLibrary(){
        knownSkills = new ArrayList<>();
    }
    
    /*-----------Mutators-----------*/
    public void addSkill(UsableSkill skill){
        knownSkills.add(skill);        
    }
    public boolean forgetSkill(String skillName){
         for(UsableSkill s : knownSkills) {
            if (s.getName().equals(skillName)) {
                return knownSkills.remove(s); 
            }
        }
        return false; //skill isn't known
    }
    
     /*-----------Accessors-----------*/
    public boolean hasSkill(String skillName){
        for(Skill s : knownSkills) {
            if (s.getName().equals(skillName)) {
                return true;
            }
        }
        return false;
    }
    
    
    /*
     * @author Jason Owens
     *
     * Activates skill.
     *@returns whether or not the skill is found    
     */
    public boolean PerformActiveSkill(String skillName, Entity callingEntity){
        for(UsableSkill s : knownSkills) {
            if (s.getName().equals(skillName)) {
                s.performSkill(callingEntity);
            }
        }
    }
}
