package model.entity.ability;

import model.entity.Entity;

public class SneakLibrary extends AbilityLibrary{
	public SneakLibrary(Entity owner){
		super(owner);
		
		SetTrapAbility setTrap = new SetTrapAbility();
		DisableTrapAbility myDisable = new DisableTrapAbility();
                PickPocketAbility pickpocket = new PickPocketAbility();
		
                this.addToLibrary(pickpocket);
		this.addToLibrary(setTrap);
		this.addToLibrary(myDisable);
	}

    
    public void update(){

    }
}
