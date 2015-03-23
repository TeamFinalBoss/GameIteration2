package controller.util;

public interface Healthable extends Describeable {
	public int getCurrentHealth();
	public int getCurrentMana();
	public int getMaxHealth();
	public int getMaxMana();
}
