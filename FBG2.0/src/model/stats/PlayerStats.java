package model.stats;


import model.stats.Stats;
import model.util.Saveable;

public class PlayerStats extends Stats implements Saveable {
		
		private int hpCurrent;
		private int mpCurrent;
		private int level;
		private int livesLeft;
		private int experience;

		public PlayerStats(int level,
			int livesLeft,
			int strength,
			int agility,
			int intellect,
			int hardiness,
			int experience,
			int movement,
			int hpMax,
			int mpMax,
			int hpCurrent,
			int mpCurrent,
			int defense,
			int offense){
			
			super(strength, agility, intellect, hardiness, movement, hpMax, mpMax, defense, offense);
			
			this.hpCurrent = hpCurrent;
			this.mpCurrent = mpCurrent;
			this.level = level;
			this.livesLeft = livesLeft;
			this.experience = experience;
		}
		
		public PlayerStats()
		{
			super(0,0,0,0,0,0,0,0,0);
			this.hpCurrent = 0;
			this.mpCurrent = 0;
			this.level = 1;
			this.livesLeft = 0;
			this.experience = 0;
		}
		
		public int getHpCurrent() {
			return hpCurrent;
		}
		public int getMpCurrent() {
			return mpCurrent;
		}
		public int getLevel() {
			return level;
		}
		public int getLivesLeft() {
			return livesLeft;
		}
		public int getExperience() {
			return experience;
		}
		
		public void modhpCurrent(int hpAdded){
			hpCurrent += hpAdded;
			if(hpCurrent > hpMax) hpCurrent = hpMax;
		}
		public void modmpCurrent(int mpAdded){
			mpCurrent += mpAdded;
			if(mpCurrent > mpMax) mpCurrent = mpMax;
		}
		public void modLevel(int levelAdded){
			level += levelAdded;
		}
		public void modLivesLeft(int livesAdded){
			livesLeft += livesAdded;
		}
		public void modExperience(int experienceAdded){
			experience += experienceAdded;
		}
		
		public void sethpCurrent(int hpNew){
			hpCurrent = hpNew;
		}
		public void setmpCurrent(int mpNew){
			hpCurrent = mpNew;
		}
		public void setLevel(int levelNew){
			hpCurrent = levelNew;
		}
		public void setLivesLeft(int livesNew){
			hpCurrent = livesNew;
		}
		public void setExperience(int experienceNew){
			hpCurrent = experienceNew;
		}
		
		public String toXML(){
			//all Stat data stored as tag attributes for now, might be better to make
			//them separate child tags
			
			String str = "";
			str += "<stats level=\"" + level + "\""
					+ " livesleft=\"" + livesLeft + "\"" 
					+ " strength=\"" + strength + "\""
					+ " agility=\"" + agility + "\""
					+ " intellect=\"" + intellect + "\""
					+ " hardiness=\"" + hardiness + "\""
					+ " experience=\"" + experience + "\""
					+ " movement=\"" + movement + "\""
					+ " hpcurrent=\"" + hpCurrent + "\""
					+ " mpcurrent=\"" + mpCurrent + "\""
					+ " hpmax=\"" + hpMax + "\""
					+ " mpmax=\"" + mpMax + "\""
					+ " defense=\"" + defense + "\""
					+ " offense=\"" + offense + "\""
					;
			str += " />";
			return str;
		}
}