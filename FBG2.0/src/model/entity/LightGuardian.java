package model.entity;

import model.dialogue.DialogueTree;
import model.dialogue.unique.DT_BasicShopkeep;
import model.dialogue.unique.DT_BasicVillager;
import model.map.pair.CoordinatePair;

public class LightGuardian extends SmasherNPC {
	protected DialogueTree createDialogueTree(){
		return new DT_BasicShopkeep();
	}
	protected String setType(){
		return "lightGuardian";
	}
	public LightGuardian(){
		super("Light Guardian", "A physical warrior of light.", new CoordinatePair(0,0));
	}
}
