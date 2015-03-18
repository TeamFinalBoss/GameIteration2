package model.dialogue.unique;

public class DT_Default extends DialogueTree {

		protected DialogueElement buildTree() {
			
			//First-Message//
			DialogueElement e1 = new DialogueElement("Hello there friend! How can I help you?", DialogueActions.NOTHING);
			
			//Option-1//
			DialogueElement e2 = new DialogueElement("Very well, I will show you my wares.", DialogueActions.STOREFRONT);
			
			//Option-2//
			DialogueElement e3 = new DialogueElement("Come again soon.", DialogueAction.EXIT);
			
			
			//Assign//
			e1.addOption("I want to buy and sell", e2);
			e1.addOption("Nothing", e3);
			
			return e1;
		}
}
