package model.entity;

import model.map.pair.CoordinatePair;

public class SneakAvatar extends SneakEntity{
	protected String setType(){
		return "avatar";
	}
	public SneakAvatar(){
		super("The Sneak", "A myserious being from a distant plane", new CoordinatePair(1,1));
	}
}
