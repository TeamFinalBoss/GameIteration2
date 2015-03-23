package model.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

import model.director.ActiveMapManager;
import model.entity.Entity;
import model.item.Interactive;
import model.item.Item;
import model.item.Obstacle;
import model.item.OneShot;
import model.item.Takeable;
import model.map.GameMap;
import model.map.pair.CoordinatePair;

public class GameSaver {
	
	private ActiveMapManager manager;
	
	public GameSaver(){
		manager = ActiveMapManager.getInstance();
	}
	
	public GameSaver(ActiveMapManager manager){
		this.manager = manager;
	}
	
	public void save(File f) throws IOException {
		String path = f.getPath();
		Files.write(Paths.get(path), getSaveString().getBytes());		
	}
	
	public void save(String path) throws IOException{
		Files.write(Paths.get(path), getSaveString().getBytes());
	}
	
	private String getSaveString(){
		ObjectSaver saver = new ObjectSaver();
		
		String save = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		save += "<gamesave>\n";		
		save += saver.getSaveFormat(manager.getAvatar());
		save += "\n";
		
		for (GameMap map : manager.getMaps()){
			save += "<map id=\"" + map.getID() + "\">\n";
			int x = map.getWidth();
			int y = map.getHeight();
			for (int i = 0; i != x; ++i){
				for (int j = 0; j != y; ++j){
					CoordinatePair CP = new CoordinatePair(i, j);
					
					//Entities
					Entity e = map.getEntityAtCoordinate(CP);
					if (e != null && !e.getType().equals("avatar")) save += saver.getSaveFormat(e) + "\n";
					
					//Items
					Item item = map.getItemAtCoordinate(CP);
					if (item != null) {
						if (item.getClassName().equals("Takeable")) 
							save += saver.getSaveFormat((Takeable)item) + "\n";
						else if (item.getClassName().equals("One Shot"))
							save += saver.getSaveFormat((OneShot)item) + "\n";
						else if (item.getClassName().equals("Obstacle"))
							save += saver.getSaveFormat((Obstacle)item);
						else if (item.getClassName().equals("Interactive"))
							save += saver.getSaveFormat((Interactive)item) + "\n";
					}
				}
			}
			
			save += "</map>\n";
		}
		
		save += "</gamesave>";
		return save;
	}
}
