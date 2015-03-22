package model.entity;

import model.entity.ability.SummonerLibrary;
import model.entity.stats.SummonerStats;
import model.map.pair.CoordinatePair;

public abstract class SummonerEntity extends Entity {
	/* -------------------- PROTECTED CREATION ------------------- */
	protected SummonerLibrary createAbilities(){
		return new SummonerLibrary(this);
	}
	protected SummonerStats createStats(){
		return new SummonerStats();
	}
	protected String setOccupation(){
		return "summoner";
	}
	
	/* -------------------- PROTECTED UTILITY ------------------- */
	protected SummonerLibrary getAbilities(){
		return (SummonerLibrary) super.getAbilities();
	}
	protected SummonerStats getStats(){
		return (SummonerStats) super.getStats();
	}
	
	/* -------------------- CONSTRUCTORS -------------------- */
	public SummonerEntity(String objectName,
				  String description,
				  CoordinatePair location){
		super(objectName,description,location);
	}
	
	/* -------------------- STATS ACCESSORS -------------------- */
	public int getEnchantment(){
		return getStats().enchantment();
	}
	public int getBane(){
		return getStats().bane();
	}
	public int getBoon(){
		return getStats().boon();
	}
	public int getStaff(){
		return getStats().staff();
	}
	
	/* -------------------- STATS MODIFIER MUTATORS -------------------- */
	public void modifyEnchantment(int modifier){
		getStats().modifyEnchantment(modifier);
	}
	public void modifyBane(int modifier){
		getStats().modifyBane(modifier);
	}
	public void modifyBoon(int modifier){
		getStats().modifyBoon(modifier);
	}
	public void modifyStaff(int modifier){
		getStats().modifyStaff(modifier);
	}
	
	/* -------------------- STATS SETTER MUTATORS -------------------- */
	public void setEnchantment(int modifier){
		getStats().setEnchantment(modifier);
	}
	public void setBane(int modifier){
		getStats().setBane(modifier);
	}
	public void setBoon(int modifier){
		getStats().setBoon(modifier);
	}
	public void setStaff(int modifier){
		getStats().setStaff(modifier);
	}
}
