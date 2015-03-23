package controller.commands.game.dialogue;

import model.director.AvatarInteractionManager;
import model.entity.NPC;
import controller.commands.Commandable;
import controller.commands.sack.SackDetails;

public class Purchase implements Commandable {

	SackDetails details;
	
	public Purchase(SackDetails deets) {
		this.details = deets;
	}
	
	public void execute() {
		NPC npc = AvatarInteractionManager.getInstance().getConversationPartner();
		if(AvatarInteractionManager.getInstance().getCurrency() - npc.checkPayment(details.getCurrentIndex()) >= 0) {
			AvatarInteractionManager.getInstance().getAvatar().insert(npc.sellItem(details.getCurrentIndex(), AvatarInteractionManager.getInstance().getAvatar()));
				
		}
		
		/*
		 * 
		 */
	}

}
