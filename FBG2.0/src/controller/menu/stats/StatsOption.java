package controller.menu.stats;

public enum StatsOption {
	STRENGTH("Strength"),
	AGILITY("Agility"),
	INTELLECT("Intellect"),
	HARDINESS("Hardiness"),
	MOVEMENT("Movement"),
	BIND_WOUND("Bind Wound"),
	BARGAIN("Bargain"),
	OBSERVATION("Observation"),
	SKILL_1("Skill 1"),
	SKILL_2("Skill 2"),
	SKILL_3("Skill 3"),
	SKILL_4("Skill 4");
	
	private String value;
	
	private StatsOption(String str) {
		this.value = str;
	}
	
	public String toString() {
		return this.value;
	}
}
