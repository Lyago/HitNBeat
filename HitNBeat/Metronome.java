import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class Metronome here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Metronome extends Actor
{ 
    /**
     * Act - do whatever the Metronome wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {   
        
    }
    public TempoUnit getNearTempoUnit(){  
        List<TempoUnit> nearTempoUnits = this.getObjectsInRange(35, TempoUnit.class);
        if(!nearTempoUnits.isEmpty()){
            return nearTempoUnits.get(0);
        }    
        return null;
    }
    
    
}
