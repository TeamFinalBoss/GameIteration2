package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.director.GameDirector;
import model.gameObject.entity.Avatar;
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
                    avatar.isMovingY(true);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    avatar.setDirection(Direction.East);
                    avatar.isMovingX(true);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    avatar.setDirection(Direction.West);
                    avatar.isMovingX(true);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    avatar.setDirection(Direction.South);
                    avatar.isMovingY(true);
                }
                
                if(e.getKeyCode() == KeyEvent.VK_1){
                    avatar.shoot();
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
                avatar.isMovingY(false);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                avatar.isMovingX(false);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                avatar.isMovingX(false);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                avatar.isMovingY(false);
            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                GameDirector.pauseGame();
            }
        }

    }

}
