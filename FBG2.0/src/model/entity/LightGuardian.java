package model.entity;

import model.map.pair.CoordinatePair;

public class LightGuardian extends SmasherNPC {
	protected String setType(){
		return "lightGuardian";
	}
	public LightGuardian(){
		super("Light Guardian", "A physical warrior of light.", new CoordinatePair(0,0));
	}
}
