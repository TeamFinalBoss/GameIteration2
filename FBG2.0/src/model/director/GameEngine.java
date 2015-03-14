package model.director;


/**
 * The game engine is responsible for updating the game and rendering it to the
 * screen. It runs on a threaded loop that updates approximately x frames per
 * second.
 * 
 * TODO: Enforce engine to run at FPS rate. It currently runs at arbitrary rate.
 *
 * @author ChrisMoscoso
 */
public class GameEngine implements Runnable {

    private Thread thread;
    private int FPS; //Right now the game enginer runs at an arbitrary 
    private GameDirector director;

    public GameEngine() {
        this(30);
    }

    /**
     * Specify how fast the game engine should run in Frames Per Second
     * @param FramesPerSecond is how fast the game engine should tick 
     */
    public GameEngine(int FramesPerSecond) {
        FPS = FramesPerSecond;
        thread = new Thread(this);
        director = GameDirector.getGameDirector();
        start();
    }

    /**
     * Begins running the game engine
     */
    private void start() {
        thread.start();
    }

    /**
     * While the game engine runs, it continually a 2 step loop: update game,
     * draw game.
     */
    @Override
    public void run() {
        while (true) {
            updateGame();
            renderGame();
        }
    }
    
    /**
     * Update the game models
     */
    private void updateGame(){
        director.updateGame();
    }
    
    /**
     * Render the game to the screen
     */
    private void renderGame(){
        director.drawGame();
    }
}
