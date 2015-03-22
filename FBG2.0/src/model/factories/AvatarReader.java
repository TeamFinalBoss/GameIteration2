package model.factories;

import java.util.ArrayList;
import java.util.List;

import model.director.ActiveMapManager;
import model.effect.Dispellable;
import model.entity.Entity;
import model.entity.MotionType;
import model.entity.SmasherAvatar;
import model.entity.SmasherEntity;
import model.entity.SneakAvatar;
import model.entity.SneakEntity;
import model.entity.SummonerAvatar;
import model.entity.SummonerEntity;
import model.gameObject.MapObject;
import model.item.Equipable;
import model.item.Takeable;
import model.map.Direction;
import model.map.pair.CoordinatePair;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * This class examines a parsed XML file forthe avatar,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class AvatarReader {
	TakeableFactory inventoryFactory;
	EffectReader effectReader;

	AvatarReader()
	{
		inventoryFactory = new TakeableFactory();
		effectReader = new EffectReader();
	}
	
	/**
	 * Examine all nodes that are children of the input node,
	 * filter out effect elements, then use their attributes to instantiate them
	 * 
	 * @author Aidan Pace
	 * @param head the node to begin search at
	 * @return the list of effects created by this method
	 * @see Dispellable
	 */
	public Entity generateAndAddToMap(Element head)
	{
		NodeList nodes = head.getElementsByTagName("avatar");
			
		for(int i = 0; i < nodes.getLength(); i++)
		{				
			Element e = (Element) nodes.item(i);
			
			Element sackHead = (Element) e.getElementsByTagName("sack").item(0);
			Element armoryHead = (Element) e.getElementsByTagName("armory").item(0);
			Element s = (Element) e.getElementsByTagName("stats").item(0);
			
			List<MapObject> sackContents = inventoryFactory.generate(sackHead);
			List<MapObject> armoryContents = inventoryFactory.generate(armoryHead);
			
			Entity en = null;
			
			switch(e.getAttribute("class")) {
			case "smasher":
				en = new SmasherAvatar();
				
				CommonStats(s, en);
				((SmasherEntity) en).setOneHanded(Integer.parseInt(s.getAttribute("onehanded")));
				((SmasherEntity) en).setTwoHanded(Integer.parseInt(s.getAttribute("twohanded")));
				((SmasherEntity) en).setBrawling(Integer.parseInt(s.getAttribute("brawling")));
				((SmasherEntity) en).setChakra(Integer.parseInt(s.getAttribute("chakra")));
				
				break;
				
			case "sneak":
				en = new SneakAvatar();
				
				CommonStats(s, en);
				((SneakEntity) en).setPickPokcet(Integer.parseInt(s.getAttribute("pickpocket")));
				((SneakEntity) en).setTrap(Integer.parseInt(s.getAttribute("trap")));
				((SneakEntity) en).setCreep(Integer.parseInt(s.getAttribute("creep")));
				((SneakEntity) en).setRangedWeapon(Integer.parseInt(s.getAttribute("ranged")));
				
				break;
				
			case "summoner":
				en = new SummonerAvatar();
				
				CommonStats(s, en);
				((SummonerEntity) en).setEnchantment(Integer.parseInt(s.getAttribute("enchantment")));
				((SummonerEntity) en).setBane(Integer.parseInt(s.getAttribute("bane")));
				((SummonerEntity) en).setBoon(Integer.parseInt(s.getAttribute("boon")));
				((SummonerEntity) en).setStaff(Integer.parseInt(s.getAttribute("staff")));
				
				break;
			}
			
			if(en == null) continue;
			
			en.setCurrency(Integer.parseInt(e.getAttribute("currency")));
			
			switch(Integer.parseInt(e.getAttribute("direction"))) {
			case 0:
				en.setDirection(Direction.North);
				break;
			case 1:
				en.setDirection(Direction.NorthWest);
				break;
			case 2:
				en.setDirection(Direction.West);
				break;
			case 3:
				en.setDirection(Direction.SouthWest);
				break;
			case 4:
				en.setDirection(Direction.South);
				break;
			case 5:
				en.setDirection(Direction.SouthEast);
				break;
			case 6:
				en.setDirection(Direction.East);
				break;
			case 7:
				en.setDirection(Direction.NorthEast);
				break;
			}
			
			switch(Integer.parseInt(e.getAttribute("motiontype"))) {
			case 0:
				en.setMotionType(MotionType.GROUND);
				break;
			case 1:
				en.setMotionType(MotionType.WATER);
				break;
			case 2:
				en.setMotionType(MotionType.UNATTAINABLE);
				break;
			}
			
			
			Element effectHead = (Element) e.getElementsByTagName("effects").item(0);
			List<Dispellable> activeFX = effectReader.generate(effectHead);
			
			for(MapObject item : sackContents) {
				en.insert((Takeable) item);
			}
			
			for(MapObject item : armoryContents) {
				en.insert((Takeable) item);
				en.equip((Equipable) item);
			}
			
			for(Dispellable ef : activeFX) {
				en.addEffect(ef);
			}
			
			en.setLocation(new CoordinatePair(Integer.parseInt(e.getAttribute("x")), Integer.parseInt(e.getAttribute("y"))));
			
			int mapID = Integer.parseInt(e.getAttribute("map"));
			ActiveMapManager.getInstance().setActiveMap(mapID);
			ActiveMapManager.getInstance().addEntityToActiveMap(en, en.getLocation());
			
			return en;
		}
		
		return null;
	}
	
	private void CommonStats(Element s, Entity e) {
		e.setLivesLeft(Integer.parseInt(s.getAttribute("livesleft")));
		e.setStrength(Integer.parseInt(s.getAttribute("strength")));
		e.setAgility(Integer.parseInt(s.getAttribute("agility")));
		e.setIntellect(Integer.parseInt(s.getAttribute("intellect")));
		e.setHardiness(Integer.parseInt(s.getAttribute("hardiness")));
		e.setExperience(Integer.parseInt(s.getAttribute("experience")));
		e.setMovement(Integer.parseInt(s.getAttribute("movement")));
		e.setBindWounds(Integer.parseInt(s.getAttribute("bindwounds")));
		e.setBargain(Integer.parseInt(s.getAttribute("bargain")));
		e.setObservation(Integer.parseInt(s.getAttribute("observation")));
		e.setCurrentHP(Integer.parseInt(s.getAttribute("currenthp")));
		e.setCurrentMP(Integer.parseInt(s.getAttribute("currentmp")));
		e.setWeaponOffense(Integer.parseInt(s.getAttribute("offense")));
		e.setEquipArmor(Integer.parseInt(s.getAttribute("defense")));
	}
}
