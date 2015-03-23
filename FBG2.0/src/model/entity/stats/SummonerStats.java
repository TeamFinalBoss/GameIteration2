//by Matthew Kroeze

package model.entity.stats;


public class SummonerStats extends Stats {
	/* -------------------- SUMMONER SKILLS -------------------- */
	private int enchantment;
	private int boon;
	private int bane;
	private int staff;
	
	/* -------------------- CONSTRUCTORS -------------------- */
	public SummonerStats(){
		super();
		enchantment = 0;
		boon = 0;
		bane = 0;
		staff = 0;
	}
	
	/* -------------------- ACCESSORS -------------------- */
	public int enchantment(){
		return enchantment;
	}
	public int boon(){
		return boon;
	}
	public int bane(){
		return bane;
	}
	public int staff(){
		return staff;
	}
	
	/* -------------------- SET MUTATORS -------------------- */
	public void setEnchantment(int next){
		if(!validate(next)) throw new IllegalArgumentException("Enchantment not set: illegal value of " + next + " attempted.");
		enchantment = next;
	}
	public void setBoon(int next){
		if(!validate(next)) throw new IllegalArgumentException("Boon not set: illegal value of " + next + " attempted.");
		boon = next;
	}
	public void setBane(int next){
		if(!validate(next)) throw new IllegalArgumentException("Bane not set: illegal value of " + next + " attempted.");
		bane = next;
	}
	public void setStaff(int next){
		if(!validate(next)) throw new IllegalArgumentException("Staff not set: illegal value of " + next + " attempted.");
		staff = next;
	}
	
	/* -------------------- MODIFY MUTATORS -------------------- */
	public void modifyEnchantment(int modifier){
		enchantment= max(enchantment+modifier,0);
	}
	public void modifyBoon(int modifier){
		boon = max(boon+modifier,0);
	}
	public void modifyBane(int modifier){
		bane = max(bane+modifier,0);
	}
	public void modifyStaff(int modifier){
		staff = max(staff+modifier,0);
	}
}
