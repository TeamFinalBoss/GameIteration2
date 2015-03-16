package model.director;

import controller.AvatarKeyController;
import controller.MenuKeyController;
import model.menu.Menu;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import model.entity.Avatar;
import model.map.GameMap;
import model.menu.Menu.MenuOption;
import view.window.GameWindow;
import view.scene.Scene;
import view.viewport.MainMenuViewPort;
import view.viewport.MapViewPort;
import view.viewport.PauseMenuViewPort;

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
    private static  Scene activeScene;
    
    private static Menu pauseMenu;

    private static GameDirector gameDirector = null;//It's a singleton object.
    
    private static MenuKeyController mainMenuKC, pauseMenuKC;

    private GameDirector() {
        window = new GameWindow();
    }

    /**
     * This is called to start the game. (It will go to Main Menu first).
     */
    public void start() {
        //Set default sizes for all scenes
        Scene.setSceneSize(window.getSize());
        startMenuScene();
    }

    /**
     * This sets up the main menu scene
     */
    public static void startMenuScene() {
        Menu mainMenu = new Menu();

        menuScene = new Scene();

        MainMenuViewPort menuVP = new MainMenuViewPort();
        menuScene.addViewport(menuVP);

        //Add observers to model object
        mainMenu.addObserver(menuVP);
        menuVP.update(mainMenu, null);

        //Add controllers to the window
        if(mainMenuKC == null){
            mainMenuKC = new MenuKeyController(mainMenu, menuScene);
            window.addKeyController(mainMenuKC);
        }

        activeScene = menuScene;
    }

    /**
     * This starts a new game programmatically.
     */
    public static void startNewGame() {
        //Create Game Scene
        gameScene = new Scene();

        //Create Map
        GameMap map1 = new GameMap();

        //Create Pause Menu 
        MenuOption[] pauseMenuOptions = {MenuOption.RESUME_GAME, MenuOption.SAVE_GAME, MenuOption.RETURN_TO_MAIN_MENU};
        pauseMenu = new Menu(pauseMenuOptions);
        pauseMenu.hide();

        //Create Viewports
        MapViewPort mapVP = new MapViewPort();
        PauseMenuViewPort pauseVP = new PauseMenuViewPort();

        //Add viewports as observers to model objects.
        map1.addObserver(mapVP);
        pauseMenu.addObserver(pauseVP);

        //Add viewports to scene
        gameScene.addViewport(mapVP);
        gameScene.addViewport(pauseVP);

        //Add controllers to window
        pauseMenuKC = new MenuKeyController(pauseMenu, gameScene);
        window.addKeyController(pauseMenuKC);
        window.addKeyController(new AvatarKeyController(Avatar.getAvatar()));

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
     * @return the active scene.
     */
    public static Scene getActiveScene(){
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
        pauseMenu.show();
    }

    /**
     * Resumes game play.
     */
    public static void resumeGame() {
        paused = false;
        pauseMenu.hide();
    }
    
    /**
     * Checks if game is paused
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
