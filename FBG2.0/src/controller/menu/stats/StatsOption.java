package controller.menu.stats;

public enum StatsOption {
	STRENGTH("Strength"),
	AGILITY("Agility"),
	INTELLECT("Intellect"),
	HARDINESS("Hardiness"),
	MOVEMENT("Movement");
	
	private String value;
	
	private StatsOption(String str) {
		this.value = str;
	}
	
	public String toString() {
		return this.value;
	}
}
