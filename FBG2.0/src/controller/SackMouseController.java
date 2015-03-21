package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import model.gameObject.entity.inventory.Sack;
import utility.Bounds;
import view.viewport.SackViewPort;

/**
 *
 * @author ChrisMoscoso
 */
public class SackMouseController implements MouseListener, MouseMotionListener {

    private Sack sack;
    private SackViewPort sackVP;

    public SackMouseController(Sack sack, SackViewPort sackVP) {
        this.sack = sack;
        this.sackVP = sackVP;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (sack.isVisible()) {
            for (int i = 0; i < sackVP.getSackBounds().size(); i++) {
                Bounds b = sackVP.getSackBounds().get(i);
                if (b.containsPoint(e.getPoint())) {
                    sack.use(i);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (sack.isVisible()) {
            for (int i = 0; i < sackVP.getSackBounds().size(); i++) {
                Bounds b = sackVP.getSackBounds().get(i);
                if (b.containsPoint(e.getPoint())) {
                    sack.setCurrentSelection(i);
                }
            }
        }
    }

}
