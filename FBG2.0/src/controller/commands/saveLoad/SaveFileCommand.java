package controller.commands.saveLoad;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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
		try {
			writer = new PrintWriter(file);
			//TODO save the game.
			writer.println(); //save the game
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error saving in SaveFileCommand");
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
		
	}

	
}
