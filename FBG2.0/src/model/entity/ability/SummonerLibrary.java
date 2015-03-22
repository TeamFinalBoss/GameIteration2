package model.entity.ability;

import model.entity.Entity;

public class SummonerLibrary extends AbilityLibrary {
	public SummonerLibrary(Entity owner){
		super(owner);
		FireballAbility myFire = new FireballAbility();
		this.addAbility(myFire);
	}
        
        public void update(){
            
        }
}
