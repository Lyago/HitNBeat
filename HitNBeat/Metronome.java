import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Metronome here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Metronome extends Actor
{ 
    private int tempo; //in bpm
    private long frame = 1;// 1/60 second
    private long lastTouchingFrame = 0;
    private int actionFrameWindow = 7;
    // Controlls the beat
    private int tempoUnitsCount = 0;
    
    public void setTempo(int tempo){
        this.tempo = tempo;
    }
    /**
     * Act - do whatever the Metronome wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        GreenfootImage image = new GreenfootImage("Frame: "+frame,          
                                    30,
                                   new Color(0,0,0,200),
                                   new Color(255,255,255,0)); 
                                            
            setImage(image);
        this.frame++;
    }
    public void resetMetronome(){
        this.frame = 1;
    }
    
    //Dictates when inputs are gonna be valid. Right now theres is a 10 frame window
    // for inputting when TempoUnits touch each other.
    public boolean isActionTime()
    {   
        ArrayList lst = (ArrayList)this.getObjectsInRange(10, TempoUnit.class);
        if(!lst.isEmpty()){
            if(((TempoUnit)lst.get(0)).isActionTime()){
            lastTouchingFrame = frame;    
            return true;
            }
        }else if(frame - lastTouchingFrame <= actionFrameWindow){
            return true;
        }
        
        return false;
    }
    public void startMetronome(int tempo){
        if (tempoUnitsCount == 0)
        {   MyWorld world = (MyWorld)this.getWorld();
            TempoUnit rightUnit = new RightTempoUnit(world.getWidth());
            TempoUnit leftUnit = new LeftTempoUnit(0);
            int y = 360;
            world.addObject(rightUnit,world.getWidth(),y);
            world.addObject(leftUnit,0,y);
                     
        }
        
        // Synchronizes spawn time off TempoUnits with tempo (60frames x 60sec/bpm)
        if (tempoUnitsCount++ == 3600/tempo)
        {
            tempoUnitsCount=0   ;
        }   
    }
}
