//buy Matthew Kroeze

package model.entity.stats;


public class SneakStats extends Stats {
	/* -------------------- SMASHER SKILLS -------------------- */
	private int pickPocket;
	private int trap;
	private int creep;
	private int rangedWeapon;
	
	/* -------------------- CONSTRUCTORS -------------------- */
	public SneakStats(){
		super();
		pickPocket = 0;
		trap = 0;
		creep = 0;
		rangedWeapon = 0;
	}
	
	/* -------------------- ACCESSORS -------------------- */
	public int pickPocket(){
		return pickPocket;
	}
	public int trap(){
		return trap;
	}
	public int creep(){
		return creep;
	}
	public int rangedWeapon(){
		return rangedWeapon;
	}
	
	/* -------------------- SET MUTATORS -------------------- */
	public void setPickPocket(int next){
		if(!validate(next)) throw new IllegalArgumentException("PickPocket not set: illegal value of " + next + " attempted.");
		pickPocket = next;
	}
	public void setTrap(int next){
		if(!validate(next)) throw new IllegalArgumentException("Trap skill not set: illegal value of " + next + " attempted.");
		trap = next;
	}
	public void setCreep(int next){
		if(!validate(next)) throw new IllegalArgumentException("Creep not set: illegal value of " + next + " attempted.");
		creep = next;
	}
	public void setRangedWeapon(int next){
		if(!validate(next)) throw new IllegalArgumentException("RangedWeapon not set: illegal value of " + next + " attempted.");
		rangedWeapon = next;
	}
	
	/* -------------------- MODIFY MUTATORS -------------------- */
	public void modifyPickPocket(int modifier){
		pickPocket = max(pickPocket+modifier,0);
	}
	public void modifyTrap(int modifier){
		trap = max(trap+modifier,0);
	}
	public void modifyCreep(int modifier){
		creep = max(creep+modifier,0);
	}
	public void modifyRangedWeapon(int modifier){
		rangedWeapon = max(rangedWeapon+modifier,0);
	}
}
