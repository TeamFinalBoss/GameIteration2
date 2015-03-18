package model.dialogue.unique;

public class DT_Default extends DialogueTree {

		protected DialogueElement buildTree() {
			
			//First-Message//
			DialogueElement e1 = new DialogueElement("Hello.", DialogueActions.EXIT);
			return e1;
		}
}
