package model.entity.ability;

import model.entity.Entity;

public class SummonerLibrary extends AbilityLibrary {
	public SummonerLibrary(Entity owner){
		super(owner);
		
		this.addAbility(new FireBall());
	}
        
        public void update(){
            
        }
}
