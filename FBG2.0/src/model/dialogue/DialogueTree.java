package model.dialogue;

import java.util.List;

/** 
 * Defines an abstract class that all specific dialogue trees must adhere to
 * 
 * 
 * @author Aidan Pace
 */

public abstract class DialogueTree {
	
	private DialogueElement head;
	private DialogueElement current;
	
	public DialogueTree() {
		head = buildTree();
		current = head;
	}
	
	/**
	 * This method must be overridden by classes extending this one.
	 * It creates all the necessary DialogueElement objects specific
	 * to the unique conversation that is building, then links them all
	 * together for traversal.
	 * 
	 * @author Aidan Pace
	 * @see DialogueElement
	 */
	protected abstract DialogueElement buildTree();
	
	/**
	 * This method sets the current element in the dialogue tree
	 * to the head element of the tree.
	 * 
	 * @author Aidan Pace
	 */
	public void resetTree() {
		current = head;
	}
	
	/**
	 * Returns the message on the current dialogue element.
	 * 
	 * @author Aidan Pace
	 * @returns current dialogue message
	 */
	public String getCurrentMessage() {
		return current.getTopMessage();
	}
	
	/**
	 * Returns a List of current dialogue element options
	 * 
	 * @author Aidan Pace
	 * @returns ArrayList of strings of element options
	 */
	public List<String> getOptions() {
		return current.getOptions();
	}
	
	/**
	 * Returns the onActive action of the current dialogue element
	 * 
	 * @author Aidan Pace
	 * @returns onActive enum
	 */
	public DialogueActions getOnActive() {
		return current.getOnActive();
	}
	
	
	/**
	 * Sets the current dialogue element to one selected by an option in the current
	 * dialogue element.
	 * 
	 * @author Aidan Pace
	 * @param num Number corresponding to a dialogue option on the current dialogue element.
	 */
	public void traverse(int num) {
		DialogueElement newElement = current.returnOption(num);
		
		if(newElement != null) current = newElement;
	}
}
