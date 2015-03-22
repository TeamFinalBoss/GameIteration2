package model.entity;

import model.map.pair.CoordinatePair;

public class SmasherAvatar extends SmasherEntity{
	protected String setType(){
		return "avatar";
	}
	public SmasherAvatar(){
		super("The Smasher", "A myserious being from a distant plane", new CoordinatePair(1,1));
	}
}
