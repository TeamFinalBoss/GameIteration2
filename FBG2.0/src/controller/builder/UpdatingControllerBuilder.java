package controller.builder;

import controller.commands.keyBindings.BindingsUpdate;
import controller.keyBindings.KeyBindings;
import controller.keyBindings.KeyBindingsUpdate;
import controller.keyBindings.KeyOptionsUpdating;
import controller.sceneControllers.SceneController;

public class UpdatingControllerBuilder {

	/**********************************************************************************************
	 * 	  Updating Controller
	 * @param bindingsUpdate2 
	 *
	 ************************************************************************************************/
	 public static SceneController buildUpdatingSceneController(
			 KeyBindingsUpdate bindingsUpdate, KeyBindings bindings,
			 BindingsUpdate update)
	 {
		 return new SceneController(new KeyOptionsUpdating(update));
	 }
}
