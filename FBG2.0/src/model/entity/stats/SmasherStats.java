//buy Matthew Kroeze

package model.entity.stats;


public class SmasherStats extends Stats {
	/* -------------------- SMASHER SKILLS -------------------- */
	private int oneHanded;
	private int twoHanded;
	private int brawling;
	private int chakra;
	
	/* -------------------- CONSTRUCTORS -------------------- */
	public SmasherStats(){
		super();
		oneHanded = 0;
		twoHanded = 0;
		brawling = 0;
		chakra = 0;
	}
	
	/* -------------------- ACCESSORS -------------------- */
	public int oneHanded(){
		return oneHanded;
	}
	public int twoHanded(){
		return twoHanded;
	}
	public int brawling(){
		return brawling;
	}
	public int chakra(){
		return chakra;
	}
	
	/* -------------------- SET MUTATORS -------------------- */
	public void setOneHanded(int next){
		if(!validate(next)) throw new IllegalArgumentException("OneHanded not set: illegal value of " + next + " attempted.");
		oneHanded = next;
	}
	public void setTwoHanded(int next){
		if(!validate(next)) throw new IllegalArgumentException("TwoHanded not set: illegal value of " + next + " attempted.");
		twoHanded = next;
	}
	public void setBrawling(int next){
		if(!validate(next)) throw new IllegalArgumentException("Brawling not set: illegal value of " + next + " attempted.");
		brawling = next;
	}
	public void setChakra(int next){
		if(!validate(next)) throw new IllegalArgumentException("Chakra not set: illegal value of " + next + " attempted.");
		chakra = next;
	}
	
	/* -------------------- MODIFY MUTATORS -------------------- */
	public void modifyOneHanded(int modifier){
		oneHanded = max(oneHanded+modifier,0);
	}
	public void modifyTwoHanded(int modifier){
		twoHanded = max(twoHanded+modifier,0);
	}
	public void modifyBrawling(int modifier){
		brawling = max(brawling+modifier,0);
	}
	public void modifyChakra(int modifier){
		chakra = max(chakra+modifier,0);
	}
}
