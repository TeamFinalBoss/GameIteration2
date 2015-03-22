package controller.commands.sceneChangers;

import controller.commands.Commandable;

public abstract class ArmorySackMaintainer extends SceneChangerCommands implements Commandable{

	private static boolean pressedArmory;
	private static boolean pressedSack;
	
	public static boolean isPressedArmory() {
		return pressedArmory;
	}
	public static void setPressedArmory(boolean pressedArmory) {
		ArmorySackMaintainer.pressedArmory = pressedArmory;
	}
	public static boolean isPressedSack() {
		return pressedSack;
	}
	public static void setPressedSack(boolean pressedSack) {
		ArmorySackMaintainer.pressedSack = pressedSack;
	}
	
	@Override
	public abstract void execute();


}
