package model.entity;

import model.dialogue.DialogueTree;
import model.dialogue.unique.DT_Default;

/** 
 * The class NPC defines only entities that are computer controlled,
 * can engage in conversation, and can attack the player.
 * 
 * 
 * @see MapObject
 * @author Aidan Pace
 */

public class NPC extends Entity {

		private DialogueTree dt;
		private Storefront sf;
		private boolean friendly;
		private NPC partner;
		
		public NPC() {
			super();
			
			this.classname = "NPC";
			this.id = 10;
			
			dt = new DT_Default();
			sf = new Storefront();
			friendly = true;
			partner = null;
		}
		
		public NPC(String objectName, String description, CoordinatePair location, 
		    		Inventory inventory, Occupation occupation, Direction direction, int speed, DialogueTree dt, Storefront sf, boolean friendly, NPC partner){
		    	super(objectName, description, location, inventory, occupation, direction, speed);
		    	
		    	this.dt = dt;
		    	this.sf = sf;
		    	this.friendly = friendly;
		    	this.partner = partner;
		}
		
		public void resetDialogue() {
			dt.resetTree();
		}
		
		public String getDialogueMessage() {
			return dt.getTopMessage();
		}
		
		public List<String> getDialogueOptions() {
			return dt.getOptions();
		}
		
		public boolean traverseDialogue(int num) {
			dt.traverse(num);
			
			//Actions//
			CurrentActions action = dt.getOnActive();
			
			switch(action) {
			case DialogueActions.NOTHING:
				return true;
				
			case DialogueActions.EXIT:
				return false;
				
			case DialogueActions.STOREFRONT;
				//open store
				return false;
				
			case DialogueActions.ANGER:
				friendly = false;
				return false;
				
			default:
				return true;
			}
		}
}
