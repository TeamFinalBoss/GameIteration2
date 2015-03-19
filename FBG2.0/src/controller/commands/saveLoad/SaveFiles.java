package controller.commands.saveLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import controller.commands.Commandable;

public abstract class SaveFiles implements Commandable {

	private List<File> files;
	
	public SaveFiles() {
		files = new ArrayList<>();
		refresh();
	}
	
	
	protected File getFileAtIndex(int index) {
		refresh();
		return this.files.get(index);
	}
	
	@Override
	public abstract void execute();


	private void refresh() {
		File path = new File("./src/resources/saves");
		boolean thing = path.isDirectory();
		if(path != null) {
			File[] list = path.listFiles();
			for(File file : list) {
				if(file.isFile()) {
					files.add(file);
				}
			}
		}
		
	}

}
