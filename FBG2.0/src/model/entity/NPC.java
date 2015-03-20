package model.entity;

import java.util.List;

import model.dialogue.DialogueActions;
import model.dialogue.DialogueTree;
import model.dialogue.unique.DT_Default;
import model.entity.inventory.Inventory;
import model.entity.inventory.Storefront;
import model.item.Takeable;
import model.link.EntityLink;
import model.link.Link;
import model.map.Direction;
import model.map.pair.CoordinatePair;

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
		private EntityLink partner;
		
		public NPC() {
			super("Generic NPC", "Generic Description", new CoordinatePair());
			
			this.setID("10");
			this.setClassName("NPC");
			
			dt = new DT_Default();
			sf = new Storefront();
			friendly = true;
			partner = new EntityLink(this, 0);
		}
		
		public NPC(String objectName, String description, CoordinatePair location, 
		    		Inventory inventory, Occupation occupation, Direction direction, int speed, DialogueTree dt, Storefront sf, boolean friendly, EntityLink partner){
		    	super(objectName, description, location, inventory, occupation, direction, speed);
		    	
				this.setID("10");
				this.setClassName("NPC");
		    	
		    	this.dt = dt;
		    	this.sf = sf;
		    	this.friendly = friendly;
		    	this.partner = partner;
		}
		
		/**
		 * This method allows the caller to ask this NPC's storefront for the price of an item.
		 * 
		 * @param position the position of the desired item in the storefront
		 * @return the change in currency that would result from purchase
		 */
		public int checkPayment(int position) {
			return sf.payForItem(position);
		}
		
		/**
		 * Removes an item from this NPC's storefront, modifies this NPC's currency,
		 * then returns the removed item.
		 * 
		 * @param position the position of the desired item in the storefront
		 * @return the removed item
		 */
		public Takeable sellItem(int position) {
			Takeable t = sf.buyItem(position);
			modifyCurrency(t.getValue());
			return t;
		}
		
		/**
		 * Adds an item to this NPC's storefront
		 * 
		 * @param item the item that is being added
		 * @return the value of the item
		 */
		public int buyItem(Takeable item) {
			return sf.sellItem(item);
		}
		
		/**
		 * Resets the NPC's dialogue tree.
		 */
		public void resetDialogue() {
			dt.resetTree();
		}
		
		/**
		 * @return the dialogue tree's current active message
		 */
		public String getDialogueMessage() {
			return dt.getCurrentMessage();
		}
		
		/**
		 * @return a list of the dialogue tree's current active options
		 */
		public List<String> getDialogueOptions() {
			return dt.getOptions();
		}
		
		/**
		 * This method first performs the action required by the CURRENT active dialogue,
		 * then it switches the current active dialogue to the one specified by the option selected.
		 * @param num The number corresponding to the dialogue option selected
		 * @return whether or not the conversation will end upon selection
		 */
		public boolean traverseDialogue(int num) {
			
			//Actions//
			DialogueActions action = dt.getOnActive();
			
			//Switch dialogue//
			dt.traverse(num);
			
			switch(action) {
			case NOTHING:
				return true;
				
			case EXIT:
				return false;
				
			case STOREFRONT:
				//open store
				return false;
				
			case ANGER:
				friendly = false;
				return false;
				
			default:
				return false;
			}
		}
}
