package controller.commands.saveLoad;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
			Date date = new Date();
			File newName = new File("./src/resources/saves/" + dateFormat.format(date) + ".xml");
			file.renameTo(newName);
			super.refresh();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
