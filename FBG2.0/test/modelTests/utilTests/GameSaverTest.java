package modelTests.utilTests;

import java.io.File;
import java.io.IOException;

import model.director.ActiveMapManager;
import model.entity.LightGuardian;
import model.entity.SmasherAvatar;
import model.item.Gun;
import model.item.Interactive;
import model.item.Obstacle;
import model.item.OneShot;
import model.item.Takeable;
import model.map.GameMap;
import model.map.MapSwitcher;
import model.map.areaEffect.InstantDeathAreaEffect;
import model.map.pair.CoordinatePair;
import model.map.tile.trap.DealDamageTrap;
import model.util.GameSaver;

public class GameSaverTest {
	public static void main(String [] args){
		
		//Set up manager
		ActiveMapManager manager = ActiveMapManager.getInstance();
		manager.setAvatar(new SmasherAvatar());
		
		//Set up one map
		GameMap map1 = new GameMap();
		map1.setID(1);
		manager.addMap(map1);
		manager.setActiveMap(map1);
		
		//Set up second map
		GameMap map2 = new GameMap();
		map2.setID(2);
		manager.addMap(map2);
		
		
		Interactive item1 = new Interactive();
		OneShot item2 = new OneShot();
		item2.setLocation(new CoordinatePair(0, 3));
		
		LightGuardian entity1 = new LightGuardian();
		
		InstantDeathAreaEffect effect1 = new InstantDeathAreaEffect();
		
		MapSwitcher switcher1 = new MapSwitcher("SwitcherTry", "Descriptiontry", 
				map1, new CoordinatePair(0, 4), 
				map2, new CoordinatePair(0, 6));
		
		manager.addItemToActiveMap(item1, item1.getLocation());
		manager.addItemToActiveMap(item2, item2.getLocation());
		manager.addEntityToActiveMap(entity1, entity1.getLocation());
		manager.addAreaEffectToActiveMap(effect1, effect1.getLocation());
		manager.addMapSwitcherToActiveMap(switcher1, switcher1.getLocation1());

		
		Obstacle item3 = new Obstacle();
		Gun gun = new Gun();
		DealDamageTrap trap1 = new DealDamageTrap();
		gun.setLocation(new CoordinatePair(5, 2));
		
		manager.setActiveMap(map2);
		
		manager.addItemToActiveMap(item3, item3.getLocation());
		manager.addItemToActiveMap(gun, gun.getLocation());
		manager.addTrapToActiveMap(trap1, trap1.getLocation());
		manager.addMapSwitcherToActiveMap(switcher1, switcher1.getLocation2());
		
		manager.addEntityToActiveMap(manager.getAvatar(), manager.getAvatar().getLocation());
		
		
		GameSaver saver = new GameSaver(manager);
		try {
			saver.save(new File("./FBG2.0/src/resources/saves/testSave2.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
