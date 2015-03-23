package model.entity.ability;

import model.entity.Entity;

public class SneakLibrary extends AbilityLibrary{
	public SneakLibrary(Entity owner){
		super(owner);
		
		SetTrapAbility setTrap = new SetTrapAbility();
		DisableTrapAbility myDisable = new DisableTrapAbility();
        PickPocketAbility pickpocket = new PickPocketAbility();
        FlameStrikeAbility fsa = new FlameStrikeAbility();
		
        this.addToLibrary(pickpocket);
		this.addToLibrary(setTrap);
		this.addToLibrary(myDisable);
                
                this.addToLibrary(fsa);
	}

    
    public void update(){

    }
}
