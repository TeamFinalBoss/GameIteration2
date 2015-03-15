package controller.commands.pauseMenu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;

import model.entity.Entity;

import org.xml.sax.SAXException;

import controller.commands.Commandable;
import org.xml.sax.XMLReader;



/**
 * @author Kyle Kyrazis
 * 
 * This class is responsible for loading a saved game.
 *
 */
public class LoadGame implements Commandable {

	@Override
	public void execute() {
		JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("./FinalBossGame/src/resources/saves/"));
        int choice = chooser.showOpenDialog(null);
        if (choice == JFileChooser.APPROVE_OPTION) {
            File loadFile = chooser.getSelectedFile();
            loadGame(loadFile);
            scheduler.changeCoordinator(CoordinatorType.GAME);
            SceneManager sm = SceneManager.getInstance();
            sm.setActiveScene(SceneManager.GAME_SCENE);
            GameCoordinator.getInstance().showPauseMenu(false);
        }
	}
	
	//TODO Fix this load game.
	
	private void loadGame(File saveFile) throws ParserConfigurationException, SAXException, IOException {
        InputStream file = new FileInputStream(saveFile);
    	XMLReader reader = XMLReader.getInstance(file);
        reader.setInputStream(file);
        GameMap loadedMap = GameMap.getInstance();
        loadedMap.takeTiles(main.RunGame.ml.getAllTiles());

        ObjectFactory objectFactory = new ObjectFactory(file, loadedMap);
        GameCoordinator gameCoordinator = GameCoordinator.getInstance();
        gameCoordinator.setActiveMap(loadedMap);
        try {
			Entity avatar = objectFactory.ParseFile();
			if(avatar == null)
				System.out.println("null avatar");
			gameCoordinator.setAvatar(avatar);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
