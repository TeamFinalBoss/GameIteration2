package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.director.GameDirector;
import model.entity.Avatar;
import model.map.Direction;

/**
 *
 * @author ChrisMoscoso
 */
public class AvatarKeyController implements KeyListener {

    Avatar avatar;

    public AvatarKeyController(Avatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!GameDirector.gameIsPaused()) {
            if (avatar != null) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    avatar.setDirection(Direction.North);
                    avatar.setSpeed(1);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    avatar.setDirection(Direction.East);
                    avatar.setSpeed(1);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    avatar.setDirection(Direction.West);
                    avatar.setSpeed(1);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    avatar.setDirection(Direction.South);
                    avatar.setSpeed(1);
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    GameDirector.pauseGame();
                }
                
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!GameDirector.gameIsPaused()) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                avatar.setSpeed(0);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                avatar.setSpeed(0);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                avatar.setSpeed(0);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                avatar.setSpeed(0);
            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                GameDirector.pauseGame();
            }
        }

    }

}
