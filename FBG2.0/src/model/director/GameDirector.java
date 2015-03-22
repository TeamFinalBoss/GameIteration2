package model.director;

import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import model.map.GameMap;
import view.MousePoint;
import view.scene.Scene;
import view.viewport.ArmoryViewport;
import view.viewport.KeyBindingsErrorViewPort;
import view.viewport.KeyBindingsMenuViewPort;
import view.viewport.MainMenuViewPort;
import view.viewport.MapViewPort;
import view.viewport.SackViewport;
import view.window.GameWindow;
import controller.Controller;
import controller.sceneControllers.SceneChanger;
import controller.sceneControllers.SceneType;
import controller.util.SceneObserver;

/**
 * This class is the director of our game, integrating the various subsystems.
 * It initializes the game by creating all the game objects (model, view, and
 * controller) to start. It is also responsible for pausing, resuming, and
 * changing the rate of time! [not implemented yet but soon ;) ] in the game
 * play.
 *
 * @author ChrisMoscoso
 */
public class GameDirector implements SceneObserver{

    private static Boolean paused = false;
    private static GameWindow window;
    private Scene menuScene, gameScene, pauseScene, keyBindingsScene, saveScene, loadScene, activeScene;
    private static Controller controller = Controller.getInstance();
    private SceneChanger sceneChanger = SceneChanger.getInstance();
    
    private Map<SceneType,Scene> scenes;

    private static GameDirector gameDirector = null;//It's a singleton object.

    private GameDirector() {
        window = new GameWindow();
        scenes = new HashMap<>();
        
        menuScene = new Scene();
        pauseScene = new Scene();
        keyBindingsScene = new Scene();
        gameScene = new Scene();
        saveScene = new Scene();
        loadScene = new Scene();
        
        scenes.put(SceneType.MAIN_MENU, menuScene);
        scenes.put(SceneType.PAUSE_MENU, pauseScene);
        scenes.put(SceneType.KEY_BINDINGS, keyBindingsScene);
        scenes.put(SceneType.UPDATING, keyBindingsScene);
        scenes.put(SceneType.GAME, gameScene);
        scenes.put(SceneType.SACK, gameScene);
        scenes.put(SceneType.ARMORY, gameScene);
        scenes.put(SceneType.SAVE, saveScene);
        scenes.put(SceneType.LOAD, loadScene);
        
        sceneChanger.registerObserver(this);
    }

    /**
     * 
     */
    public void startMainMenuScene() {
    	KeyListener listener = controller.buildController();
        window.addKeyController(listener);//Add controller to menu
        window.addMouseController(controller.getMouseParser());

        List<Observable> mainMenuObservables = controller.getObservables(SceneType.MAIN_MENU);
        
        MainMenuViewPort menuVP = new MainMenuViewPort();
        ((Observable)menuVP).addObserver((Observer) mainMenuObservables.get(0));
        
        menuScene.addViewport(menuVP);//Add menuVP to menuScene
        
        List<Observable> pauseMenuObservables = controller.getObservables(SceneType.PAUSE_MENU);
        
        MainMenuViewPort pauseVP = new MainMenuViewPort();
        pauseScene.addViewport(pauseVP);
        
        ((Observable)pauseVP).addObserver((Observer) pauseMenuObservables.get(0));
        
        
        List<Observable> keyMenuObservables = controller.getObservables(SceneType.KEY_BINDINGS);
        MainMenuViewPort keyBindingsVP = new KeyBindingsMenuViewPort();
        
        
        keyBindingsScene.addViewport(keyBindingsVP);
        ((Observable)keyBindingsVP).addObserver((Observer) keyMenuObservables.get(0));
        KeyBindingsErrorViewPort errorViewPort = new KeyBindingsErrorViewPort();
        keyBindingsScene.addViewport(errorViewPort);
        
        MainMenuViewPort saveVP = new MainMenuViewPort();
        saveScene.addViewport(saveVP);
        List<Observable> saveMenuObservables = controller.getObservables(SceneType.SAVE);
        ((Observable)saveVP).addObserver((Observer) saveMenuObservables.get(0));
        
        
        MainMenuViewPort loadVP = new MainMenuViewPort();
        loadScene.addViewport(loadVP);
        List<Observable> loadMenuObservables = controller.getObservables(SceneType.LOAD);
        ((Observable)loadVP).addObserver((Observer) loadMenuObservables.get(0));
        
        
        controller.getMouseParser().setMousePoint(SceneType.MAIN_MENU,(MousePoint)menuVP);
        controller.getMouseParser().setMousePoint(SceneType.PAUSE_MENU,(MousePoint)pauseVP);
        controller.getMouseParser().setMousePoint(SceneType.KEY_BINDINGS, (MousePoint)keyBindingsVP);
        controller.getMouseParser().setMousePoint(SceneType.LOAD, (MousePoint)loadVP);
        controller.getMouseParser().setMousePoint(SceneType.SAVE, (MousePoint)saveVP);
        
        
        controller.addObserver(menuVP, SceneType.MAIN_MENU);
        controller.addObserver(pauseVP, SceneType.PAUSE_MENU);
        controller.addObserver(keyBindingsVP, SceneType.KEY_BINDINGS);
        controller.addObserver(saveVP, SceneType.SAVE);
        controller.addObserver(loadVP, SceneType.LOAD);
        controller.addObserver(errorViewPort,SceneType.UPDATING);
        
        
        sceneChanger.changeScene(SceneType.MAIN_MENU);
        activeScene = menuScene;
    }

