package controller.keyBindings;

public enum KeyBindingsOption {
	UP("Up"),
	DOWN("Down"),
	LEFT("Left"),
	RIGHT("Right"),
	UP_RIGHT("Up Right"),
	UP_LEFT("Up Left"),
	DOWN_RIGHT("Down Right"),
	DOWN_LEFT("Down Left"),
	SKILL_0("Skill 0"),
	SKILL_1("Skill 1"),
	SKILL_2("Skill 2"),
	SKILL_3("Skill 3"),
	SKILL_4("Skill 4"),
	SKILL_5("Skill 5"),
	SKILL_6("Skill 6"),
	SKILL_7("Skill 7"),
	SKILL_8("Skill 8"),
	SKILL_9("Skill 9"),
	SACK("Toggle Sack"),
	ARMORY("Toggle Armory"),
	NEAREST_ENTITY("Target Nearest Entity"),
	DIALOGUE("Toggle Dialogue"),
	PAUSE("Toggle Pause"),
	CONFIRM("Confirm"),
	DROP("Drop"),
	TILE_INFO("Tile Information"),
	SAVE("Save Updates"),
	CANCEL("Cancel");
	
	
	private KeyBindingsOption(String str) {
		this.value = str;
	}
	
	private String value;
	
    @Override
	public String toString() {
		return this.value;
	}
    
    public static KeyBindingsOption fromString(String text) {
        if (text != null) {
          for (KeyBindingsOption b : KeyBindingsOption.values()) {
            if (text.equalsIgnoreCase(b.value)) {
              return b;
            }
          }
        }
        return null;
      }
        
}
