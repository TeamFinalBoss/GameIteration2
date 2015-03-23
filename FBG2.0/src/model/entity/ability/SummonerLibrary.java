package model.entity.ability;

import model.entity.Entity;

public class SummonerLibrary extends AbilityLibrary {
	public SummonerLibrary(Entity owner){
		super(owner);
		FireballAbility myFire = new FireballAbility();
		BlinkAbility myBlink = new BlinkAbility();
		BargainEnchantmentAbility myBargain = new BargainEnchantmentAbility();
		
		this.addToLibrary(myFire);
		this.addToLibrary(myBlink);
		this.addToLibrary(myBargain);
	}
        
        public void update(){
            
        }
}
