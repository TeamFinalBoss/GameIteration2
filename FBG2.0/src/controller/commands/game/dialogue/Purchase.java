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
		if(npc.checkPayment(details.getCurrentIndex()) > AvatarInteractionManager.getInstance().getCurrency()) {
			npc.buyItem(npc.getFullStoreContents().get(details.getCurrentIndex()),
					AvatarInteractionManager.getInstance().getAvatar());
		}

	}

}
