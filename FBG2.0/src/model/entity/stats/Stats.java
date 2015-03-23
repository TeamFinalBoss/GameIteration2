package model.entity.stats;

import java.util.Random;

public abstract class Stats {
	public int getCurrentHealth() {
		return currentHealth;
	}
	public int getCurrentMana() {
		return currentMana;
	}
	/* -------------------- PRIMARY ATTRIBUTES -------------------- */
	private int livesLeft;
	private int strength;
	private int agility;
	private int intellect;
	private int hardiness;
	private int experience;
	private int movement;
	/* -------------------- DERIVED ATTRIBUTES -------------------- */
	private int level;
	private int maxHealth;
	private int maxMana;
	private int offense;
	private int defense;
	private int armor;
	/* -------------------- GENERIC SKILLS -------------------- */
	private int bindWounds;
	private int bargain;
	private int observation;
	/* -------------------- OTHER STATS -------------------- */
	private int currentHealth;
	private int currentMana;
	private int weaponOffense; //stores the contribution of the currently equipped weapon towards offense
	private int equipArmor; //stores the contribution of the current equipment towards armor
	
	/* -------------------- PRIVATE UTILITY -------------------- */
	private void updateDerived(){
		level = experience / 10000;
		maxHealth = (hardiness*10) + (level*20);
		maxMana = (intellect*10) + (level*20);
		offense = weaponOffense + (strength*10) + (level*20);
		defense = (agility*10) + (level*20);
		armor = equipArmor + (hardiness*30);
		
	}
	private boolean checkHitSuccess(int amount){
		Random generator = new Random();
		return (generator.nextInt(100)*amount > defense) ? true : false;
		
	}
	private int mitigateDamage(int amount){
		return amount - armor;
	}
	
	/* -------------------- PROTECTED UTILITY -------------------- */
	protected int max(int a, int b){
		return a > b ? a : b;
	}
	
	protected boolean validate(int value){
		return (value < 0) ? false : true;
	}
	
	/* -------------------- CONSTRUCTORS -------------------- */
	public Stats(){
		livesLeft = 1;
		strength = 10;
		agility = 10;
		intellect = 10;
		hardiness = 10;
		experience = 0;
		movement = 30;
		bindWounds = 0;
		bargain = 0;
		observation = 0;
		updateDerived();
		currentHealth = maxHealth;
		currentMana = maxMana;
		weaponOffense = 0;
		equipArmor = 0;
	}
	
	/* -------------------- ACCESSORS -------------------- */
	public int livesLeft(){
		return livesLeft;
	}
	public int strength(){
		return strength;
	}
	public int agility(){
		return agility;
	}
	public int intellect(){
		return intellect;
	}
	public int hardiness(){
		return hardiness;
	}
	public int experience(){
		return experience;
	}
	public int movement(){
		return movement;
	}
	public int level(){
		return level;
	}
	public int maxHealth(){
		return maxHealth;
	}
	public int maxMana(){
		return maxMana;
	}
	public int offense(){
		return offense;
	}
	public int defense(){
		return defense;
	}
	public int armor(){
		return armor;
	}
	public int bindWounds(){
		return bindWounds;
	}
	public int bargain(){
		return bargain;
	}
	public int observation(){
		return observation;
	}
	public int currentHealth(){
		return currentHealth;
	}
	public int currentMana(){
		return currentMana;
	}
	public int weaponOffense(){
		return weaponOffense;
	}
	public int equipArmor(){
		return equipArmor;
	}
	
