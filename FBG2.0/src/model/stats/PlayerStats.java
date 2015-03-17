package model.stats;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import model.stats.Stats;
import model.util.Saveable;

public class PlayerStats extends Stats  {
		
	
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
			super();
			this.hpCurrent = 5;
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
		
		@Override
		public String toXML() {
			String superXML = super.toXML();
			BufferedReader reader = new BufferedReader(new StringReader(superXML));
			String xml = "<PlayerStats>\n";
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
			
			xml += "\t<hpCurrent>" + hpCurrent + "</hpCurrent>\n";
			xml += "\t<mpCurrent>" + mpCurrent + "</mpCurrent>\n";
			xml += "\t<level>" + level + "</level>\n";
			xml += "\t<livesLeft>" + livesLeft + "</livesLeft>\n";
			xml += "\t<experience>" + experience + "</experience>\n";
			
			xml += "</PlayerStats>\n";
			
			return xml;
		}
		
		
}
