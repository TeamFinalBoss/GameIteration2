package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.director.GameDirector;
import model.entity.Avatar;
import model.entity.Entity;
import model.map.Direction;

/**
 *
 * @author ChrisMoscoso
 */
public class AvatarKeyController implements KeyListener {

    Entity player;

    public AvatarKeyController(Avatar avatar) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!GameDirector.gameIsPaused()) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                player.setDirection(Direction.North);
                player.setSpeed(1);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                GameDirector.pauseGame();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
