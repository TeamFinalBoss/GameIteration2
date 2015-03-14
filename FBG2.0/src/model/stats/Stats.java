package model.stats;


public class Stats{

	protected int strength;
	protected int agility;
	protected int intellect;
	protected int hardiness;
	protected int movement;
	protected int hpMax;
	protected int mpMax;
	protected int defense;
	protected int offense;
	
	public Stats( int strength,
	  		 int agility,
	  		 int intellect,
	  		 int hardiness,
	  		 int movement,
	  		 int hpMax,
	  		 int mpMax,
	  		 int defense,
	  		 int offense){
		this.strength = strength;
		this.agility = agility;
		this.intellect = intellect;
		this.hardiness = hardiness;
		this.movement = movement;
		this.hpMax = hpMax;
		this.mpMax = mpMax;
		this.defense = defense;
		this.offense = offense;
	}
	
	public Stats(){
		this.strength = 0;
		this.agility = 0;
		this.intellect = 0;
		this.hardiness = 0;
		this.movement = 0;
		this.hpMax = 0;
		this.mpMax = 0;
		this.defense = 0;
		this.offense = 0; 
	}
	
	public int getStrength(){
		return strength;
	}
	public int getAgility(){
		return agility;
	}
	public int getIntellect(){
		return intellect;
	}
	public int getHardiness(){
		return hardiness;
	}
	public int getMovement(){
		return movement;
	}
	public int gethpMax(){
		return hpMax;
	}
	public int getmpMax(){
		return mpMax;
	}
	public int getOffense(){
		return offense;
	}
	public int getDefense(){
		return defense;
	}
	
	public void setStrength(int nextStr){
		strength = verifyBounds(nextStr) ? nextStr : strength;
	}
	public void setAgility(int nextAgi){
		agility = verifyBounds(nextAgi) ? nextAgi : agility;
	}
	public void setIntellect(int nextInt){
		intellect = verifyBounds(nextInt) ? nextInt : intellect;
	}
	public void setHardiness(int nextHard){
		hardiness = verifyBounds(nextHard) ? nextHard : hardiness;
	}
	public void setMovement(int nextMove){
		movement = verifyBounds(nextMove) ? nextMove : movement;
	}
	public void sethpMax(int nextHP){
		hpMax = nextHP;
	}
	public void setmpMax(int nextMP){
		mpMax = verifyBounds(nextMP) ? nextMP : mpMax;
	}
	public void setDefense(int nextDef){
		defense = verifyBounds(nextDef) ? nextDef : defense;
	}
	public void setOffense(int nextOff){
		offense = verifyBounds(nextOff) ? nextOff : offense;
	}
	public void modStrength(int strAdded){
		strength = verifyBounds(strength+strAdded) ? (strength+strAdded) : 0;
	}
	public void modAgility(int agiAdded){
		agility = verifyBounds(agility + agiAdded) ? (agility + agiAdded) : 0;
	}
	public void modIntellect(int intAdded){
		intellect = verifyBounds(intellect + intAdded) ? (intellect+intAdded) : 0;
	}
	public void modHardiness(int hardAdded){
		hardiness = verifyBounds(hardiness + hardAdded) ? (hardiness + hardAdded) : 0;
	}
	public void modMovement(int moveAdded){
		movement = verifyBounds(movement + moveAdded) ? (movement + moveAdded) : 0;
	}
	public void modhpMax(int hpAdded){
		hpMax += hpAdded;
	}
	public void modmpMax(int mpAdded){
		mpMax = verifyBounds(mpMax + mpAdded) ? (mpMax + mpAdded) : 0;
	}
	public void modOffense(int offAdded){
		offense = verifyBounds(offense + offAdded) ? (offense + offAdded) : 0;
	}
	public void modDefense(int defAdded){
		defense = verifyBounds(defense + defAdded) ? (defense + defAdded) : 0;
	}
	public Stats inverted(){
		return new Stats(strength*-1,agility*-1,intellect*-1,hardiness*-1,movement*-1,hpMax*-1,mpMax*-1,defense*-1,offense*-1);
	}
	
	protected boolean verifyBounds(int value) {
		return (value < 0) ? false : true;
	}
	
	public void mergeStats(Stats modifier){
        modStrength(modifier.getStrength());
        modAgility(modifier.getAgility());
        modIntellect(modifier.getIntellect());
        modHardiness(modifier.getHardiness());
        modMovement(modifier.getMovement());
        modhpMax(modifier.gethpMax());
        modmpMax(modifier.getmpMax());
        modOffense(modifier.getOffense());
        modDefense(modifier.getDefense());
	}

}
