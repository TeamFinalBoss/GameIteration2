package model.entity;

public class Stats {
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
	private int maxHP;
	private int maxMP;
	private int offense;
	private int defense;
	private int armor;
	/* -------------------- GENERIC SKILLS -------------------- */
	private int bindWounds;
	private int bargain;
	private int observation;
	/* -------------------- OTHER STATS -------------------- */
	private int currentHP;
	private int currentMP;
	private int weaponOffense; //stores the contribution of the currently equipped weapon towards offense
	private int equipArmor; //stores the contribution of the current equipment towards armor
	
	/* -------------------- PRIVATE UTILITY -------------------- */
	private void updateDerived(){
		level = experience / 10000;
		maxHP = (hardiness*10) + (level*20);
		maxMP = (intellect*10) + (level*20);
		offense = weaponOffense + (strength*10) + (level*20);
		defense = (agility*10) + (level*20);
		armor = equipArmor + (hardiness*30);
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
		currentHP = maxHP;
		currentMP = maxMP;
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
	public int maxHP(){
		return maxHP;
	}
	public int maxMP(){
		return maxMP;
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
	public int currentHP(){
		return currentHP;
	}
	public int currentMP(){
		return currentMP;
	}
	public int weaponOffense(){
		return weaponOffense;
	}
	public int equipArmor(){
		return equipArmor;
	}
	
	/* -------------------- MUTATORS -------------------- */

}
