package model.director;

import main.RunGame;

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
    
    private static final int MAX_FRAME_SKIPS = 5;
    /* Number of frames that can be skipped by the renderer to keep the 
     game updates per second close to the frames per second.
     */

    /**
     * Specify how fast the game engine should run in Frames Per Second
     *
     * @param FramesPerSecond is how fast the game engine should tick
     */
    public GameEngine(int FramesPerSecond) {
        FPS = FramesPerSecond;
        thread = new Thread(this);
        director = GameDirector.getGameDirector();
        director.startMainMenuScene();
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
        long beforeTime, afterTime, timeDiff, sleepTime, period;
        long overSleepTime = 0L;
        int noDelays = 0;
        long excess = 0L;

        period = 1000 / FPS;

        beforeTime = System.currentTimeMillis();
        
        while (true) {
            updateGame();
            renderGame();

            afterTime = System.currentTimeMillis();
            timeDiff = afterTime - beforeTime;
            sleepTime = period - timeDiff - overSleepTime; // time left in this loop

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);  // sleep a bit
                } catch (InterruptedException ex) {
                }
                overSleepTime = (System.currentTimeMillis() - afterTime) - sleepTime;
            } else { //sleeptime <=0; frame took longer than a period
                excess -= sleepTime; //store excess time value
                overSleepTime = 0;
            }

            beforeTime = System.currentTimeMillis();

            /* If frame animation is taking too long, update the game state
             without rendering it, to get the updates/sec nearer to
             the required FPS. */
            int skips = 0;
            while ((excess > period) && (skips < MAX_FRAME_SKIPS)) {
                excess -= period;
                updateGame(); // update state but don't render 
                skips++;
            }
        }
    }

    /**
     * Update the game models
     */
    private void updateGame() {
        director.updateGame();
    }

    /**
     * Render the game to the screen
     */
    private void renderGame() {
        director.drawGame();
    }
}
