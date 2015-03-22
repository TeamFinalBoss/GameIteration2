package controller.commands.saveLoad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import model.director.GameDirector;
import controller.commands.Commandable;

public class LoadFileCommand extends SaveFiles implements Commandable{

	private int index;
	
	public LoadFileCommand() {
		this.index = 0;
	}
	
	public LoadFileCommand(int index) {
		this.index = index;
	}
	
	@Override
	public void execute() {
		File file = super.getFileAtIndex(index);
		GameDirector.getGameDirector().startNewGame(file);
		
	}

}
