package model.factories;


import java.util.ArrayList;
import java.util.List;

import model.gameObject.MapObject;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.director.ActiveMapManager;
import model.effect.Dispellable;
import model.entity.Entity;
import model.entity.LightGuardian;
import model.entity.MotionType;
import model.entity.NPC;
import model.entity.SmasherAvatar;
import model.entity.SmasherEntity;
import model.entity.SneakAvatar;
import model.entity.SneakEntity;
import model.entity.SummonerAvatar;
import model.entity.SummonerEntity;
import model.item.Equipable;
import model.item.Takeable;
import model.map.Direction;
import model.map.pair.CoordinatePair;

/**
 * This class examines a parsed XML file for all entity entries,
 * then uses their attributes to instantiate them.
 * 
 * @author Aidan Pace
 */
public class EntityFactory implements PlaceableObjectFactory{
	
	TakeableFactory inventoryFactory;
	EffectReader effectReader;

	public EntityFactory()
	{
		inventoryFactory = new TakeableFactory();
		effectReader = new EffectReader();
	}
	
	/**
	 * Examine all nodes that are children of the input node,
	 * filter out Entity elements, then use their attributes to instantiate them,
	 * along with relevant items and stats.
	 * 
	 * @author Aidan Pace
	 * @param head the node to begin search at
	 * @return the list of Entities created by this method
	 * @see Entity
	 */
	public List<MapObject> generate(Element head)
	{
		List<MapObject> entities = new ArrayList<MapObject>();
		NodeList nodes = head.getElementsByTagName("entity");
		
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Element e = (Element) nodes.item(i);
			
			Element sackHead = (Element) e.getElementsByTagName("sack").item(0);
			Element armoryHead = (Element) e.getElementsByTagName("armory").item(0);
			Element storeHead = (Element) e.getElementsByTagName("store").item(0);
			Element s = (Element) e.getElementsByTagName("stats").item(0);
			
			List<MapObject> sackContents = inventoryFactory.generate(sackHead);
			List<MapObject> armoryContents = inventoryFactory.generate(armoryHead);
			List<MapObject> storeContents = inventoryFactory.generate(storeHead);
			
			Entity en = null;
			
			switch(e.getAttribute("occupation")) {
			case "smasher":
				en = switchSmasherType(e);
				if(en == null) break;
				
				/*
				CommonStats(s, en);
				((SmasherEntity) en).setOneHanded(Integer.parseInt(s.getAttribute("stat1")));
				((SmasherEntity) en).setTwoHanded(Integer.parseInt(s.getAttribute("stat2")));
				((SmasherEntity) en).setBrawling(Integer.parseInt(s.getAttribute("stat3")));
				((SmasherEntity) en).setChakra(Integer.parseInt(s.getAttribute("stat4")));
				*/
				
				break;
				
			case "sneak":
				en = switchSneakType(e);
				if(en == null) break;
				
				/*
				CommonStats(s, en);
				((SneakEntity) en).setPickPokcet(Integer.parseInt(s.getAttribute("stat1")));
				((SneakEntity) en).setTrap(Integer.parseInt(s.getAttribute("stat2")));
				((SneakEntity) en).setCreep(Integer.parseInt(s.getAttribute("stat3")));
				((SneakEntity) en).setRangedWeapon(Integer.parseInt(s.getAttribute("stat4")));
				*/
				
				break;
				
			case "summoner":
				en = switchSummonerType(e);
				if(en == null) break;
				/*
				CommonStats(s, en);
				((SummonerEntity) en).setEnchantment(Integer.parseInt(s.getAttribute("stat1")));
				((SummonerEntity) en).setBane(Integer.parseInt(s.getAttribute("stat2")));
				((SummonerEntity) en).setBoon(Integer.parseInt(s.getAttribute("stat3")));
				((SummonerEntity) en).setStaff(Integer.parseInt(s.getAttribute("stat4")));
				*/
				
				break;
			}
			
			if(en == null) continue;
			
			en.setCurrency(Integer.parseInt(e.getAttribute("currency")));
			
			switch(e.getAttribute("direction")) {
			case "North":
				en.setDirection(Direction.North);
				break;
			case "NorthWest":
				en.setDirection(Direction.NorthWest);
				break;
			case "West":
				en.setDirection(Direction.West);
				break;
			case "SouthWest":
				en.setDirection(Direction.SouthWest);
				break;
			case "South":
				en.setDirection(Direction.South);
				break;
			case "SouthEast":
				en.setDirection(Direction.SouthEast);
				break;
			case "East":
				en.setDirection(Direction.East);
				break;
			case "NorthEast":
				en.setDirection(Direction.NorthEast);
				break;
			}
			
			switch(e.getAttribute("motionType")) {
			case "GROUND":
				en.setMotionType(MotionType.GROUND);
				break;
			case "WATER":
				en.setMotionType(MotionType.WATER);
				break;
			case "UNATTAINABLE":
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
			
			for(MapObject item : storeContents) {
				((NPC) en).buyItem((Takeable) item);
			}
			
			for(Dispellable ef : activeFX) {
				en.addEffect(ef);
			}
			
			en.setLocation(new CoordinatePair(Integer.parseInt(e.getAttribute("x")), Integer.parseInt(e.getAttribute("y"))));
			
			entities.add(en);
		}
		
		return entities;
	}
	
	private void CommonStats(Element s, Entity e) {
		e.setLivesLeft(Integer.parseInt(s.getAttribute("livesLeft")));
		e.setStrength(Integer.parseInt(s.getAttribute("strength")));
		e.setAgility(Integer.parseInt(s.getAttribute("agility")));
		e.setIntellect(Integer.parseInt(s.getAttribute("intellect")));
		e.setHardiness(Integer.parseInt(s.getAttribute("hardiness")));
		e.setExperience(Integer.parseInt(s.getAttribute("experience")));
		e.setMovement(Integer.parseInt(s.getAttribute("movement")));
		e.setBindWounds(Integer.parseInt(s.getAttribute("bindWounds")));
		e.setBargain(Integer.parseInt(s.getAttribute("bargain")));
		e.setObservation(Integer.parseInt(s.getAttribute("observation")));
		e.setCurrentHP(Integer.parseInt(s.getAttribute("currentHp")));
		e.setCurrentMP(Integer.parseInt(s.getAttribute("currentMp")));
		e.setWeaponOffense(Integer.parseInt(s.getAttribute("offense")));
		e.setEquipArmor(Integer.parseInt(s.getAttribute("defense")));
	}
	
	private Entity switchSmasherType(Element e) {
		NPC en = null;
		
		switch(e.getAttribute("type")) {
		case "lightGuardian":
			en = new LightGuardian();
			en.setLink(Integer.parseInt(e.getAttribute("link")));
			
		}
		
		return (Entity) en;
	}
	
	private Entity switchSneakType(Element e) {
		Entity en = null;
		
		switch(e.getAttribute("type")) {
		case "blah":
			en = new SneakAvatar();
			//en.setLink(Integer.parseInt(e.getAttribute("link")));
		}
		
		return en;
	}
	
	private Entity switchSummonerType(Element e) {
		Entity en = null;
		
		switch(e.getAttribute("type")) {
		case "blah":
			en = new SummonerAvatar();
			//en.setLink(Integer.parseInt(e.getAttribute("link")));
		}
		
		return en;
	}
}
