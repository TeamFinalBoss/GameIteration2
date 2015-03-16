package model.stats;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

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
			int offense){
			
			super(strength, agility, intellect, hardiness, movement, hpMax, mpMax, defense, offense);
			
			this.durability = durability;
			this.value = value;
		}
		
		public ItemStats()
		{
			super(0,0,0,0,0,0,0,0,0);
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

		@Override
		public String toXML() {
			String superXML = super.toXML();
			BufferedReader reader = new BufferedReader(new StringReader(superXML));
			String xml = "<ItemStat>\n";
		
			String curLine;
			try {
				while((curLine = reader.readLine()) != null)
				{
					xml += "\t" + curLine + "\n";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//xml += "\n";
			xml += "\t<durability>" + this.durability + "</durability>\n";
			xml += "\t<value>" + this.value + "</value>\n";
			xml += "\n";
			
			xml += "</ItemStat>";
			return xml;
		}
		
		
	
}
