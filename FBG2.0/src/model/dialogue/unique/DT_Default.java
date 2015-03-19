package model.dialogue.unique;

import model.dialogue.DialogueActions;
import model.dialogue.DialogueElement;
import model.dialogue.DialogueTree;

public class DT_Default extends DialogueTree {

		protected DialogueElement buildTree() {
			
			//First-Message//
			DialogueElement e1 = new DialogueElement("Hello.", DialogueActions.EXIT);
			return e1;
		}
}
