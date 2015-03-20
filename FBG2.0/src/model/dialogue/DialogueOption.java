package model.dialogue;

/** 
 * A simple class that contains a string and a reference to a dialogue element.
 * Used for pairing an element with a name, and packaging them into one class.
 * 
 * 
 * @author Aidan Pace
 */
public class DialogueOption {
	private String name;
	private DialogueElement element;
	
	public DialogueOption(String name, DialogueElement element) {
		this.name = name;
		this.element = element;
	}
	
	public String getName() {
		return name;
	}
	
	public DialogueElement getElement() {
		return element;
	}
}
