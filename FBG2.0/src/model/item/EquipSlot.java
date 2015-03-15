package model.item;

public enum EquipSlot {
	HEAD,
	TORSO,
	LEGS,
	FEET,
	HANDS,
	MAIN_HAND,
	OFF_HAND,
	TWO_HAND,
	WHISTLE,
	CAPE,
	NECKLACE;
	
	private static final int size = 11;
	
	public static int size(){
		return size;
	}
}
