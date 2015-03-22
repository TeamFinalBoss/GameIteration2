package model.director;

import java.io.File;

import model.entity.Entity;

public class MapInstantiator {
	private static MapInstantiator me = null;
	
	public MapInstantiator() {
		
	}
	
	public static MapInstantiator getInstance() {
		if(me == null) me = new MapInstantiator();
		return me;
	}
	
	public Entity createAvatarFromFile(File f) {
		
	}
	
	public void createMapsFromFile(File f) {
		
		ActiveMapManager.getInstance().clearMaps();
		
		
	}
}
