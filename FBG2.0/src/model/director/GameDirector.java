package model.director;

import view.viewport.MapViewPort;
import model.menu.Menu;
import controller2.MenuKeyController;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import model.map.GameMap;
import view.window.GameWindow;
import view.scene.Scene;
import view.viewport.MainMenuViewPort;

/**
 * This class is the director of our game, integrating the various subsystems.
 * It initializes the game by creating all the game objects (model, view, and
 * controller) to start. It is also responsible for pausing, resuming, and
 * changing the rate of time! [not implemented yet but soon ;) ] in the game
 * play.
 *
 * @author ChrisMoscoso
 */
public class GameDirector {

    private static Boolean paused = false;
    private static GameWindow window;
    private Scene menuScene, gameScene, activeScene;

    private static GameDirector gameDirector = null;//It's a singleton object.

    private GameDirector() {
        window = new GameWindow();
        //Set default sizes for all scenes
        Scene.setSceneSize(window.getSize());
        startMainMenuScene(); //As this grows this method will be broken into smaller chunks.
    }

    /**
     * 
     */
    private void startMainMenuScene() {
        Menu mainMenu = new Menu();
        window.addKeyController(new MenuKeyController(mainMenu));//Add controller to menu

        menuScene = new Scene();
        MainMenuViewPort menuVP = new MainMenuViewPort();

        menuScene.addViewport(menuVP);//Add menuVP to menuScene
        mainMenu.addObserver(menuVP);//Add menuVP as an Observer to menu
        activeScene = menuScene;

    }

    public void startNewGame() {
        GameMap map = new GameMap();

        gameScene = new Scene();
        MapViewPort mapVP = new MapViewPort();

        gameScene.addViewport(mapVP);//Add mapVP to gameScene
        map.addObserver(mapVP);//Add mapVP as an Observer to map
        
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
        BufferedImage gameImage = activeScene.getImage();//render the game to buffer
        window.paintImageToScreen(gameImage); //paint the buffer to screen
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
}