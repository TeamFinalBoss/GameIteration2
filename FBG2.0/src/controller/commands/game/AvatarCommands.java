package controller.commands.game;

import controller.commands.Commandable;

public abstract class AvatarCommands implements Commandable {
	//private All things avatar related. 
	//TODO remove this if Avatar are not interacted with uniformly.
	
	public abstract void execute();
}
