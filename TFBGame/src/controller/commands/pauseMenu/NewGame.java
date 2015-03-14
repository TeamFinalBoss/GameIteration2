package controller.commands.pauseMenu;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import model.entities.Entity;

import org.xml.sax.SAXException;

import controller.commands.Commandable;

/**
 * @author Kyle Kyrazis
 * 
 * This game is responsible for starting a new game
 *
 */
public class NewGame implements Commandable {

	@Override
	public void execute() {
		GameMap loadedMap = GameMap.getInstance();
	    loadedMap.takeTiles(main.RunGame.ml.getAllTiles());
	    
	    InputStream file = getClass().getResourceAsStream("/resources/saves/save1.xml");
	    XMLReader reader = XMLReader.getInstance(file);
	    reader.setInputStream(file);
	    ObjectFactory objectFactory = new ObjectFactory(file, loadedMap);
	   
	    GameCoordinator gameCoordinator = GameCoordinator.getInstance();
	    gameCoordinator.setActiveMap(loadedMap);
	    try {
	    	Entity avatar = objectFactory.ParseFile();
			if(avatar == null)
				
			gameCoordinator.setAvatar(avatar);
				
		} catch (ParserConfigurationException | SAXException | IOException e) {
				
			e.printStackTrace();
		}
	        
	        
	    SceneManager sm = SceneManager.getInstance();
	    sm.setActiveScene(SceneManager.GAME_SCENE);
	}

}
