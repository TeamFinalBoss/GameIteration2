package controller.menu.dialogue;

import java.util.Observable;

import model.director.AvatarInteractionManager;
import controller.util.Describeable;

public class DialogueMenu extends Observable implements Describeable {

	AvatarInteractionManager manager = AvatarInteractionManager.getInstance();
	
	public DialogueMenu() {
		
	}
			
			
	
	@Override
	public int getCurrentIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
