package controller.commands.saveLoad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
		try {
			InputStream fileStream = new FileInputStream(file);
			
			fileStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error with loading a file in LoadFileCommand");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error with loading a file in LoadFileCommand");
		}
		
	}

}
