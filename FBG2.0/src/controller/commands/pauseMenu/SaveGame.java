package controller.commands.pauseMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.commands.Commandable;

/**
 * @author Kyle Kyrazis
 * 
 * This class is responsible for saving the game.
 *
 */
public class SaveGame implements Commandable {
	
	//TODO Implement a good saving technique.

	@Override
	public void execute() {
		JFileChooser chooser = new JFileChooser();
    	chooser.setCurrentDirectory(new File("./FinalBossGame/src/resources/saves/"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(null); //MAY NOT NEED TO BE NULL		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			System.out.println("Saved to the file: " + chooser.getSelectedFile().getName());
		}
		File timeToWrite = chooser.getSelectedFile();
		try{
		PrintWriter writeFile = new PrintWriter(timeToWrite);
		writeFile.println(GameMap.getInstance().toXML());
		writeFile.close();
		}
		catch(FileNotFoundException a){
			a.printStackTrace();
		}

	}

}
