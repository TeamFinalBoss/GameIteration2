/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * use addEvent(TimerTask event, int timeInMil)
 * @author Jason Owens
 */
public class GameTimer{
    Timer myTimer;
    public GameTimer(){
        myTimer = new Timer();
    }
    public void addEvent(TimerTask event, int timeInMil){
        myTimer.schedule(event, timeInMil);
    }
}
