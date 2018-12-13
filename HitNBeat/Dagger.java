import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LeftTempoUnit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dagger extends TempoUnit
{
    
    public Dagger(int startLocation){
        this.startLocation = startLocation;
    }
    /**
     * Act - do whatever the LeftTempoUnit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       createDagger();
    }
    public void createDagger(){
        Actor metronome;
        metronome = getObjectsInRange(500, Metronome.class).get(0);
        if(this.getX() >= metronome.getX()){
            World world = this.getWorld();
            world.removeObject(this);
        }else{
            move(speed);
        }
    }
}
