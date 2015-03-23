package controller.commands.saveLoad;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import model.director.ActiveMapManager;
import model.map.GameMap;
import model.util.GameSaver;
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
		this.index = index;
	}
	
	
	@Override
	public void execute() {
		File file = super.getFileAtIndex(this.index);
		GameSaver s = new GameSaver();
		try {
			s.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
