package model.dialogue.unique;

import model.dialogue.DialogueActions;
import model.dialogue.DialogueElement;
import model.dialogue.DialogueTree;

public class DT_VillageKing extends DialogueTree {

		protected DialogueElement buildTree() {
			
			//First-Message//
			DialogueElement e1 = new DialogueElement("Welcome to my domain, traveller. How can I help you?", DialogueActions.NOTHING);
			
			//Option-1//
			DialogueElement e2 = new DialogueElement("What would you like to know about?", DialogueActions.NOTHING);
			
			//Option-1.1//
			DialogueElement e3 = new DialogueElement("This town has been around for decades. It is the fairest town for miles.", DialogueActions.NOTHING);
			
			//Option-1.2//
			DialogueElement e4 = new DialogueElement("There are a few shops in town, and people to talk to.", DialogueActions.NOTHING);
			
			//Option-1.3//
			DialogueElement e5 = new DialogueElement("Oh my, no! There are many scary foes outside the town.", DialogueActions.NOTHING);
			
			//Option-1.4//
			DialogueElement e6 = new DialogueElement("Goodbye!", DialogueActions.EXIT);
			
			//Option-2//
			DialogueElement e7 = new DialogueElement("I have a few special items I can sell to you...", DialogueActions.STOREFRONT);
			
			//Option-3// --e6
			
			
			//Assign//
			e1.addOption("I need information", e2);
			e1.addOption("Do you sell anything?", e7);
			e1.addOption("Nothing", e6);
			
			e2.addOption("Town history", e3);
			e2.addOption("What is there to do here?", e4);
			e2.addOption("Is the area outside safe?", e5);
			e2.addOption("Nevermind.", e6);
			
			e3.addOption("I had another question", e2);
			e3.addOption("Thank you.", e6);
			
			e4.addOption("I had another question", e2);
			e4.addOption("Thank you.", e6);
			
			e5.addOption("I had another question", e2);
			e5.addOption("Thank you.", e6);
			
			return e1;
		}
}
