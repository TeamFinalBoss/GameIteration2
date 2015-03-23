package model.entity;

import model.director.ActiveMapManager;

import java.util.ArrayList;
import model.item.Item;
import model.map.projectiles.Projectile;
import model.map.areaEffect.AreaEffect;
import model.map.tile.Tile;
import model.map.tile.trap.Trap;
import java.util.Collections;
import java.util.List;

public class VisibleMap {
	private Entity owner;
	private ActiveMapManager activeMap;
	private ArrayList<Tile> tiles;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Entity> entities;
	private ArrayList<Trap> traps;
	private ArrayList<Item> items;
	private ArrayList<AreaEffect> areaEffects;
	
	private int computeInfluenceRadius(int observation){
		return 3;//observation / 20;
	}
	
	private void clearAll(){
		tiles.clear();
		projectiles.clear();
		entities.clear();
		traps.clear();
		items.clear();
		areaEffects.clear();
	}
	
	public VisibleMap(Entity owner){
		//initialization
		this.owner = owner;
		activeMap = ActiveMapManager.getInstance();
		tiles = new ArrayList<Tile>();
		projectiles = new ArrayList<Projectile>();
		entities = new ArrayList<Entity>();
		traps = new ArrayList<Trap>();
		items = new ArrayList<Item>();
		areaEffects = new ArrayList<AreaEffect>();
		update();
	}
	public void update(){
		int influenceRadius = computeInfluenceRadius(owner.getObservation());
		clearAll();
		activeMap.getEverythingInRange(owner.getLocation(), influenceRadius, tiles, projectiles, entities, traps, items, areaEffects);
		for(Projectile p : projectiles){
			if(!p.canSee(owner.getObservation())) projectiles.remove(p);
		}
		for(Entity e : entities){
			if(!e.canSee(owner.getObservation())) entities.remove(e);
		}
		for(Trap t : traps){
			if(!t.canSee(owner)) traps.remove(t);
		}
		for(Item i : items){
			if(!i.canSee(owner.getObservation())) items.remove(i);
		}
		for(AreaEffect ae : areaEffects){
			if(!ae.canSee(owner.getObservation())) areaEffects.remove(ae);
		}
	}
	public List<Tile> getVisibleTiles(){
		return Collections.unmodifiableList(tiles);
	}
	public List<Projectile> getVisibleProjectiles(){
		return Collections.unmodifiableList(projectiles);
	}
	public List<Entity> getVisibleEntities(){
		return Collections.unmodifiableList(entities);
	}
	public List<Trap> getVisibleTraps(){
		return Collections.unmodifiableList(traps);
	}
	public List<Item> getVisibleItems(){
		return Collections.unmodifiableList(items);
	}
	public List<AreaEffect> getVisibleAreaEffects(){
		return Collections.unmodifiableList(areaEffects);
	}
}
