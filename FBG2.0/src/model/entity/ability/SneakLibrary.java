package model.entity.ability;

import model.entity.Entity;

public class SneakLibrary extends AbilityLibrary{
	public SneakLibrary(Entity owner){
		super(owner);
		
		SetTrapAbility setTrap = new SetTrapAbility();
		this.addToLibrary(setTrap);
	}

    
    public void update(){

    }
}