    public void startNewGame() {
        
        GameMap map = new GameMap();
        ActiveMapManager.getInstance().addMap(map);
        ActiveMapManager.getInstance().setActiveMap(map);
       
        MapViewPort mapVP = new MapViewPort();

        gameScene.addViewport(mapVP);//Add mapVP to gameScene
        
        SackViewport sack = new SackViewport();
        gameScene.addViewport(sack);
        
        ArmoryViewport armory = new ArmoryViewport();
        gameScene.addViewport(armory);
        
        controller.addObserver(sack, SceneType.SACK);
        controller.addObserver(armory, SceneType.ARMORY);
        
        List<Observable> sackObservables = controller.getObservables(SceneType.SACK);
        ((Observable)sack).addObserver((Observer) sackObservables.get(0));
        controller.getMouseParser().setMousePoint(SceneType.SACK, (MousePoint)sack);
        
       
        map.addObserver(mapVP);//Add mapVP as an Observer to map
        
        sceneChanger.changeScene(SceneType.GAME);
        activeScene = gameScene;
    }

    /**
     * This is where execution of the game logic and updating of the model takes
     * place
     */
    public void updateGame() {
        if (!paused) {
            //The game is runnning
        } else {
            //Some menu is active
        }
    }

    /**
     * Uses a double buffering technique to paint the image to the screen. First
     * it renders the game to a bufferedImage. Then, it takes the bufferedImage
     * and paints it to the screen.
     */
    public void drawGame() {
        if(activeScene != null){
        BufferedImage gameImage = activeScene.getImage();//render the game to buffer
            window.paintImageToScreen(gameImage); //paint the buffer to screen
        }
    }

    /**
     * Since game director is a singleton object it returns only one instance of
     * the game director
     *
     * @return the single instance of game director
     */
    public static GameDirector getGameDirector() {
        if (gameDirector == null) {
            gameDirector = new GameDirector();
        }
        return gameDirector;
    }

    /**
     * This returns the dimensions of the game GUI in pixels
     *
     * @return the dimensions of the game GUI in pixels
     */
    public static Dimension getSize() {
        return window.getSize();
    }

    /**
     * This sets the dimensions for the game GUI by setting the size for all the
     * scenes.
     *
     * @param width the width of the game GUI in pixels
     * @param height the height of the game GUI in pixels
     */
    public static void setSize(int width, int height) {
        //Set default sizes for all scenes
        Scene.setSceneSize(window.getSize());
    }

    /**
     * Pauses game play only. Menus will still work.
     */
    public static void pauseGame() {
        paused = true;
    }

    /**
     * Resumes game play.
     */
    public static void resumeGame() {
        paused = false;
    }
    
    public GameWindow getWindow() {
    	return window;
    }

	@Override
	public void update(SceneType type) {
		activeScene = scenes.get(type);
	}
	
	public void addKeyListener(KeyListener listener) {
		window.addKeyController(listener);
	}
	
	public void removeKeyListener(KeyListener listener) {
		window.removeKeyController(listener);
	}
}
