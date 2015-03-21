/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

import java.util.ArrayList;
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
    
    ArrayList<TimerTask> myEvents;
    
    public GameTimer(){
        myEvents = new ArrayList<>();
        myTimer = new Timer();
    }
    
    public static GameTimer getInstance(){
        if(gt == null){
            gt = new GameTimer();
        }
        return gt;
    }
    
    /**
     * This is the method that activates all TimerTask's run methods
     */
    public void update(){
        
        
    }
    
    public void addEvent(TimerTask event, int timeInMil){
        
        myTimer.schedule(event, timeInMil);
    }
}
