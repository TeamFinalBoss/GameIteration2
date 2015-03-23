package model.entity;

import model.dialogue.DialogueTree;
import model.dialogue.unique.DT_BasicVillager;
import model.dialogue.unique.DT_VillageKing;
import model.map.pair.CoordinatePair;

public class FriendlyOldBrawler extends SmasherNPC {
	private String id;
	protected DialogueTree createDialogueTree(){
		return new DT_VillageKing();
	}
	protected String setType(){
		return "friendlyOldBrawler";
	}
	public FriendlyOldBrawler(){
		super("Friendly Old Brawler", "An old warrior retired from fighting.", new CoordinatePair(0,0));
	}
	@Override
	public void setId(String id) {
		this.id = id;
		
	}
}
