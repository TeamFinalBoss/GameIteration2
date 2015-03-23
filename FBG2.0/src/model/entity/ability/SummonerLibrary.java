package model.entity.ability;

import model.entity.Entity;

public class SummonerLibrary extends AbilityLibrary {
	public SummonerLibrary(Entity owner){
		super(owner);
		FireballAbility myFire = new FireballAbility();
		BlinkAbility myBlink = new BlinkAbility();
		BargainEnchantmentAbility myBargain = new BargainEnchantmentAbility();
		PacifyEnchantment myPacify = new PacifyEnchantment();
		SiphonHealthEnchantment mySiphon = new SiphonHealthEnchantment();
        FlameStrikeAbility flameStrike = new FlameStrikeAbility();
        FrostNovaAbility frostNova = new FrostNovaAbility();
		Shield shield = new Shield(owner);
		GodLevelAbility godLevel = new GodLevelAbility();
                
        
		this.addToLibrary(shield);
		this.addToLibrary(myFire);
		this.addToLibrary(myBlink);
		this.addToLibrary(myBargain);
		this.addToLibrary(myPacify);
		this.addToLibrary(mySiphon);
		this.addToLibrary(flameStrike);
		this.addToLibrary(frostNova);
		this.addToLibrary(godLevel);
	}
        
        public void update(){
            
        }
}
