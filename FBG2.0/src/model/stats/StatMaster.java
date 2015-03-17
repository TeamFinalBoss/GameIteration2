package model.stats;


import model.stats.Stats;

/**
 * 
 * maintains temporary and normal stats for an Entity
 * @author Jason Owens (and someone else who didn't comment)
 */
public class StatMaster {
	private Stats tempStats;
	private PlayerStats baseStats;
	
	public StatMaster(PlayerStats baseStats, Stats tempStats) {
		this.tempStats = tempStats;
		this.baseStats = baseStats;
	}
	
	public StatMaster() {
		this.tempStats = new Stats();
		this.baseStats = new PlayerStats();
	}
        	
	public Stats getTempStats() {
		return tempStats;
	}
	public PlayerStats getBaseStats() {
		return baseStats;
	}
	
	public int getOffensiveRating() {
		int combinedStrength = tempStats.getStrength() + baseStats.getStrength();
		int combinedOffense = tempStats.getOffense() + baseStats.getOffense();
		
		return (combinedStrength * 10) + (baseStats.getLevel() * 100) + (combinedOffense * 10);
	}
	
	public int getDefensiveRating() {
		int combinedAgility = tempStats.getAgility() + baseStats.getAgility();
		
		return (combinedAgility * 10) + (baseStats.getLevel() * 100);
	}
	
	public int getArmorRating() {
		int combinedHardiness = tempStats.getHardiness() + baseStats.getHardiness();
		int combinedDefense = tempStats.getDefense() + baseStats.getDefense();
		
		return (combinedHardiness * 10) + (combinedDefense * 10);
	}
	
	public int getStrength(){
		return tempStats.getStrength() + baseStats.getStrength();
	}
	public int getAgility(){
		return tempStats.getAgility() + baseStats.getAgility();
	}
	public int getIntellect(){
		return tempStats.getIntellect() + baseStats.getIntellect();
	}
	public int getHardiness(){
		return tempStats.getHardiness() + baseStats.getHardiness();
	}
	public int getMovement(){
		return tempStats.getMovement() + baseStats.getMovement();
	}
	public int gethpMax(){
		return tempStats.gethpMax() + baseStats.gethpMax();
	}
	public int getmpMax(){
		return tempStats.getmpMax() + baseStats.getmpMax();
	}
	public int getOffense(){
		return tempStats.getOffense() + baseStats.getOffense();
	}
	public int getDefense(){
		return tempStats.getDefense() + baseStats.getDefense();
	}
	public int getLevel(){
		return baseStats.getLevel();
	}
	public int getLivesLeft(){
		return baseStats.getLivesLeft();
	}
	public int getExperience(){
		return baseStats.getExperience();
	}
	public int gethpCurrent(){
		return baseStats.getHpCurrent();
	}
	public int getmpCurrent(){
		return baseStats.getMpCurrent();
	}
	public void addTempStats(Stats newTemp){
		baseStats.mergeStats(newTemp);
	}
	public void removeTempStats(Stats oldTemp){
		baseStats.mergeStats(oldTemp.inverted());
	}
	
}
