package controller.menu;


/**
 * @author Kyle Kyrazis
 *
 * Options that could possibly be displayed when going to a menu. 
 * TODO May have to add to these.
 */
public enum MenuOption {
	RESUME_GAME("Resume Game"),
	NEW_GAME("New Game"),
	SAVE_GAME("Save Game"),
	LOAD_GAME("Load Game"),
	KEY_BINDINGS("Key Bindings"),
	EXIT_GAME("Exit Game"), RESTART_MUSIC("Restart Music");
	
	private MenuOption(String str) {
		this.value = str;
	}
	
	private String value;
	
	public String toString() {
		return this.value;
	}
}
