package model.util;

import model.effect.Dispellable;
import model.entity.Entity;
import model.entity.SmasherEntity;
import model.entity.SneakEntity;
import model.entity.SummonerEntity;
import model.item.Interactive;
import model.item.Obstacle;
import model.item.OneShot;
import model.item.Takeable;

public class ObjectSaver {
	
	public String getSaveFormat(Takeable i) {
		String save = "<takeable ";
		save += "name=\"" + i.getName() + "\" ";
		save += "x=\"" + i.getLocation().getX() + "\" ";
		save += "y=\"" + i.getLocation().getY() + "\" ";
		save += "durabiity=\"" + i.getDurability() + "\" />";
		return save;
	}
	
	public String getSaveFormat(OneShot i) {
		String save = "<oneshot ";
		save += "name=\"" + i.getName() + "\" ";
		save += "x=\"" + i.getLocation().getX() + "\" ";
		save += "y=\"" + i.getLocation().getY() + "\" />";
		return save;
	}
	
	public String getSaveFormat(Obstacle i) {
		String save = "<obstacle ";
		save += "name=\"" + i.getName() + "\" ";
		save += "x=\"" + i.getLocation().getX() + "\" ";
		save += "y=\"" + i.getLocation().getY() + "\" ";
		save += "link=\"" + i.getLink() + "\" />";
		return save;
	}

	public String getSaveFormat(Interactive i) {
		String save = "<interactive ";
		save += "name=\"" + i.getName() + "\" ";
		save += "x=\"" + i.getLocation().getX() + "\" ";
		save += "y=\"" + i.getLocation().getY() + "\" ";
		save += "link=\"" + i.getLink() + "\" />";
		return save;
	}
	
	public String getSaveFormat(Entity e) {
		String save = "<";
		
		if(e.getType().equals("avatar")) save += "avatar ";
		else save += "entity ";
		
		save += "occupation=\"" + e.getOccupation() + "\" ";
		save += "x=\"" + e.getLocation().getX() + "\" ";
		save += "y=\"" + e.getLocation().getY() + "\" ";
		save += "currency=\"" + e.getCurrency() + "\" ";
		save += "direction=\"" + e.getDirection() + "\" ";
		save += "motiontype=\"" + e.getMotionType() + "\" >\n";
		
		save += getStatsFormat(e) + "\n";
		save += getSackFormat(e) + "\n";
		save += getArmoryFormat(e) + "\n";
		save += getEffectsFormat(e) + "\n";
		
		save += "</entity>";
		
		return save;
	}
	
	private String getSackFormat(Entity e) {
		String save = "<sack>\n";
		
		for(Takeable t : e.getSackContents()) {
			save += getTakeableFormat(t) + "\n";
		}
		
		save += "</sack>";
		return save;
	}
	
	private String getArmoryFormat(Entity e) {
		String save = "<armory>\n";
		
		for(Takeable t : e.getArmoryContents().values()) {
			save += getTakeableFormat(t) + "\n";
		}
		
		save += "</armory>";
		return save;
	}
	
	private String getEffectsFormat(Entity e) {
		String save = "<effects>\n";
		
		for(Dispellable d : e.getEffects()) {
			save += getEffectFormat(d) + "\n";
		}
		
		save += "</effects>";
		return save;
	}
	
	private String getEffectFormat(Dispellable d) {
		String save = "<effect ";
		save += "name=\"default\" />";
		return save;
	}
	
	private String getTakeableFormat(Takeable t) {
		String save = "<item ";
		save += "name=\"" + t.getName() + "\" ";
		save += "durability=\"" + t.getDurability() + "\" />";
		return save;
	}
	
	private String getStatsFormat(Entity e) {
		String save = "<stats ";
		save += "livesLeft=\"" + e.getLivesLeft() + "\" ";
		save += "strength=\"" + e.getStrength() + "\" ";
		save += "agility=\"" + e.getAgility() + "\" ";
		save += "intellect\"" + e.getIntellect() + "\" ";
		save += "hardiness=\"" + e.getHardiness() + "\" ";
		save += "experience=\"" + e.getExperience() + "\" ";
		save += "movement=\"" + e.getMovement() + "\" ";
		save += "bindWounds=\"" + e.getBindWounds() + "\" ";
		save += "bargain=\"" + e.getBargain() + "\" ";
		save += "observation=\"" + e.getObservation() + "\" ";
		save += "currentHp=\"" + e.getCurrentHP() + "\" ";
		save += "currentMp=\"" + e.getCurrentMP() + "\" ";
		save += "offense=\"" + e.getOffense() + "\" ";
		save += "defense=\"" + e.getDefense() + "\" ";
		
		switch(e.getOccupation()) {
		case "smasher":
			save += "stat1=\"" + ((SmasherEntity) e).getOneHanded() + "\" ";
			save += "stat2=\"" + ((SmasherEntity) e).getTwoHanded() + "\" ";
			save += "stat3=\"" + ((SmasherEntity) e).getBrawling() + "\" ";
			save += "stat4=\"" + ((SmasherEntity) e).getChakra() + "\" ";
			
		case "sneak":
			save += "stat1=\"" + ((SneakEntity) e).getPickPocket() + "\" ";
			save += "stat2=\"" + ((SneakEntity) e).getTrapSkill() + "\" ";
			save += "stat3=\"" + ((SneakEntity) e).getCreep() + "\" ";
			save += "stat4=\"" + ((SneakEntity) e).getRangedWeapon() + "\" ";
			
		case "summoner":
			save += "stat1=\"" + ((SummonerEntity) e).getEnchantment() + "\" ";
			save += "stat2=\"" + ((SummonerEntity) e).getBane() + "\" ";
			save += "stat3=\"" + ((SummonerEntity) e).getBoon() + "\" ";
			save += "stat4=\"" + ((SummonerEntity) e).getStaff() + "\" ";
		}
		
		save += "/>";
		
		return save;
	}
}
