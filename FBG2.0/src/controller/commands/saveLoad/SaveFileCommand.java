package controller.commands.saveLoad;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import model.util.ObjectSaver;
import controller.commands.Commandable;

public class SaveFileCommand extends SaveFiles implements Commandable {
	
	private int index;

	public SaveFileCommand() {
		super();
		this.index = 0;
	}
	
	public SaveFileCommand(int index) {
		super();
		this.index = 0;
	}
	
	
	@Override
	public void execute() {
		PrintWriter writer = null;
		File file = super.getFileAtIndex(this.index);
		GameSaver s = new GameSaver();
		s.savetoFile(file);
		
	}

	
}
