/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Skills;

import java.util.ArrayList;

/**
 *  The purpose of this class is to serve as a container for Spells, passing commands downward from [TBD, Entity?]
 * @author Jason Owens
 */
public class SkillLibrary {
    private ArrayList<Skills> knownSkills;
    
    public SkillLibrary(){
        knownSkills = new ArrayList<>();
    }
    
    public void addSkill(Skill skill){
        knownSkills.add(skill);
        
    }
    
}
