package model.entity;

import model.map.pair.CoordinatePair;

public class FriendlyOldBrawler extends SmasherNPC {
	protected String setType(){
		return "friendlyOldBrawler";
	}
	public FriendlyOldBrawler(){
		super("Friendly Old Brawler", "An old warrior retired from fighting.", new CoordinatePair(0,0));
	}
}
