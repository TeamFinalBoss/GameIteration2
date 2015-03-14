/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * use addEvent(TimerTask event, int timeInMil)
 * @author Jason Owens
 */
public class GameTimer{
    private static GameTimer gt = null;
    private Timer myTimer;
    public GameTimer(){
        myTimer = new Timer();
    }
    
    public static GameTimer getInstance(){
        if(gt == null){
            gt = new GameTimer();
        }
        return gt;
    }
    
    public void addEvent(TimerTask event, long timeInMil){
        myTimer.schedule(event, timeInMil);
    }
}
