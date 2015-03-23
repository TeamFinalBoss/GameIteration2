package model.entity;

import model.map.pair.CoordinatePair;

public class EvilMuscleSlime extends SmasherNPC {
	protected String setType(){
		return "evilMuscleSlime";
	}
	public EvilMuscleSlime(){
		super("Evil Muscle Slime", "A physically powerful pile of slime", new CoordinatePair(0,0));
	}
}
