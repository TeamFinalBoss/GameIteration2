package model.entity;

import model.effect.DealDamageEffect;
import model.entity.ability.Brawl;
import model.entity.ability.SmasherLibrary;
import model.entity.stats.SmasherStats;
import model.map.pair.CoordinatePair;
import model.item.Takeable;
public abstract class SmasherEntity extends Entity{
        Brawl myB;
    
	/* -------------------- PROTECTED CREATION ------------------- */
	protected SmasherLibrary createAbilities(){
		return new SmasherLibrary(this);
	}
	protected SmasherStats createStats(){
		return new SmasherStats();
	}
	protected String setOccupation(){
		return "smasher";
	}
	
	/* -------------------- PROTECTED UTILITY ------------------- */
	protected SmasherLibrary getAbilities(){
		return (SmasherLibrary) super.getAbilities();
	}
	public SmasherStats getStats(){
		return (SmasherStats) super.getStats();
	}
	
	/* -------------------- CONSTRUCTORS -------------------- */
	public SmasherEntity(String objectName,
				  String description,
				  CoordinatePair location){
		super(objectName,description,location);
                myB = new Brawl();
	}
	
	/* -------------------- STATS ACCESSORS -------------------- */
	public int getOneHanded(){
		return getStats().oneHanded();
	}
	public int getTwoHanded(){
		return getStats().twoHanded();
	}
	public int getBrawling(){
		return getStats().brawling();
	}
	public int getChakra(){
		return getStats().chakra();
	}
	
	/* -------------------- STATS MODIFIER MUTATORS -------------------- */
	public void modifyOneHanded(int modifier){
		getStats().modifyOneHanded(modifier);
	}
	public void modifyTwoHanded(int modifier){
		getStats().modifyTwoHanded(modifier);
	}
	public void modifyBrawling(int modifier){
		getStats().modifyBrawling(modifier);
	}
	public void modifyChakra(int modifier){
		getStats().modifyChakra(modifier);
	}
	
	/* -------------------- STATS SETTER MUTATORS -------------------- */
	public void setOneHanded(int modifier){
		getStats().setOneHanded(modifier);
	}
	public void setTwoHanded(int modifier){
		getStats().setTwoHanded(modifier);
	}
	public void setBrawling(int modifier){
		getStats().setBrawling(modifier);
	}
	public void setChakra(int modifier){
		getStats().setChakra(modifier);
	} 
        
        @Override
        public void useWeapon(){
            if(!this.getInventory().useWeapon(this)){
                myB.performAbility(this);
            }
        }
        
}

