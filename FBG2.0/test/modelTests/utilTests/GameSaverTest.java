package modelTests.utilTests;

import java.io.IOException;

import model.director.ActiveMapManager;
import model.entity.SmasherAvatar;
import model.item.Interactive;
import model.item.OneShot;
import model.item.Takeable;
import model.map.GameMap;
import model.map.pair.CoordinatePair;
import model.util.GameSaver;

public class GameSaverTest {
	public static void main(String [] args){
		
		//Set up manager
		ActiveMapManager manager = new ActiveMapManager();
		manager.setAvatar(new SmasherAvatar());
		
		//Set up one map
		GameMap map1 = new GameMap();
		manager.addMap(map1);
		manager.setActiveMap(map1);
		Interactive item1 = new Interactive();
		OneShot item2 = new OneShot();
		item2.setLocation(new CoordinatePair(0, 3));
		
		manager.addEntityToActiveMap(manager.getAvatar(), manager.getAvatar().getLocation());
		manager.addItemToActiveMap(item1, item1.getLocation());
		manager.addItemToActiveMap(item2, item2.getLocation());
		
		GameSaver saver = new GameSaver(manager);
		try {
			saver.save("./FBG2.0/src/resources/saves/testSave2.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
