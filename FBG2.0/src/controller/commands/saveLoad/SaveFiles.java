package controller.commands.saveLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
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


	protected void refresh() {
		File[] list = new File("./src/resources/saves/").listFiles();
		files.clear();
		for(File file : list) {
			if(file.isFile() && !file.getName().equals("default.xml")) {
				files.add(file);
			}
		}
		files.sort(new Comparator<File>() {

			@Override
			public int compare(File arg0, File arg1) {
				return Long.valueOf(arg1.lastModified()).compareTo(arg0.lastModified());
			}  
		});
		
	
	}
	

}
