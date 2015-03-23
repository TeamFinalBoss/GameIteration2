package model.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

import controller.Controller;
import model.director.ActiveMapManager;
import model.entity.Entity;
import model.item.Interactive;
import model.item.Item;
import model.item.Obstacle;
import model.item.OneShot;
import model.item.Takeable;
import model.map.GameMap;
import model.map.MapSwitcher;
import model.map.areaEffect.AreaEffect;
import model.map.pair.CoordinatePair;
import model.map.tile.trap.Trap;

public class GameSaver {
	
	private ActiveMapManager manager;
	
	public GameSaver(){
		manager = ActiveMapManager.getInstance();
	}
	
	public GameSaver(ActiveMapManager manager){
		this.manager = manager;
	}
	
	public void save(File f) throws IOException {
		String path = f.getAbsolutePath();
		Files.write(Paths.get(path), getSaveString().getBytes());		
	}
	
	public void save(String path) throws IOException{
		Files.write(Paths.get(path), getSaveString().getBytes());
	}
	
	private String getSaveString(){
		ObjectSaver saver = new ObjectSaver();
		
		String save = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		save += "<gamesave>\n";
		save += Controller.getInstance().getKeyBindings().toXML();
		save += saver.getSaveFormat(manager.getAvatar());
		save += "\n";
		
		//Cycle through every map for relevant information
		for (GameMap map : manager.getMaps()){
			save += "<map id=\"" + map.getID() + "\">\n";
			
			//Entities
			for (Entity entity : map.getEntities()){
				if (!entity.getType().equals("avatar")) save += saver.getSaveFormat(entity) + "\n";
			}
			
			//Items
			for (Item item : map.getItems()){
				if (item.getType().equals("Takeable")) 
					save += saver.getSaveFormat((Takeable)item) + "\n";
				else if (item.getType().equals("One Shot"))
					save += saver.getSaveFormat((OneShot)item) + "\n";
				else if (item.getType().equals("Obstacle"))
					save += saver.getSaveFormat((Obstacle)item) + "\n";
				else if (item.getType().equals("Interactive"))
					save += saver.getSaveFormat((Interactive)item) + "\n";
			}
			
			//Area effects
			for (AreaEffect effect : map.getAreaEffects()){
				save += saver.getSaveFormat(effect) + "\n";
			}
			
			//Traps
			for (Trap trap: map.getTraps()){
				save += saver.getSaveFormat(trap) + "\n";
			}
			
			//Map Switchers
			for (MapSwitcher m : map.getMapSwitchers()){
				save += saver.getSaveFormat(m) + "\n";
			}
			
			save += "</map>\n";
		}
		
		save += "</gamesave>";
		return save;
	}
}