	/* -------------------- SET MUTATORS -------------------- */
	public void setLivesLeft(int next){
		if(!validate(next)) throw new IllegalArgumentException("LivesLeft not set: illegal value of " + next + " attempted.");
		livesLeft = next;
		updateDerived();
	}
	public void setStrength(int next){
		if(!validate(next)) throw new IllegalArgumentException("Strength not set: illegal value of " + next + " attempted.");
		strength = next;
		updateDerived();
	}
	public void setAgility(int next){
		if(!validate(next)) throw new IllegalArgumentException("Agility not set: illegal value of " + next + " attempted.");
		agility = next;
		updateDerived();
	}
	public void setIntellect(int next){
		if(!validate(next)) throw new IllegalArgumentException("Intellect not set: illegal value of " + next + " attempted.");
		intellect = next;
		updateDerived();
	}
	public void setHardiness(int next){
		if(!validate(next)) throw new IllegalArgumentException("Hardiness not set: illegal value of " + next + " attempted.");
		hardiness = next;
		updateDerived();
	}
	public void setExperience(int next){
		if(!validate(next)) throw new IllegalArgumentException("Experience not set: illegal value of " + next + " attempted.");
		experience = next;
		updateDerived();
	}
	public void setMovement(int next){
		if(!validate(next)) throw new IllegalArgumentException("Movement not set: illegal value of " + next + " attempted.");
		movement = next;
		updateDerived();
	}
	public void setBindWounds(int next){
		if(!validate(next)) throw new IllegalArgumentException("BindWounds not set: illegal value of " + next + " attempted.");
		bindWounds = next;
		updateDerived();
	}
	public void setBargain(int next){
		if(!validate(next)) throw new IllegalArgumentException("Bargain not set: illegal value of " + next + " attempted.");
		bargain = next;
		updateDerived();
	}
	public void setObservation(int next){
		if(!validate(next)) throw new IllegalArgumentException("Observation not set: illegal value of " + next + " attempted.");
		observation = next;
		updateDerived();
	}
	public void setCurrentHealth(int next){
		if(!validate(next)) throw new IllegalArgumentException("CurrentHP not set: illegal value of " + next + " attempted.");
		currentHealth = next;
		updateDerived();
	}
	public void setCurrentMana(int next){
		if(!validate(next)) throw new IllegalArgumentException("CurrentMP not set: illegal value of " + next + " attempted.");
		currentMana = next;
		updateDerived();
	}
	public void setWeaponOffense(int next){
		weaponOffense = next;
		updateDerived();
	}
	public void setEquipArmor(int next){
		equipArmor = next;
		updateDerived();
	}
	
	/* -------------------- MODIFY MUTATORS -------------------- */
	public void levelUp(){
		experience += 10000 - (experience % 10000);
	}
	public void dealDamage(int amount){
		if(!checkHitSuccess(amount)) return;
		amount = mitigateDamage(amount);
		modifyCurrentHP(amount);
	}
	public void modifyLivesLeft(int modifier){
		livesLeft = max(livesLeft+modifier,0);
		updateDerived();
	}
	public void modifyStrength(int modifier){
		strength = max(strength+modifier,0);
		updateDerived();
	}
	public void modifyAgility(int modifier){
		agility = max(agility+modifier,0);
		updateDerived();
	}
	public void modifyIntellect(int modifier){
		intellect = max(intellect+modifier,0);
		updateDerived();
	}
	public void modifyHardiness(int modifier){
		hardiness = max(hardiness+modifier,0);
		updateDerived();
	}
	public void modifyExperience(int modifier){
		experience = max(experience+modifier,0);
		updateDerived();
	}
	public void modifyMovement(int modifier){
		movement = max(movement+modifier,0);
		updateDerived();
	}
	public void modifyBindWounds(int modifier){
		bindWounds = max(bindWounds+modifier,0);
		updateDerived();
	}
	public void modifyBargain(int modifier){
		bargain = max(bargain+modifier,0);
		updateDerived();
	}
	public void modifyObservation(int modifier){
		observation = max(observation+modifier,0);
		updateDerived();
	}
	public void modifyCurrentHP(int modifier){
		currentHealth = max(currentHealth+modifier,0);
		updateDerived();
	}
	public void modifyCurrentMP(int modifier){
		currentMana = max(currentMana+modifier,0);
		updateDerived();
	}
	public void modifyWeaponOffense(int modifier){
		weaponOffense = weaponOffense+modifier;
		updateDerived();
	}
	public void modifyEquipArmor(int modifier){
		equipArmor = equipArmor+modifier;
		updateDerived();
	}
}
