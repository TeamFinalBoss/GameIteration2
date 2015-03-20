package model.entity;

import model.entity.ability.SneakLibrary;
import model.entity.stats.SneakStats;
import model.map.pair.CoordinatePair;

public abstract class SneakEntity extends Entity {
	/* -------------------- PROTECTED CREATION ------------------- */
	protected SneakLibrary createAbilities(){
		return new SneakLibrary();
	}
	protected SneakStats createStats(){
		return new SneakStats();
	}
	
	/* -------------------- PROTECTED UTILITY ------------------- */
	protected SneakLibrary getAbilities(){
		return (SneakLibrary) super.getAbilities();
	}
	protected SneakStats getStats(){
		return (SneakStats) super.getStats();
	}
	
	/* -------------------- CONSTRUCTORS -------------------- */
	public SneakEntity(String objectName,
				  String description,
				  CoordinatePair location){
		super(objectName,description,location);
	}
	
	/* -------------------- STATS ACCESSORS -------------------- */
	public int getPickPocket(){
		return getStats().pickPocket();
	}
	public int getTrapSkill(){
		return getStats().trap();
	}
	public int getCreep(){
		return getStats().creep();
	}
	public int getRangedWeapon(){
		return getStats().rangedWeapon();
	}
	
	/* -------------------- STATS MODIFIER MUTATORS -------------------- */
	public void modifyPickPocket(int modifier){
		getStats().modifyPickPocket(modifier);
	}
	public void modifyTrapSkill(int modifier){
		getStats().modifyTrap(modifier);
	}
	public void modifyCreep(int modifier){
		getStats().modifyCreep(modifier);
	}
	public void modifyRangedWeapon(int modifier){
		getStats().modifyRangedWeapon(modifier);
	}
	
	/* -------------------- STATS SETTER MUTATORS -------------------- */
	public void setPickPokcet(int modifier){
		getStats().setPickPocket(modifier);
	}
	public void setTrap(int modifier){
		getStats().setTrap(modifier);
	}
	public void setCreep(int modifier){
		getStats().setCreep(modifier);
	}
	public void setRangedWeapon(int modifier){
		getStats().setRangedWeapon(modifier);
	}
}
