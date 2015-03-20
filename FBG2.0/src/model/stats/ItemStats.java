package model.stats;


import model.stats.Stats;


public class ItemStats extends Stats{
	private int durability;
	private int value;
	
	public ItemStats(
			int strength,
			int agility,
			int intellect,
			int hardiness,
			int movement,
			int hpMax,
			int mpMax,
			int durability,
			int value,
			int defense,
			int offense,
                        int speed){
			
			super(strength, agility, intellect, hardiness, movement, hpMax, mpMax, defense, offense, speed);
			
			this.durability = durability;
			this.value = value;
		}
		
		public ItemStats()
		{
			super(1,1,1,1,1,1,1,1,1, 1);
			this.durability = 0;
			this.value = 0;
		}
		
		public int getDurability() {
			return durability;
		}
		public int getValue() {
			return value;
		}
		
		public void setDurability(int durabilityNew) {
			durability = durabilityNew;
		}
		public void setValue(int valueNew) {
			value = valueNew;
		}
		
		public void modDurability(int durabilityMod) {
			durability += durabilityMod;
		}
		public void modValue(int valueMod) {
			value += valueMod;
		}
}
