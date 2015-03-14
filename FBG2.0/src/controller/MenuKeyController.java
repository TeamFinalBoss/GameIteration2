package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.menu.Menu;

/**
 * This is a key controller for a menu object. The way I have designed the
 * controller so far is that it only needs a reference to a menu. This cuts down
 * # of classes enormously. So far only one class in controller to get the game
 * running.
 *
 * @author ChrisMoscoso
 */
public class MenuKeyController implements KeyListener {

    private final Menu menu;

    /**
     * The menu that the controller mutates.
     * @param m the menu object that the controller object uses.
     */
    public MenuKeyController(Menu m) {
        menu = m;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*Navigate Menu*/
        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            menu.next();
        } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_LEFT) {
            menu.prev();
        }
        /*Confirm menu*/
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            switch (menu.getCurrentSelection()) {
                case NEW_GAME:
                    break;
                case EXIT:
                    System.exit(0);
                    break;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
