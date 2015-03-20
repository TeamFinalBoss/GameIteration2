
package model.entity;

import view.viewport.StatusViewPort;


/**
 *
 * @author ChrisMoscoso
 */
public class Avatar extends Entity {
    
    private static Avatar avatar;
    
    public Avatar(){
        super();
        avatar = this;
    }
    
    /**
     *
     * @return
     */
    public static Avatar getAvatar(){
        return avatar;
    }

    public void addObserverOfStats(StatusViewPort statusVP) {
        myStats.addObserver(statusVP);
    }

    
}
