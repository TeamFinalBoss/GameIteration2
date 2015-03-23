package controller.commands.game.dialogue;

import java.util.List;

import model.director.ActiveMapManager;
import model.director.AvatarInteractionManager;
import model.entity.NPC;
import model.entity.inventory.Sack;
import model.item.Takeable;
import controller.commands.Commandable;
import controller.commands.sack.SackDetails;

public class Sell implements Commandable {
	
	SackDetails details;
	
	public Sell(SackDetails details) {
		this.details = details;
	}
	
	@Override
	public void execute() {
		NPC npc = AvatarInteractionManager.getInstance().getConversationPartner();
		Takeable item = AvatarInteractionManager.getInstance().getSack().get(details.getCurrentIndex());
		npc.buyItem(item, AvatarInteractionManager.getInstance().getAvatar());
		Sack sack = AvatarInteractionManager.getInstance().getAvatar().getSack();
		sack.remove(item);
		

	}

}
