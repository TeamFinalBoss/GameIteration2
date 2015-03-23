package model.entity.ability;

import model.entity.Entity;

public class SummonerLibrary extends AbilityLibrary {
	public SummonerLibrary(Entity owner){
		super(owner);
		FireballAbility myFire = new FireballAbility();
		BlinkAbility myBlink = new BlinkAbility();
		
		this.addToLibrary(myFire);
		this.addToLibrary(myBlink);
	}
        
        public void update(){
            
        }
}
