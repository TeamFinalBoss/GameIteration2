package model.dialogue;

import java.util.ArrayList;
import java.util.List;


/** 
 * Defines DialogueElement, a class that contains a message for a single piece
 * of dialogue, as well as dialogue options attached to that message.
 * 
 * 
 * @author Aidan Pace
 */

public class DialogueElement {
	
	private String topMessage;
	private List<DialogueOption> options;
	private DialogueActions onActive;
	
	public DialogueElement() {
		this.topMessage = "Default message";
		this.onActive = DialogueActions.NOTHING;
		this.options = new ArrayList<DialogueOption>();
	}
	
	public DialogueElement(String topMessage, DialogueActions onActive) {
		this.topMessage = topMessage;
		this.onActive = onActive;
		this.options = new ArrayList<DialogueOption>();
	}
	
	//--------------------Getters---------------------\\
	public DialogueActions getOnActive() {
		return onActive;
	}
	
	public String getTopMessage() {
		return topMessage;
	}
	
	/** 
	 * Allows a new option to be added to the dialogue element, in the form
	 * of a pair of a string, for the option's visible name, and another
	 * dialogue element, to specify what the option should lead to.
	 * 
	 * @param name Visible name of the option
	 * @param element Destination dialogue element
	 */
	public void addOption(String name, DialogueElement element) {
		options.add(new DialogueOption(name, element));
	}
	
	/** 
	 * Given an integer, returns the dialogue element at the
	 * corresponding location in the options list.
	 * 
	 * @param num Location in list
	 * @returns Dialogue element at that position in list
	 */
	public DialogueElement returnOption(int num) {
		if(num > 0 && num <= options.size()) {
			return options.get(num-1).getElement();
		}
		
		return null;
	}
	
	/** 
	 * Returns a list of the names of all options this
	 * dialogue element contains.
	 * 
	 * @returns List of strings of option names
	 */
	public List<String> getOptions() {
		List<String> names = new ArrayList<String>();
		
		for(DialogueOption o : options) {
			names.add(o.getName());
		}
		
		return names;
	}
}
