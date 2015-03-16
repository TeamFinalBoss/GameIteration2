package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.entity.Entity;
import model.map.Direction;

/**
 *
 * @author ChrisMoscoso
 */
public class PlayerKeyController implements KeyListener {

    Entity player;
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.setDirection(Direction.North);
            player.setSpeed(1);
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
