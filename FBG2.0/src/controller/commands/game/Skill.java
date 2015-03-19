package controller.commands.game;

import controller.commands.Commandable;

public class Skill extends AvatarCommands implements Commandable {

	private int skillNumber;
	
	public Skill() {
		this.skillNumber = 1;
	}
	
	public Skill(int skillNumber) {
		this.skillNumber = skillNumber;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
