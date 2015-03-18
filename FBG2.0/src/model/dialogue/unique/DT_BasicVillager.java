package model.dialogue.unique;

public class DT_Default extends DialogueTree {

		protected DialogueElement buildTree() {
			
			//First-Message//
			DialogueElement e1 = new DialogueElement("Good day.", DialogueActions.NOTHING);
			
			//Option-1//
			DialogueElement e2 = new DialogueElement("I am doing quite well, thank you.", DialogueActions.NOTHING);
			
			//Option-1.1// -- e1
			
			//Option-1.2//
			DialogueElement e3 = new DialogueElement("How dare you! En guarde!", DialogueAction.ANGER);
			
			//Option-1.3//
			DialogueElement e4 = new DialogueElement("Good bye.", DialogueAction.EXIT);
			
			//Option-2//
			DialogueElement e5 = new DialogueElement("I have heard no news, sorry.", DialogueAction.EXIT);
			
			//Option-3// -- e4
			
			
			//Assign//
			e1.addOption("How are you?", e2);
			e1.addOption("What news have you?", e5);
			e1.addOption("Nothing", e4);
			
			e2.addOption("There was something else...", e1);
			e2.addOption("I wish you were dead.", e3);
			e2.addOption("That is all.", e4);
			
			return e1;
		}
}
