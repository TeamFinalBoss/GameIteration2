package model.entity;

import model.dialogue.DialogueTree;
import model.dialogue.unique.DT_BasicVillager;
import model.map.pair.CoordinatePair;

public class EvilMuscleSlime extends SmasherNPC {
	String id;
	protected DialogueTree createDialogueTree(){
		return new DT_BasicVillager();
	}
	protected String setType(){
		return "evilMuscleSlime";
	}
	public EvilMuscleSlime(){
		super("Evil Muscle Slime", "A physically powerful pile of slime", new CoordinatePair(0,0));
	}
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
}
