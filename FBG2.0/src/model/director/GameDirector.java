package model.director;

import controller.AvatarKeyController;
import controller.MenuKeyController;
import model.menu.Menu;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import model.entity.Avatar;
import model.entity.WalkInLoopAIEntity;
import model.map.GameMap;
import model.menu.Menu.MenuOption;
import view.window.GameWindow;
import view.scene.Scene;
import view.viewport.MainMenuViewPort;
import view.viewport.MapViewPort;
import view.viewport.PauseMenuViewPort;
import view.viewport.StatusViewPort;

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
    private static Scene menuScene, gameScene;
    private static Scene activeScene;

    private static Menu mainMenu, pauseMenu;

    private static GameDirector gameDirector = null;//It's a singleton object.

    private static MenuKeyController mainMenuKC, pauseMenuKC;
    private static AvatarKeyController avatarKC;

    private static GameMap map1;

    private GameDirector() {
        window = new GameWindow();
        startMenuScene();
    }

    /**
     * This sets up the main menu scene
     */
    public static void startMenuScene() {
        mainMenu = new Menu();
        menuScene = new Scene();

        MainMenuViewPort menuVP = new MainMenuViewPort();
        menuScene.addViewport(menuVP);

        //Add observers to model object
        mainMenu.addObserver(menuVP);

        //Add controllers to the window
        mainMenuKC = new MenuKeyController(mainMenu, menuScene);
        window.addKeyController(mainMenuKC);

        activeScene = menuScene;
    }

    /**
     * This starts a new game programmatically.
     */
    public static void startNewGame() {
        //Create Game Scene
        gameScene = new Scene();

        //Create Map
        map1 = new GameMap();
        Avatar a = new Avatar();
        WalkInLoopAIEntity e = new WalkInLoopAIEntity();
        map1.addEntity(Avatar.getAvatar());
        map1.addEntity(e);
        
        //Create Pause Menu 
        MenuOption[] pauseMenuOptions = {MenuOption.RESUME_GAME, MenuOption.SAVE_GAME, MenuOption.RETURN_TO_MAIN_MENU};
        pauseMenu = new Menu(pauseMenuOptions);
        paused = false;
        pauseMenu.hide();

        //Create Viewports
        MapViewPort mapVP = new MapViewPort();
        PauseMenuViewPort pauseVP = new PauseMenuViewPort();
        StatusViewPort statusVP = new StatusViewPort();

        //Add viewports to scene
        gameScene.addViewport(mapVP);
        gameScene.addViewport(pauseVP);
        gameScene.addViewport(statusVP);

        //Add viewports as observers to model objects.
        map1.addObserver(mapVP);
        pauseMenu.addObserver(pauseVP);
        a.addObserverOfStats(statusVP);

        //Add controllers to window
        pauseMenuKC = new MenuKeyController(pauseMenu, gameScene);
        window.addKeyController(pauseMenuKC);
        avatarKC = new AvatarKeyController(Avatar.getAvatar());
        window.addKeyController(avatarKC);

        //Set game scene as active scene
        activeScene = gameScene;
        
    }

    /**
     * This is where execution of the game logic and updating of the model takes
     * place
     */
    public void updateGame() {
        if (!paused) {
            //The game is runnning
            if (map1 != null) {
                map1.moveGameObjects();    
            }

        } else {
            //Some menu is active
        }
    }

    /**
     * This sets the active scene which to be drawn.
     */
    private static void setActiveScene(Scene s) {
        activeScene = s;
    }

    /**
     * This gets which scene is currently active.
     *
     * @return the active scene.
     */
    public static Scene getActiveScene() {
        return activeScene;
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
     * This returns the dimensions of the game window in pixels
     *
     * @return the dimensions of the game window in pixels
     */
    public static Dimension getSize() {
        return window.getSize();
    }

    /**
     * Pauses game play only. Menus will still work.
     */
    public static void pauseGame() {
        paused = true;
        if(pauseMenu != null){
            pauseMenu.show();
        }
    }

    /**
     * Resumes game play.
     */
    public static void resumeGame() {
        paused = false;
        if(pauseMenu != null){
            pauseMenu.hide();
        }
    }

    /**
     * Checks if game is paused
     *
     * @return true if the game is paused
     */
    public static boolean gameIsPaused() {
        return paused;
    }

    /**
     * Returns the user to the main menu. This is accomplished by simply setting
     * the menu scene to be active.
     */
    public static void returnToMainMenu() {
        setActiveScene(menuScene);
    }
}
